package com.project.search.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.search.entity.Gadget;
import com.project.search.service.GadgetService;

@RestController
@RequestMapping("/api/gadgets")
public class GadgetRestController {

    @Autowired
    private GadgetService gadgetService;

    @GetMapping("/all")
    public List<Gadget> getAllGadgets() {
        return gadgetService.getAllGadgets();
    }

    @GetMapping("/search")
    public List<Gadget> searchGadgets(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice) {
        return gadgetService.searchGadgets(name, type, minPrice == null ? 0 : minPrice, maxPrice == null ? Double.MAX_VALUE : maxPrice);
    }
}
