package com.abdalla.bestvaluestore.services;

import com.abdalla.bestvaluestore.dao.CustomerRepository;
import com.abdalla.bestvaluestore.dao.ElectronicsRepository;
import com.abdalla.bestvaluestore.dao.GroceriesRepository;
import com.abdalla.bestvaluestore.misc.CustomException;
import com.abdalla.bestvaluestore.models.Customer;
import com.abdalla.bestvaluestore.models.ElectronicDevice;
import com.abdalla.bestvaluestore.models.Grocery;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final GroceriesRepository groceriesRepository;
    private final ElectronicsRepository electronicsRepository;

    public CustomerService(CustomerRepository customerRepository, GroceriesRepository groceriesRepository, ElectronicsRepository electronicsRepository) {
        this.customerRepository = customerRepository;
        this.groceriesRepository = groceriesRepository;
        this.electronicsRepository = electronicsRepository;
    }

    @PostConstruct
    public void populateDatabase() {
        Customer customer1 = new Customer(1L, "Abdalla", "Sabri", false, false, LocalDate.now().minusYears(3));
        Customer customer2 = new Customer(2L, "John", "Doe", true, false, LocalDate.now());
        Customer customer3 = new Customer(3L, "Sylvester", "Stallone", false, true, LocalDate.now().minusDays(27));
        customerRepository.saveAll(List.of(customer1, customer2, customer3));

        Grocery grocery1 = new Grocery(1L, "Apples", BigDecimal.valueOf(15));
        Grocery grocery2 = new Grocery(2L, "Bananas", BigDecimal.valueOf(10));
        Grocery grocery3 = new Grocery(3L, "Zucchini", BigDecimal.valueOf(8));
        groceriesRepository.saveAll(List.of(grocery1, grocery2, grocery3));

        ElectronicDevice electronicDevice1 = new ElectronicDevice(1L, "iPod", BigDecimal.valueOf(200));
        ElectronicDevice electronicDevice2 = new ElectronicDevice(2L, "Pixel 4", BigDecimal.valueOf(400));
        ElectronicDevice electronicDevice3 = new ElectronicDevice(3L, "2080 RTX", BigDecimal.valueOf(600));
        electronicsRepository.saveAll(List.of(electronicDevice1, electronicDevice2, electronicDevice3));
    }

    public BigDecimal calculatePrice(Long customerId, List<Long> groceryIds, List<Long> electronicDevicesIds) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if(customer == null) {
            throw new CustomException(1, "Invalid customer id");
        }

        BigDecimal groceriesPrice = BigDecimal.ZERO;
        BigDecimal electronicsPrice = BigDecimal.ZERO;

        for(Long groceryId : groceryIds) {
            Grocery grocery = groceriesRepository.findById(groceryId).orElse(null);
            if(grocery == null) {
                throw new CustomException(2, "Available grocery ids are only 1,2,3");
            }

            groceriesPrice = groceriesPrice.add(grocery.getPrice());
        }

        for(Long electronicDeviceId : electronicDevicesIds) {
            ElectronicDevice electronicDevice = electronicsRepository.findById(electronicDeviceId).orElse(null);
            if(electronicDevice == null) {
                throw new CustomException(3, "Available electronic devices ids are only 1,2,3");
            }

            electronicsPrice = electronicsPrice.add(electronicDevice.getPrice());
        }

        //lets calculate
        BigDecimal totalSellingPrice = groceriesPrice.add(electronicsPrice);

        //determine the %
        BigDecimal discountPercentage = determineDiscountPercentage(customer);
        BigDecimal fixedDiscount = totalSellingPrice.divide(BigDecimal.valueOf(100), 0, RoundingMode.FLOOR).multiply(BigDecimal.valueOf(5));
        BigDecimal variableDiscountForElectronics = electronicsPrice.multiply(discountPercentage);
        BigDecimal finalSellingPrice = totalSellingPrice.subtract(fixedDiscount).subtract(variableDiscountForElectronics);
        return finalSellingPrice.setScale(2);
    }

    private BigDecimal determineDiscountPercentage(Customer customer) {
        BigDecimal discountPercentage = BigDecimal.ZERO;
        if(customer.isEmployee()) {
            discountPercentage = BigDecimal.valueOf(0.3);
        } else if(customer.isAffiliate()) {
            discountPercentage = BigDecimal.valueOf(0.1);
        } else {
            //user is neither an employee or an affiliate, lets check if older than 2 years
            Period period = Period.between(customer.getCreationDate(), LocalDate.now());
            if(period.getYears() >= 2) {
                discountPercentage = BigDecimal.valueOf(0.05);
            }
        }

        return discountPercentage;
    }
}
