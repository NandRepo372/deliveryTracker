package com.demo.deliveryTracker.service;

import com.demo.deliveryTracker.entity.PackageDetails;
import com.demo.deliveryTracker.entity.Packages;
import com.demo.deliveryTracker.pojo.Events;
import com.demo.deliveryTracker.pojo.PackageRequest;
import com.demo.deliveryTracker.pojo.PackageResponse;
import com.demo.deliveryTracker.repository.PackagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.kafka.core.KafkaTemplate;
@Service
public class DeliveryServices {

    private final PackagesRepository packagesRepository;
    private final KafkaTemplate <String, Events> kafkaTemplate;

    @Autowired
    public DeliveryServices(PackagesRepository packagesRepository, KafkaTemplate<String, Events> kafkaTemplate) {
        this.packagesRepository = packagesRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public PackageResponse createPackage(PackageRequest packageRequest) {
        //create packageDetails
        final PackageDetails packageDetails = PackageDetails.builder()
                .weight(packageRequest.getPackageDetails().getWeight())
                .dimensions(packageRequest.getPackageDetails().getDimensions())
                .description(packageRequest.getPackageDetails().getDescription())
                .value(packageRequest.getPackageDetails().getValue())
                .build();

        //create package in DB
        final Packages packages = Packages.builder()
                .senderName(packageRequest.getSender().getName())
                .senderAddress(packageRequest.getSender().getAddress())
                .senderPhoneNumber(packageRequest.getSender().getPhoneNumber())
                .recipientName(packageRequest.getRecipient().getName())
                .recipientAddress(packageRequest.getRecipient().getAddress())
                .pickupDate(packageRequest.getPickupDate())
                .deliveryType(packageRequest.getDeliveryType())
                .status("PACKAGE_RECEIVED")
                .packageDetails(packageDetails)
                .trackingEnabled(true).build(); //setup tarckingEnabled seperately if req

        packagesRepository.saveAndFlush(packages);
        //create response and return it

        return PackageResponse.builder()
                .packageId(packages.getId())
                .eventType(packages.getStatus())
                .location(packages.getRecipientAddress())
                .date(packages.getPickupDate())
                .build();
    }

    public ResponseEntity createEvent(Events event) {
        //sent to kafka topic
        kafkaTemplate.send("delivery-Event", event);
        return ResponseEntity.ok("Event created successfully");
    }
}
