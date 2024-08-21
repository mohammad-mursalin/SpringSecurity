package com.example.SpringSecurity.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {

    private String name;
    private int roll;
    private int mark;
}
