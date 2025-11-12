package th.ac.tu.cs.projectportal.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import th.ac.tu.cs.projectportal.entity.Project;
import th.ac.tu.cs.projectportal.service.ProjectService;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")  
public class ProjectController {

    private final ProjectService projectService;

    // ✅ เพิ่มข้อมูลโครงงาน
    @PostMapping
    public Project createProject(@RequestBody Project project) {
        return projectService.saveProject(project);
    }

    // ✅ ดึงข้อมูลโครงงานทั้งหมด
    @GetMapping
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }
}
