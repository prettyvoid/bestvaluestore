package com.abdalla.bestvaluestore.dao;

import com.abdalla.bestvaluestore.models.Grocery;
import org.springframework.data.repository.CrudRepository;

public interface GroceriesRepository extends CrudRepository<Grocery, Long> {
}
