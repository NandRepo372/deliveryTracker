package com.demo.deliveryTracker.controller;

import com.demo.deliveryTracker.pojo.Events;
import com.demo.deliveryTracker.pojo.PackageRequest;
import com.demo.deliveryTracker.pojo.PackageResponse;
import com.demo.deliveryTracker.service.DeliveryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DeliveryTrackController {

    private final DeliveryServices deliveryServices;

    @Autowired
    public DeliveryTrackController(DeliveryServices deliveryServices) {
        this.deliveryServices = deliveryServices;
    }

    @PostMapping("/delivery/newPackage/")
    public PackageResponse createNewPackage(@RequestBody PackageRequest packageRequest) {
       return  deliveryServices.createPackage(packageRequest);
    }


    @PostMapping("/delivery/events/")
    public ResponseEntity packageEvents(@RequestBody Events event) {
        return  deliveryServices.createEvent(event);
    }

    @GetMapping("/delivery/package/{packageId}")
    public ResponseEntity<PackageResponse> getPackageDetails(@PathVariable Long packageId) {
        return deliveryServices.getPackageDetails(packageId);
    }

}
