package com.bug.spring;

import org.jsondoc.spring.boot.starter.EnableJSONDoc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@EnableJSONDoc
public class BugTrackerApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(BugTrackerApplication.class, args);
		
	}

}
