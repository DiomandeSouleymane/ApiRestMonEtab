package ci.digitalacademy.monEtab.web.resource;


import ci.digitalacademy.monEtab.services.TeacherService;
import ci.digitalacademy.monEtab.services.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
@RequestMapping("/api/teachers")
public class TeacherResources {

    private final TeacherService teacherService;

    @PostMapping

    @ApiResponse(responseCode = "201", description = "Request to save teacher")
    @Operation(summary = "save new teacher", description = "This endpoint allow to save teacher")


    public ResponseEntity<TeacherDTO> save(@RequestBody TeacherDTO teacherDTO) {
        log.debug("REST Request to save teacher {}", teacherDTO);
        TeacherDTO teacher = teacherService.saveTeacher(teacherDTO);
        return new ResponseEntity<>(teacher, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Request to get teacher"),
            @ApiResponse(responseCode = "404", description = "teacher not found", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Operation(summary = "Find one teacher", description = "This endpoint allows to find")

    public ResponseEntity<TeacherDTO> getTeacherById(@PathVariable Long id) {
        Optional<TeacherDTO> teacherDTO = teacherService.findById(id);
        return teacherDTO.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }



    @GetMapping("/slug/{slug}")
    public ResponseEntity<?> getOneBySlug(@PathVariable String slug) {
        log.debug("REST Request to get one by slug  {}", slug);
        return null;

    }


    @GetMapping
    public ResponseEntity<List<TeacherDTO>> getAllTeachers() {
        List<TeacherDTO> teacherDTOList = teacherService.findAll();
        return new ResponseEntity<>(teacherDTOList, HttpStatus.OK);
    }





    @PutMapping("/{id}")
    public ResponseEntity<TeacherDTO> updateTeacher(
            @RequestBody TeacherDTO teacherDTO,
            @Parameter(required = true, description = "ID of teacher to be retrieved")
            @PathVariable Long id) {

        log.debug("REST Request to update Teacher: {}", teacherDTO);

        TeacherDTO updatedTeacher = teacherService.update(teacherDTO, id);
        return new ResponseEntity<>(updatedTeacher, HttpStatus.OK);
    }


    @PatchMapping("/{id}")
    public TeacherDTO partialUpdate(@RequestBody TeacherDTO teacherDTO, @PathVariable Long id){
        log.debug("REST request to partial update {} {}", teacherDTO, id);
        return teacherService.partialUpdate(teacherDTO, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.debug("REST Request to delete Teacher with ID: {}", id);
        teacherService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/register-teacher")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseRegisterTeacherDTO registerTeacherDTO(@RequestBody RegistrationTeacherDTO registrationTeacherDTO) {
        log.debug("REST Request to register teacher: {}", registrationTeacherDTO);
        return teacherService.registerTeacher(registrationTeacherDTO);
    }
}



