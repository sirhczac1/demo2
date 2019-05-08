package com.example.demo.service;

import com.cloudant.client.api.model.Response;
import com.example.demo.model.DemoDao;
import com.example.demo.model.DemoDaoImpl;
import com.example.demo.model.Students;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DemoRepository {

  private DemoDao dao;

  @Autowired
  public DemoRepository(DemoDaoImpl dao) {
    this.dao = dao;
  }

  public List<Students> getStudents() {
    return dao.getStutents();
  }

  public void addStudent(Students student) {
    dao.addStudent(student);
  }

  public Response updateStudent(Students student) {
    return dao.updateStudent(student);
  }

  public Students getStudentByName(String name) {
    return dao.findStudentsByName(name);
  }

}
