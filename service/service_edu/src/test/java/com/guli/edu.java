package com.guli;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@SpringBootTest
public class edu {


    Employee employee = new Employee();
    List<Employee> empList = Arrays.asList(
            new Employee("张三", 88, 111.1, Employee.Status.FREE),
            new Employee("李四", 88, 222.2, Employee.Status.FREE),
            new Employee("王五", 22, 333.3, Employee.Status.VOCATION),
            new Employee("赵六", 33, 444.4, Employee.Status.FREE),
            new Employee("田七", 55, 555.5, Employee.Status.BUSY)
    );

    @Test
    public void testMap(){
        List<String> stringList = Arrays.asList("a", "b", "c", "d");
        stringList.stream()
                .map(e->e.toUpperCase())
                .forEach(System.out::println);
    }

    @Test
    public void testEntityMethod(){
        Employee jack = new Employee("jack", 18, 998.0, Employee.Status.VOCATION);

        Supplier<String > supplier = Employee::getNation;
        Function<Employee, String> function= Employee::getName;
        System.out.println("====supplier===="+supplier);
        System.out.println("====supplier.get()===="+supplier.get());

        System.out.println("===function==="+function);
        System.out.println("====function.apply(jack)===="+function.apply(jack));

    }


    @Test
    public void testCollectionSort(){
        List<Integer> list = new ArrayList<>();
        List<Integer> integers = Arrays.asList(1,2,3);
        list.addAll(integers);
        System.out.println(list);

    }

    @Test
     public void testStream(){
        Optional<Employee> max = empList.stream()
                .max((e1, e2) -> {
                    if (e1.getAge() == e2.getAge()) {
                        return e1.getName().compareTo(e2.getName());
                    }
                    return e1.getAge().compareTo(e2.getAge());
                });
        System.out.println("===max employee==="+max.get());

        Optional<Double> max1 = empList.stream()
                .map(Employee::getSalary)
                .max(Double::compareTo);
        System.out.println("===max1 employee==="+max1.get());
    }
    @Test
    public void testReduce(){

        Optional<Double> reduce = empList.stream()
                .map(Employee::getSalary)
                .reduce(Double::sum);
        System.out.println(reduce.get());

    }
    @Test
    public void testCollect(){
        Map<String, Employee.Status> collect = empList.stream()
//                .map(e->e.getName())
//                .collect(Collectors.toList());
                .collect(Collectors.toMap(Employee::getName, Employee::getStatus));

        collect.forEach(System.out::printf);
    }
}

