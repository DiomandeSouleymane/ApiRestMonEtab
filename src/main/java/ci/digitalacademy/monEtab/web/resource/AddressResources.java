package ci.digitalacademy.monEtab.web.resource;

import ci.digitalacademy.monEtab.repositories.AddressRepository;
import ci.digitalacademy.monEtab.services.AddresseService;
import ci.digitalacademy.monEtab.services.dto.AddressDTO;
import ci.digitalacademy.monEtab.services.dto.TeacherDTO;
import ci.digitalacademy.monEtab.services.mapper.AddressMapper;
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
@RequestMapping("/api/address")
public class AddressResources {

    private final AddressRepository addressRepository;
    private final AddresseService addresseService;
    private final AddressMapper addressMapper;
    @PostMapping
    @ApiResponse(responseCode = "201", description = "Request to save address")
    @Operation(summary = "Save a new address", description = "This endpoint allows to save a new address.")

    public ResponseEntity<AddressDTO> save(@RequestBody AddressDTO addressDTO) {
        log.debug("REST Request to save address {}", addressDTO);
        AddressDTO address = addresseService.saveAddress(addressDTO);
        return new ResponseEntity<>(address, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Request to get address by ID"),
            @ApiResponse(responseCode = "404", description = "Address not found", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Operation(summary = "Find address by ID", description = "This endpoint allows to find an address by its ID.")
    public ResponseEntity<AddressDTO> getAddressById(@PathVariable Long id) {
        Optional<AddressDTO> addressDTO = addresseService.findById(id);
        return addressDTO.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/slug/{slug}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Request to get address by slug"),
            @ApiResponse(responseCode = "404", description = "Address not found", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Operation(summary = "Find address by slug", description = "This endpoint allows to find an address by its slug.")
    public ResponseEntity<AddressDTO> getAddressBySlug(@PathVariable String slug) {
        Optional<AddressDTO> addressDTO = addressRepository.findBySlug(slug)
                .map(addressMapper::ToDto);
        return addressDTO.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @GetMapping
    @ApiResponse(responseCode = "200", description = "Request to get all addresses")
    @Operation(summary = "List all addresses", description = "This endpoint allows to list all addresses.")
    public List<AddressDTO> getAllAddresses() {

        return addresseService.findAll();
    }

    @PutMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "Request to update address")
    @Operation(summary = "Update address", description = "This endpoint allows to update an existing address.")
    public ResponseEntity<AddressDTO> updateAddress(@PathVariable Long id, @RequestBody AddressDTO addressDTO) {
        Optional<AddressDTO> updatedAddress = addresseService.update(addressDTO, id);
        return updatedAddress.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PatchMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "Request to partially update address")
    @Operation(summary = "Partially update address", description = "This endpoint allows to partially update an existing address.")
    public ResponseEntity<AddressDTO> partialUpdateAddress(@PathVariable Long id, @RequestBody AddressDTO addressDTO) {
        AddressDTO updatedAddress = addresseService.partialUpdate(addressDTO, id);
        return new ResponseEntity<>(updatedAddress, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204", description = "Request to delete address")
    @Operation(summary = "Delete address", description = "This endpoint allows to delete an address.")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        addresseService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
