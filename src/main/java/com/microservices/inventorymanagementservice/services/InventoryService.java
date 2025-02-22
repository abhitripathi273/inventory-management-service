package com.microservices.inventorymanagementservice.services;

import com.microservices.inventorymanagementservice.models.Inventory;

import java.util.List;

public interface InventoryService {

    List<Inventory> getInventoryDetails();
    boolean purchaseProduct(String type, int quantity);
    Inventory restockProduct(String type, int quantity);
}
