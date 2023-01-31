package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.guice.annotation.EnableGuiceModules;

import com.google.inject.AbstractModule;

@SpringBootApplication
@EnableGuiceModules
public class DemoApplication {

	public static void main(String[] args) {
		System.err.println(SpringApplication.run(DemoApplication.class, args).getBean(Spam.class));
	}

	@Bean
	public static MyModule myModule() {
		return new MyModule();
	}

	@Bean
	public Spam spam(Service service) {
		return new Spam(service);
	}

}

class MyModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(Service.class).to(MyService.class);
	}

}

class Spam {
	public Spam(Service service) {
	}
}

class MyService implements Service {
}