package com.jwt_security.JwtSpringSecurity.controller;

import com.jwt_security.JwtSpringSecurity.Dtos.Responses.IpLocationResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/userinfo")
public class UserInfoController {
    @GetMapping("/get-location")
    public ResponseEntity<?> getUserLocation(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        // Call external API (e.g., ip-api.com)
        String url = "http://ip-api.com/json/" + ip;

        RestTemplate restTemplate = new RestTemplate();
        IpLocationResponse response = restTemplate.getForObject(url, IpLocationResponse.class);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @GetMapping("/device-info")
    public ResponseEntity<?> getDeviceInfo(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        return new ResponseEntity<>("User-Agent: " + userAgent, HttpStatus.OK);
    }
}
