package com.api.ecommerce.services;

import com.api.ecommerce.entities.User;
import com.api.ecommerce.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

  @Autowired
  private IUserRepository userRepository;

  @Override
  public Optional<User> getUserById(String id) {
    return userRepository.findById(id);
  }
}
