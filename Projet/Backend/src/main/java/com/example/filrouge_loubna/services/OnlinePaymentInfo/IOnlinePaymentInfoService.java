package com.example.filrouge_loubna.services.OnlinePaymentInfo;

import com.example.filrouge_loubna.dto.model.OnlinePaymentInfoDto;

import java.util.List;

public interface IOnlinePaymentInfoService {
    OnlinePaymentInfoDto AddOnlinePaymentInfo(OnlinePaymentInfoDto onlinePaymentInfo);
    List<OnlinePaymentInfoDto> getAllOnlinePaymentInfos(int page , int limit);
    boolean deleteOnlinePaymentInfo(long id);
    OnlinePaymentInfoDto updateOnlinePaymentInfo(OnlinePaymentInfoDto onlinePaymentInfo);
    OnlinePaymentInfoDto getOnlinePaymentInfoById(long id);
}
