package com.abdalla.bestvaluestore.controllers;

import com.abdalla.bestvaluestore.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class MainController {
    private final CustomerService customerService;

    public MainController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/calculate_price")
    ResponseEntity<BigDecimal> calculatePrice(@RequestParam Long customerId, @RequestParam List<Long> groceryIds,
                                              @RequestParam List<Long> electronicDevicesIds) {
        BigDecimal bigDecimal = customerService.calculatePrice(customerId, groceryIds, electronicDevicesIds);
        return new ResponseEntity<>(bigDecimal, HttpStatus.OK);
    }
}
