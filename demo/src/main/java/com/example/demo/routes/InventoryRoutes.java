package com.example.demo.routes;

import com.example.demo.controller.InventoryController;
import com.example.demo.model.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventories")
public class InventoryRoutes {

    @Autowired
    private InventoryController inventoryController;

    @PostMapping
    public Inventory createInventory(@RequestBody Inventory inventory) {
        return inventoryController.createInventory(inventory);
    }

    // Autres routes pour supprimer, modifier, etc.
}
