package com.example.filrouge_loubna.services.Appointement;

import com.example.filrouge_loubna.dto.model.AppointementDto;
import com.example.filrouge_loubna.dto.model.AppointementDto;
import com.example.filrouge_loubna.dto.services.IMapClassWithDto;
import com.example.filrouge_loubna.entities.Appointement;
import com.example.filrouge_loubna.entities.Appointement;
import com.example.filrouge_loubna.repositories.AppointementRepository;
import com.example.filrouge_loubna.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointementService implements IAppointementService{

    @Autowired
    AppointementRepository appointementRepository;

    @Autowired
    IMapClassWithDto<Appointement, AppointementDto> appointementMapping;


    // Add appointement : -----------------------------------------------------------------------------

    @Override
    public AppointementDto AddAppointement(AppointementDto appointementDto) {
        System.out.println(appointementDto.getDateTimeAppointement());
        Appointement appointement = appointementMapping.convertToEntity(appointementDto, Appointement.class);
        appointement = appointementRepository.save(appointement);
        return appointementMapping.convertToDto(appointement, AppointementDto.class);
    }


    // get all  appointement  :------------------------------------------------------------

    @Override
    public List<AppointementDto> getAllAppointements(int page , int limit) {
        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<Appointement> appointement = appointementRepository.findAll(pageableRequest);
        return appointementMapping.convertPageToListDto(appointement, AppointementDto.class);
    }
    @Override
    public List<AppointementDto> getAllAppointementsByUserID(long Id) {
        List<Appointement> appointement = appointementRepository.findAllByUserId(Id);
        return appointementMapping.convertListToListDto(appointement, AppointementDto.class);
    }

    // Delete Review  : ____________________________________________________________________________________

    @Override
    public boolean deleteAppointement(long id) {
        try {
            appointementRepository.deleteById(id);
        }catch (Exception ex){
            return false;
        }
        return true;
    }


    // Get appointement By Id  : ____________________________________________________________________________________

    @Override
    public AppointementDto getAppointementById(long id) {
        Appointement appointement = appointementRepository.findById(id);
        return appointementMapping.convertToDto(appointement, AppointementDto.class);

    }

    // Update Review : ____________________________________________________________________________________

    @Override
    public AppointementDto updateAppointement(AppointementDto appointementDto) {
        Appointement appointement = appointementMapping.convertToEntity(appointementDto, Appointement.class);
        appointement= appointementRepository.save(appointement);
        return appointementMapping.convertToDto(appointement, AppointementDto.class);


    }


}
