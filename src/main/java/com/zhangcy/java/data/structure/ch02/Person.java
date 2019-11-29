package com.zhangcy.java.data.structure.ch02;

/**
 * @author zhangcy
 */
public class Person {

    private String firstName;

    private String lastName;

    private int age;

    public Person(String lastName, String firstName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public void displayPerson() {
        System.out.print("    Last name: " + lastName);
        System.out.print(",   First name: " + firstName);
        System.out.println(",   age: " + age);
    }

    public String getLastName() {
        return this.lastName;
    }
}
