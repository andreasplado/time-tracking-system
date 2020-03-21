package com.logines.schedule.controller;

import com.logines.schedule.model.Class;
import com.logines.schedule.model.Job;
import com.logines.schedule.model.UserProfile;
import com.logines.schedule.service.ClassService;
import com.logines.schedule.service.JobService;
import com.logines.schedule.service.StudentService;
import com.logines.schedule.service.UserProfileService;
import com.logines.schedule.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class ScheduleController {

    @Autowired
    private ClassService classService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private UserProfileService userProfileService;
    @Autowired
    private JobService jobService;
    @Autowired
    private AuthenticationManager authenticationManager;

    //@RequestMapping(value="/",method = RequestMethod.GET)
    @GetMapping({"/", "/home"})
    public String welcome(Model model, Principal principal) {
        List<Class> classes = classService.getAllClasses();
        model.addAttribute("username", principal.getName());

        UserProfile userProfile = userProfileService.findUserProfile(principal.getName());
        //Kui kasutajaandmeid on lisatud
        if(userProfile != null) {
            model.addAttribute("userProfile", userProfile);
            model.addAttribute("jobs", jobService.getAllJobs());
            model.addAttribute("scheduleClasses", classes);
            model.addAttribute("allStudents", studentService.getAllStudents());
            model.addAttribute("studentClasses", studentService.getAllStudentsWithClasses(classes));
            return "main";
        }else{
            return "add_user_details";
        }
    }

    @GetMapping("/edit-class/{id}")
    public String edit(Model model, @PathVariable("id") long id){
        model.addAttribute("scheduleClass", classService.getAClass(id));

        return "class_edit";
    }

    @PostMapping("/edit-class/{id}")
    public String edit(@PathVariable("id") long id,
                       @Valid Class aClass,
                       BindingResult bindingResult,
                       Model model){
        classService.updateClass(aClass);

        return "class_edited_successfully";
    }

    @PostMapping("time/{id}")
    public String postTime(@PathVariable("id") long id,
                       @Valid Job job,
                       BindingResult bindingResult,
                       Model model){
        jobService.addJob(job);

        return "class_edited_successfully";
    }

    @GetMapping("/class-details/{id}")
    public String classDetails(Model model, @PathVariable("id") long id){
        Class aClass = classService.getAClass(id);
        if(aClass != null){
            model.addAttribute("scheduleClass", aClass);
            return "class_details";
        }else {
            return "404";
        }
    }

    @GetMapping("/add-class")
    public String addClass(){
        return "class_add";
    }

    @PostMapping("/add-class")
    public String classSubmit(@ModelAttribute Class aClass){
        classService.saveClass(aClass);

        return "class_submitted_successfully";
    }

    @PostMapping("delete-class/{id}")
    public String deleteClass(Model model, @PathVariable("id") int id,
                              @Valid Class aClass){
        if(classService.deleteClass(aClass)){
            return "class_deleted_successfully";
        }else{
            return "class_not_found";
        }
    }

    @GetMapping("/query-result-student-classes/{studentName}")
    public String studentClasses(Model model, @PathVariable("studentName") String studentName){
        List<Class> classes = classService.getAllClasses();

        model.addAttribute("scheduleClasses", classes );
        model.addAttribute("students", studentService.getAllStudentsWithClasses( classes ) );
        //model.addAttribute("studentClasses", studentService.getAllClassesByStudentName(studentName));
        model.addAttribute("searchString", studentName);
        model.addAttribute("allStudents", studentService.getAllStudents());
        //model.addAttribute("studentClassesDistinctTime", studentService.getStudentClassesWithDistinctTime(studentName));

        return "student_classes_query_result";
    }

    @GetMapping("/student-details/{id}")
    public String studentDetails(Model model, @PathVariable("id") int id){
        List<Class> classes = classService.getAllClasses();

        model.addAttribute("scheduleClasses", classes );
        model.addAttribute("studentWithClasses", studentService.getStudentWithClasses( classes, id ) );

        return "student_details";
    }

    @GetMapping("/job-details/{id}")
    public String jobDetails(Model model, @PathVariable("id") int id){
        Job job = jobService.viewJob(id);
        List<Class> classes = classService.getAllClasses();
        model.addAttribute("scheduleClasses", classes);
        model.addAttribute("job", job );

        return "job_details";
    }
}
