package com.example.filrouge_loubna.services.MedicalOffice;


import com.example.filrouge_loubna.dto.model.MedicalOfficeDto;
import com.example.filrouge_loubna.dto.services.IMapClassWithDto;
import com.example.filrouge_loubna.entities.MedicalOffice;
import com.example.filrouge_loubna.entities.User;
import com.example.filrouge_loubna.helpers.PageConverter;
import com.example.filrouge_loubna.repositories.MedicalOfficeRepository;
import com.example.filrouge_loubna.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

@Service
public class MedicalOfficeService implements IMedicalOfficeService {

    @Autowired
    MedicalOfficeRepository medicalOfficeRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PageConverter pageConverter;

    @Autowired
    IMapClassWithDto<MedicalOffice, MedicalOfficeDto> medicalOfficeMapping;


    // Add medicalOffice : -----------------------------------------------------------------------------

    @Override
    public MedicalOfficeDto AddMedicalOffice(MedicalOfficeDto medicalOfficeDto) {
        MedicalOffice medicalOffice = medicalOfficeMapping.convertToEntity(medicalOfficeDto, MedicalOffice.class);

        User user=userRepository.findById(medicalOfficeDto.getAdministrator().getId());

        medicalOffice.setAdministrator(user);
        medicalOffice = medicalOfficeRepository.save(medicalOffice);
        return medicalOfficeMapping.convertToDto(medicalOffice, MedicalOfficeDto.class);
    }


    // get all  medicalOffice  :------------------------------------------------------------

    @Override
    public Page<MedicalOfficeDto> getAllMedicalOffices(int page , int limit) {
        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<MedicalOffice> medicalOffice = medicalOfficeRepository.findAllByOrderByIdAsc(pageableRequest);
        return pageConverter.MedicalOffice_Convert(medicalOffice);
    }

    @Override
    public Page<MedicalOfficeDto> searchMedicalOffices(String keyword, int page, int limit) {
        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<MedicalOffice> medicalOffices = medicalOfficeRepository.findMedicalOfficeByNameContainingIgnoreCaseOrAdressContainingIgnoreCaseOrAdministrator_VilleContainingIgnoreCaseOrAdministrator_UserExtraInfo_SpecialiteContainingIgnoreCaseOrAdministrator_firstnameContainingIgnoreCaseOrAdministrator_lastnameContainingIgnoreCase(keyword,keyword,keyword,keyword,keyword,keyword,pageableRequest);

        return pageConverter.MedicalOffice_Convert(medicalOffices);
        //return medicalOfficeMapping.convertPageToListDto(medicalOffices, MedicalOfficeDto.class);
    }
    // Delete Review  : ____________________________________________________________________________________

    @Override
    public boolean deleteMedicalOffice(long id) {
        try {
            medicalOfficeRepository.deleteById(id);
        }catch (Exception ex){
            return false;
        }
        return true;
    }


    // Get medicalOffice By Id  : ____________________________________________________________________________________

    @Override
    public MedicalOfficeDto getMedicalOfficeById(long id) {
        MedicalOffice medicalOffice = medicalOfficeRepository.findById(id);
        return medicalOfficeMapping.convertToDto(medicalOffice, MedicalOfficeDto.class);

    }

    // Update Review : ____________________________________________________________________________________

    @Override
    public MedicalOfficeDto updateMedicalOffice(MedicalOfficeDto medicalOfficeDto) {
        MedicalOffice medicalOffice = medicalOfficeMapping.convertToEntity(medicalOfficeDto, MedicalOffice.class);
        medicalOffice= medicalOfficeRepository.save(medicalOffice);
        return medicalOfficeMapping.convertToDto(medicalOffice, MedicalOfficeDto.class);


    }


}
