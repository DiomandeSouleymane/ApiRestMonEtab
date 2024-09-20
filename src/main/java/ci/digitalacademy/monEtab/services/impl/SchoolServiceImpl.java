package ci.digitalacademy.monEtab.services.impl;


import ci.digitalacademy.monEtab.models.School;
import ci.digitalacademy.monEtab.repositories.SchoolRepository;
import ci.digitalacademy.monEtab.services.SchoolService;
import ci.digitalacademy.monEtab.services.dto.SchoolDTO;
import ci.digitalacademy.monEtab.services.mapper.SchoolMapper;
import ci.digitalacademy.monEtab.services.mapping.SchoolMapping;
import ci.digitalacademy.monEtab.utils.SlugifyUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class SchoolServiceImpl implements SchoolService {

    private final SchoolRepository schoolRepository;
    private final SchoolMapper schoolMapper;


    @Override
    public SchoolDTO save(SchoolDTO schoolDTO) {
        School school = schoolMapper.DtoToEntity(schoolDTO);
        return schoolMapper.ToDto(schoolRepository.save(school));
    }

    @Override
    public Optional<SchoolDTO> update(SchoolDTO schoolDTO) {
        log.debug("Request to update School : {}", schoolDTO);
        return Optional.ofNullable(findById(schoolDTO.getId_school()).map(existingSchool -> {
            School school = schoolMapper.DtoToEntity(schoolDTO);
            school.setId_school(existingSchool.getId_school());  // Ensure the existing teacher's ID is preserved
            return save(schoolDTO);
        }).orElseThrow(() -> new RuntimeException("School not found")));
    }

    @Override
    public Optional<SchoolDTO> update(SchoolDTO schoolDTO, Long id) {
        log.debug("Request to update School with ID : {}", id);
        schoolDTO.setId_school(id);  // Set the correct ID for the update
        return update(schoolDTO);
    }

    @Override
    public Optional<SchoolDTO> findById(Long id) {
        log.debug("Request to find School by ID : {}", id);
        return schoolRepository.findById((id))
                .map(schoolMapper::ToDto);
    }



    @Override
    public List<SchoolDTO> findAll() {
        return  schoolRepository.findAll().stream().map(school -> schoolMapper.ToDto(school)).toList();
    }

    @Override
    public SchoolDTO findOne(Long id) {
        return schoolRepository.findById((id)).map(school -> schoolMapper.ToDto(school)).orElse(null);
    }

    @Override
    public void delete(Long id) {
        schoolRepository.deleteById((id));
    }


    @Override
    public SchoolDTO existingSchool() {
        log.debug("Request to check existing School");
        List<SchoolDTO> schoolDTOS = findAll();
        return  schoolDTOS.stream().findFirst().orElse(null);
    }

    @Override
    public SchoolDTO initSchool(SchoolDTO schoolDTO) {
        log.debug("Request to initSchool {}", schoolDTO);
        SchoolDTO schoolDTO1 = existingSchool();
        if (schoolDTO1 == null){
            return save(schoolDTO);
        }
        return schoolDTO1;
    }


    @Override
    public SchoolDTO partialUpdate(SchoolDTO schoolDTO, Long id) {
        return schoolRepository.findById((id)).map(school -> {
            SchoolMapping.partialUpdate(school, schoolDTO);
            return school;
        }).map(schoolRepository::save).map(schoolMapper::ToDto).orElse(null);
    }

    @Override
    public SchoolDTO saveSchool(SchoolDTO schoolDTO) {
        final String slug = SlugifyUtils.generate(schoolDTO.getNameSchool());
        schoolDTO.setSlug(slug);
        return save(schoolDTO);
    }

}
