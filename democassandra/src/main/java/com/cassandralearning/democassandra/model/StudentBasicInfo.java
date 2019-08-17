package com.cassandralearning.democassandra.model;

import lombok.*;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Data
@ToString
@Builder
@Table(value = "student_basic_info")
@Getter
@Setter
public class StudentBasicInfo {

    @PrimaryKey
    private int id;

    private String name;

    private String password;

    private String location;

    private int age;

    public StudentBasicInfo() {}

    public StudentBasicInfo(int id, String name, String password, String location, int age) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.location = location;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getLocation() {
        return location;
    }

    public int getAge() {
        return age;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
