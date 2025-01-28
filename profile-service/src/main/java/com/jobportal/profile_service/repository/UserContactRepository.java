package com.jobportal.profile_service.repository;

import com.jobportal.profile_service.model.UserContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserContactRepository extends JpaRepository<UserContact, Long> {


    UserContact findByUserId(Long userId);
}
