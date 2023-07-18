package com.udacity.jdnd.course3.critter.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.exception.CustomerException;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;

@Service
@Transactional
public class CustomerServives {

	@Autowired
	private CustomerRepository customerRepository;

	public List<Customer> findAllCustomers() {
		return customerRepository.findAll();
	}

	public Customer findCustomerById(Long id) {
		return customerRepository.findById(id)
				.orElseThrow(() -> new CustomerException("Customer is not found with ID: " + id));
	}

	public Customer save(Customer customer) {
		return customerRepository.save(customer);
	}
}
