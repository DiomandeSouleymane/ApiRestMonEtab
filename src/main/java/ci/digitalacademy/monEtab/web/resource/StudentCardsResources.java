package ci.digitalacademy.monEtab.web.resource;

import ci.digitalacademy.monEtab.repositories.StudentCardsRepository;
import ci.digitalacademy.monEtab.services.StudentCardsService;
import ci.digitalacademy.monEtab.services.dto.AddressDTO;
import ci.digitalacademy.monEtab.services.dto.StudentCardsDTO;
import ci.digitalacademy.monEtab.services.mapper.StudentCardsMapper;
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
@RequestMapping("/api/studentCards")
public class StudentCardsResources {

    private final StudentCardsService studentCardsService;
    private final StudentCardsRepository studentCardsRepository;
    private final StudentCardsMapper studentCardsMapper;
    @PostMapping
    @ApiResponse(responseCode = "201", description = "Request to save studentCards")
    @Operation(summary = "Save a new studentCards", description = "This endpoint allows to save a new studentCards.")

    public ResponseEntity<StudentCardsDTO> save(@RequestBody StudentCardsDTO studentCardsDTO) {
        log.debug("REST Request to save studentCards {}", studentCardsDTO);
        StudentCardsDTO studentCards = studentCardsService.saveStudentCards(studentCardsDTO);
        return new ResponseEntity<>(studentCards, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Request to get studentCards by ID"),
            @ApiResponse(responseCode = "404", description = "studentCards not found", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Operation(summary = "Find studentCards by ID", description = "This endpoint allows to find an studentCards by its ID.")
    public ResponseEntity<StudentCardsDTO> getStudentCardsById(@PathVariable Long id) {
        Optional<StudentCardsDTO> studentCardsDTO = studentCardsService.findById(id);
        return studentCardsDTO.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/slug/{slug}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Request to get studentCards by slug"),
            @ApiResponse(responseCode = "404", description = "studentCards not found", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Operation(summary = "Find studentCards by slug", description = "This endpoint allows to find an studentCards by its slug.")
    public ResponseEntity<StudentCardsDTO> getStudentCardsBySlug(@PathVariable String slug) {
        Optional<StudentCardsDTO> studentCardsDTO = studentCardsRepository.findBySlug(slug)
                .map(studentCardsMapper::ToDto);
        return studentCardsDTO.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @GetMapping
    @ApiResponse(responseCode = "200", description = "Request to get all studentCards")
    @Operation(summary = "List all studentCards", description = "This endpoint allows to list all studentCards.")
    public List<StudentCardsDTO> getAllStudentCards() {
        return studentCardsService.findAll();
    }

    @PutMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "Request to update studentCards")
    @Operation(summary = "Update studentCards", description = "This endpoint allows to update an existing studentCards.")
    public ResponseEntity<StudentCardsDTO> updateStudentCards(@PathVariable Long id, @RequestBody StudentCardsDTO studentCardsDTO) {
        Optional<StudentCardsDTO> updateStudentCards = studentCardsService.update(studentCardsDTO, id);
        return updateStudentCards.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PatchMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "Request to partially update studentCards")
    @Operation(summary = "Partially update studentCards", description = "This endpoint allows to partially update an existing studentCards.")
    public ResponseEntity<StudentCardsDTO> partialUpdateStudentCards(@PathVariable Long id, @RequestBody StudentCardsDTO studentCardsDTO) {
        StudentCardsDTO updateStudentCards = studentCardsService.partialUpdate(studentCardsDTO, id);
        return new ResponseEntity<>(updateStudentCards, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204", description = "Request to delete studentCards")
    @Operation(summary = "Delete studentCards", description = "This endpoint allows to delete an studentCards.")
    public ResponseEntity<Void> deleteStudentCards(@PathVariable Long id) {
        studentCardsService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
