package DesignPatterns.Creational;

//Creation design pattern is used to create object or controls of creations of objects


//Its used when we need a copy of existing object

interface PrototypeClone {
    PrototypeClone cloneStudent();
}

class Student implements PrototypeClone {
    int age;
    private int rollNo;
    String name;

    Student() {}

    Student(int age, int rollNo, String name) {
        this.age = age;
        this.rollNo = rollNo;
        this.name = name;
    }

    @Override
    public PrototypeClone cloneStudent()  {
        return new Student(age, rollNo, name);
    }
}

public class Prototype {
    public static void main(String[] args) {
        Student student = new Student(12, 200, "Nishi");

        Student student1 = (Student) student.cloneStudent();

        System.out.println(student.equals(student1));
    }
}
