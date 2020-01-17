package com.flywayTest.FlywayTestApplication;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.Location;
import org.flywaydb.core.api.configuration.Configuration;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationInitializer;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;

import javax.sql.DataSource;
import java.util.Properties;

@SpringBootApplication
public class FlywayTestApplication {
	@Autowired
	DefaultListableBeanFactory factory;

	public static void main(String[] args) {
		SpringApplication.run(FlywayTestApplication.class, args);

	}

	@Autowired
	DataSource dataSource;

	@Bean
	/**
	 * This part of code migrates scripts using default script location from
	 * application.properties file
	 */
	FlywayMigrationInitializer flywayInitializer(@Autowired Flyway flyway) {
		return new FlywayMigrationInitializer(flyway, null);
	}


	/**
	 * This part of code is working after Spring boot startup and creates another instance of flyway
	 * with another script location
	 * @param event
	 * @throws Exception
	 */
	@EventListener
	public void onStartup(ApplicationReadyEvent event) throws Exception {
		FluentConfiguration configuration = new FluentConfiguration();
				configuration.locations("classpath:/db/migration2");
				configuration.dataSource(dataSource);
				configuration.ignoreMissingMigrations(true);
				configuration.baselineOnMigrate(true);
				configuration.outOfOrder(true);
				new Flyway(configuration).migrate();
	}






}
