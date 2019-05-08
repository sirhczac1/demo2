package com.example.demo.model;

import com.cloudant.client.api.model.Response;

import java.util.List;

public interface DemoDao {

  List<Students> getStutents();

  void addStudent(Students student);

  Response updateStudent(Students student);

  Students findStudentsByName(String name);

}
