package com.example.demo;

import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplicationAotProcessor;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.aot.AbstractAotProcessor;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {
	}

	public static void main(String[] args) {
		AbstractAotProcessor.Settings.Builder settings = AbstractAotProcessor.Settings.builder();
		settings.artifactId("demo");
		settings.groupId("com.example");
		settings.classOutput(Paths.get("target", "test", "classes"));
		settings.resourceOutput(Paths.get("target", "test", "classes"));
		settings.sourceOutput(Paths.get("target", "test", "src"));
		new SpringApplicationAotProcessor(DemoApplication.class, settings.build(), new String[0]).process();
	}
}
