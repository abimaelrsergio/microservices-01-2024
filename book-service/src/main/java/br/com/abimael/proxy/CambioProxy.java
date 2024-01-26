package br.com.abimael.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.abimael.response.Cambio;

@FeignClient(name = "cambio-servive", url = "localhost:8000")
public interface CambioProxy {

	@GetMapping("/cambio-service/{amount}/{from}/{to}")
	public Cambio getCamio(@PathVariable("amount") Double amount, @PathVariable("from") String from,
			@PathVariable("to") String to);
}
