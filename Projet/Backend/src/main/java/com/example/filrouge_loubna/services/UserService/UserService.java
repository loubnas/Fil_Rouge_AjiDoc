package com.example.filrouge_loubna.services.UserService;

import com.example.filrouge_loubna.dto.model.UserDto;
import com.example.filrouge_loubna.dto.services.IMapClassWithDto;
import com.example.filrouge_loubna.entities.MedicalOffice;
import com.example.filrouge_loubna.entities.User;
import com.example.filrouge_loubna.helpers.PageConverter;
import com.example.filrouge_loubna.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService, UserDetailsService {


    @Autowired
    UserRepository userRepository;

    @Autowired
    PageConverter pageConverter;

    @Autowired
    IMapClassWithDto<User, UserDto> userMapping;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    // ADD User : ____________________________________________________________________________________

    @Override
    public UserDto AddUser(UserDto userDto) {
        String pass = bCryptPasswordEncoder.encode(userDto.getPassword());
        userDto.setPassword(pass);
        userDto.setType(null);// simple or MO_user Only
        User user = userMapping.convertToEntity(userDto, User.class);
        user = userRepository.save(user);
        return userMapping.convertToDto(user, UserDto.class);
    }


    // Get All Users : ____________________________________________________________________________________

    @Override
    public Page<UserDto> getAllUsers(int page, int limit) {
        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<User> users = userRepository.findAllByOrderByIdAsc(pageableRequest);
        return pageConverter.User_Convert(users);
    }
    // Get All Users Villes : ____________________________________________________________________________________

    @Override
    public List<String> getVilles() {

        List<String> villes =userRepository.getVilles();
        return villes;
    }

    // Get User By Id  : ____________________________________________________________________________________

    @Override
    public UserDto getUserById(long id) {
      User user = userRepository.findById(id);
        return userMapping.convertToDto(user, UserDto.class);
    }


    // Delete User : ____________________________________________________________________________________

    @Override
    public boolean deleteUser(long id) {
        try {
           userRepository.deleteById(id);
        }catch (Exception ex){
            return false;
        }
        return true;
    }


    // Update User : ____________________________________________________________________________________

    @Override
    public UserDto updateUser(UserDto userDto) {
        String pass = bCryptPasswordEncoder.encode(userDto.getPassword());
        userDto.setPassword(pass);
        User user =userMapping.convertToEntity(userDto, User.class);
        user = userRepository.save(user);

        return userMapping.convertToDto(user, UserDto.class);
    }


    //-------------- Authentication Security ------------------------------------------------------------------------------

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User userEntity = userRepository.findByEmail(email);

        if (userEntity == null) throw new UsernameNotFoundException(email);

        return new org.springframework.security.core.userdetails.User(userEntity.getEmail(), userEntity.getPassword(), new ArrayList<>());
    }

    //---------------------------------------------------------------------------------------------
    @Override
    public User getByEmail(String email) throws UsernameNotFoundException {
        User userEntity = userRepository.findByEmail(email);

        if (userEntity == null) throw new UsernameNotFoundException(email);

        return userEntity;
    }


    @Override
    public UserDto changepass(UserDto user,UserDto userDB) throws Exception {
        User userUpdated = null;

        String newpasswordcrypt = bCryptPasswordEncoder.encode(user.getNewpassword());

        Boolean isPasswordCorrect = bCryptPasswordEncoder.matches(user.getPassword(), userDB.getPassword());
        if (isPasswordCorrect) {
            User u=userMapping.convertToEntity(userDB,User.class);
            u.setPassword(newpasswordcrypt);

            userUpdated = userRepository.save(u);

        }

        return userMapping.convertToDto(userUpdated, UserDto.class);


    }
}
