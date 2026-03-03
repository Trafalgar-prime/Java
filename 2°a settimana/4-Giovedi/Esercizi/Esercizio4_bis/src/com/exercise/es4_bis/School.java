package com.exercise.es4_bis;

import java.util.ArrayList;

public class School {

    ArrayList<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
    }

    public void Check(Student student) {
        int count = 0;
        for (int voto : student.getVoto()) {
            if (voto >= 6) {
                count++;
            }
        }
        if (count >= 4) {
            IO.println("Promosso");
        } else {
            IO.println("Bocciato");
        }
    }



    public void Mean(Student student) {
        int somma = 0;
        for (int voto : student.getVoto()) {
            somma += voto;
        }
        if ((somma / student.getVoto().length) >= 8) {
            IO.println("Promosso");
        } else {
            IO.println("Bocciato");
        }
    }


    public void Alternative(Student student) {
        int count = 0;
        for (int voto : student.getVoto()) {
            if (voto >= 6) {
                count++;
            }
        }
        switch (count) {
            case 0, 1 -> IO.println("Bocciato");
            case 2, 3, 4 -> IO.println("Promosso");
        }
    }

}
