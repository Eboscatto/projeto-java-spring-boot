package com.eboscatto.projetoJava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class ProjetoJavaApplication {

	public static void main(String[] args) {

		SpringApplication.run(ProjetoJavaApplication.class, args);
		System.out.println("Projeto inicializado com sucesso!");
	}

}
