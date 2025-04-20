package com.example.Urbanfood.repository;

import com.example.Urbanfood.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    List<Supplier> findAll();
    // Calls the stored procedure "insert_supplier" using the Supplier entity's named query
    @Procedure(name = "Supplier.insert_supplier")
    Integer insertSupplier(@Param("pa_name") String name,
                           @Param("pa_phone") Long phone,
                           @Param("pa_addr") String address);
    @Procedure(name = "Supplier.delete_supplier")
    void deleteSupplier(@Param("pa_id") Long id);


    // Call stored procedure to update a supplier
    @Procedure(name = "Supplier.update_supplier")
    void updateSupplier(@Param("pa_id") Long id,
                        @Param("pa_name") String name,
                        @Param("pa_phone") Long phone,
                        @Param("pa_addr") String address);

    @Procedure(name = "Supplier.getSupplierByName")
    List<Supplier> getSupplierByName(@Param("pa_name") String name);

}
