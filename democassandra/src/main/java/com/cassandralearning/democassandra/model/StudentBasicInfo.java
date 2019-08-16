package com.cassandralearning.democassandra.model;

import lombok.*;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder
@Table(value = "student_basic_info")
public class StudentBasicInfo {

    @PrimaryKey
    private int id;

    private String name;

    private String password;

    private String location;

    private int age;
}
