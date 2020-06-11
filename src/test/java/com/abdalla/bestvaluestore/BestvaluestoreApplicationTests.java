package com.abdalla.bestvaluestore;

import com.abdalla.bestvaluestore.dao.CustomerRepository;
import com.abdalla.bestvaluestore.dao.ElectronicsRepository;
import com.abdalla.bestvaluestore.dao.GroceriesRepository;
import com.abdalla.bestvaluestore.models.Customer;
import com.abdalla.bestvaluestore.models.ElectronicDevice;
import com.abdalla.bestvaluestore.models.Grocery;
import com.abdalla.bestvaluestore.services.CustomerService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class BestvaluestoreApplicationTests {
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private GroceriesRepository groceriesRepository;
    @Mock
    private ElectronicsRepository electronicsRepository;

    @InjectMocks // auto inject helloRepository
    private CustomerService customerService;


    @Test
    void contextLoads() {
        Customer customer = new Customer(1L, "Abdalla", "Sabri", false, false, LocalDate.now().minusYears(3));
        Grocery grocery1 = new Grocery(1L, "Apples", BigDecimal.valueOf(15));
        Grocery grocery2 = new Grocery(2L, "Bananas", BigDecimal.valueOf(10));
        Grocery grocery3 = new Grocery(3L, "Zucchini", BigDecimal.valueOf(8));
        ElectronicDevice electronicDevice1 = new ElectronicDevice(1L, "iPod", BigDecimal.valueOf(200));
        ElectronicDevice electronicDevice2 = new ElectronicDevice(2L, "Pixel 4", BigDecimal.valueOf(400));
        ElectronicDevice electronicDevice3 = new ElectronicDevice(3L, "2080 RTX", BigDecimal.valueOf(600));

        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(groceriesRepository.findById(1L)).thenReturn(Optional.of(grocery1));
        when(groceriesRepository.findById(2L)).thenReturn(Optional.of(grocery2));
        when(groceriesRepository.findById(3L)).thenReturn(Optional.of(grocery3));

        when(electronicsRepository.findById(1L)).thenReturn(Optional.of(electronicDevice1));
        when(electronicsRepository.findById(2L)).thenReturn(Optional.of(electronicDevice2));
        when(electronicsRepository.findById(3L)).thenReturn(Optional.of(electronicDevice3));
        assertEquals(new BigDecimal("573.00"), customerService.calculatePrice(1L, List.of(1L,2L,3L), List.of(1L,2L)));
    }
}
