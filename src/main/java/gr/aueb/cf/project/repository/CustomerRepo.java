package gr.aueb.cf.project.repository;

import gr.aueb.cf.project.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository <Customer, Integer> {
    Customer findByCustomerName(String customerName);

//    Optional<Object> findById(boolean customerId);
}
