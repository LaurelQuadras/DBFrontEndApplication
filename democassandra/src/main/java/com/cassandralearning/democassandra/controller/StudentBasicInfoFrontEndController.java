package com.cassandralearning.democassandra.controller;

import com.cassandralearning.democassandra.model.StudentBasicInfo;
import com.cassandralearning.democassandra.repository.StudentBasicInfoRepository;
import com.cassandralearning.democassandra.script.PopulateStudentBasicInfo;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class StudentBasicInfoFrontEndController {
    @Autowired
    private PopulateStudentBasicInfo populateStudentBasicInfo;
    @Autowired
    private StudentBasicInfoRepository studentBasicInfoRepository;

    private String GREATER_THAN = ">";
    private String LESSER_THAN = "<";
    private String EQUALS_TO = "=";
    private List<String> SYMBOLS = Arrays.asList(GREATER_THAN, LESSER_THAN, EQUALS_TO);

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

    @RequestMapping(value = "/ageFilter")
    public String ageFilter(ModelMap modelMap, @RequestParam String age, @RequestParam String symbol) {
        if (Strings.isNullOrEmpty(age)) {
            modelMap.put("errormessage", "Age cannot be empty");
            return "ageFilter";
        }

        if (Strings.isNullOrEmpty(symbol) || !SYMBOLS.contains(symbol)) {
            modelMap.put("errormessage", "Symbol can only be '<' or '>' or '='.");
            return "ageFilter";
        }

        List<StudentBasicInfo> listOfAllStudents = studentBasicInfoRepository.findAll();
        List<String> filteredNamesList = new ArrayList<>();

        if (symbol.equals(GREATER_THAN)) {
            filteredNamesList = listOfAllStudents.stream()
                    .filter(student -> student.getAge() > Integer.parseInt(age))
                    .map(StudentBasicInfo::getName)
                    .collect(Collectors.toList());
        }

        else if (symbol.equals(LESSER_THAN)) {
            filteredNamesList = listOfAllStudents.stream()
                    .filter(student -> student.getAge() < Integer.parseInt(age))
                    .map(StudentBasicInfo::getName)
                    .collect(Collectors.toList());
        }

        else {
            filteredNamesList = listOfAllStudents.stream()
                    .filter(student -> student.getAge() == Integer.parseInt(age))
                    .map(StudentBasicInfo::getName)
                    .collect(Collectors.toList());
        }

        modelMap.put("errormessage", "The students are "+filteredNamesList);
        return "ageFilter";
    }
}