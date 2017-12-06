package org.graphast.api.controller;

import org.graphast.api.repository.MapGraph;
import org.graphast.model.EdgeImpl;
import org.graphast.model.Graph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("edge")
public class EdgeGenerator {
	@Autowired
	private MapGraph mapGraph;
	
	@PostMapping
	public ResponseEntity<String> createEdge(@RequestParam("nameGraph") String nameGraph, @RequestBody EdgeImpl edgeImpl){
		mapGraph.getMapGraph().get(nameGraph).addEdge(edgeImpl);
		return ResponseEntity.ok("Edge created");
	}
	@GetMapping
	public ResponseEntity<int[][]> getEdge(@RequestParam("nameGraph") String nameGraph) {
		Graph graph = mapGraph.getMapGraph().get(nameGraph);
		return ResponseEntity.ok(graph.getEdges().elements());
	}
}
