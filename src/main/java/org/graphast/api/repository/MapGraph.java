package org.graphast.api.repository;

import java.util.HashMap;
import java.util.Map;

import org.graphast.model.Graph;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope
public class MapGraph {
	
	private Map<String, Graph> mapGraph;
	
	public MapGraph() {
		this.mapGraph = new HashMap<String, Graph>();
	}
	
	public Map<String, Graph> getMapGraph() {
		return mapGraph;
	}
	
	public void setMapGraph(Map<String, Graph> mapGraph) {
		this.mapGraph = mapGraph;
	}
}
