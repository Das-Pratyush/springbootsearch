package com.project.search.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.project.search.entity.Gadget;
import com.project.search.repository.GadgetRepository;
import com.project.search.service.GadgetService;

public class GadgetServiceTest {

    @Mock
    private GadgetRepository gadgetRepository;

    @InjectMocks
    private GadgetService gadgetService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSearchGadgets() {
        List<Gadget> mockGadgets = new ArrayList<>();
        mockGadgets.add(new Gadget(1L, "Smartwatch", "Model1", 100.0, "Type1"));
        mockGadgets.add(new Gadget(2L, "Smartphone", "Model2", 200.0, "Type2"));

        // Mock repository methods
        when(gadgetRepository.findByTypeContainingIgnoreCase("Type1")).thenReturn(mockGadgets);
        when(gadgetRepository.findByPriceBetween(50.0, 150.0)).thenReturn(mockGadgets.subList(0, 1));
        when(gadgetRepository.findByNameIgnoreCase("Smartwatch")).thenReturn(mockGadgets.subList(0, 1));

        List<Gadget> result = gadgetService.searchGadgets("Smartwatch", "Type1", 50.0, 150.0);

        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals("Smartwatch", result.get(0).getName());
    }

    @Test
    public void testGetAllGadgets() {
        List<Gadget> mockGadgets = new ArrayList<>();
        mockGadgets.add(new Gadget(1L, "Smartwatch", "Model1", 100.0, "Type1"));
        mockGadgets.add(new Gadget(2L, "Smartphone", "Model2", 200.0, "Type2"));

        when(gadgetRepository.findAll()).thenReturn(mockGadgets);

        List<Gadget> result = gadgetService.getAllGadgets();

        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals("Smartwatch", result.get(0).getName());
        assertEquals(2L, result.get(1).getId());
        assertEquals("Smartphone", result.get(1).getName());
    }
}
