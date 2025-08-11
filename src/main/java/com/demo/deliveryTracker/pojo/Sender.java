package com.demo.deliveryTracker.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Sender {
    private String name;
    private String address;
    private Long phoneNumber;
    private String email;
}
