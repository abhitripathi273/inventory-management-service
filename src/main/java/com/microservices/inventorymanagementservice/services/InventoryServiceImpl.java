package com.microservices.inventorymanagementservice.services;

import com.microservices.inventorymanagementservice.models.Inventory;
import com.microservices.inventorymanagementservice.repos.InventoryManagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    InventoryManagementRepository repository;

    @Override
    public List<Inventory> getInventoryDetails() {
        return repository.findAll();
    }

    @Override
    public boolean purchaseProduct(String productType, int quantity) {
        AtomicBoolean success = new AtomicBoolean(false);
        repository.findById(productType).ifPresent(inventory -> {
            Inventory updatedInventory = new Inventory();
            if (inventory.getStock() > quantity) {
                updatedInventory.setProductCategory(productType);
                updatedInventory.setStock(inventory.getStock() - quantity);
                repository.save(updatedInventory);
                success.set(true);
            }
        });
        return success.get();
    }

    @Override
    public Inventory restockProduct(String productType, int quantity) {
        Inventory updatedInventory = new Inventory();
        updatedInventory.setProductCategory(productType);
        updatedInventory.setStock(quantity);
        repository.findById(productType).ifPresent(inventory -> {
            updatedInventory.setStock(inventory.getStock() + quantity);
        });
        return repository.save(updatedInventory);
    }
}
