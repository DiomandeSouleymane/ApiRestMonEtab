package ci.digitalacademy.monEtab.controller;

import ci.digitalacademy.monEtab.services.UserService;
import ci.digitalacademy.monEtab.services.dto.StudentDTO;
import ci.digitalacademy.monEtab.services.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private static final Logger log = LoggerFactory.getLogger(UsersController.class);
    private final UserService userService;

    @GetMapping
    public String showUserList(Model model) {
        List<UserDTO> users = userService.findAll();
        model.addAttribute("users", users);
        return "users/list";
    }



    @PostMapping
    public String saveOrUpdateUser(UserDTO userDTO, RedirectAttributes redirectAttributes) {
        log.debug("Saving or updating user: {}", userDTO);

        if (userDTO.getId_user() != null) {
            userService.update(userDTO, userDTO.getId_user());
            redirectAttributes.addFlashAttribute("message", "Utilisateur mis à jour avec succès");
        } else {
            userDTO.setCreatedDate(Instant.now());
            userService.save(userDTO);
            redirectAttributes.addFlashAttribute("message", "Nouvel utilisateur créé avec succès");
        }

        return "redirect:/users";
    }

    @GetMapping("/add")
    public String showAddUserForm(Model model) {
        log.debug("showAddUserForm");
        model.addAttribute("user", new UserDTO());
        return "users/forms";
    }

    @GetMapping("/{id}")
    public String showEditUserForm(Model model, @PathVariable Long id) {
        log.debug("Request to show Edit User form");
        Optional<UserDTO> user = userService.findById(id);
        if (user.isPresent()) {
            model.addAttribute("user", user.get());
            return "users/forms";
        } else {
            return "redirect:/users";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        log.debug("Request to delete user with id: {}", id);
        userService.delete(id);
        redirectAttributes.addFlashAttribute("message", "Utilisateur supprimé avec succès");
        return "redirect:/users";
    }
}
