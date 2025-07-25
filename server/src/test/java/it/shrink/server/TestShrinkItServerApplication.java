package it.shrink.server;

import org.springframework.boot.SpringApplication;

public class TestShrinkItServerApplication {

	public static void main(String[] args) {
		SpringApplication.from(ShrinkItServerApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
