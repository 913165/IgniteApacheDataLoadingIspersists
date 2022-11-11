package com.example.ignitedropcreatepopulate.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "apache.ignite")
@Data
@Component
public class IgniteProperties {
    private String createTable;
    private String dropTable;

}
