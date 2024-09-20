package ci.digitalacademy.monEtab.controller;

import ci.digitalacademy.monEtab.models.Student;
import ci.digitalacademy.monEtab.services.StudentService;
import ci.digitalacademy.monEtab.services.dto.StudentDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentsController {
    private static final Logger log = LoggerFactory.getLogger(StudentsController.class);
    private final StudentService studentService;

    @GetMapping
    public String showStudentList(Model model) {
        List<StudentDTO> students = studentService.findAll();
        model.addAttribute("students", students);
        return "students/list";
    }


    @PostMapping
    public String saveStudent(StudentDTO studentDTO) {
        log.info("Saving student: " + studentDTO);
        studentService.save(studentDTO);
        return "redirect:/students";
    }


    @GetMapping("/add")
    public String showAddStudentForms(Model model) {
        log.info("Showing add student page");
        model.addAttribute("student", new Student());
        model.addAttribute("students", studentService.findAll());
        model.addAttribute("action", "add");
        return "students/forms";
    }

    // Sauvegarde des modifications d'un étudiant

    @GetMapping("/update/{id}")
    public String showUpdateStudentForms(Model model, @PathVariable Long id) {
        log.info("Showing update student page");
        Optional<StudentDTO> student = studentService.findOne(id);
        if (student.isPresent()) {
            model.addAttribute("student", student.get());
            return "students/forms";
        }else {

            return "redirect:/students";
        }
    }


    @PostMapping("/update")
    public String updateStudent(@ModelAttribute("student") StudentDTO studentDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "students/forms";
        }
        studentService.update(studentDTO);
        redirectAttributes.addFlashAttribute("message", "Student updated successfully!");
        return "redirect:/students";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        log.debug("Request to delete Student with id: {}", id);
        studentService.delete(id);
        return "redirect:/students";
    }

    @GetMapping("/search")
    @ResponseBody
    public List<Student> searchStudents(@RequestParam String query, @RequestParam(required = false) String gender) {
        return studentService.findByLastNameOrGenderOrMatricule(query, gender);
    }


}

