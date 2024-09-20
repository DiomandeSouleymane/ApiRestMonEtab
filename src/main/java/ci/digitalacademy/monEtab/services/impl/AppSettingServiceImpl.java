package ci.digitalacademy.monEtab.services.impl;

import ci.digitalacademy.monEtab.models.AppSetting;
import ci.digitalacademy.monEtab.repositories.AppSettingRepository;
import ci.digitalacademy.monEtab.services.AppSettingService;
import ci.digitalacademy.monEtab.services.dto.AppSettingDTO;
import ci.digitalacademy.monEtab.services.mapper.AppSettingMapper;
import ci.digitalacademy.monEtab.services.mapping.AppSettingMapping;
import ci.digitalacademy.monEtab.utils.SlugifyUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
@Slf4j
@Service
public class AppSettingServiceImpl implements AppSettingService {


    private final AppSettingRepository appSettingRepository;
    private final AppSettingMapper appSettingMapper;


    @Override
    public AppSettingDTO save(AppSettingDTO appSettingDTO) {
        log.debug("Saving appSetting {}", appSettingDTO);
        AppSetting appSetting = appSettingMapper.DtoToEntity(appSettingDTO);
        return appSettingMapper.ToDto(appSettingRepository.save(appSetting));

    }

    @Override
    public AppSettingDTO update(AppSettingDTO appSettingDTO) {

        return findById(appSettingDTO.getId()).map(existingAppSetting -> {
            existingAppSetting.setSmtpPort(appSettingDTO.getSmtpPort());
            existingAppSetting.setSmtpPassword(appSettingDTO.getSmtpPassword());
            existingAppSetting.setSmtpUsername(appSettingDTO.getSmtpUsername());
            existingAppSetting.setSmtpServer(appSettingDTO.getSmtpServer());
            return save(existingAppSetting);
        }).orElseThrow(() -> new IllegalArgumentException("Absence not found with id:" + appSettingDTO));
    }

    @Override
    public AppSettingDTO update(AppSettingDTO appSettingDTO, Long id) {
        appSettingDTO.setId(id);
        return update(appSettingDTO);

    }

    @Override
    public List<AppSettingDTO> findAll() {
        return appSettingRepository.findAll().stream().map(appSetting -> appSettingMapper.ToDto(appSetting)).toList();
    }

    @Override
    public Optional<AppSettingDTO> findById(Long id) {
        return appSettingRepository.findById(id).map(appSetting -> appSettingMapper.ToDto(appSetting));
    }


    @Override
    public void deleteById(Long id) {
        appSettingRepository.deleteById(id);
    }

    @Override
    public AppSettingDTO initAppSetting(AppSettingDTO appSettingDTO) {
        log.debug("Request to init app {}", appSettingDTO);
        AppSettingDTO settingDTO = existingParameter();
        if (settingDTO == null) {
            return save(appSettingDTO);
        }
        return settingDTO;

    }

    @Override
    public AppSettingDTO existingParameter() {
        log.debug("Request to check existing parameter");
        List<AppSettingDTO> appSettingDTOS = findAll();
        return appSettingDTOS.stream().findFirst().orElse(null);
    }

    @Override
    public AppSettingDTO saveAppSetting(AppSettingDTO appSettingDTO) {
        final String slug = SlugifyUtils.generate(appSettingDTO.getSmtpServer().toString());
        appSettingDTO.setSlug(slug);
        return save(appSettingDTO);
    }

    @Override
    public AppSettingDTO partialUpdate(AppSettingDTO appSettingDTO, Long id) {
        return appSettingRepository.findById(id).map(appSetting -> {
            AppSettingMapping.partialUpdate(appSetting, appSettingDTO);
            return appSetting;
        }).map(appSettingRepository::save).map(appSettingMapper::ToDto).orElse(null);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete AppSetting by ID: {}", id);
        appSettingRepository.deleteById(id);  // Utilisation de la méthode deleteById
    }
}

