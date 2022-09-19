package com.example.customerservice.service;

import com.example.customerservice.entity.Customer;
import com.example.customerservice.repository.CustomerRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRespository customerRespository;

    public CustomerService(CustomerRespository customerRespository) {
        this.customerRespository = customerRespository;
    }


    public Customer saveCustomer(Customer customer) {
        return customerRespository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRespository.findAll();
    }

    public Customer getCustomerById(String customerId) {
        Optional<Customer> customerData = customerRespository.findById(customerId);
        if(customerData.isPresent()){
            return customerData.get();
        }
        return null;
    }

    public ResponseEntity<HttpStatus> deleteCustomerById(String customerId) {
        Customer customer = getCustomerById(customerId);
        if(customer!=null){
            customerRespository.deleteById(customerId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public Customer updateCustomerById(String customerId, Customer customer) {
        Customer _customer = getCustomerById(customerId);
        if(_customer!=null){
            _customer.setCustomerName(customer.getCustomerName());
            _customer.setCustomerEmail(customer.getCustomerEmail());
            _customer.setCustomerPhone(customer.getCustomerPhone());
            _customer.setCustomerCity(customer.getCustomerCity());
            _customer.setCustomerState(customer.getCustomerState());
            _customer.setCustomerCountry(customer.getCustomerCountry());

            customerRespository.save(_customer);
            return _customer;
        }
        return null;
    }
}
