package com.example.ignitedropcreatepopulate.schedule;

import com.example.ignitedropcreatepopulate.service.IgniteService;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Async;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ScheduleService {

    @Autowired
    IgniteService igniteService;

    @Scheduled(fixedDelay = 1000*60*10, initialDelay = 5000)
    public void ddlService() {
        log.info("ddlService started");
        igniteService.dropAndCreateTable();
        log.info("ddlService ended");
    }

    @Scheduled(fixedDelay = 1000*60*25, initialDelay = 1000*60*20)
    public void deleteandinsert() {
        log.info("ddlService started");
        igniteService.dropAndCreateTable();
        log.info("ddlService ended");
        log.info("-------------------------------------------------------------------");
    }
}

