package com.sapient.springapp.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sapient.springapp.domain.Customer;

/**
 * Repository gets the customer information
 * @author Karthik Rao
 *
 */
@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {

    public Customer findByFirstName(String firstName);
    public List<Customer> findByLastName(String lastName);

}
