package com.jobportal.application_service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "job-listing-service")
public interface JobListingFeignClient {

    @GetMapping("/api/jobs/{id}")
    Object getJobById(@PathVariable("id") Long id);
}
