package com.example.demo.service;

import com.cloudant.client.api.model.Response;
import com.example.demo.model.Students;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemoService {

  DemoRepository repository;

  @Autowired
  public void setDemoRepository(DemoRepository repository) {
    this.repository = repository;
  }

  public List<Students> getStudents() {
    return repository.getStudents();
  }

  public Students addStudent(Students student) {
    repository.addStudent(student);
    return student;
  }

  public Response updateStudent(Students student) {
    return repository.updateStudent(student);
  }

  public Students getStudentByName(String name) {
    return repository.getStudentByName(name);
  }

}
