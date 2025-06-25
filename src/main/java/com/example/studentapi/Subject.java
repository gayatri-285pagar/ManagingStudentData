//package com.example.studentapi;
//
//public class Subject {
//    private String name;
//    private String code;
//
//    // Constructor
//    public Subject() {}
//
//    public Subject(String name, String code) {
//        this.name = name;
//        this.code = code;
//    }
//
//    // Getters and Setters
//    public String getName() { return name; }
//    public void setName(String name) { this.name = name; }
//
//    public String getCode() { return code; }
//    public void setCode(String code) { this.code = code; }
//}


package com.example.studentapi;

public class Subject {
    private String subjectName;
    private int marks;

    // Getters and setters
    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }
}
