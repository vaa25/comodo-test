package com.comodo.vaa25;

import com.github.springtestdbunit.bean.DatabaseConfigBean;
import com.github.springtestdbunit.bean.DatabaseDataSourceConnectionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static java.time.ZoneOffset.UTC;

@Configuration
public class TestConfiguration {

    @Bean
    public DatabaseDataSourceConnectionFactoryBean dbUnitDatabaseConnection(final DataSource dataSource) {
        final DatabaseConfigBean config = new DatabaseConfigBean();
        config.setAllowEmptyFields(true);
        config.setDatatypeFactory(new ModifiedPostgresqlDataTypeFactory());
        final DatabaseDataSourceConnectionFactoryBean factory = new DatabaseDataSourceConnectionFactoryBean(dataSource);
        factory.setDatabaseConfig(config);
        return factory;
    }

    @Bean
    public Clock clock() {
        return Clock.fixed(LocalDateTime.of(2018, 1, 1, 0, 0, 0).toInstant(UTC), ZoneId.of("UTC"));
    }

}
