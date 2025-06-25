package com.example.studentapi.controller;

import com.example.studentapi.Student;
import com.example.studentapi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/students")

public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping
    public String createStudent(@RequestBody Student student) {
        studentService.addStudent(student);
        return "Student added successfully!";
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable int id) {
        boolean removed = studentService.deleteStudentById(id);
        return removed ? "Student deleted successfully!" : "Student not found!";
    }

    @PutMapping("/{id}")
    public String updateStudent(@PathVariable int id, @RequestBody Student updatedStudent) {
        boolean updated = studentService.updateStudentById(id, updatedStudent);
        return updated ? "Student updated successfully!" : "Student not found!";
    }
    @GetMapping("/students/search")
    public List<Student> searchStudent(@RequestParam String name) {
        return studentService.searchByName(name);
    }
}
