package gr.aueb.cf.project.service;

import gr.aueb.cf.project.model.Customer;
import gr.aueb.cf.project.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
   private final CustomerRepo customerRepo;

    public CustomerService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    public void createCustomer(Customer customer) {
        customerRepo.save(customer);
    }

    public List<Customer> customersList() {
        return customerRepo.findAll();
    }

    public Customer readCustomer(String customerName) {
        return customerRepo.findByCustomerName(customerName);
    }

    public Optional<Customer> readCustomer (Integer customerId){
            return customerRepo.findById(customerId);
        }
    public void updateCustomer(Integer customerId, Customer newCustomer) {
        Customer customer = customerRepo.findById(customerId).get();
        customer.setCustomerName(newCustomer.getCustomerName());
        customerRepo.save(customer);
    }

        public boolean findById(int customerId) {
            return customerRepo.findById(customerId).isPresent();
    }

    public void deleteById(int customerId) {
        customerRepo.deleteById(customerId);
    }


    }

