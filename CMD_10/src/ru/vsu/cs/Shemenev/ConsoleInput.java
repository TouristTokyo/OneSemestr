package ru.vsu.cs.Shemenev;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ConsoleInput {
    public static @NotNull
    List<Student> persons(String inputFile) {
        int length = 0;
        String[][] matrix = new String[length][3];
        try {
            Scanner scanner = new Scanner(new File(inputFile));
            while (scanner.hasNext()) {
                length++;
                String line = scanner.nextLine();
                String[] array = line.split(", ");
                matrix = Arrays.copyOf(matrix, length);
                matrix[length - 1] = array;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.print("Error");
        }
        return createList(matrix);
    }

    private static List<Student> createList(String[][] matrix) {
        List<Student> list = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            try {
                list.add(new Student(Integer.parseInt(matrix[i][0]), Integer.parseInt(matrix[i][1]), matrix[i][2]));
            } catch (NumberFormatException e){
                System.err.println("Error");
            }
        }
        return list;
    }
}
