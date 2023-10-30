package DesignPatterns;

public class MethodChainingDemo {
    public static void main(String args[])
    {
        Student student1 = new Student();
        Student student2 = new Student();

        student1.setId(1)
                .setName(" Ram & quot")
                .setAddress(" Noida & quot");

        student2.setId(2)
                .setName(" Shyam & quot")
                .setAddress(" Delhi & quot");

        System.out.println(student1);
        System.out.println(student2);
    }
}


