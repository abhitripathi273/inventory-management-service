package com.microservices.inventorymanagementservice.controllers;

import com.microservices.inventorymanagementservice.models.Inventory;
import com.microservices.inventorymanagementservice.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InventoryController {

    @Autowired
    InventoryService service;

    @GetMapping("/inventory")
    public List<Inventory> getInventory() {
        return service.getInventoryDetails();
    }

    @GetMapping("/inventory/purchase/{productType}/quantity/{quantity}")
    public ResponseEntity<Void> purchaseProduct(@PathVariable String productType,
                                                @PathVariable int quantity) {
        return service.purchaseProduct(productType, quantity) ?
                new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/inventory/purchase/{productType}")
    public ResponseEntity<Void> purchaseSingleProduct(@PathVariable String productType) {
        return service.purchaseProduct(productType, 1) ?
                new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/inventory/restock/{productType}/quantity/{quantity}")
    public ResponseEntity<Inventory> restockProduct(@PathVariable String productType,
                                                    @PathVariable int quantity) {
        Inventory updatedInventory = service.restockProduct(productType, quantity);
        return new ResponseEntity<>(updatedInventory, HttpStatus.OK);
    }

    @GetMapping("/inventory/restock/{productType}")
    public ResponseEntity<Inventory> restockSingleProduct(@PathVariable String productType) {
        Inventory updatedInventory = service.restockProduct(productType, 1);
        return new ResponseEntity<>(updatedInventory, HttpStatus.OK);
    }
}
