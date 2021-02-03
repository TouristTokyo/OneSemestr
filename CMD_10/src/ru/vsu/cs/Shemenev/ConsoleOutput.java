package ru.vsu.cs.Shemenev;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ConsoleOutput {
    public static void saveOut(List<Student> persons, String outFile) {
        try {
            File file = new File(outFile);
            if (!file.exists()) {
                file.createNewFile();
            }
            PrintWriter write = new PrintWriter(file);
            List<Student> students = Logic.sortStudents(persons);
            write.println("Номер курса:| Номер группы:| ФИО:");
            for (int i = 0; i < students.size(); i++) {
                write.println("     " + students.get(i).numCourse + "               "  + students.get(i).numGroup +
                        "      "+ students.get(i).name);
            }
            write.close();
        } catch (IOException e) {
            System.err.println("Error");
        }
    }
}
