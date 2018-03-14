package org.graphast.api.controller;

import org.graphast.api.repository.MapGraph;
import org.graphast.model.EdgeImpl;
import org.graphast.model.Graph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("edge")
public class EdgeGenerator {
	@Autowired
	private MapGraph mapGraph;
	
	@PostMapping("/{nameGraph}")
	public ResponseEntity<String> createEdge(@PathVariable("nameGraph") String nameGraph, @RequestBody EdgeImpl edgeImpl){
		EdgeImpl e = edgeImpl;
		mapGraph.getMapGraph().get(nameGraph).addEdge(e);
		return ResponseEntity.ok("Edge created");
	}
	@GetMapping("/{nameGraph}")
	public ResponseEntity<int[][]> getEdge(@PathVariable("nameGraph") String nameGraph) {
		Graph graph = mapGraph.getMapGraph().get(nameGraph);
		return ResponseEntity.ok(graph.getEdges().elements());
	}
}
