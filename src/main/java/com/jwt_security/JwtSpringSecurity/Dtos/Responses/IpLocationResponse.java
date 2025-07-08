package com.jwt_security.JwtSpringSecurity.Dtos.Responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IpLocationResponse {
    private String status;
    private String country;
    private String city;
    private String regionName;
    private String query;
}
