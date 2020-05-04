package com.microservices.inventorymanagementservice.services;

import com.microservices.inventorymanagementservice.exceptions.OutOfStockException;
import com.microservices.inventorymanagementservice.models.Inventory;
import com.microservices.inventorymanagementservice.repos.InventoryReactiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    InventoryReactiveRepository repository;

    @Override
    public Flux<Inventory> getInventoryDetails() {
        return repository.findAll();
    }

    @Override
    public Mono<Inventory> purchaseProduct(String productType, int quantity) {
        return repository.findById(productType)
                .flatMap(current -> {
                    if (quantity > current.getStock())
                        return Mono.just(new Inventory());
                    current.setStock(current.getStock() - quantity);
                    return repository.save(current);
                })
                .defaultIfEmpty(new Inventory());
    }

    @Override
    public Mono<Inventory> restockProduct(String productType, int quantity) {
        return repository.findById(productType)
                .flatMap(current -> {
                    current.setStock(current.getStock() + quantity);
                    return repository.save(current);
                })
                .defaultIfEmpty(new Inventory(productType, quantity))
                .flatMap(inventory -> repository.save(inventory));
    }
}
