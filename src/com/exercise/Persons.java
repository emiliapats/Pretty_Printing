package com.exercise;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rpatil on 10/11/15.
 */
public class Persons {
    List<Person> entries = new ArrayList<Person>();
    List<Integer> errors = new ArrayList<Integer>();

    public List<Person> getEntries() {
        return entries;
    }

    public List<Integer> getErrors() {
        return errors;
    }

    public void setEntries(List<Person> entries) {
        this.entries = entries;
    }

    public void setErrors(List<Integer> errors) {
        this.errors = errors;
    }

    @Override
    public String toString() {
        return "Persons{" +
                "entries=" + entries +
                "\n, errors=" + errors +
                '}';
    }
}
