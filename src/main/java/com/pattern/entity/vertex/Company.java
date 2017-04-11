package com.pattern.entity.vertex;

import com.pattern.entity.edge.Owns;

import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

public class Company {
    @Getter @Setter
    private String name;

    @Getter @Setter
    private Set<Owns> ownedBy = new HashSet<>();

    @Getter @Setter
    private Set<Owns> owns;

    @Getter @Setter
    private Set<Employee> employees;

    public Company() {}
}