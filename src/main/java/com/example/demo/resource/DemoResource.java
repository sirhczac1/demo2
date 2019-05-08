package com.example.demo.resource;

import com.cloudant.client.api.model.Response;
import com.example.demo.model.Students;
import com.example.demo.service.DemoService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/demo")
@RestController
public class DemoResource {

  DemoService service;

  @Autowired
  public void setDemoService(DemoService service) {
    this.service = service;
  }

  @GetMapping
  public List<Students> getStudents() {
    return service.getStudents();
  }

  @PostMapping
  public Students addStudent(@RequestBody Students student) {
    return service.addStudent(student);
  }

  @PutMapping
  public Response updateStudent(@RequestBody Students student) {
    return service.updateStudent(student);
  }

  @GetMapping("/filter")
  public Students getStudentByName(@RequestParam(value = "name", required = false, defaultValue = "") String name) {
    return service.getStudentByName(name);
  }
}

