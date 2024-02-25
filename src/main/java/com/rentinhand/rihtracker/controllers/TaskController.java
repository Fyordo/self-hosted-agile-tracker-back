package com.rentinhand.rihtracker.controllers;

import com.rentinhand.rihtracker.dto.requests.project.ProjectDataRequest;
import com.rentinhand.rihtracker.dto.responses.ListResponse;
import com.rentinhand.rihtracker.dto.responses.project.ProjectDetailResponse;
import com.rentinhand.rihtracker.dto.responses.project.ProjectResponse;
import com.rentinhand.rihtracker.dto.responses.scrumColumn.ScrumColumnResponse;
import com.rentinhand.rihtracker.entities.Project;
import com.rentinhand.rihtracker.entities.ScrumColumn;
import com.rentinhand.rihtracker.exceptions.ModelNotFoundException;
import com.rentinhand.rihtracker.services.ProjectService;
import com.rentinhand.rihtracker.services.TaskService;
import com.rentinhand.rihtracker.utilities.AuthorityAnnotations.Authorized;
import com.rentinhand.rihtracker.utilities.AuthorityAnnotations.DirectorAuth;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/project/{projectId}/task")
@Authorized
public class TaskController extends BaseController {
    private final ProjectService projectService;
    private final TaskService taskService;

    @GetMapping()
    public ResponseEntity<ListResponse<ScrumColumnResponse>> getTasksWithColumn(
            @PathVariable Long projectId
    ){
        List<ScrumColumn> columns = taskService.getTasksByColumns(
                projectService.findById(projectId).orElseThrow(ModelNotFoundException::new)
        );

        List<ScrumColumnResponse> mappedResult = columns.stream().map((element) -> mapper.map(element, ScrumColumnResponse.class)).toList();
        return ResponseEntity.ok(new ListResponse<>(mappedResult));
    }
}
