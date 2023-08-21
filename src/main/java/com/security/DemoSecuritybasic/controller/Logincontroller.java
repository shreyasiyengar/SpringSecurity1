package com.security.DemoSecuritybasic.controller;

import com.security.DemoSecuritybasic.model.Customer;
import com.security.DemoSecuritybasic.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class Logincontroller {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Customer customer){
        Customer savedcustomer = null;
        ResponseEntity response = null;
        try{
            String hashpswd =passwordEncoder.encode(customer.getPwd());
            customer.setPwd(hashpswd);
            customer.setCreateDt(String.valueOf(new Date()));
            savedcustomer = customerRepository.save(customer);
            if(savedcustomer.getId() > 0){
                response = ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body("Given user details are successfully register");
            }
        }catch (Exception ex){
            response = ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An exception occured due to "+ex.getMessage());
        }
        return response;
    }

    @RequestMapping("/user")
    public Customer getUserDetailsAfterLogin(Authentication authentication){
        List<Customer> customerList = customerRepository.findByEmail(authentication.getName());
        if(customerList.size() > 0){
            return customerList.get(0);
        }else{
            return null;
        }
    }
}
