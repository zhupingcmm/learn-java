package com.mf.spring01;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class ZClass {
    private List<Student> students;

    public void dong () {
        System.out.println(this.getStudents());
    }
}
