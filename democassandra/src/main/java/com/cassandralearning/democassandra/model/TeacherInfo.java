package com.cassandralearning.democassandra.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Data
@ToString
@Table(value = "teacher_info")
@Builder
public class TeacherInfo {

    @PrimaryKey
    private int id;
    private int age;
    private String first_name;
    private String last_name;
    private String gender;
    private boolean masters;
    private String password;

    public TeacherInfo(int id, int age, String first_name, String last_name, String gender, boolean masters, String password) {
        this.id = id;
        this.age = age;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.masters = masters;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean isMasters() {
        return masters;
    }

    public void setMasters(boolean masters) {
        this.masters = masters;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
