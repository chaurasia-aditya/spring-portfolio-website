package com.chauri.portfolio.entity;


import jakarta.persistence.*;

@Entity
public class Role {

    @Id
    @Column(name="id")
    private Integer id;

    @Column(name="name")
    private String name;

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
