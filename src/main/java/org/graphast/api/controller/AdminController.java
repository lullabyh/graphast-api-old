package org.graphast.api.controller;

import java.util.List;
import java.util.Map;

import org.graphast.app.GraphInfo;
import org.graphast.app.GraphService;
import org.graphast.config.Configuration;
import org.graphast.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {
	
	private GraphService service = new GraphService();
	
	@PostMapping("/create")
	public GraphInfo create(@RequestParam Map<String, String> params) {
		System.out.println("Create");
		GraphInfo graphInfo = new GraphInfo();
		graphInfo.setAppName(params.get("app"));
		graphInfo.setCosts(params.get("costs"));
		graphInfo.setGraphDir(params.get("dir"));
		graphInfo.setImporter(params.get("importer"));
		graphInfo.setNetwork(params.get("network"));
		graphInfo.setPoiCategoryFilter(StringUtils.splitIntToList(",", params.get("poi-category-filter")));
		graphInfo.setPois(params.get("pois"));
		graphInfo.setQueryServices(params.get("query-services"));
		service.create(graphInfo);
		return graphInfo;
	}
	
	@GetMapping("/load/{app}")
	public GraphInfo load(@PathVariable String app) {
		return service.load(app);
	}
	
	@GetMapping("/apps")
	public List<String> appNames() {
		return Configuration.getAppNames();
	}
}
