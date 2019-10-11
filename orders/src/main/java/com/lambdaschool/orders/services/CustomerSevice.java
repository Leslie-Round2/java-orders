package com.lambdaschool.orders.services;

import com.lambdaschool.orders.models.Customer;

import java.util.List;

public interface CustomerSevice
{
	//find all customer
	List<Customer> findAll();

	Customer findByName(String name);

	Customer save(Customer customer);

	Customer update(Customer customer, long custcode);

	void delete(long custcode);



}
