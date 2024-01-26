package br.com.abimael.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@RefreshScope // Com esta anotação as alterações no config-server serão refletidas neste microserviço (Mas tem que rodar o endpoint do actuator: http://localhost:8080/actuator/refresh)
@ConfigurationProperties("greeting-service")
public class GreetingConfiguration {
	private String greeting;
	private String defaultValue;

	public GreetingConfiguration() {
	}

	public String getGreeting() {
		return greeting;
	}

	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

}
