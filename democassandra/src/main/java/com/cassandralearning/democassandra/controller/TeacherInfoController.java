package com.cassandralearning.democassandra.controller;

import com.cassandralearning.democassandra.model.TeacherInfo;
import com.cassandralearning.democassandra.repository.TeacherInfoRepository;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class TeacherInfoController {

    @Autowired
    private TeacherInfoRepository teacherInfoRepository;

    private Logger log = LoggerFactory.getLogger(TeacherInfoRepository.class);

    @RequestMapping(value = "/signin")
    private String signinPage() {
        return "signinPage";
    }

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    private String signinPageValidation(ModelMap modelMap, @RequestParam String id, @RequestParam String firstname, @RequestParam String password) {

        if (Strings.isNullOrEmpty(id)) {
            modelMap.put("errormessage", "ID cannot be empty");
            return "signinPage";
        }

        if (Strings.isNullOrEmpty(firstname)) {
            modelMap.put("errormessage", "First Name cannot be empty");
            return "signinPage";
        }

        if (Strings.isNullOrEmpty(password)) {
            modelMap.put("errormessage", "Password cannot be empty");
            return "signinPage";
        }

        Optional<TeacherInfo> teacherFound = teacherInfoRepository.findById(Integer.parseInt(id));

        if (!teacherFound.isPresent()) {
            modelMap.put("errormessage", "Login Credentials not valid");
            return "signinPage";
        }

        TeacherInfo teacherInfo = teacherFound.get();

        if (teacherInfo.getFirst_name().equals(firstname) && teacherInfo.getPassword().equals(password)) {
            log.info("User {} is verifies ", teacherInfo.getFirst_name());
            return "mainPage";
        }

        modelMap.put("errormessage", "First Name or Password is Invalid");
        return "signinPage";
    }
}
