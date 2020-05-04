package com.microservices.inventorymanagementservice.repos;

import com.microservices.inventorymanagementservice.models.Inventory;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface InventoryReactiveRepository extends ReactiveMongoRepository<Inventory, String> {

}
