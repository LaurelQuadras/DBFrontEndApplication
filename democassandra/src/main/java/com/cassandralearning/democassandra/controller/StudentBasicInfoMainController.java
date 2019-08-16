package com.cassandralearning.democassandra.controller;

import com.cassandralearning.democassandra.model.StudentBasicInfo;
import com.cassandralearning.democassandra.repository.StudentBasicInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class StudentBasicInfoMainController {

    @Autowired
    private StudentBasicInfoRepository studentBasicInfoRepository;

    @RequestMapping(value = "/")
    public String mainpage() {
        return "mainpage";
    }

    @RequestMapping(value = "/viewpage")
    public String viewPage() {
        return "viewPage";
    }

    @RequestMapping(value = "/insertingToDB")
    public String insertingToDB() {
        return "insertToDB";
    }

    @RequestMapping(value = "/updatingToDB")
    public String updatingToDB() {
        return "updateToDB";
    }

    @RequestMapping(value = "/deletingToDB")
    public String deletingToDB() {
        return "deleteDB";
    }

    @RequestMapping(value = "/scriptingToDB")
    public String scriptingToDB() {
        return "scriptRun";
    }

    @RequestMapping(value = "/viewing")
    public String viewing(ModelMap modelMap, @RequestParam String id) {

        if (id.isEmpty()) {
            modelMap.put("errormessage","ID cannot be empty");
            return "viewPage";
        }

        Optional<StudentBasicInfo> studentBasicInfoOptional = studentBasicInfoRepository.findById(Integer.valueOf(id));
        if (!studentBasicInfoOptional.isPresent()) {
            modelMap.put("errormessage","User not present in DB");
            return "viewPage";
        }

        StudentBasicInfo studentBasicInfo = studentBasicInfoOptional.get();
        modelMap.put("name",studentBasicInfo.getName());
        modelMap.put("password",studentBasicInfo.getPassword());
        modelMap.put("location",studentBasicInfo.getLocation());
        modelMap.put("age",studentBasicInfo.getAge());
        modelMap.put("errormessage","User data presented");
        return "viewPage";
    }

}
