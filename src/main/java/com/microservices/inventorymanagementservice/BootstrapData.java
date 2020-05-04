package com.microservices.inventorymanagementservice;

import com.microservices.inventorymanagementservice.models.Inventory;
import com.microservices.inventorymanagementservice.repos.InventoryReactiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

@Component
public class BootstrapData implements CommandLineRunner {

    @Autowired
    InventoryReactiveRepository repository;

    @Override
    public void run(String... args) throws Exception {
        initialDataSetup();
    }

    private void initialDataSetup() {
        repository.deleteAll()
                .thenMany(Flux.fromIterable(data()))
                .flatMap(repository::save)
                .thenMany(repository.findAll())
                .subscribe(inventory -> System.out.println("Object added by CLR: " + inventory));
    }

    private List<Inventory> data() {
        return Arrays.asList(new Inventory("Phones", 3),
                new Inventory("Tablets", 3),
                new Inventory("Smartwatches", 3));
    }
}
