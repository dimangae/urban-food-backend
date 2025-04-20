package com.example.Urbanfood.controller;

import com.example.Urbanfood.entity.Supplier;
import com.example.Urbanfood.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3001")
@RequestMapping("/api")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @RequestMapping(method = RequestMethod.OPTIONS, value = "/update")
    public ResponseEntity<?> corsHeaders() {
        return ResponseEntity.ok().build();
    }

    /**
     * Handles POST requests to insert a new supplier.
     * @param supplier Supplier entity passed as request body
     * @return Response with inserted Supplier ID
     */
    @PostMapping(path = "/suppliers")
    public ResponseEntity<String> createSupplier(@RequestBody Supplier supplier) {
        int supplierId = supplierService.addSupplier(supplier.getName(), supplier.getContact(), supplier.getAddress());
        return ResponseEntity.ok("Supplier inserted with ID: " + supplierId);
    }
    @GetMapping(path = "/suppliers")
    public ResponseEntity<List<Supplier>> getAllSuppliers() {
        List<Supplier> suppliers = supplierService.getAllSuppliers();
        return ResponseEntity.ok(suppliers);
    }
    @DeleteMapping("/suppliers/{id}")
    public ResponseEntity<String> deleteSupplier(@PathVariable Long id) {
        supplierService.deleteSupplier(id);
        return ResponseEntity.ok("Supplier deleted successfully");
    }
    @GetMapping("/search")
    public ResponseEntity<List<Supplier>> getSupplierByName(@RequestParam String name) {
        List<Supplier> suppliers = supplierService.getSupplierByName(name);
        return ResponseEntity.ok(suppliers);
    }
    @GetMapping("/searchById")
    public ResponseEntity<Supplier> getSupplierById(@RequestParam Long id) {
        Supplier supplier = supplierService.getSupplierById(id);
        if(supplier != null){
            return ResponseEntity.ok(supplier);
        }
        return ResponseEntity.notFound().build();
    }
    @PutMapping("/suppliers/update")
    public ResponseEntity<Void> updateSupplier(
            @RequestParam Long id,
            @RequestParam String name,
            @RequestParam Long contact,
            @RequestParam String address) {
        supplierService.updateSupplier(id, name, contact, address);
        return ResponseEntity.ok().build();
    }

}
