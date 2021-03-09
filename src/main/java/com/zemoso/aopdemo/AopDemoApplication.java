package com.zemoso.aopdemo;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.MigrationVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.boot.jdbc.DataSourceBuilder;

import javax.sql.DataSource;

@SpringBootApplication
public class AopDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopDemoApplication.class, args);
	}

	@Bean
	public JdbcTemplate postgresJdbcTemplate(@Qualifier("empDataSource") DataSource dsPostgres) {
		return new JdbcTemplate(dsPostgres);
	}

	@Bean(name = "empDataSource")
	DataSource empDataSource(
			@Value("${emp-database.url}") String jdbcUrl,
			@Value("${emp-database.username}") String jdbcUsername,
			@Value("${emp-database.password}") String jdbcPassword) {
		DataSource dataSource = DataSourceBuilder.create().url(jdbcUrl).username(jdbcUsername).password(jdbcPassword).build();
		migrateFlywayBookService(dataSource);
		return dataSource;
	}

	private void migrateFlywayBookService(DataSource dataSource){
		Flyway flyway = Flyway.configure()
				.dataSource(dataSource)
				.baselineOnMigrate(true)
				.baselineVersion("0")
				.locations("db/migration")
				.target(MigrationVersion.LATEST)
				.outOfOrder(true)
				.validateOnMigrate(true)
				.load();
		flyway.migrate();
	}

}
