package com.security.DemoSecuritybasic;

import com.security.DemoSecuritybasic.model.Loans;
import com.security.DemoSecuritybasic.repository.LoanRepository;
import jakarta.persistence.Entity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import java.util.List;

@SpringBootApplication
@EnableJpaRepositories("com.security.DemoSecuritybasic.repository")
public class DemoSecuritybasicApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(DemoSecuritybasicApplication.class);
	@Autowired
	private LoanRepository loanRepository;
	public static void main(String[] args) {
		SpringApplication.run(DemoSecuritybasicApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("hello this is just logger");
		List<Loans> loanRepository1 = loanRepository.findByCustomerIdOrderByStartDtDesc(1);
//		logger.info(loanRepository1.toString());
		for(Loans loan : loanRepository1) {
			System.out.println(loan);
		}
//		System.out.print(loanRepository1);

	}
}
