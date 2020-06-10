package com.abdalla.bestvaluestore.models;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(name = "electronics")
@Entity
public class ElectronicDevice {
    @Id
    private Long id;
    private String name;
    private BigDecimal price;

    public ElectronicDevice(Long id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public ElectronicDevice() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
