package ci.digitalacademy.monEtab.services.mapper;

import ci.digitalacademy.monEtab.models.AppSetting;
import ci.digitalacademy.monEtab.services.dto.AppSettingDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AppSettingMapper extends EntityMapper<AppSettingDTO , AppSetting> {
}

