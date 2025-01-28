package com.jobportal.profile_service.serviceimpl;

import com.jobportal.profile_service.model.UserContact;
import com.jobportal.profile_service.repository.UserContactRepository;
import com.jobportal.profile_service.service.UserContactService;
import com.jobportal.profile_service.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserContactServiceImpl implements UserContactService {

    @Autowired
    private UserContactRepository userContactRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserContact saveUserContact(UserContact userContact) {
        if (userContact == null) {
            throw new IllegalArgumentException("UserContact cannot be null");
        }
        return userContactRepository.save(userContact);
    }

    @Override
    public Optional<UserContact> findUserContactById(Long id) {
        Optional<UserContact> userContact = userContactRepository.findById(id);
        if (userContact.isEmpty()) {
            throw new ResourceNotFoundException("UserContact not found with id: " + id);
        }
        return userContact;
    }

    @Override
    public Optional<UserContact> findUserContactByUserId(Long userId) {
        Optional<UserContact> userContact = Optional.ofNullable(userContactRepository.findByUserId(userId));
        if (userContact.isEmpty()) {
            throw new ResourceNotFoundException("UserContact not found for user with id: " + userId);
        }
        return userContact;
    }

    @Override
    public void deleteUserContactById(Long id) {
        Optional<UserContact> userContact = findUserContactById(id);
        if (userContact.isPresent()) {
            userContactRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Cannot delete UserContact. Not found with id: " + id);
        }
    }
}
