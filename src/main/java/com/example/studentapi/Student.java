package com.example.studentapi;

import java.util.Map;

public class Student {
    private long id;  // instead of int

    private String name;
    private String email;
    private Map<String, Integer> subjects;
    private double average;
    private String grade;

    public Student() {}

    public Student(int id, String name, String email, Map<String, Integer> subjects) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.subjects = subjects;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Map<String, Integer> getSubjects() {
        return subjects;
    }

    public void setSubjects(Map<String, Integer> subjects) {
        this.subjects = subjects;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
