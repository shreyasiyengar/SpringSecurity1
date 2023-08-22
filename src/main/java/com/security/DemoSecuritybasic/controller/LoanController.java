package com.security.DemoSecuritybasic.controller;

import com.security.DemoSecuritybasic.model.Loans;
import com.security.DemoSecuritybasic.repository.LoanRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoanController {

    @Autowired
    private LoanRepository loanRepository;

    private Logger logger = LoggerFactory.getLogger(LoanController.class);

    @GetMapping("/myLoans")
    public List<Loans> getLoanDetails(@RequestParam int id){
        List<Loans> loans = loanRepository.findByCustomerIdOrderByStartDtDesc(id);
        if(loans != null){
//            System.out.println(loans);
//            System.out.println("hello this is loan");
//            logger.info("hello ");
//            LoanRepository loan1= (LoanRepository) loanRepository.findByCustomerIdOrderByStartDtDesc(1);
//            logger.info(loan1.toString());

            return loans;
        }else{
            return  null;
        }
    }
}
