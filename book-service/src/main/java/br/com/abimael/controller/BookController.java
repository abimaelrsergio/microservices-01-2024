package br.com.abimael.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.abimael.model.Book;
import br.com.abimael.proxy.CambioProxy;
import br.com.abimael.repository.BookRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Book endpoint")
@RestController
@RequestMapping("/book-service")
public class BookController {

	@Autowired
	private Environment environment;

	@Autowired
	private BookRepository repository;

	@Autowired
	private CambioProxy proxy;

	@Operation(summary = "Find a specific book by ID")
	@GetMapping("/{id}/{currency}")
	public Book findBook(@PathVariable("id") Long id, @PathVariable("currency") String currency) {
		var book = repository.findById(id).get();
		if (book == null) {
			throw new RuntimeException("Book not Found");
		}

		var cambio = proxy.getCamio(book.getPrice(), "USD", currency);
		var port = environment.getProperty("local.server.port");
		book.setEnvironment("Book port: " + port + " Cambio port: " + cambio.getEnvironment());
		book.setPrice(cambio.getConvetedValue());
		return book;
	}

	/*
	 * public Book findBook(@PathVariable("id") Long id, @PathVariable("currency")
	 * String currency) { var book = repository.findById(id).get(); if (book ==
	 * null) { throw new RuntimeException("Book not Found"); }
	 * 
	 * HashMap<String, String> params = new HashMap<>(); params.put("amount",
	 * book.getPrice().toString()); params.put("from", "USD"); params.put("to",
	 * currency); var response = new RestTemplate().getForEntity(
	 * "http://localhost:8000/cambio-service/{amount}/{from}/{to}", Cambio.class,
	 * params); var cambio = response.getBody(); var port =
	 * environment.getProperty("local.server.port"); book.setEnvironment(port);
	 * book.setPrice(cambio.getConvetedValue()); return book; }
	 */

}
