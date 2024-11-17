package com.example.day21lab5q2.Controller;

import com.example.day21lab5q2.ApiResponse.ApiResponse;
import com.example.day21lab5q2.Model.Project;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {

    ArrayList<Project> projects = new ArrayList<>();


    // endpoint to get all the projects
@GetMapping("/get/all")
    public ArrayList<Project> getProjects() {
        return projects;
    }

// endpoint to add a project
    @PostMapping("/add")
    public ApiResponse addProjects(@RequestBody Project project) {
        projects.add(project);
        return new ApiResponse("Project successfully added");
    }

    // endpoint to update a specefic a project by id
    @PutMapping("/update/{id}")
    public ApiResponse updateProjects(@PathVariable String id, @RequestBody Project project) {

        for (Project p : projects) {
            if (p.getId().equals(id)) {

                projects.set(projects.indexOf(p), project);
                return new ApiResponse("Project successfully updated");
            }
        }
        return new ApiResponse("Project not found");

    }

    // endpoint to delete a project
    @DeleteMapping("/delete/{id}")
    public ApiResponse deleteProjects(@PathVariable String id) {
    for (Project p : projects) {
        if (p.getId().equals(id)) {
            projects.remove(p);
            return new ApiResponse("Project successfully deleted");
        }
    }
    return new ApiResponse("Project not found");
    }


    // endpoint to change the project status to Done/Not Done
@PutMapping("/change/status/{id}")
    public ApiResponse changeStatus(@PathVariable String id) {
    for (Project p : projects) {
        if (p.getId().equals(id)) {
            if (p.getStatus().equals("Done")){
                p.setStatus("Not Done");
                return new ApiResponse("Project status successfully updated");
            }else {
                p.setStatus("Done");
                return new ApiResponse("Project status successfully updated");
            }
        }
    }
    return new ApiResponse("Project not found");
    }

    // endpoint to get a project by title
    @GetMapping("/get/title/{title}")
    public Project getProjectbyTitle(@PathVariable String title) {

    for (Project p : projects) {
        if (p.getTitle().equals(title)) {
            return p;
        }
    }
    return null;

    }

    // endpoint to display all projects of same company
    @GetMapping("/get/company/{company}")
    public ArrayList<Project> getProjectByCompany(@PathVariable String company) {
   ArrayList<Project> companyProjects = new ArrayList<>();
    for (Project p : projects) {
        if (p.getCompanyName().equals(company)) {
            companyProjects.add(p);
        }
    }
    return companyProjects;
    }




}
