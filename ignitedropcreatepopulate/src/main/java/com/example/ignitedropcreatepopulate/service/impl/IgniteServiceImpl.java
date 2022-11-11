package com.example.ignitedropcreatepopulate.service.impl;

import com.example.ignitedropcreatepopulate.properties.IgniteProperties;
import com.example.ignitedropcreatepopulate.service.IgniteService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ignite.internal.util.IgniteUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Slf4j
@Service
public class IgniteServiceImpl implements IgniteService {
    @Autowired
    IgniteProperties igniteProperties;

    @Autowired
    DataSource dataSource;

    @Override
    public void dropAndCreateTable() {
        log.info("dropAndCreateTable : started");
        try (Connection conn = dataSource.getConnection()) {

            executeCommand(conn, igniteProperties.getDropTable());
            executeCommand(conn, igniteProperties.getCreateTable());

            File file = new File("src/main/resources/city.csv");
            String absolutePath = file.getAbsolutePath();
            log.info("absolute path for the resource file  {}",absolutePath);
            executeCommand(conn, "COPY FROM '" +
                    IgniteUtils.resolveIgnitePath(absolutePath) + "' " +
                    "INTO City (ID, Name, CountryCode, District, Population) FORMAT CSV");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        log.info("dropAndCreateTable : ended");
    }

    private static void executeCommand(Connection conn, String sql) throws Exception {
        log.info("executing query : {}", sql);
        long starttime = System.currentTimeMillis();
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        }
        long endtime = System.currentTimeMillis();
        long totaltimeinseconds  = (endtime - starttime )/1000;
        log.info("executed query : {}", sql);
        log.info("total time taken in seconds {} ",totaltimeinseconds);
    }
}
