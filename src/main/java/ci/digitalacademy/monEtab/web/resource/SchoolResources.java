package ci.digitalacademy.monEtab.web.resource;

import ci.digitalacademy.monEtab.repositories.SchoolRepository;
import ci.digitalacademy.monEtab.services.AppSettingService;
import ci.digitalacademy.monEtab.services.SchoolService;
import ci.digitalacademy.monEtab.services.dto.AddressDTO;
import ci.digitalacademy.monEtab.services.dto.AppSettingDTO;
import ci.digitalacademy.monEtab.services.dto.SchoolDTO;
import ci.digitalacademy.monEtab.services.mapper.SchoolMapper;
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
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/schools")
public class SchoolResources {

    private final SchoolService schoolService;
    private final SchoolRepository schoolRepository;
    private final SchoolMapper schoolMapper;
    private final AppSettingService appSettingService;

    @PostMapping
    @ApiResponse(responseCode = "201", description = "Request to save school")
    @Operation(summary = "Save a new school", description = "This endpoint allows to save a new school.")
  /*public ResponseEntity<SchoolDTO> save(@RequestBody SchoolDTO schoolDTO) {
        log.debug("REST Request to save school {}", schoolDTO);
        SchoolDTO school = schoolService.saveSchool(schoolDTO);
        return new ResponseEntity<>(school, HttpStatus.CREATED);
    }*/
    public ResponseEntity<?> saveSchool(@RequestBody SchoolDTO schoolDTO) {
        log.debug("REST request to save School: {}", schoolDTO);

        Optional<AppSettingDTO> setting = appSettingService.findById(schoolDTO.getAppSetting().getId());

        if (setting.isPresent()) {
            schoolDTO.setAppSetting(setting.get());
            return ResponseEntity.ok(schoolService.saveSchool(schoolDTO));
        }

        return new ResponseEntity<>(Map.of("body", "Setting not found !"), HttpStatus.NOT_FOUND);
    }



    @GetMapping("/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Request to get school by ID"),
            @ApiResponse(responseCode = "404", description = "school not found", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Operation(summary = "Find school by ID", description = "This endpoint allows to find an school by its ID.")
    public ResponseEntity<SchoolDTO> getSchoolById(@PathVariable Long id) {
        Optional<SchoolDTO> schoolDTO = (Optional<SchoolDTO>) schoolService.findById(id);
        return schoolDTO.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/slug/{slug}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Request to get school by slug"),
            @ApiResponse(responseCode = "404", description = "school not found", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Operation(summary = "Find school by slug", description = "This endpoint allows to find an school by its slug.")
    public ResponseEntity<SchoolDTO> getSchoolBySlug(@PathVariable String slug) {
        Optional<SchoolDTO> schoolDTO = schoolRepository.findBySlug(slug)
                .map(schoolMapper::ToDto);
        return schoolDTO.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    @ApiResponse(responseCode = "200", description = "Request to get all school")
    @Operation(summary = "List all school", description = "This endpoint allows to list all school.")
    public List<SchoolDTO> getAllSchool() {
        return schoolService.findAll();
    }

    @PutMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "Request to update school")
    @Operation(summary = "Update school", description = "This endpoint allows to update an existing school.")
    public ResponseEntity<SchoolDTO> updateSchool(@PathVariable Long id, @RequestBody SchoolDTO schoolDTO) {
        Optional<SchoolDTO> updateSchool = schoolService.update(schoolDTO, id);
        return updateSchool.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PatchMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "Request to partially update school")
    @Operation(summary = "Partially update school", description = "This endpoint allows to partially update an existing school.")
    public ResponseEntity<SchoolDTO> partialUpdateSchool(@PathVariable Long id, @RequestBody SchoolDTO schoolDTO) {
        SchoolDTO updateSchool = schoolService.partialUpdate(schoolDTO, id);
        return new ResponseEntity<>(updateSchool, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204", description = "Request to delete school")
    @Operation(summary = "Delete school", description = "This endpoint allows to delete an school.")
    public ResponseEntity<Void> deleteSchool(@PathVariable Long id) {
        schoolService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
