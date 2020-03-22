package com.logines.schedule.controller;

import com.logines.schedule.model.Class;
import com.logines.schedule.model.WorkHour;
import com.logines.schedule.model.UserProfile;
import com.logines.schedule.service.ClassService;
import com.logines.schedule.service.WorkHourService;
import com.logines.schedule.service.StudentService;
import com.logines.schedule.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
public class ScheduleController {

    @Autowired
    private ClassService classService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private UserProfileService userProfileService;
    @Autowired
    private WorkHourService workHourService;
    @Autowired
    private AuthenticationManager authenticationManager;

    //@RequestMapping(value="/",method = RequestMethod.GET)
    @GetMapping({"/", "/home"})
    public String welcome(Model model, Principal principal, String error) {
        List<Class> classes = classService.getAllClasses();
        model.addAttribute("usernameText", principal.getName());

        UserProfile userProfile = userProfileService.findUserProfile(principal.getName());
        //Kui kasutajaandmeid on lisatud
        if(userProfile != null) {
            model.addAttribute("workHour", new WorkHour());
            model.addAttribute("userProfile", userProfile);
            model.addAttribute("jobs", workHourService.getAllJobs());
            model.addAttribute("scheduleClasses", classes);
            model.addAttribute("allStudents", studentService.getAllStudents());
            model.addAttribute("studentClasses", studentService.getAllStudentsWithClasses(classes));
            return "main";
        }else{
            if (error != null)
                model.addAttribute("error", "Your username and password is invalid.");
            model.addAttribute("userProfileForm", new UserProfile());
            return "add_user_profile";
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
                       @Valid WorkHour workHour,
                       BindingResult bindingResult,
                       Model model){
        workHourService.addJob(workHour);

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

    @GetMapping(value = "/images/{image}", produces = {MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public @ResponseBody
    byte[] getImage(@PathVariable String image) throws IOException {
        ClassPathResource file = new ClassPathResource("static/images/" + image);
        byte[] bytes;
        bytes = StreamUtils.copyToByteArray(file.getInputStream());
        return bytes;
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
        WorkHour workHour = workHourService.viewJob(id);
        List<Class> classes = classService.getAllClasses();
        model.addAttribute("scheduleClasses", classes);
        model.addAttribute("job", workHour);

        return "job_details";
    }
}
