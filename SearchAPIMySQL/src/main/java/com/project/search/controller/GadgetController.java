package com.project.search.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.search.entity.Gadget;
import com.project.search.repository.GadgetRepository;
import com.project.search.service.GadgetService;

@Controller
public class GadgetController {

    @Autowired
    private GadgetService gadgetService;

    @Autowired
    private GadgetRepository gadgetRepository;

    @GetMapping("/gadgets")
    public String showAllGadgets(Model model) {
        List<Gadget> gadgets = gadgetRepository.findAll();
        model.addAttribute("gadgets", gadgets);
        return "allGadgets";
    }

//    @GetMapping("/")
//    public String home() {
//        return "home";
//    }

    @GetMapping("/gadgets/search")
    public String searchGadgets(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            Model model) {
    	
        List<Gadget> gadgets = gadgetService.searchGadgets(name, type, minPrice == null ? 0 : minPrice, maxPrice == null ? Double.MAX_VALUE : maxPrice);

        model.addAttribute("gadgets", gadgets);
        model.addAttribute("name", name);
        model.addAttribute("type", type);
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);

        return "gadgetList";
    }
}

