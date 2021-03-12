package com.rest.microserviceb;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

	//private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/callMicroserviceB")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "Response From MicroserviceB") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(name));
	}
	
	@GetMapping("/AcallsMicroserviceB")
	public Greeting greetingAB(@RequestParam(value = "name", defaultValue = "Request Received from MicroserviceA and Response Sent From MicroserviceB") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(name));
	}
}
