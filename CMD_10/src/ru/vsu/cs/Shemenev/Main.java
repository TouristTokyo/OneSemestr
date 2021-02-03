package ru.vsu.cs.Shemenev;

import java.io.Console;

public class Main {

    public static void main(String[] args) {
        Console cmd = System.console();
        if (cmd != null) {
            String inputFile = cmd.readLine("¬ведите название входного файла: ");
            String outFile = cmd.readLine("¬ведите название выходного файла: ");
            ConsoleOutput.saveOut(ConsoleInput.persons(inputFile),outFile);
        } //CMD
        /*if(args.length!=0){
            String inputFile = args[0];
            String outFile = args[1];
            ConsoleOutput.saveOut(ConsoleInput.persons(inputFile),outFile);
        }*/
    }
}
