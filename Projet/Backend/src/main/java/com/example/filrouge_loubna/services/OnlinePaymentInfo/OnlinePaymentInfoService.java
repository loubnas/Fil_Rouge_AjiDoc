package com.example.filrouge_loubna.services.OnlinePaymentInfo;


import com.example.filrouge_loubna.dto.model.OnlinePaymentInfoDto;
import com.example.filrouge_loubna.dto.services.IMapClassWithDto;
import com.example.filrouge_loubna.entities.OnlinePaymentInfo;
import com.example.filrouge_loubna.repositories.OnlinePaymentInfoRepository;
import com.example.filrouge_loubna.services.OnlinePaymentInfo.IOnlinePaymentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OnlinePaymentInfoService implements IOnlinePaymentInfoService {

    @Autowired
    OnlinePaymentInfoRepository onlinePaymentInfoRepository;

    @Autowired
    IMapClassWithDto<OnlinePaymentInfo, OnlinePaymentInfoDto> onlinePaymentInfoMapping;


    // Add onlinePaymentInfo : -----------------------------------------------------------------------------

    @Override
    public OnlinePaymentInfoDto AddOnlinePaymentInfo(OnlinePaymentInfoDto onlinePaymentInfoDto) {
        OnlinePaymentInfo onlinePaymentInfo = onlinePaymentInfoMapping.convertToEntity(onlinePaymentInfoDto, OnlinePaymentInfo.class);
        onlinePaymentInfo = onlinePaymentInfoRepository.save(onlinePaymentInfo);
        return onlinePaymentInfoMapping.convertToDto(onlinePaymentInfo, OnlinePaymentInfoDto.class);
    }


    // get all  onlinePaymentInfo  :------------------------------------------------------------

    @Override
    public List<OnlinePaymentInfoDto> getAllOnlinePaymentInfos(int page , int limit) {
        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<OnlinePaymentInfo> onlinePaymentInfo = onlinePaymentInfoRepository.findAll(pageableRequest);
        return onlinePaymentInfoMapping.convertPageToListDto(onlinePaymentInfo, OnlinePaymentInfoDto.class);
    }

    // Delete Review  : ____________________________________________________________________________________

    @Override
    public boolean deleteOnlinePaymentInfo(long id) {
        try {
            onlinePaymentInfoRepository.deleteById(id);
        }catch (Exception ex){
            return false;
        }
        return true;
    }


    // Get onlinePaymentInfo By Id  : ____________________________________________________________________________________

    @Override
    public OnlinePaymentInfoDto getOnlinePaymentInfoById(long id) {
        OnlinePaymentInfo onlinePaymentInfo = onlinePaymentInfoRepository.findById(id);
        return onlinePaymentInfoMapping.convertToDto(onlinePaymentInfo, OnlinePaymentInfoDto.class);

    }

    // Update Review : ____________________________________________________________________________________

    @Override
    public OnlinePaymentInfoDto updateOnlinePaymentInfo(OnlinePaymentInfoDto onlinePaymentInfoDto) {
        OnlinePaymentInfo onlinePaymentInfo = onlinePaymentInfoMapping.convertToEntity(onlinePaymentInfoDto, OnlinePaymentInfo.class);
        onlinePaymentInfo= onlinePaymentInfoRepository.save(onlinePaymentInfo);
        return onlinePaymentInfoMapping.convertToDto(onlinePaymentInfo, OnlinePaymentInfoDto.class);


    }


}
