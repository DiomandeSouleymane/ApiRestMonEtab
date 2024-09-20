package ci.digitalacademy.monEtab.services;

import ci.digitalacademy.monEtab.services.dto.AppSettingDTO;

import java.util.List;
import java.util.Optional;

public interface AppSettingService {

    Optional<AppSettingDTO> findById(Long id);

    AppSettingDTO save(AppSettingDTO appSettingDTO);

    AppSettingDTO update(AppSettingDTO appSettingDTO, Long id);

    List<AppSettingDTO> findAll();

    AppSettingDTO update(AppSettingDTO appSettingDTO);

    void deleteById(Long id);

    AppSettingDTO initAppSetting(AppSettingDTO appSettingDTO);

    AppSettingDTO existingParameter();

    AppSettingDTO saveAppSetting(AppSettingDTO appSettingDTO);

    AppSettingDTO partialUpdate(AppSettingDTO appSettingDTO, Long id);

    void delete(Long id);

}
