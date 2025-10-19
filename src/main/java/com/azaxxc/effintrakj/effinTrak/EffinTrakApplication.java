package com.azaxxc.effintrakj.effinTrak;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class EffinTrakApplication {

	public static void main(String[] args) {
		SpringApplication.run(EffinTrakApplication.class, args);
	}

}
