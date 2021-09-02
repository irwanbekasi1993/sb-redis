package sb.sbredis.sbredis.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

// @Autowired
// private ReactiveRedisTemplate<String,Customer> redisTemplate;

    @GetMapping("/customer")
    public Iterable<Customer> getAllCustomer(){
        Iterable<Customer> iter = customerRepository.findAll();
        return iter;
    } 

    @PostMapping("/customer")
    public ResponseEntity<?> saveCustomer(@RequestBody Customer customer){
        
            customerRepository.save(customer);
        
        return ResponseEntity.ok(customer);
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") String id){
        boolean flag = customerRepository.existsById(id);
        String status= "";
        if(flag==true){
            customerRepository.deleteById(id);
            status="customer successfully deleted";
        }else{
            status="customer not found";
        } 
        return ResponseEntity.ok(status);
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer> getById(@PathVariable("id") String id){
        Optional<Customer> c = customerRepository.findById(id);
        Customer cust = new Customer();
        if(c.isPresent()){
            cust = c.get();

        }else{
            cust = new Customer();
        }
        return ResponseEntity.ok(cust);
    }

    @PutMapping("/customer/{id}")
    public ResponseEntity<Customer> updateById(@PathVariable("id") String id){
        Customer cekCustomer = customerRepository.findById(id).get();
        cekCustomer.setFirstName(cekCustomer.getFirstName()+"_Updated");
        cekCustomer.setLastName(cekCustomer.getLastName()+"_Updated");
        customerRepository.save(cekCustomer);
        return ResponseEntity.ok(cekCustomer);
    }
}
