package com.pattern.entity.vertex;

import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

public class Employee {
    @Getter @Setter
    private String name;

    @Getter @Setter
    private Set<Company> employers = new HashSet<>();

    public Employee() {}
}