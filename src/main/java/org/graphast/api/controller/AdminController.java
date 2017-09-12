package org.graphast.api.controller;

import java.net.URI;
import java.util.List;

import org.graphast.app.GraphInfo;
import org.graphast.app.GraphService;
import org.graphast.config.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	public Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private GraphService service = new GraphService();
	

	@PostMapping
	public ResponseEntity<GraphInfo> create(@RequestBody GraphInfo graphInfo ) {
		
		double initialTime = System.currentTimeMillis();
		
		service.create(graphInfo);
		
		double finallTime = System.currentTimeMillis();
		double totalTime = finallTime - initialTime; 
		logger.info("Total time: {}", totalTime);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{app}").buildAndExpand(graphInfo.getAppName()).toUri();
		return ResponseEntity.created(uri).body(graphInfo);
	}
	
	@GetMapping("/{app}")
	public ResponseEntity<GraphInfo> load(@PathVariable String app) {
		GraphInfo graph = service.load(app);	
		if(graph == null)
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(graph);
	}
	
	@GetMapping
	public ResponseEntity<List<String>> appNames() {
		return ResponseEntity.ok(Configuration.getAppNames());
	}
	
	@DeleteMapping("/{app}")
	public ResponseEntity<HttpStatus> delete(@PathVariable String app) {
		logger.info("delete {}", app);
		service.delete(app);
		logger.info("Application " + app + " removed successfully");
		return ResponseEntity.noContent().build();
	}
}
