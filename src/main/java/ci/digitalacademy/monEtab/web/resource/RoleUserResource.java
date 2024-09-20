package ci.digitalacademy.monEtab.web.resource;

import ci.digitalacademy.monEtab.repositories.RoleUserRepository;
import ci.digitalacademy.monEtab.services.RoleUserService;
import ci.digitalacademy.monEtab.services.dto.AddressDTO;
import ci.digitalacademy.monEtab.services.dto.RoleUserDTO;
import ci.digitalacademy.monEtab.services.mapper.RoleUserMapper;
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

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/roleUsers")
public class RoleUserResource {

    private final RoleUserService roleUserService;
    private final RoleUserRepository roleUserRepository;
    private final RoleUserMapper roleUserMapper;
    @PostMapping
    @ApiResponse(responseCode = "201", description = "Request to save role user")
    @Operation(summary = "Save a new role user", description = "This endpoint allows to save a new role user.")

    public ResponseEntity<RoleUserDTO> save(@RequestBody RoleUserDTO roleUserDTO) {
        log.debug("REST Request to save role user {}", roleUserDTO);
        RoleUserDTO roleUser = roleUserService.saveRoleUser(roleUserDTO);
        return new ResponseEntity<>(roleUser, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Request to get role user by ID"),
            @ApiResponse(responseCode = "404", description = "role user not found", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Operation(summary = "Find role user by ID", description = "This endpoint allows to find an role user by its ID.")
    public ResponseEntity<RoleUserDTO> getRoleUserById(@PathVariable Long id) {
        Optional<RoleUserDTO> roleUserDTO = roleUserService.findById(id);
        return roleUserDTO.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/slug/{slug}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Request to get role user by slug"),
            @ApiResponse(responseCode = "404", description = "role user not found", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Operation(summary = "Find role user by slug", description = "This endpoint allows to find an role user by its slug.")
    public ResponseEntity<RoleUserDTO> getRoleUserBySlug(@PathVariable String slug) {
        Optional<RoleUserDTO> roleUserDTO = roleUserRepository.findBySlug(slug)
                .map(roleUserMapper::ToDto);
        return roleUserDTO.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    @ApiResponse(responseCode = "200", description = "Request to get all role user")
    @Operation(summary = "List all role user", description = "This endpoint allows to list all role user.")
    public List<RoleUserDTO> getAllRoleUser() {
        return roleUserService.findAll();
    }

    @PutMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "Request to update role user")
    @Operation(summary = "Update role user", description = "This endpoint allows to update an existing role user.")
    public ResponseEntity<RoleUserDTO> updateRoleUser(@PathVariable Long id, @RequestBody RoleUserDTO roleUserDTO) {
        Optional<RoleUserDTO> updateRoleUser = Optional.ofNullable(roleUserService.update(roleUserDTO, id));
        return updateRoleUser.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PatchMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "Request to partially update role user")
    @Operation(summary = "Partially update role user", description = "This endpoint allows to partially update an existing role user.")
    public ResponseEntity<RoleUserDTO> partialUpdateRoleUser(@PathVariable Long id, @RequestBody RoleUserDTO roleUserDTO) {
        RoleUserDTO updateRoleUser = roleUserService.partialUpdate(roleUserDTO, id);
        return new ResponseEntity<>(updateRoleUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204", description = "Request to delete role user")
    @Operation(summary = "Delete role user", description = "This endpoint allows to delete an role user.")
    public ResponseEntity<Void> deleteRoleUser(@PathVariable Long id) {
        roleUserService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
