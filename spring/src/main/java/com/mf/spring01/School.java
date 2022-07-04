package com.mf.spring01;

import com.mf.aop.ISchool;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Data
//@Component
public class School implements ISchool {

    @Autowired
    private ZClass zClass;

    @Resource(name = "student100")
    private Student student100;

    @Override
    public void ding() {
        System.out.println("Class1 have " + this.zClass.getStudents().size() + " students and one is " + this.student100);
    }
}
