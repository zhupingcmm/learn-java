package com.op;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@PropertySource(value = "person.properties", encoding = "UTF-8")
public class Person {

    private String id;

    private String name;

    private int age;

    private boolean isManager;

    private Date birthday;

    private Map<String, Object> map;

    private List<String> list;

    private Address address;

}
