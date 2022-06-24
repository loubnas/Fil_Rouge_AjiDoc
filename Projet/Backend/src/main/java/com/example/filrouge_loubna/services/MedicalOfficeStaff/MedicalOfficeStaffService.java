package com.example.filrouge_loubna.services.MedicalOfficeStaff;


import com.example.filrouge_loubna.dto.model.MedicalOfficeStaffDto;
import com.example.filrouge_loubna.dto.services.IMapClassWithDto;
import com.example.filrouge_loubna.entities.MedicalOffice;
import com.example.filrouge_loubna.entities.MedicalOfficeStaff;
import com.example.filrouge_loubna.entities.User;
import com.example.filrouge_loubna.repositories.MedicalOfficeRepository;
import com.example.filrouge_loubna.repositories.MedicalOfficeStaffRepository;
import com.example.filrouge_loubna.services.MedicalOfficeStaff.IMedicalOfficeStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicalOfficeStaffService implements IMedicalOfficeStaffService {

    @Autowired
    MedicalOfficeStaffRepository medicalOfficeStaffRepository;
    @Autowired
    MedicalOfficeRepository medicalOfficeRepository;

    @Autowired
    IMapClassWithDto<MedicalOfficeStaff, MedicalOfficeStaffDto> medicalOfficeStaffMapping;


    // Add medicalOfficeStaff : -----------------------------------------------------------------------------

    @Override
    public MedicalOfficeStaffDto AddMedicalOfficeStaff(MedicalOfficeStaffDto medicalOfficeStaffDto) {
        MedicalOfficeStaff medicalOfficeStaff = medicalOfficeStaffMapping.convertToEntity(medicalOfficeStaffDto, MedicalOfficeStaff.class);
        medicalOfficeStaff.setId(medicalOfficeStaff.getMedicalOfficeStaffUser().getId());
        medicalOfficeStaff = medicalOfficeStaffRepository.save(medicalOfficeStaff);

        /*Optional<MedicalOffice> MO=medicalOfficeRepository.findById(medicalOfficeStaff.getMedicalOffice().getId());
        MO.get().getMedicalOfficeStaffList().add(medicalOfficeStaff);
        medicalOfficeRepository.save(MO.get());*/

        return medicalOfficeStaffMapping.convertToDto(medicalOfficeStaff, MedicalOfficeStaffDto.class);
    }


    // get all  medicalOfficeStaff  :------------------------------------------------------------

    @Override
    public List<MedicalOfficeStaffDto> getAllMedicalOfficeStaffs(int page , int limit) {
        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<MedicalOfficeStaff> medicalOfficeStaff = medicalOfficeStaffRepository.findAll(pageableRequest);
        return medicalOfficeStaffMapping.convertPageToListDto(medicalOfficeStaff, MedicalOfficeStaffDto.class);
    }

    // Delete Review  : ____________________________________________________________________________________

    @Override
    public boolean deleteMedicalOfficeStaff(long id) {
        //try {
            medicalOfficeStaffRepository.deleteById(id);
        //}catch (Exception ex){
        //    return false;
        //}
        return true;
    }


    // Get medicalOfficeStaff By Id  : ____________________________________________________________________________________

    @Override
    public MedicalOfficeStaffDto getMedicalOfficeStaffById(long id) {
        MedicalOfficeStaff medicalOfficeStaff = medicalOfficeStaffRepository.findById(id);
        return medicalOfficeStaffMapping.convertToDto(medicalOfficeStaff, MedicalOfficeStaffDto.class);

    }

    // Update Review : ____________________________________________________________________________________

    @Override
    public MedicalOfficeStaffDto updateMedicalOfficeStaff(MedicalOfficeStaffDto medicalOfficeStaffDto) {

        //update
        MedicalOfficeStaff medicalOfficeStaff = medicalOfficeStaffMapping.convertToEntity(medicalOfficeStaffDto, MedicalOfficeStaff.class);
        medicalOfficeStaff= medicalOfficeStaffRepository.save(medicalOfficeStaff);



        return medicalOfficeStaffMapping.convertToDto(medicalOfficeStaff, MedicalOfficeStaffDto.class);


    }


}
