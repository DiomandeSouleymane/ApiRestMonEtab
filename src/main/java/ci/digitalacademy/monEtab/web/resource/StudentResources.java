package ci.digitalacademy.monEtab.web.resource;

import ci.digitalacademy.monEtab.services.StudentService;
import ci.digitalacademy.monEtab.services.dto.FileStudentDTO;
import ci.digitalacademy.monEtab.services.dto.RegistrationStudentDTO;
import ci.digitalacademy.monEtab.services.dto.ResponseRegisterStudentDTO;
import ci.digitalacademy.monEtab.services.dto.StudentDTO;
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

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/students")
public class StudentResources {

    private final StudentService studentService;

    @PostMapping
    @ApiResponse(responseCode = "201", description = "Request to save student")
    @Operation(summary = "save new student", description = "This endpoint allow to save student")
    public ResponseEntity<StudentDTO> saveStudent(@RequestBody StudentDTO studentDTO){
        log.debug("REST Request to save  {}", studentDTO);
        return new ResponseEntity<>(studentService.save(studentDTO), HttpStatus.CREATED);
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadStudentPicture(@ModelAttribute FileStudentDTO fileStudentDTO) throws IOException {
        StudentDTO studentDTO = studentService.uploadStudentPicture(fileStudentDTO.getId(), fileStudentDTO.getFile());
        if (studentDTO != null){
            return new ResponseEntity<>(studentDTO, HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Error while uploading student picture", HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<?>  updateStudent(
            @RequestBody StudentDTO studentDTO,
            @Parameter(required = true, description = "ID of student to be retrieved")
            @PathVariable Long id){
        log.debug("REST Request to update ");
        Optional<StudentDTO> student = studentService.update(studentDTO, id);
        if (student.isPresent()){
            return new ResponseEntity<>(student.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Request to get student"),
            @ApiResponse(responseCode = "404", description = "Student not found", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Operation(summary = "Find one student", description = "This endpoint allows to find")
    public  ResponseEntity<?> getStudent (@PathVariable Long id){
        log.debug("Request to get one ");
        Optional<StudentDTO> studentDTO = studentService.findOne(id);
        if(studentDTO.isPresent()){
            return new ResponseEntity<>(studentDTO.get(), HttpStatus.OK);

        }else {
            return new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);
        }
    }


    public List<StudentDTO> getAllStudent(){
        log.debug("Request to all student ");

        return studentService.findAll();
    }
    @DeleteMapping
    public void delete(@PathVariable Long id){
        log.debug("Request to delete: {}", id);
        studentService.delete(id);
    }
    public void  deleteStudent (@PathVariable Long id){
      studentService.deleteById(id);
    }

    @PostMapping("/register-student")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseRegisterStudentDTO registerStudent(@RequestBody RegistrationStudentDTO registrationStudentDTO){
     log.debug("REST Request to register student: {}", registrationStudentDTO);
     return studentService.registerStudent(registrationStudentDTO);
    }
}