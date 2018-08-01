package com.javasampleapproach.h2database.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javasampleapproach.h2database.model.Customer;
import com.javasampleapproach.h2database.repository.CustomerRepository;

@RestController
public class WebController {
    @Autowired
    CustomerRepository repository;
    
    //-----------New----------------
   	@GetMapping("/customers") // Get all 		GET: http://localhost:8080/customers   
   	public List getCustomers() {
   		return repository.findAll();
    }

	@GetMapping("/customers/{id}") // Get one	GET: http://localhost:8080/customers/1
	public ResponseEntity getCustomer(@PathVariable("id") Long id) {

		Customer customer = repository.findOne(id);
		if (customer == null) {
			return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(customer, HttpStatus.OK);
	}

	@PostMapping(value = "/customers") // Save	POST: http://localhost:8080/customers
	public ResponseEntity createCustomer(@RequestBody Customer customer) {

		repository.save(customer);

		return new ResponseEntity(customer, HttpStatus.OK);
	}

	@DeleteMapping("/customers/{id}") // Delete	Delete: http://localhost:8080/customers/1
	public ResponseEntity deleteCustomer(@PathVariable Long id) {

		repository.delete(id);

		return new ResponseEntity(id, HttpStatus.OK);

	}

	@PutMapping("/customers/{id}") //Update  PUT:  http://localhost:8080/customers/1
	public ResponseEntity updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
		System.out.println("Inside Update ....");
		Customer cust = repository.findOne(id);
		
		System.out.println("Inside Update ....Cust:"+cust);
		
		cust.setFirstName(customer.getFirstName());
		cust.setLastName(customer.getLastName());
		
		repository.saveAndFlush(cust);
		
		return new ResponseEntity(id, HttpStatus.OK);
	}

    
    
    //-----------New----------------
      
    @GetMapping(path = "/get/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> getJSON() {
       List<String> list = new ArrayList<>();
       list.add("One");
       list.add("Two");
       list.add("Three");
       return ResponseEntity
             .ok()
             .cacheControl(CacheControl.noCache())
             .body(list);
    }
    @RequestMapping("/save")
    public String process(){
        repository.save(new Customer("Jack", "Smith"));
        repository.save(new Customer("Adam", "Johnson"));
        repository.save(new Customer("Kim", "Smith"));
        repository.save(new Customer("David", "Williams"));
        repository.save(new Customer("Peter", "Davis"));
        return "Done";
    }
       
       
    @RequestMapping("/findAll")
    public List<Customer> findAll(){
    	List<Customer> list= repository.findAll();
    	return list;
        
    }
    
    @GetMapping(path = "/findCust", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Customer>> getCust() {
    	List<Customer> list= repository.findAll();
    	list.forEach(System.out::println);
       return ResponseEntity
             .ok()
             .cacheControl(CacheControl.noCache())
             .body(list);
    }
    
    @RequestMapping("/findbyid")
    public String findById(@RequestParam("id") long id){
        String result = "";
        result = repository.findOne(id).toString();
        return result;
    }
       
    @RequestMapping("/findbylastname")
    public String fetchDataByLastName(@RequestParam("lastname") String lastName){
        String result = "";
           
        for(Customer cust: repository.findByLastName(lastName)){
            result += cust.toString() + "</br>"; 
        }
           
        return result;
    }
}
