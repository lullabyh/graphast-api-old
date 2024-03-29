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

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/graphApp")
public class GraphAppController {
	
	public Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private GraphService service = new GraphService();

	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully retrieved list"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
	)
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
	public ResponseEntity<List<GraphInfo>> apps() {
		logger.debug("apps");
		return ResponseEntity.ok(Configuration.getApps());
	}
	
	@GetMapping("/appNames")
	public ResponseEntity<List<String>> appNames() {
		return ResponseEntity.ok(Configuration.getAppNames());
	}
	
	@DeleteMapping("/{app}")
	public ResponseEntity<HttpStatus> delete(@PathVariable String app) {
		logger.debug("delete {}", app);
		service.delete(app);
		logger.debug("Application " + app + " removed successfully");
		return ResponseEntity.noContent().build();
	}
}
