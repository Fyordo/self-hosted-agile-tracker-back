package com.fyordo.shatback.controllers;

import com.fyordo.shatback.dto.responses.dashboard.DashboardAllResponse;
import com.fyordo.shatback.services.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dashboard")
public class DashboardController extends BaseController{
    private final DashboardService dashboardService;

    @GetMapping("/{projectId}")
    public ResponseEntity<DashboardAllResponse> getAll(@PathVariable Long projectId) {

        return ResponseEntity.ok(dashboardService.getAll(projectId));
    }
}
