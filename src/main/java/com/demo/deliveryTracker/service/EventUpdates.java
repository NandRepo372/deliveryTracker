package com.demo.deliveryTracker.service;

import com.demo.deliveryTracker.pojo.Events;
import com.demo.deliveryTracker.repository.PackagesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EventUpdates {

    private final PackagesRepository packagesRepository;

    @Autowired
    public EventUpdates(PackagesRepository packagesRepository) {
        this.packagesRepository = packagesRepository;
    }

    @KafkaListener(topics = "delivery-events", groupId = "delivery-tracker")
    public void listenEvents(Events event){
        //Listen event
        log.info("received entry for event: {}", event);
        //update DB entry for event
        packagesRepository.findById(event.getPackageId()).ifPresentOrElse(
            packageDetails -> {
                packageDetails.setStatus(event.getEventType());
                packagesRepository.saveAndFlush(packageDetails);
                log.info("Updated package status to: {}", event.getEventType());
            },
            () -> log.warn("No package found with ID: {}", event.getPackageId())
        );
    }
}
