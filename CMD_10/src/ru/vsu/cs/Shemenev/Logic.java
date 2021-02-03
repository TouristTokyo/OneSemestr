package ru.vsu.cs.Shemenev;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Logic {
    public static List<Student> sortStudents(List<Student> persons){
        Collections.sort(persons, new Comparator<Student>() {
            @Override
            public int compare(Student st1, Student st2) {
                int res = st1.numCourse - st2.numCourse;
                if(res==0){
                    res = st1.numGroup- st2.numGroup;
                }
                if(res ==0){
                    res = st1.name.compareTo(st2.name);
                }
                return res;
            }
        });
        return persons;
    }
}
