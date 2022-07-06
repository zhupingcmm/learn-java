package com.mf.spring01;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @Setter
    private String name;
    @Setter
    private int age;
}
