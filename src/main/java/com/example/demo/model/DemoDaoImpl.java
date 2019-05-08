package com.example.demo.model;

import com.cloudant.client.api.Database;
import com.cloudant.client.api.Search;
import com.cloudant.client.api.model.Response;
import com.cloudant.client.api.views.Key;
import com.cloudant.client.api.views.Key.ComplexKey;
import com.cloudant.client.api.views.ViewRequest;
import com.cloudant.client.api.views.ViewRequestBuilder;
import com.cloudant.client.api.views.ViewResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DemoDaoImpl implements DemoDao {
  private Database db;
  private ObjectMapper mapper;

  @Autowired
  public DemoDaoImpl(Database db) {
    this.db = db;
    mapper = new ObjectMapper();
  }

  @Override
  public List<Students> getStutents() {
    List<Students> students = new ArrayList<>();
    try {
      ViewResponse<ComplexKey, String> view = getView("studentsView");
      view.getRows().stream().forEach(student -> {
        try {
          students.add(mapper.readValue(student.getDocumentAsType(JsonObject.class).toString(), Students.class));
        } catch (IOException e) {
          e.printStackTrace();
        }
      });
    } catch (IOException e) {
      e.printStackTrace();
    }
    return students;
  }

  private ViewResponse<ComplexKey, String> getView(String viewName) throws IOException {
    ViewResponse<ComplexKey, String> response = null;
    ViewRequestBuilder viewBuilder = db.getViewRequestBuilder("searchIndex", viewName);
    if (viewBuilder != null) {
      ViewRequest<ComplexKey, String> request =
          viewBuilder.newRequest(Key.Type.COMPLEX, String.class).includeDocs(true).limit(200).build();
      response = request != null ? request.getResponse() : null;
    }
    return response;
  }

  @Override
  public void addStudent(Students student) {
    db.save(student);
  }

  @Override
  public Response updateStudent(Students student) {
    return db.update(student);
  }

  @Override
  public Students findStudentsByName(String name) {
    Students student = null;
    List<JsonObject> students = null;
    Search search = db.search("searchIndex/getStudentsByName").includeDocs(true).limit(200);
    students = search.query(String.format("name:'%s'", name), JsonObject.class);
    try {
      student = mapper.readValue(students.get(0).toString(), Students.class);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return student;
  }

}
