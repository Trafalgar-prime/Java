package com.exercise.es4_bis;

public class Main {
    public static void main(String[] args) {

        Student student = new Student();
        Student student2 = new Student();
        Student student3 = new Student();
        Student student4 = new Student();

        student.setName("John");
        student2.setName("Dave");
        student3.setName("Daniel");
        student4.setName("Steve");

        student.setVoto(new int[] {5,7,9,5});
        student2.setVoto(new int[] {8,7,9,6});
        student3.setVoto(new int[] {8,7,9,8});
        student4.setVoto(new int[] {6,7,6,6});

        School school = new School();

        school.addStudent(student);
        school.addStudent(student2);
        school.addStudent(student3);
        school.addStudent(student4);

        IO.println("Esercizio 1");
        IO.println("Studente1: ");
        school.Check(student);
        IO.println("\nStudente2: ");
        school.Check(student2);
        IO.println("\nStudente3: ");
        school.Check(student3);
        IO.println("\nStudente4: ");
        school.Check(student4);
        IO.println("");


        IO.println("Esercizio 2");
        IO.println("\nStudente1: ");
        school.Mean(student);
        IO.println("\nStudente2: ");
        school.Mean(student2);
        IO.println("\nStudente3: ");
        school.Mean(student3);
        IO.println("\nStudente4: ");
        school.Mean(student4);
        IO.println("");



        IO.println("Esercizio 3");
        IO.println("\nStudente1: ");
        school.Alternative(student);
        IO.println("\nStudente2: ");
        school.Alternative(student2);
        IO.println("\nStudente3: ");
        school.Alternative(student3);
        IO.println("\nStudente4: ");
        school.Alternative(student4);

    }
}
