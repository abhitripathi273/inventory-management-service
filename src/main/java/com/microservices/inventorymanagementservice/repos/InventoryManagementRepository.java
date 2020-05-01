package com.microservices.inventorymanagementservice.repos;

import com.microservices.inventorymanagementservice.models.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryManagementRepository extends JpaRepository<Inventory, String> {
}
