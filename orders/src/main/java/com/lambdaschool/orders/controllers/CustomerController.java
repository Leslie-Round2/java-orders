package com.lambdaschool.orders.controllers;

import com.lambdaschool.orders.models.Customer;
import com.lambdaschool.orders.services.CustomerSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController
{
	@Autowired
	private CustomerSevice customerSevice;

	@GetMapping(value="/order",
				produces = {"application/json"})
	public ResponseEntity<?> listAllCustomers()
	{
		List<Customer> myList = customerSevice.findAll();
		return new ResponseEntity<>(myList, HttpStatus.OK);
	}

	@GetMapping(value = "/name/{custname}",
				produces = {"application/json"})
	public ResponseEntity<?> findByCustName(@PathVariable String custName)
	{
		Customer myCustomer = customerSevice.findByName(custName);
		return new ResponseEntity<>(myCustomer, HttpStatus.OK);
	}

	@PostMapping(value = "/new",
				consumes = {"application/json"})
	public ResponseEntity<?> addNewCustomer(@Valid
	                                        @RequestBody Customer newcustomer)
	{
		customerSevice.save(newcustomer);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping(value = "update/{custcode}",
				consumes = {"application/json"})
	public ResponseEntity<?> updateCustomer(@Valid
	                                            @RequestBody Customer customer,
	                                        @PathVariable long custcode)
	{
		customerSevice.update(customer, custcode);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/delete/{custcode}")
	public ResponseEntity<?> deleteCustomer(@PathVariable long custcode)
	{
		customerSevice.delete(custcode);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
