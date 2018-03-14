package org.graphast.api.controller;

import org.graphast.api.repository.MapGraph;
import org.graphast.config.Configuration;
import org.graphast.model.Graph;
import org.graphast.model.GraphImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/graph")
public class GraphGenerator {

	@Autowired
	private MapGraph mapGraph;

	@PostMapping("{nameGraph}")
	public ResponseEntity<String> createGraph(@PathVariable("nameGraph") String nameGraph) {
		Graph graph = new GraphImpl(Configuration.USER_HOME + "/graphast/" + nameGraph);
		mapGraph.getMapGraph().put(nameGraph, graph);

		return ResponseEntity.ok(nameGraph);
	}

	@GetMapping
	public ResponseEntity<Integer> sizeMap() {
		return ResponseEntity.ok(mapGraph.getMapGraph().size());
	}
}
