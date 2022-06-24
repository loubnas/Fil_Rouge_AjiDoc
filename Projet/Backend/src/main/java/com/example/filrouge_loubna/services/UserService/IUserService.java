package com.example.filrouge_loubna.services.UserService;

import com.example.filrouge_loubna.dto.model.UserDto;
import com.example.filrouge_loubna.entities.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IUserService {
    UserDto AddUser(UserDto user);
    Page<UserDto> getAllUsers(int page , int limit);
    List<String> getVilles();
    boolean deleteUser(long id);
    UserDto updateUser(UserDto user);
    UserDto getUserById(long id);
    User getByEmail(String email);
    UserDto changepass(UserDto user,UserDto userDB) throws Exception;



}
