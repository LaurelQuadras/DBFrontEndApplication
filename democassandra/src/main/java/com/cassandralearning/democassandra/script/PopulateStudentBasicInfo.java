
package com.cassandralearning.democassandra.script;

import com.cassandralearning.democassandra.model.StudentBasicInfo;
import com.cassandralearning.democassandra.repository.StudentBasicInfoRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PopulateStudentBasicInfo {

    @Autowired
    private  StudentBasicInfoRepository studentBasicInfoRepository;

    private  Faker faker = new Faker();

    public void scriptRun()  {
        int id = faker.number().numberBetween(100,1000);
        String name = faker.name().firstName();
        String password = faker.phoneNumber().phoneNumber();
        String location = faker.gameOfThrones().city();
        int age = faker.number().numberBetween(20,50);

        StudentBasicInfo studentBasicInfo = new StudentBasicInfo(id,name,password,location,age);
        studentBasicInfoRepository.save(studentBasicInfo);
    }

    public void finalRun(int n) {
        for (int i=0; i< n; i++) {
            scriptRun();
        }
    }
}
