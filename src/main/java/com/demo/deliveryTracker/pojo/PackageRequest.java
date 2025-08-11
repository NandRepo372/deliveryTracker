package com.demo.deliveryTracker.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PackageRequest {
    private Sender sender;
    private Recipient recipient;
    private PackageDetails packageDetails;
    private String pickupDate;
    private String deliveryType;
    private String trackingEnabled;
}
