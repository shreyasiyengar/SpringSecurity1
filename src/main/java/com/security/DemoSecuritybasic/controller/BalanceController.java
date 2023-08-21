package com.security.DemoSecuritybasic.controller;

import com.security.DemoSecuritybasic.model.AccountTransactions;
import com.security.DemoSecuritybasic.repository.AcoountTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BalanceController {

    @Autowired
    private AcoountTransactionRepository acoountTransactionRepository;

    @GetMapping("/myBalance")
    public List<AccountTransactions> getBalanceDetails(@RequestParam int id) {
        List<AccountTransactions> accountTransactions = acoountTransactionRepository.findByCustomerIdOrderByTransactionDtDesc(id);
        if(accountTransactions !=null){
            return accountTransactions;
        }else{
         return null;
        }

    }
}
