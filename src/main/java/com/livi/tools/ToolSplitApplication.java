package com.livi.tools;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ToolSplitApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToolSplitApplication.class, args);
	}

}
