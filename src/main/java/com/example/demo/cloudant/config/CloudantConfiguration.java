package com.example.demo.cloudant.config;

import com.cloudant.client.api.ClientBuilder;
import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(CloudantConfigurationProperties.class)
public class CloudantConfiguration {


  @Autowired
  private CloudantConfigurationProperties config;

  @Bean
  public CloudantClient clientStudents() {
    return ClientBuilder.account(config.getUserName()).username(config.getUserName()).iamApiKey(config.getPassword())
        .build();
  }

  @Bean
  public Database studentsDatabase(CloudantClient client) {
    return client.database(config.getDbName(), true);
  }

}
