package com.example.filrouge_loubna.repositories;

import com.example.filrouge_loubna.entities.Appointement;
import com.example.filrouge_loubna.entities.MedicalOfficeStaff;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MedicalOfficeStaffRepository  extends PagingAndSortingRepository<MedicalOfficeStaff,Long> {
    MedicalOfficeStaff findById(long id);


    @Override
    void deleteById(Long aLong);
}
