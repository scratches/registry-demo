package com.example.demo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(proxyBeanMethods = false)
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

class MyModule implements BeanDefinitionRegistryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
	}

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		RootBeanDefinition bean = new RootBeanDefinition(ServiceFactoryBean.class);
		ConstructorArgumentValues args = new ConstructorArgumentValues();
		args.addIndexedArgumentValue(0, new Foo());
		bean.setConstructorArgumentValues(args);
		registry.registerBeanDefinition("service", bean);
	}

}

class ServiceFactoryBean implements FactoryBean<Service> {

	private Foo foo;

	ServiceFactoryBean(Foo foo) {
		this.foo = foo;
	}

	@Override
	public Service getObject() throws Exception {
		return new MyService(this.foo);
	}

	@Override
	public Class<?> getObjectType() {
		return MyService.class;
	}
	
}

class Spam {
	public Spam(Service service) {
	}
}

class MyService implements Service {
	MyService(Foo foo) {
		System.err.println("Foo: " + foo);
	}
}

class Foo {
}