package com.org.stream;

import com.org.domain.Student;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by admin on 2018/7/15.
 */
public class MethodOfStream {
    List<Student> students = Student.getStudentlist();

    @Test
    public void testforeach() {
        students.stream()
                .filter(student -> student.getSex().equals("G"))
                .forEach(student -> System.out.println(student));
    }


    @Test
    public void testmap() {
        students.forEach(s -> System.out.println(s));
        System.out.println("----------------------------");
        students.stream()
                .filter(student -> student.getSex().equals("G"))
                .map((Student student) -> {
                    student.setSex("Woman");
                    return student;
                })
                .forEach(student -> System.out.println(student));
    }

    @Test
    public void testcount() {
        long count = students.stream()
                .filter(student -> student.getSex().equals("G"))
                .map((Student student) -> {
                    student.setSex("Woman");
                    return student;
                })
                .count();
        System.out.println(count);
    }

    @Test
    public void testimplement() {
        new Thread(() -> System.out.println("线程启动---")).start();
    }

    @Test
    public void testReduce() {
        List<Integer> num = Arrays.asList(100, 200, 300, 200, 400, 900, 500);
        Optional accresult = num.stream()
                .reduce((acc, itm) -> {
                    System.out.println("acc: " + acc);
                    acc += itm;
                    System.out.println("item: " + itm);
                    System.out.println("acc: " + acc);
                    System.out.println("------------");
                    return acc;
                });
        System.out.println("***********************");
        System.out.println("accresult: " + accresult.get());

    }

    @Test
    public void testReduce1() {
        int accResult = Stream.of(1, 4, 32, 98)
                .reduce(0, (acc, it) -> {
                    System.out.println("acc: " + acc);
                    acc += it;
                    System.out.println("it: " + it);
                    System.out.println("acc: " + acc);
                    System.out.println("-----------");
                    return acc;
                });
        System.out.println("**********************");
        System.out.println("accresult: " + accResult);
    }

    @Test
    public void testReduce2() {
        ArrayList<Integer> accresult = Stream.of(1, 3, 4, 3, 9)
                .reduce(new ArrayList<Integer>(),
                        new BiFunction<ArrayList<Integer>, Integer, ArrayList<Integer>>() {
                            @Override
                            public ArrayList<Integer> apply(ArrayList<Integer> integers, Integer integer) {
                                integers.add(integer);
                                System.out.println("integer: " + integer);
                                System.out.println("acc: " + integers);
                                System.out.println("Bifunction");
                                return integers;
                            }
                        }, new BinaryOperator<ArrayList<Integer>>() {
                            @Override
                            public ArrayList<Integer> apply(ArrayList<Integer> integers, ArrayList<Integer> integers2) {
                                System.out.println("BinaryOperator: ");
                                integers.addAll(integers2);
                                System.out.println("integers2: " + integers);
                                System.out.println("integers: " + integers);
                                System.out.println("-----------");
                                return integers;
                            }
                        });
        System.out.println("*****************");
        System.out.println("accresult: " + accresult);

    }


}
