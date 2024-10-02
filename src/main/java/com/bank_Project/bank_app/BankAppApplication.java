package com.bank_Project.bank_app;

import com.bank_Project.bank_app.service.Impl.userServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class BankAppApplication{

	public static void main(String[] args) {
		SpringApplication.run(BankAppApplication.class, args);
		}

}
