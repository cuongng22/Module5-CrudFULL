package com.codegym.module5_backend.service.user;

import com.codegym.module5_backend.model.entity.User;
import com.codegym.module5_backend.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends IGeneralService<User>, UserDetailsService {
    User findByUserName(String username);
}
