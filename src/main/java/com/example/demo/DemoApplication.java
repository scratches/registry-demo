package com.example.demo;

import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportRuntimeHints;
import org.springframework.guice.annotation.EnableGuiceModules;

import com.google.inject.AbstractModule;

@SpringBootApplication
@EnableGuiceModules
@ImportRuntimeHints(DemoRuntimeHints.class)
public class DemoApplication {

	public static void main(String[] args) {
		// System.setProperty("spring.aot.enabled", "true");
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

class DemoRuntimeHints implements RuntimeHintsRegistrar {

	@Override
	public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
		hints.reflection().registerType(Integer.class, MemberCategory.INVOKE_DECLARED_METHODS);
		hints.reflection().registerType(Long.class, MemberCategory.INVOKE_DECLARED_METHODS);
		hints.reflection().registerType(Double.class, MemberCategory.INVOKE_DECLARED_METHODS);
		hints.reflection().registerType(Float.class, MemberCategory.INVOKE_DECLARED_METHODS);
		hints.reflection().registerType(Boolean.class, MemberCategory.INVOKE_DECLARED_METHODS);
		hints.reflection().registerType(Byte.class, MemberCategory.INVOKE_DECLARED_METHODS);
		hints.reflection().registerType(Short.class, MemberCategory.INVOKE_DECLARED_METHODS);
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