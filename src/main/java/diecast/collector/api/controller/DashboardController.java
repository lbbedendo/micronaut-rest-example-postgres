package diecast.collector.api.controller;

import diecast.collector.api.dto.response.ModelsDashboardResponse;
import diecast.collector.api.service.DashboardService;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller("/dashboard")
public class DashboardController {
    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @Get("/charts/models")
    public ModelsDashboardResponse loadModelQuantityByAutomakers() {
        return dashboardService.getModelsDashboardResponse();
    }
}
