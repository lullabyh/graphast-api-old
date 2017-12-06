package org.graphast.api.controller;

import org.graphast.api.repository.MapGraph;
import org.graphast.model.Graph;
import org.graphast.model.NodeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("node")
public class NodeGenerator {

	@Autowired
	private MapGraph mapGraph;

	@PostMapping
	public ResponseEntity<String> createNode(@RequestParam("nameGraph") String name, @RequestBody NodeImpl nodeImpl) {
		System.out.println("Latitude: " + nodeImpl.getLatitude() + " Longitude: " + nodeImpl.getLongitude());
		mapGraph.getMapGraph().get(name).addNode(nodeImpl);
		return ResponseEntity.ok("Node created");
	}

	@GetMapping
	public ResponseEntity<int[][]> getNode(@RequestParam("nameGraph") String nameGraph) {
		Graph graph = mapGraph.getMapGraph().get(nameGraph);
		return ResponseEntity.ok(graph.getNodes().elements());
	}
}
