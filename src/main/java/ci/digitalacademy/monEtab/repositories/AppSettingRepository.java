package ci.digitalacademy.monEtab.repositories;

import aj.org.objectweb.asm.commons.Remapper;
import ci.digitalacademy.monEtab.models.Address;
import ci.digitalacademy.monEtab.models.AppSetting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AppSettingRepository extends JpaRepository<AppSetting, Long> {
    Optional<AppSetting> findById(Long id);
    Optional<AppSetting> findBySlug(String slug);
}
