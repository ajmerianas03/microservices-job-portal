package com.jobportal.application_service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "profile-service")
public interface ProfileFeignClient {

   /* @GetMapping("/api/profiles/{id}")
    Object getUserProfileById(@PathVariable("id") Long id);
*/
    @GetMapping("profile/userProfiles/{id}")
    Object getUserProfileById(@PathVariable Long id);
}
