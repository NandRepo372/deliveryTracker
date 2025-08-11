package com.demo.deliveryTracker.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PackageDetails {
    private BigDecimal weight;
    private String dimensions; // e.g., "10x10x10 cm"
    private String description;
    private BigDecimal value; // e.g., monetary value of the package
}
