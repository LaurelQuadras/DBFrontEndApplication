package com.cassandralearning.democassandra.controller;

import com.cassandralearning.democassandra.model.StudentBasicInfo;
import com.cassandralearning.democassandra.repository.StudentBasicInfoRepository;
import com.cassandralearning.democassandra.script.PopulateStudentBasicInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class StudentBasicInfoFrontEndController {
    @Autowired
    private PopulateStudentBasicInfo populateStudentBasicInfo;
    @Autowired
    private StudentBasicInfoRepository studentBasicInfoRepository;

    @RequestMapping(value = "/inserting", method = RequestMethod.GET)
    public String inserting(@RequestParam String id, @RequestParam String name, @RequestParam String password,
                            @RequestParam String location, @RequestParam String age, ModelMap modelMap) {
        if (id.isEmpty()) {
            modelMap.put("errormessage","ID cannot be empty");
            return "insertToDB";
        }

        if (name.isEmpty()) {
            modelMap.put("errormessage","Name cannot be empty");
            return "insertToDB";
        }

        if (password.isEmpty()) {
            modelMap.put("errormessage","Password cannot be empty");
            return "insertToDB";
        }

        if (location.isEmpty()) {
            modelMap.put("errormessage","Location cannot be empty");
            return "insertToDB";
        }

        if (age.isEmpty()) {
            modelMap.put("errormessage","Age cannot be empty");
            return "insertToDB";
        }

        StudentBasicInfo studentBasicInfo = new StudentBasicInfo(Integer.parseInt(id), name, password, location, Integer.parseInt(age));
        studentBasicInfoRepository.save(studentBasicInfo);
        modelMap.put("errormessage","User successfully inserted in DB");
        return "insertToDB";
    }

    @RequestMapping(value = "/deleting", method = RequestMethod.GET)
    public String deleting(ModelMap modelMap, @RequestParam String id) {

        if (id.isEmpty()) {
            modelMap.put("errormessage","ID cannot be empty");
            return "deleteDB";
        }

        Optional<StudentBasicInfo> studentBasicInfoPresent = studentBasicInfoRepository.findById(Integer.valueOf(id));
        if (!studentBasicInfoPresent.isPresent()) {
            modelMap.put("errormessage","User not found in DB");
            return "deleteDB";
        }

        studentBasicInfoRepository.deleteById(Integer.valueOf(id));
        modelMap.put("errormessage","User successfully deleted from DB");
        return "deleteDB";
    }

    @RequestMapping(value = "/updating", method = RequestMethod.GET)
    public String updating(@RequestParam String id, @RequestParam String name, @RequestParam String password,
                           @RequestParam String location, @RequestParam String age, ModelMap modelMap) {

        if (id.isEmpty()) {
            modelMap.put("errormessage","ID cannot be empty");
            return "updateToDB";
        }

        if (name.isEmpty()) {
            modelMap.put("errormessage","Name cannot be empty");
            return "updateToDB";
        }

        if (password.isEmpty()) {
            modelMap.put("errormessage","Password cannot be empty");
            return "updateToDB";
        }

        if (location.isEmpty()) {
            modelMap.put("errormessage","Location cannot be empty");
            return "updateToDB";
        }

        if (age.isEmpty()) {
            modelMap.put("errormessage","Age cannot be empty");
            return "updateToDB";
        }

        Optional<StudentBasicInfo> studentBasicInfoPresent =studentBasicInfoRepository.findById(Integer.valueOf(id));
        if (!studentBasicInfoPresent.isPresent()) {
           modelMap.put("errormessage","User not found in DB");
           return "updateToDB";
        }

        StudentBasicInfo studentBasicInfo = new StudentBasicInfo(Integer.parseInt(id),name,password,location,Integer.parseInt(age));
        studentBasicInfoRepository.save(studentBasicInfo);
        modelMap.put("errormessage","User successafully updated in DB");
        return "updateToDB";
    }

    @RequestMapping(value = "/scripting", method = RequestMethod.GET)
    public String scripting(@RequestParam String number, ModelMap modelMap) {

        if (number.isEmpty()) {
            modelMap.put("errormessage","Number cannot be empty");
            return "scriptRun";
        }

        populateStudentBasicInfo.finalRun(Integer.parseInt(number));
        modelMap.put("errormessage","Script ran successfully");
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