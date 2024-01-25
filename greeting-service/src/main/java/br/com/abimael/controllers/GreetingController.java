package br.com.abimael.controllers;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.abimael.configuration.GreetingConfiguration;
import br.com.abimael.model.Greeting;

@RestController
public class GreetingController {

	private static final String template = "%s, %s!";
	private final AtomicLong counter = new AtomicLong();

	@Autowired
	private GreetingConfiguration configuration;

	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "") String name) {
		if (name.isEmpty()) {
			name = configuration.getDefaultValue();
		}
		return new Greeting(counter.incrementAndGet(), String.format(template, configuration.getGreeting(), name));
	}
}
