package com.example.Urbanfood.controller;

import com.example.Urbanfood.entity.Customer;
import com.example.Urbanfood.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3001")
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // GET by ID
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Customer customer = customerService.getCustomerById(id);
        if (customer != null) {
            return ResponseEntity.ok(customer);
        }
        return ResponseEntity.notFound().build();
    }

    // POST to create a new customer
    @PostMapping
    public ResponseEntity<Long> insertCustomer(@RequestParam String name,
                                               @RequestParam String email,
                                               @RequestParam Long contact,
                                               @RequestParam String address) {
        Long newId = customerService.insertCustomer(name, email, contact, address);
        return ResponseEntity.ok(newId);
    }

    // PUT to update an existing customer
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateCustomer(@PathVariable Long id,
                                               @RequestParam String name,
                                               @RequestParam String email,
                                               @RequestParam Long contact,
                                               @RequestParam String address) {
        //customerService.updateCustomer(id, name, email, contact, address);
        //return ResponseEntity.ok().build();s

        Customer updatedCustomer = customerService.updateCustomer(id, name, email, contact, address);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Customer updated successfully");
        response.put("customer", updatedCustomer);

        return ResponseEntity.ok(response);
    }

    // DELETE a customer
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok().build();
    }
}
