package roomescape.repository;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import roomescape.domain.theme.Theme;

@Repository
public class JdbcThemeRepository implements ThemeRepository {

    private static final RowMapper<Theme> ROW_MAPPER = (resultSet, rowNum) ->
            new Theme(
                    resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getString("description"),
                    resultSet.getString("thumbnail")
            );

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    public JdbcThemeRepository(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("theme")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Long save(Theme theme) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("name", theme.getName().getName())
                .addValue("description", theme.getDescription().getDescription())
                .addValue("thumbnail", theme.getThumbnail().getThumbnail());
        return jdbcInsert.executeAndReturnKey(params).longValue();
    }

    @Override
    public List<Theme> findAll() {
        String sql = "SELECT * FROM theme";
        return jdbcTemplate.query(sql, ROW_MAPPER);
    }

    @Override
    public Theme findById(Long id) {
        String sql = "SELECT * FROM theme WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, ROW_MAPPER, id);
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM theme WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public Boolean existId(Long id) {
        String sql = "SELECT EXISTS (SELECT 1 FROM theme WHERE id = ?)";
        return jdbcTemplate.queryForObject(sql, Boolean.class, id);
    }

    @Override
    public Boolean existName(String name) {
        String sql = "SELECT EXISTS (SELECT 1 FROM theme WHERE name = ?)";
        return jdbcTemplate.queryForObject(sql, Boolean.class, name);
    }
}
