package com.demo.deliveryTracker.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "packages")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Packages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "senderName")
    private String senderName;
    @Column(name = "senderAdddress")
    private String senderAddress;
    @Column(name="senderPhoneNumber")
    private Long senderPhoneNumber;
    @Column(name="recipientName")
    private String recipientName;
    @Column(name="recipientAddress")
    private String recipientAddress;
    @Column(name="pickupDate")
    private String pickupDate;
    @Column(name="deliveryType")
    private String deliveryType;
    @Column(name="trackingEnabled")
    private boolean trackingEnabled;
    @Column(name="status")
    private String status; // e.g. "Package Received","Out for Delivery", "Dispatched", "Delivered"
    @OneToOne
    @JoinColumn(name="packageDetails", referencedColumnName = "id")
    private PackageDetails packageDetails;

}
