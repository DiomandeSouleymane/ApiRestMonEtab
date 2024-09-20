package ci.digitalacademy.monEtab.web.resource;


import ci.digitalacademy.monEtab.repositories.AppSettingRepository;
import ci.digitalacademy.monEtab.services.AppSettingService;
import ci.digitalacademy.monEtab.services.dto.AddressDTO;
import ci.digitalacademy.monEtab.services.dto.AppSettingDTO;
import ci.digitalacademy.monEtab.services.mapper.AppSettingMapper;
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
@RequestMapping("/api/appSettings")
public class AppSettingResource {

    private final AppSettingService appSettingService;
    private final AppSettingRepository appSettingRepository;
    private final AppSettingMapper appSettingMapper;
    @PostMapping

    @ApiResponse(responseCode = "201", description = "Request to save appSetting")
    @Operation(summary = "save new appSetting", description = "This endpoint allow to save appSetting")


    public ResponseEntity<AppSettingDTO> save(@RequestBody AppSettingDTO appSettingDTO) {
        log.debug("REST Request to save appSetting {}", appSettingDTO);
        AppSettingDTO appSetting = appSettingService.saveAppSetting(appSettingDTO);
        return new ResponseEntity<>(appSetting, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Request to get appSetting"),
            @ApiResponse(responseCode = "404", description = "appSetting not found", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Operation(summary = "Find one appSetting", description = "This endpoint allows to find")

    public ResponseEntity<AppSettingDTO> getAppSettingById(@PathVariable Long id) {
        Optional<AppSettingDTO> appSettingDTO = appSettingService.findById(id);
        return appSettingDTO.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/slug/{slug}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Request to get appSetting by slug"),
            @ApiResponse(responseCode = "404", description = "appSetting not found", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Operation(summary = "Find appSetting by slug", description = "This endpoint allows to find an appSetting by its slug.")
    public ResponseEntity<AppSettingDTO> getAppSettingBySlug(@PathVariable String slug) {
        Optional<AppSettingDTO> appSettingDTO = appSettingRepository.findBySlug(slug)
                .map(appSettingMapper::ToDto);
        return appSettingDTO.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    @ApiResponse(responseCode = "200", description = "Request to get all appSetting")
    @Operation(summary = "List all appSetting", description = "This endpoint allows to list all appSetting.")
    public List<AppSettingDTO> getAllAddresses() {
        return appSettingService.findAll();
    }

    @PutMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "Request to update appSetting")
    @Operation(summary = "Update appSetting", description = "This endpoint allows to update an existing appSetting.")

    public ResponseEntity<AppSettingDTO> updateAppSetting(@PathVariable Long id, @RequestBody AppSettingDTO appSettingDTO) {
        Optional<AppSettingDTO> updateAppSetting = Optional.ofNullable(appSettingService.update(appSettingDTO, id));
        return updateAppSetting.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PatchMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "Request to partially update appSetting")
    @Operation(summary = "Partially update appSetting", description = "This endpoint allows to partially update an existing appSetting.")
    public ResponseEntity<AppSettingDTO> partialUpdateAppSetting(@PathVariable Long id, @RequestBody AppSettingDTO appSettingDTO) {
        AppSettingDTO updateAppSetting = appSettingService.partialUpdate(appSettingDTO, id);
        return new ResponseEntity<>(updateAppSetting, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204", description = "Request to delete appSetting")
    @Operation(summary = "Delete appSetting", description = "This endpoint allows to delete an appSetting.")
    public ResponseEntity<Void> deleteAppSetting(@PathVariable Long id) {
        appSettingService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
