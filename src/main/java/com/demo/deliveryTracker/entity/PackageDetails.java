package com.demo.deliveryTracker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "packageDetails")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PackageDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal weight;
    private String dimensions; // e.g., "10x10x10 cm"
    private String description;
    private BigDecimal value; // e.g., monetary value of the package

}
