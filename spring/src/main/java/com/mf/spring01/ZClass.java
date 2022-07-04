package com.mf.spring01;

import lombok.Data;

import java.util.List;

@Data
public class ZClass {
    private List<Student> students;

    public void dong () {
        System.out.println(this.getStudents());
    }
}
