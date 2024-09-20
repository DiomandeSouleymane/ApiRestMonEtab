package ci.digitalacademy.monEtab.web.resource;

import ci.digitalacademy.monEtab.repositories.AbsenceRepository;
import ci.digitalacademy.monEtab.services.AbsenceService;
import ci.digitalacademy.monEtab.services.dto.AbsenceDTO;
import ci.digitalacademy.monEtab.services.dto.AddressDTO;
import ci.digitalacademy.monEtab.services.dto.TeacherDTO;
import ci.digitalacademy.monEtab.services.mapper.AbsenceMapper;
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
@RequestMapping("/api/absences")
@Slf4j
@RequiredArgsConstructor
public class AbsenceResources {

    private final AbsenceService absenceService;
    private final AbsenceRepository absenceRepository;
    private final AbsenceMapper absenceMapper;

    @PostMapping
    @ApiResponse(responseCode = "201", description = "Request to save absence")
    @Operation(summary = "Save a new absence", description = "This endpoint allows to save a new absence.")

    public ResponseEntity<AbsenceDTO> save(@RequestBody AbsenceDTO absenceDTO) {
        log.debug("REST Request to save absence {}", absenceDTO);
        AbsenceDTO absence = absenceService.saveAbsence(absenceDTO);

        return new ResponseEntity<>(absence, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Request to get absence by ID"),
            @ApiResponse(responseCode = "404", description = "absence not found", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Operation(summary = "Find absence by ID", description = "This endpoint allows to find an absence by its ID.")
    public ResponseEntity<AbsenceDTO> getAbsenceById(@PathVariable Long id) {
        Optional<AbsenceDTO> absenceDTO = absenceService.findById(id);
        return absenceDTO.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @GetMapping("/slug/{slug}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Request to get absence by slug"),
            @ApiResponse(responseCode = "404", description = "Address not found", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Operation(summary = "Find absence by slug", description = "This endpoint allows to find an absence by its slug.")
    public ResponseEntity<AbsenceDTO> getAbsenceBySlug(@PathVariable String slug) {
        Optional<AbsenceDTO> absenceDTO = absenceRepository.findBySlug(slug)
                .map(absenceMapper::ToDto);
        return absenceDTO.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

   /* @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        log.debug("REST Request to get one {}", id);
        Optional<AbsenceDTO> absenceDTO = absenceService.findOne(id);
        if (absenceDTO.isPresent()) {
            return new ResponseEntity<>(absenceDTO.get(), HttpStatus.OK);

        } else {
            return new ResponseEntity<>("Absence not found", HttpStatus.NOT_FOUND);
        }
    }*/

    @GetMapping
    @ApiResponse(responseCode = "200", description = "Request to get all Absence")
    @Operation(summary = "List all Absence", description = "This endpoint allows to list all Absence.")
    public List<AbsenceDTO> getAll() {
        log.debug("REST Request to get all");
        List<AbsenceDTO> absenceDTOSList = absenceService.findAll();
        return new ResponseEntity<>(absenceDTOSList, HttpStatus.OK).getBody();
    }

    @PutMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "Request to update Absence")
    @Operation(summary = "Update Absence", description = "This endpoint allows to update an existing Absence.")
    public ResponseEntity<AbsenceDTO> updateAbsence(@PathVariable Long id, @RequestBody AbsenceDTO absenceDTO) {
        Optional<AbsenceDTO> updateAbsence = Optional.ofNullable(absenceService.update(absenceDTO, id));
        return updateAbsence.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        log.debug("REST Request to delete  {}", id);
        absenceService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "Request to partially update Absence")
    @Operation(summary = "Partially update Absence", description = "This endpoint allows to partially update an existing Absence.")
    public AbsenceDTO partialUpdate(@RequestBody AbsenceDTO absenceDTO, @PathVariable Long id){
        log.debug("REST request to partial update {} {}", absenceDTO, id);
        return absenceService.partialUpdate(absenceDTO, id);
    }


}
