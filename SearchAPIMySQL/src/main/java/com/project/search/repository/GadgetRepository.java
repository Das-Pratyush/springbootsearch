package com.project.search.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.search.entity.Gadget;

public interface GadgetRepository extends JpaRepository<Gadget, Long> {

    List<Gadget> findByTypeContainingIgnoreCase( String type);
    List<Gadget> findByNameIgnoreCase(String name);


    List<Gadget> findByPriceBetween(double minPrice, double maxPrice);
}
