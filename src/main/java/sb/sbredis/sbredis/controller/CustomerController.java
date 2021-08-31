package sb.sbredis.sbredis.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sb.sbredis.sbredis.model.Customer;
import sb.sbredis.sbredis.repository.CustomerRepository;

@RestController
@RequestMapping("/sbredis")
public class CustomerController {
    private static Logger log = LoggerFactory.getLogger(CustomerController.class);

@Value("${sbredis.key}")
private String key;    

@Autowired
CustomerRepository customerRepository;

    @GetMapping("/customer")
    public Iterable<Customer> getAllCustomer(){
        log.info("key",key);
        Iterable<Customer> iter = customerRepository.findAll();
        
        return iter;
    } 

    @PostMapping("/customer")
    public ResponseEntity<?> saveCustomer(@RequestBody Customer customer){
        long increment = customerRepository.count();
        if(increment==0){
            increment=1;
            customer.setId(increment);
        }else{

            customer.setId(increment+1);
        }
            customerRepository.save(customer);
        
        return ResponseEntity.ok(customer);
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") long id){
        customerRepository.deleteById(id);
        return ResponseEntity.ok("customer is deleted");
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer> getById(@PathVariable("id") long id){
        Optional<Customer> c = customerRepository.findById(id);
        Customer cust = c.get();
        return ResponseEntity.ok(cust);
    }

    @PutMapping("/customer/{id}")
    public ResponseEntity<Customer> updateById(@PathVariable("id") long id){
        Customer cekCustomer = customerRepository.findById(id).get();
        cekCustomer.setFirstName(cekCustomer.getFirstName()+"_Updated");
        cekCustomer.setLastName(cekCustomer.getLastName()+"_Updated");
        customerRepository.save(cekCustomer);
        return ResponseEntity.ok(cekCustomer);
    }
}
