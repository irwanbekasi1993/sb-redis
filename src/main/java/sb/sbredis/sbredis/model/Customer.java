package sb.sbredis.sbredis.model;


import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash("customer")
public class Customer {
    @Id
    private String id;

    public Customer(String firstName, String lastName) {
        
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public Customer() {
    }
    private String firstName;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    private String lastName;
    
    
    
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    

}
