package com.example.filrouge_loubna.services.UserExtraInfo;

import com.example.filrouge_loubna.dto.model.UserExtraInfoDto;

import java.util.List;

public interface IUserExtraInfoService {

    UserExtraInfoDto AddUserExtraInfo(UserExtraInfoDto user);
    List<UserExtraInfoDto> getAllUsersExtraInfo();
    List<String> getSpecialites();
    boolean deleteUsersExtraInfo(long id);
    UserExtraInfoDto updateUsersExtraInfo(UserExtraInfoDto userinfo);
    UserExtraInfoDto getUserInfoById(long id);

}
