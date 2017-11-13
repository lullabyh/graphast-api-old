package org.graphast.api.controller;

import org.graphast.app.AppGraph;
import org.graphast.config.Configuration;
import org.graphast.model.Graph;
import org.graphast.query.route.shortestpath.AbstractShortestPathService;
import org.graphast.query.route.shortestpath.dijkstra.DijkstraConstantWeight;
import org.graphast.query.route.shortestpath.model.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shortestPath")
public class ShortestPathController {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	
	public ResponseEntity<String> test(String queryParam) {
		
		log.info(queryParam);
		Configuration.setSelectedApp(queryParam);
		return ResponseEntity.ok("App selecionado" + queryParam);
	}
	
	@GetMapping
	public ResponseEntity<Path> shortestPath(Double lat1, Double long1, Double lat2, Double long2) {
		
		log.info("Shortest Path use Dijkstra ");
		Graph graph = AppGraph.getGraph();
		
		log.info("get graph selected ");
		
		AbstractShortestPathService sp = new DijkstraConstantWeight(graph);
		
		log.info("Calculating the path ");
		long source = graph.getNodeId(lat1, long1);
		long target = graph.getNodeId(lat2, long2);
		Path path = sp.shortestPath(source, target);
		log.info("Calculo terminado...");
		return ResponseEntity.ok(path);
	}
}
