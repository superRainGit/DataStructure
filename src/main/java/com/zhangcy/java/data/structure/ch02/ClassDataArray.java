package com.zhangcy.java.data.structure.ch02;

/**
 * @author zhangcy
 */
public class ClassDataArray {

    private Person[] person;

    private int size;

    public ClassDataArray(int maxSize) {
        this.person = new Person[maxSize];
        size = 0;
    }

    public Person find(String lastName) {
        for (int i = 0; i < size; i++) {
            if(person[i].getLastName().equals(lastName)) {
                return person[i];
            }
        }
        return null;
    }

    public void inset(Person p) {
        person[size] = p;
        size++;
    }

    public boolean delete(String lastName) {
        int i;
        for (i = 0; i < size; i++) {
            if(person[i].getLastName().equals(lastName)) {
                break;
            }
        }
        if(i == size) {
            return false;
        } else {
            System.arraycopy(person, i + 1, person, 1, size - 1 - i);
            person[size] = null;
            size--;
            return true;
        }
    }

    public void display() {
        for (int i = 0; i < size; i++) {
            person[i].displayPerson();
        }
    }
}
