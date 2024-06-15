package com.rentinhand.rihtracker.controllers;

import com.rentinhand.rihtracker.dto.responses.dashboard.DashboardAllResponse;
import com.rentinhand.rihtracker.services.DashboardService;
import lombok.AllArgsConstructor;
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
