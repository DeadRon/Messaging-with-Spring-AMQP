package br.com.cashback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CashbackServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CashbackServiceApplication.class, args);
	}

}
