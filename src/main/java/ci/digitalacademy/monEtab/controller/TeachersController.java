package ci.digitalacademy.monEtab.controller;

import ci.digitalacademy.monEtab.services.TeacherService;
import ci.digitalacademy.monEtab.services.dto.StudentDTO;
import ci.digitalacademy.monEtab.services.dto.TeacherDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeachersController {

    private static final Logger log = LoggerFactory.getLogger(TeachersController.class);
    private final TeacherService teacherService;

    @GetMapping
    public String showTeachersList(Model model) {
        List<TeacherDTO> teachers = teacherService.findAll();
        model.addAttribute("teachers", teachers);
        return "teachers/list";
    }

    @PostMapping
    public String saveTeacher(TeacherDTO teacherDTO) {
        log.debug("Request to save Teacher: {}", teacherDTO);
        teacherService.save(teacherDTO);
        return "redirect:/teachers";
    }

    @GetMapping("/add")
    public String showAddTeacherForms(Model model) {
        log.debug("Request to show Add Teacher Forms");
        model.addAttribute("teacher", new TeacherDTO());
        return "teachers/forms";
    }

    @GetMapping("/update/{id}")
    public String showUpdateTeacherForms(Model model, @PathVariable Long id) {
        log.info("Showing update teacher page");
        Optional<TeacherDTO> teacher = teacherService.findOne(id);
        if (teacher.isPresent()) {
            model.addAttribute("teacher", teacher.get());
            return "teachers/forms";
        }else {

            return "redirect:/teachers";
        }
    }


    @GetMapping("/delete/{id}")
    public String deleteTeacher(@PathVariable Long id) {
        log.debug("Request to delete Teacher with id: {}", id);
        teacherService.delete(id);
        return "redirect:/teachers";
    }


}
