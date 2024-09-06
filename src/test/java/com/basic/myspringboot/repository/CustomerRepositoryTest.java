package com.basic.myspringboot.repository;

import com.basic.myspringboot.entity.Customer;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class CustomerRepositoryTest {
    @Autowired
    CustomerRepository customerRepository;


    @Test
    @Rollback(value = false)
    public void customer_update() throws Exception {
        Optional<Customer> optional = customerRepository.findByCustomerId("A001");
        if(optional.isPresent()){
            Customer customer = optional.get();
            customer.setCustomerName("스프링 FW");
        }
    }


    @Test
    @Disabled
    @Rollback(value = false)
    public void customer() throws Exception {
        //등록
        Customer customer = new Customer();
        customer.setCustomerId("A001");
        customer.setCustomerName("스프링");
        Customer addCustomer = customerRepository.save(customer);

        System.out.println(addCustomer.getCustomerId() + " " + addCustomer.getCustomerName());
        assertThat(addCustomer).isNotNull();
        assertEquals("A001",addCustomer.getCustomerId());
        assertThat(addCustomer.getCustomerName()).isEqualTo("스프링");

        Optional<Customer> existCustomer = customerRepository.findByCustomerId(addCustomer.getCustomerId());
        if(existCustomer.isPresent()){
            Customer cust = existCustomer.get();
        }
        assertThat(existCustomer).isNotEmpty();


        Optional<Customer> notExistOptional = customerRepository.findByCustomerId("B001");
//        assertThat(notExistOptional).isEmpty();
        Customer cust1 = notExistOptional.orElseThrow(() -> new RuntimeException("Customer Not Found"));
        System.out.println(cust1.getCustomerId());
    }
}