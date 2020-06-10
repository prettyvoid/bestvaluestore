package com.abdalla.bestvaluestore.models;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "customers")
@Entity
public class Customer {
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private boolean affiliate;
    private boolean employee;
    private LocalDate creationDate;

    public Customer() {
    }

    public Customer(Long id, String firstName, String lastName, boolean affiliate, boolean employee, LocalDate creationDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.affiliate = affiliate;
        this.employee = employee;
        this.creationDate = creationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isAffiliate() {
        return affiliate;
    }

    public void setAffiliate(boolean affiliate) {
        this.affiliate = affiliate;
    }

    public boolean isEmployee() {
        return employee;
    }

    public void setEmployee(boolean employee) {
        this.employee = employee;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}
