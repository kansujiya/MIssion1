package DesignPatterns.Creational;

//Separates the construction of a complex object from its representation, allowing the same construction process to create different representations.
// Commonly used for creating complex objects with a clear API.

//A way to create object step by step

import java.util.ArrayList;
import java.util.List;

//Ultimate object that we want to create
class Students {
    int rollNo;
    String name;
    List<String> subjects;

    public Students(StudentBuilder builder) {
        this.rollNo = builder.rollNo;
        this.name = builder.name;
        this.subjects = builder.subjects;
    }

    public String toString() {
        return ""+ "roll no: "+ rollNo + ", name: " + name + ", subjects: " + subjects.get(0);
    }
}


abstract class StudentBuilder {
    int rollNo;
    String name;
    List<String> subjects;

    public StudentBuilder setRollNo(int rollNo) {
        this.rollNo = rollNo;
        return this;
    }

    public StudentBuilder setName(String name) {
        this.name = name;
        return this;
    }

    abstract public StudentBuilder setSubject();

    public Students build() {
        return new Students(this);
    }
}

class EngineeringStudent extends StudentBuilder {

    @Override
    public StudentBuilder setSubject() {
        List<String> subs = new ArrayList<>();
        subs.add("Computer science");
        this.subjects = subs;
        return this;
    }
}

class MBAStudent extends StudentBuilder {

    @Override
    public StudentBuilder setSubject() {
        List<String> subs = new ArrayList<>();
        subs.add("HR");
        this.subjects = subs;
        return this;
    }
}


class Director {
    StudentBuilder studentBuilder;

    Director(StudentBuilder studentBuilder) {
        this.studentBuilder = studentBuilder;
    }

    public Students createStudent() {
        if(studentBuilder instanceof EngineeringStudent) {
            return createEngineeringStudent();
        } else if (studentBuilder instanceof MBAStudent) {
            return createMBAStudent();
        } else {
            return null;
        }
    }

    private Students createEngineeringStudent() {
        return studentBuilder.setName("Suresh").setRollNo(101).setSubject().build();
    }

    private Students createMBAStudent() {
        return studentBuilder.setName("Ashish").setRollNo(105).setSubject().build();
    }
}

public class Builder {
    public static void main(String[] args) {
        Director director1 = new Director(new EngineeringStudent());
        Director director2 = new Director(new MBAStudent());

        Students engineeringStudent = director1.createStudent();
        Students mbaStudent = director2.createStudent();

        System.out.println(engineeringStudent.toString());
        System.out.println(mbaStudent.toString());
    }
}
