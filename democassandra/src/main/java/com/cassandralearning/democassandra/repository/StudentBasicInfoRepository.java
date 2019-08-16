package com.cassandralearning.democassandra.repository;

import com.cassandralearning.democassandra.model.StudentBasicInfo;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import java.util.List;

public interface StudentBasicInfoRepository extends CassandraRepository<StudentBasicInfo, Integer> {

    @Query(allowFiltering = true)
    List<StudentBasicInfo> findByIdGreaterThan(int age);
}
