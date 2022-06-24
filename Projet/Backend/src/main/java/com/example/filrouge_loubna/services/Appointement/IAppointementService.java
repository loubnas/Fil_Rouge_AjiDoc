package com.example.filrouge_loubna.services.Appointement;

import com.example.filrouge_loubna.dto.model.AppointementDto;

import java.util.List;

public interface IAppointementService {
    AppointementDto AddAppointement(AppointementDto appointement);
    List<AppointementDto> getAllAppointements(int page , int limit);

    List<AppointementDto> getAllAppointementsByUserID(long Id);

    boolean deleteAppointement(long id);
    AppointementDto updateAppointement(AppointementDto appointement);
    AppointementDto getAppointementById(long id);
}
