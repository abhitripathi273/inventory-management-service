package com.microservices.inventorymanagementservice.controllers;

import com.microservices.inventorymanagementservice.models.Inventory;
import com.microservices.inventorymanagementservice.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class InventoryController {

    @Autowired
    InventoryService service;

    @GetMapping("/inventory")
    public Flux<Inventory> getInventory() {
        return service.getInventoryDetails();
    }

    @GetMapping("/inventory/purchase/{productType}/quantity/{quantity}")
    public Mono<ResponseEntity<Void>> purchaseProduct(@PathVariable String productType,
                                                      @PathVariable int quantity) {
        return service.purchaseProduct(productType, quantity)
                .map(inventory -> {
                    if (inventory.getProductCategory() == null)
                        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                    return new ResponseEntity<>(HttpStatus.OK);
                });
    }

    @GetMapping("/inventory/purchase/{productType}")
    public Mono<ResponseEntity<Void>> purchaseSingleProduct(@PathVariable String productType) {
        return purchaseProduct(productType, 1);
    }

    @GetMapping("/inventory/restock/{productType}/quantity/{quantity}")
    public Mono<ResponseEntity<Inventory>> restockProduct(@PathVariable String productType,
                                                          @PathVariable int quantity) {
        return service.restockProduct(productType, quantity)
                .map(updatedInventory -> new ResponseEntity<>(updatedInventory, HttpStatus.OK));
    }

    @GetMapping("/inventory/restock/{productType}")
    public Mono<ResponseEntity<Inventory>> restockSingleProduct(@PathVariable String productType) {
        return restockProduct(productType, 1);
    }
}
