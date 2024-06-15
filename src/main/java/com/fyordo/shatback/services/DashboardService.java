package com.fyordo.shatback.services;

import com.fyordo.shatback.dto.responses.dashboard.DashboardAllResponse;

public interface DashboardService {
    DashboardAllResponse getAll(Long projectId);
}
