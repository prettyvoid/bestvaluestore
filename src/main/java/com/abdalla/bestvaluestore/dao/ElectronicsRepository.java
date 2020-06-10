package com.abdalla.bestvaluestore.dao;

import com.abdalla.bestvaluestore.models.ElectronicDevice;
import org.springframework.data.repository.CrudRepository;

public interface ElectronicsRepository extends CrudRepository<ElectronicDevice, Long> {
}
