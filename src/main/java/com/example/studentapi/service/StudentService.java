package com.example.studentapi.service;

import com.example.studentapi.Student;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.annotation.PostConstruct;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.io.*;

@Service
public class StudentService {

    private List<Student> studentList = new ArrayList<>();
    private AtomicInteger idCounter = new AtomicInteger(1); // Auto-increment ID

    //  Load students from file on app start
    @PostConstruct
    public void loadStudentsFromFile() {
        try (Reader reader = new FileReader("students.txt")) {
            Gson gson = new Gson();
            Student[] students = gson.fromJson(reader, Student[].class);
            if (students != null) {
                studentList = new ArrayList<>(Arrays.asList(students));
                // Set idCounter = max ID + 1
                int maxId = (int) studentList.stream()
                        .mapToLong(Student::getId)
                        .max()
                        .orElse(0L);
                idCounter.set(maxId + 1);
            }
        } catch (FileNotFoundException e) {
            System.out.println("students.txt not found. Starting with empty list.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Get all students
    public List<Student> getAllStudents() {
        return studentList;
    }

    // Add new student
    public Student addStudent(Student student) {
        student.setId(idCounter.getAndIncrement());
        processPerformance(student);
        studentList.add(student);
        writeStudentsToFile();
        return student;
    }

    // Delete student
    public boolean deleteStudentById(int id) {
        boolean removed = studentList.removeIf(student -> student.getId() == id);
        if (removed) {
            writeStudentsToFile();
        }
        return removed;
    }

    //  Update student
    public boolean updateStudentById(int id, Student updatedStudent) {
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getId() == id) {
                updatedStudent.setId(id); // retain old ID
                processPerformance(updatedStudent);
                studentList.set(i, updatedStudent);
                writeStudentsToFile();
                return true;
            }
        }
        return false;
    }

    // Search by name
    public List<Student> searchByName(String name) {
        List<Student> result = new ArrayList<>();
        for (Student student : studentList) {
            if (student.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(student);
            }
        }
        return result;
    }

    //  Calculate average and grade
    private void processPerformance(Student student) {
        Map<String, Integer> subjects = student.getSubjects();
        for (int mark : subjects.values()) {
            if (mark < 0 || mark > 100) {
                throw new IllegalArgumentException("Marks must be between 0 and 100");
            }
        }

        double avg = subjects.values().stream().mapToInt(Integer::intValue).average().orElse(0);
        student.setAverage(avg);

        String grade = (avg >= 90) ? "A+" :
                (avg >= 75) ? "A" :
                        (avg >= 60) ? "B" :
                                (avg >= 45) ? "C" : "F";

        student.setGrade(grade);
    }

    //  Write to students.txt
    private void writeStudentsToFile() {
        try (Writer writer = new FileWriter("students.txt")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(studentList, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
