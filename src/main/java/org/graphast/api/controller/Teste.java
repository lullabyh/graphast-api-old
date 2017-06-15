package org.graphast.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Teste {
	
	@GetMapping("/")
	public String index(){
		
		return "Hello 1";
	}
}
