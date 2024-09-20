package ci.digitalacademy.monEtab.controller;


import ci.digitalacademy.monEtab.services.*;
import ci.digitalacademy.monEtab.services.dto.AppSettingDTO;
import ci.digitalacademy.monEtab.services.dto.RoleUserDTO;
import ci.digitalacademy.monEtab.services.dto.SchoolDTO;
import ci.digitalacademy.monEtab.services.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/schools")
@Slf4j
@RequiredArgsConstructor
public class SchoolController {


    private final InitAppService initAppService;

    private final AppSettingService appSettingService;

    private final SchoolService schoolService;

    private final UserService userService;

    private final RoleUserService roleUserService;

    @GetMapping
    public String showSchoolPage(Model model){
        model.addAttribute("school" , new SchoolDTO());
        return "inits/school";
    }

    @PostMapping
    public String saveSchool(@ModelAttribute SchoolDTO schoolDTO) {
        // Vérifiez si l'appSetting existe
        AppSettingDTO appSettingDTO = appSettingService.findAll().get(0);
        // Ajouter l'appSetting à l'objet SchoolDTO
        schoolDTO.setAppSetting(appSettingDTO);
        // Enregistrer l'école
        initAppService.initSchool(schoolDTO, appSettingDTO);

        return "redirect:/home";
    }

    @GetMapping("/update/{id}")
    public String showUpdateTeacherPage(
            @PathVariable Long id,
            Model model
    ){
        log.debug("show update school page {}" , id);

        model.addAttribute("school", schoolService.findById(id));
        model.addAttribute("nameSchool");
        model.addAttribute("urlLogo");
        model.addAttribute("action", "update");
        return "inits/school";
    }

    @PostMapping("/save")
    public String saveTeacher(SchoolDTO schoolDTO) {
        log.debug("school {}" , schoolDTO);
        schoolService.save(schoolDTO);
        return "redirect:/home";
    }



    public List<RoleUserDTO> createRoleUser(){
        List<RoleUserDTO> roleUserDTOList = new ArrayList<>();

        RoleUserDTO roleUserDTO1 = new RoleUserDTO();
        roleUserDTO1.setNameRole("ADMIN");
        roleUserDTOList.add(roleUserDTO1);

        RoleUserDTO roleUserDT02 = new RoleUserDTO();
        roleUserDT02.setNameRole("USER");
        roleUserDTOList.add(roleUserDT02);

        return roleUserDTOList;
    }

    public List<UserDTO> createUser(){
        List<UserDTO> userDTOList = new ArrayList<>();
        SchoolDTO schoolDTO = schoolService.findAll().get(0);

        log.debug("{}", schoolDTO);

        List<RoleUserDTO> roleUserDTOList = roleUserService.findAll();
        List<RoleUserDTO> roleUserADMIN = new ArrayList<>();
        roleUserADMIN.add(roleUserDTOList.get(0));

        List<RoleUserDTO> roleUserUSER = new ArrayList<>();
        roleUserUSER.add(roleUserDTOList.get(1));

        UserDTO userAdmin = new UserDTO();
        userAdmin.setPseudo("admin");
        userAdmin.setPassword("admin");
        userAdmin.setCreatedDate(Instant.now());
        userAdmin.setSchool(schoolDTO);
        userAdmin.setRoleUser(roleUserADMIN);
        userDTOList.add(userAdmin);

        UserDTO userUser = new UserDTO();
        userUser.setPseudo("user");
        userUser.setPassword("user");
        userUser.setCreatedDate(Instant.now());
        userUser.setSchool(schoolDTO);
        userUser.setRoleUser(roleUserUSER);
        userDTOList.add(userUser);

        return userDTOList;

    }

}
