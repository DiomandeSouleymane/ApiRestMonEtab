package ci.digitalacademy.monEtab.web.resource;


import ci.digitalacademy.monEtab.services.RoleUserService;
import ci.digitalacademy.monEtab.services.SchoolService;
import ci.digitalacademy.monEtab.services.UserService;
import ci.digitalacademy.monEtab.services.dto.RoleUserDTO;
import ci.digitalacademy.monEtab.services.dto.SchoolDTO;
import ci.digitalacademy.monEtab.services.dto.TeacherDTO;
import ci.digitalacademy.monEtab.services.dto.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/users")
public class UserResources {

    private final UserService userService;
    private final SchoolService schoolService;
    private final RoleUserService roleUserService;

    @PostMapping
    @ApiResponse(responseCode = "201", description = "Request to save user")
    @Operation(summary = "Save a new user", description = "This endpoint allows to create a new user")
    /*public ResponseEntity<UserDTO> save(@RequestBody UserDTO userDTO) {
        log.debug("REST Request to save user {}", userDTO);
        UserDTO user = userService.saveUser(userDTO);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }*/

    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO) {
        log.debug("REST request to save User : {}", userDTO);

        AtomicInteger temp = new AtomicInteger();
        Optional<SchoolDTO> school = Optional.ofNullable(schoolService.findOne(userDTO.getSchool().getId_school()));

        // Récupérer les rôles associés à l'utilisateur
        List<RoleUserDTO> roles = userDTO.getRoleUser().stream().map(roleDTO -> roleUserService.findById(roleDTO.getIdRoleUser())
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + roleDTO.getIdRoleUser()))).toList();

        if (school.isPresent()) {
            userDTO.setSchool(school.get());
        }

        userDTO.setRoleUser(roles);
        return ResponseEntity.ok(userService.saveUser(userDTO));
    }



    @GetMapping("/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User found"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @Operation(summary = "Find user by ID", description = "This endpoint allows to find a user by their ID")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        Optional<UserDTO> userDTO = userService.findById(id);
        return userDTO.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @GetMapping("/slug/{slug}")
    @Operation(summary = "Find user by slug", description = "This endpoint allows to find a user by their slug")
    public ResponseEntity<UserDTO> getUserBySlug(@PathVariable String slug) {
        Optional<UserDTO> userDTO = userService.findBySlug(slug); // Méthode findBySlug à ajouter dans le service
        return userDTO.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    @Operation(summary = "List all users", description = "This endpoint lists all users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "User updated")
    @Operation(summary = "Update user", description = "This endpoint allows to update a user by ID")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        UserDTO updatedUser = userService.update(userDTO, id);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "User partially updated")
    @Operation(summary = "Partial update user", description = "This endpoint allows to partially update a user by ID")
    public ResponseEntity<UserDTO> partialUpdateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        UserDTO updatedUser = userService.partialUpdate(userDTO, id);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204", description = "User deleted")
    @Operation(summary = "Delete user", description = "This endpoint allows to delete a user by ID")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
