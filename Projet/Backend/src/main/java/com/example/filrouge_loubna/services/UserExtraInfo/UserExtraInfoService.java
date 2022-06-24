package com.example.filrouge_loubna.services.UserExtraInfo;

import com.example.filrouge_loubna.dto.model.UserDto;
import com.example.filrouge_loubna.dto.model.UserExtraInfoDto;
import com.example.filrouge_loubna.dto.services.IMapClassWithDto;
import com.example.filrouge_loubna.entities.User;
import com.example.filrouge_loubna.entities.UserExtraInfo;
import com.example.filrouge_loubna.repositories.UserExtraInfoRepository;
import com.example.filrouge_loubna.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserExtraInfoService implements  IUserExtraInfoService{

    @Autowired
    UserExtraInfoRepository userInfoRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    IMapClassWithDto<UserExtraInfo, UserExtraInfoDto> userInfoMapping;
    @Autowired
    IMapClassWithDto<User, UserDto> userMapping;

    // Add user info ---------------------------------------------------------------

    @Override
    public UserExtraInfoDto AddUserExtraInfo(UserExtraInfoDto userInfo) {
        UserExtraInfo userInfos = userInfoMapping.convertToEntity(userInfo, UserExtraInfo.class);
        User user=userRepository.findById(userInfo.getUser().getId());
        userInfos.setUser(user);

        userInfos = userInfoRepository.save(userInfos);
        return  userInfoMapping.convertToDto(userInfos, UserExtraInfoDto.class);
    }


    // get all users extra info :------------------------------------------------------------

    @Override
    public List<UserExtraInfoDto> getAllUsersExtraInfo() {
        List<UserExtraInfo> userinfos = userInfoRepository.findAll();
        return userInfoMapping.convertListToListDto(userinfos, UserExtraInfoDto.class);
    }

    // get all users specialites info :------------------------------------------------------------

    @Override
    public List<String> getSpecialites() {
        List<String> specs = userInfoRepository.getSpecialites();
        return specs;
    }


    // delete user info  :------------------------------------------------------------

    @Override
    public boolean deleteUsersExtraInfo(long id) {
        try {
            userInfoRepository.deleteById(id);
        }catch (Exception ex){
            return false;
        }
        return true;
    }

    // get info user   :------------------------------------------------------------

    @Override
    public UserExtraInfoDto getUserInfoById(long id) {
        UserExtraInfo userinfo = userInfoRepository.findById(id);
        return userInfoMapping.convertToDto(userinfo, UserExtraInfoDto.class);
    }


    // update user info   :------------------------------------------------------------

    @Override
    public UserExtraInfoDto updateUsersExtraInfo(UserExtraInfoDto userinfoDto) {
        UserExtraInfo userinfo =userInfoMapping.convertToEntity(userinfoDto, UserExtraInfo.class);

        System.out.println(userinfoDto.getId());
        User user=userRepository.findById(userinfoDto.getId()).get();
        //System.out.println(user);

        userinfo.setUser(user);
        userinfo = userInfoRepository.save(userinfo);

        return userInfoMapping.convertToDto(userinfo, UserExtraInfoDto.class);
    }


}
