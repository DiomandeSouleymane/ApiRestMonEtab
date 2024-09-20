package ci.digitalacademy.monEtab.services.impl;

import ci.digitalacademy.monEtab.models.Absence;
import ci.digitalacademy.monEtab.repositories.AbsenceRepository;
import ci.digitalacademy.monEtab.services.AbsenceService;
import ci.digitalacademy.monEtab.services.dto.AbsenceDTO;
import ci.digitalacademy.monEtab.services.mapper.AbsenceMapper;
import ci.digitalacademy.monEtab.services.mapping.AbsenceMapping;
import ci.digitalacademy.monEtab.utils.SlugifyUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
@Slf4j
@Component
public class AbsenceServiceImpl implements AbsenceService {

   private final AbsenceMapper absenceMapper;
   private final AbsenceRepository absenceRepository;

    @Override
    public AbsenceDTO save(AbsenceDTO absenceDTO) {
        log.debug("Saving absence {}", absenceDTO);
        Absence absence  = absenceMapper.DtoToEntity(absenceDTO);
        return absenceMapper.ToDto(absenceRepository.save(absence));

    }

    @Override
    public AbsenceDTO update(AbsenceDTO absenceDTO) {

        return findById(absenceDTO.getId_absence()).map(existingAbsence ->{
            existingAbsence.setAbsenceNumber(absenceDTO.getAbsenceNumber());
            existingAbsence.setAbsenceDate(absenceDTO.getAbsenceDate());
            return save(existingAbsence);
        }).orElseThrow(()->new IllegalArgumentException("Absence not found with id:" + absenceDTO ));
    }

    @Override
    public AbsenceDTO update(AbsenceDTO absenceDTO, Long id) {
        absenceDTO.setId_absence(id);
        return update(absenceDTO);

    }


    @Override
    public Optional<AbsenceDTO> findById(Long id) {

        return Optional.empty();
    }

    @Override
    public Optional<AbsenceDTO> findOne(Long id) {
       return absenceRepository.findById(id).map(absence ->
               absenceMapper.ToDto(absence));
    }


    @Override
    public List<AbsenceDTO> findAll() {
        log.debug("Finding all users");
        return absenceRepository.findAll().stream().map(absenceMapper::ToDto).toList();
    }


    @Override
    public void delete(Long id) {

    }

    @Override
    public AbsenceDTO saveAbsence(AbsenceDTO absenceDTO) {
        final String slug = SlugifyUtils.generate(absenceDTO.getAbsenceNumber().toString());
        absenceDTO.setSlug(slug);
        return save(absenceDTO);
    }

    @Override
    public AbsenceDTO partialUpdate(AbsenceDTO absenceDTO, Long id) {
        return absenceRepository.findById(id).map(absence -> {
            AbsenceMapping.partialUpdate(absence, absenceDTO);
            return absence;
        }).map(absenceRepository::save).map(absenceMapper::ToDto).orElse(null);
    }


}


