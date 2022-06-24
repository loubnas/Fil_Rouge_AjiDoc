package com.example.filrouge_loubna.services.MedicalOffice;

import com.example.filrouge_loubna.dto.model.MedicalOfficeDto;
import com.example.filrouge_loubna.entities.MedicalOffice;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IMedicalOfficeService {

    MedicalOfficeDto AddMedicalOffice(MedicalOfficeDto medicalOffice);
    Page<MedicalOfficeDto> getAllMedicalOffices(int page , int limit);
    Page<MedicalOfficeDto> searchMedicalOffices(String keyword, int page , int limit);
    boolean deleteMedicalOffice(long id);
    MedicalOfficeDto updateMedicalOffice(MedicalOfficeDto medicalOffice);
    MedicalOfficeDto getMedicalOfficeById(long id);
}
