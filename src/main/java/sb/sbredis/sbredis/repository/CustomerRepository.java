package sb.sbredis.sbredis.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import sb.sbredis.sbredis.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer,String>{
    
}
