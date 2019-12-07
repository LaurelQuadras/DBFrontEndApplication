package com.cassandralearning.democassandra.repository;

import com.cassandralearning.democassandra.model.TeacherInfo;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface TeacherInfoRepository extends CassandraRepository<TeacherInfo, Integer> {
}
