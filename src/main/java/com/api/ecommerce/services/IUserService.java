package com.api.ecommerce.services;

import com.api.ecommerce.entities.User;

import java.util.Optional;

public interface IUserService {
  Optional<User> getUserById(String id);
}
