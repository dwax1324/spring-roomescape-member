package roomescape.repository;

import java.util.List;
import java.util.Optional;
import roomescape.domain.theme.Theme;

public interface ThemeRepository {

    Long save(Theme theme);

    List<Theme> findAll();

    Optional<Theme> findById(Long id);

    void delete(Long id);

    Boolean existsByName(String name);
}
