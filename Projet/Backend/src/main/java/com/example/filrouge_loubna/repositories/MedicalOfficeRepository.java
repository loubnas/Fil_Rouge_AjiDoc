package com.example.filrouge_loubna.repositories;

import com.example.filrouge_loubna.entities.Appointement;
import com.example.filrouge_loubna.entities.MedicalOffice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface MedicalOfficeRepository  extends PagingAndSortingRepository<MedicalOffice,Long> {
    MedicalOffice findById(long id);
    Void deleteById(long id);
    Page<MedicalOffice> findAllByOrderByIdAsc(Pageable p);

    Page<MedicalOffice> findMedicalOfficeByNameContainingIgnoreCaseOrAdressContainingIgnoreCaseOrAdministrator_VilleContainingIgnoreCaseOrAdministrator_UserExtraInfo_SpecialiteContainingIgnoreCaseOrAdministrator_firstnameContainingIgnoreCaseOrAdministrator_lastnameContainingIgnoreCase(String name,String adress,String ville,String specialite, String f, String l,  Pageable p);

}

