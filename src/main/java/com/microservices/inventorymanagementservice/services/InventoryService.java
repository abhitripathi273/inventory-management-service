package com.microservices.inventorymanagementservice.services;

import com.microservices.inventorymanagementservice.models.Inventory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface InventoryService {
    Flux<Inventory> getInventoryDetails();
    Mono<Inventory> purchaseProduct(String type, int quantity);
    Mono<Inventory> restockProduct(String type, int quantity);
}
