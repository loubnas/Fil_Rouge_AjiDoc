package com.example.filrouge_loubna.helpers;

import com.example.filrouge_loubna.dto.model.*;
import com.example.filrouge_loubna.dto.services.IMapClassWithDto;
import com.example.filrouge_loubna.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class PageConverter {


    @Autowired
    IMapClassWithDto<Appointement, AppointementDto> appointementMapping;

    @Autowired
    IMapClassWithDto<MedicalOffice, MedicalOfficeDto> medicalOfficeMapping;

    @Autowired
    IMapClassWithDto<MedicalOfficeStaff, MedicalOfficeStaffDto> medicalOfficeStaffMapping;

    @Autowired
    IMapClassWithDto<OnlinePaymentInfo, OnlinePaymentInfoDto> onlinePaymentMapping;

    @Autowired
    IMapClassWithDto<Payment, PaymentDto> paymentMapping;

    @Autowired
    IMapClassWithDto<Review, ReviewDto> reviewMapping;

    @Autowired
    IMapClassWithDto<UserDocument, UserDocumentDto> userDocumentMapping;

    @Autowired
    IMapClassWithDto<UserExtraInfo, UserExtraInfoDto> userExtraInfoMapping;

    @Autowired
    IMapClassWithDto<User, UserDto> userMapping;


    public  Page<AppointementDto> Appointement_Convert(Page<Appointement> original){
        Page<AppointementDto> dtoPage = original.map(new Function<Appointement, AppointementDto>() {
            @Override
            public AppointementDto apply(Appointement object) {
                return appointementMapping.convertToDto(object,AppointementDto.class);
            }
        });
        return  dtoPage;
    }



    public  Page<MedicalOfficeDto> MedicalOffice_Convert(Page<MedicalOffice> original){
        Page<MedicalOfficeDto> dtoPage = original.map(new Function<MedicalOffice, MedicalOfficeDto>() {
            @Override
            public MedicalOfficeDto apply(MedicalOffice object) {
                return medicalOfficeMapping.convertToDto(object,MedicalOfficeDto.class);
            }
        });
        return  dtoPage;
    }


    public  Page<MedicalOfficeStaffDto> MedicalOfficeStaff_Convert(Page<MedicalOfficeStaff> original){
        Page<MedicalOfficeStaffDto> dtoPage = original.map(new Function<MedicalOfficeStaff, MedicalOfficeStaffDto>() {
            @Override
            public MedicalOfficeStaffDto apply(MedicalOfficeStaff object) {
                return medicalOfficeStaffMapping.convertToDto(object,MedicalOfficeStaffDto.class);
            }
        });
        return  dtoPage;
    }


    public  Page<OnlinePaymentInfoDto> OnlinePaymentInfo_Convert(Page<OnlinePaymentInfo> original){
        Page<OnlinePaymentInfoDto> dtoPage = original.map(new Function<OnlinePaymentInfo, OnlinePaymentInfoDto>() {
            @Override
            public OnlinePaymentInfoDto apply(OnlinePaymentInfo object) {
                return onlinePaymentMapping.convertToDto(object,OnlinePaymentInfoDto.class);
            }
        });
        return  dtoPage;
    }


    public  Page<PaymentDto> Payment_Convert(Page<Payment> original){
        Page<PaymentDto> dtoPage = original.map(new Function<Payment, PaymentDto>() {
            @Override
            public PaymentDto apply(Payment object) {
                return paymentMapping.convertToDto(object,PaymentDto.class);
            }
        });
        return  dtoPage;
    }


    public  Page<ReviewDto> Review_Convert(Page<Review> original){
        Page<ReviewDto> dtoPage = original.map(new Function<Review, ReviewDto>() {
            @Override
            public ReviewDto apply(Review object) {
                return reviewMapping.convertToDto(object,ReviewDto.class);
            }
        });
        return  dtoPage;
    }


    public  Page<UserDocumentDto> UserDocument_Convert(Page<UserDocument> original){
        Page<UserDocumentDto> dtoPage = original.map(new Function<UserDocument, UserDocumentDto>() {
            @Override
            public UserDocumentDto apply(UserDocument object) {
                return userDocumentMapping.convertToDto(object,UserDocumentDto.class);
            }
        });
        return  dtoPage;
    }


    public  Page<UserExtraInfoDto> UserExtraInfo_Convert(Page<UserExtraInfo> original){
        Page<UserExtraInfoDto> dtoPage = original.map(new Function<UserExtraInfo, UserExtraInfoDto>() {
            @Override
            public UserExtraInfoDto apply(UserExtraInfo object) {
                return userExtraInfoMapping.convertToDto(object,UserExtraInfoDto.class);
            }
        });
        return  dtoPage;
    }


    public  Page<UserDto> User_Convert(Page<User> original){
        Page<UserDto> dtoPage = original.map(new Function<User, UserDto>() {
            @Override
            public UserDto apply(User object) {
                return userMapping.convertToDto(object,UserDto.class);
            }
        });
        return  dtoPage;
    }
}
