package com.lambdaschool.orders.services;

import com.lambdaschool.orders.models.Customer;
import com.lambdaschool.orders.models.Order;
import com.lambdaschool.orders.repos.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "customerService")
public class CustomerServiceImpl implements CustomerSevice
{
	@Autowired
	private CustomerRepository customerRepo;

	@Override
	public List<Customer> findAll()
	{
		List<Customer> rtnList = new ArrayList<>();

		customerRepo.findAll().iterator().forEachRemaining(rtnList :: add);

		return rtnList;

	}

	@Override
	public Customer findByName(String name)
	{
		return customerRepo.findByCustname(name);
	}

	@Override
	public Customer save(Customer customer)
	{
		Customer newCustomer = new Customer();

		newCustomer.setCustname(customer.getCustname());
		newCustomer.setCustcity(customer.getCustcity());
		newCustomer.setWorkingarea(customer.getWorkingarea());
		newCustomer.setCustcountry(customer.getCustcountry());
		newCustomer.setGrade(customer.getGrade());
		newCustomer.setOpeningamt(customer.getOpeningamt());
		newCustomer.setReceiveamt(customer.getReceiveamt());
		newCustomer.setPaymentamt(customer.getPaymentamt());
		newCustomer.setOutstandingamt(customer.getOutstandingamt());
		newCustomer.setPhone(customer.getPhone());
		newCustomer.setAgent(customer.getAgent());

		for(Order o : customer.getOrders())
		{
			newCustomer.getOrders().add(new Order(o.getOrdamount(), o.getAdvanceamount(), o.getCustomer(), o.getOrddescription()));
		}

		return customerRepo.save(customer);
	}

	@Override
	public Customer update(Customer customer, long custcode)
	{
		return customerRepo.save(customer);
	}

	@Override
	public void delete(long custcode)
	{
		customerRepo.deleteById(custcode);
	}
}
