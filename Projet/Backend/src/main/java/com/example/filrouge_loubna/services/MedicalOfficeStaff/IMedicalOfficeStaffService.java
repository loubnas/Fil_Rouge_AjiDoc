package com.example.filrouge_loubna.services.MedicalOfficeStaff;

import com.example.filrouge_loubna.dto.model.MedicalOfficeStaffDto;

import java.util.List;

public interface IMedicalOfficeStaffService {

    MedicalOfficeStaffDto AddMedicalOfficeStaff(MedicalOfficeStaffDto medicalOfficeStaff);
    List<MedicalOfficeStaffDto> getAllMedicalOfficeStaffs(int page , int limit);
    boolean deleteMedicalOfficeStaff(long id);
    MedicalOfficeStaffDto updateMedicalOfficeStaff(MedicalOfficeStaffDto medicalOfficeStaff);
    MedicalOfficeStaffDto getMedicalOfficeStaffById(long id);
}
