package com.project.search.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.search.entity.Gadget;
import com.project.search.repository.GadgetRepository;

@Service
public class GadgetService {

    @Autowired
    private GadgetRepository gadgetRepository;

    public List<Gadget> searchGadgets(String name, String type, double minPrice, double maxPrice) {
        List<Gadget> byType = gadgetRepository.findByTypeContainingIgnoreCase(type);
        List<Gadget> filteredList = new ArrayList<>(byType);

        if (minPrice >= 0 && maxPrice >= 0 && maxPrice >= minPrice) {
            List<Gadget> byPriceRange = gadgetRepository.findByPriceBetween(minPrice, maxPrice);
            filteredList.retainAll(byPriceRange);
        }
        if (name != null && !name.isEmpty()) {
            List<Gadget> byName = gadgetRepository.findByNameIgnoreCase(name);
            filteredList.retainAll(byName);
        }

        return filteredList;
    }
    public List<Gadget> getAllGadgets() {
        return gadgetRepository.findAll();
    }
}
