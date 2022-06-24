package com.example.filrouge_loubna.services.UserDocuments;

import com.example.filrouge_loubna.dto.model.UserDocumentDto;
import com.example.filrouge_loubna.dto.services.IMapClassWithDto;
import com.example.filrouge_loubna.entities.UserDocument;
import com.example.filrouge_loubna.repositories.UserDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDocumentService implements  IUserDocumentService {

    @Autowired
    UserDocumentRepository userDocRepository;

    @Autowired
    IMapClassWithDto<UserDocument, UserDocumentDto> userDocMapping;



    // ADD UserDocument : ____________________________________________________________________________________

    @Override
    public UserDocumentDto AddUserDoc(UserDocumentDto userDocDto) {
        UserDocument userDocu= userDocMapping.convertToEntity(userDocDto, UserDocument.class);
        userDocu = userDocRepository.save(userDocu);
        return userDocMapping.convertToDto(userDocu, UserDocumentDto.class);
    }


    // get all users docuemnts :------------------------------------------------------------

    @Override
    public List<UserDocumentDto> getAllDocs() {
        List<UserDocument> userDocs = userDocRepository.findAll();
        return userDocMapping.convertListToListDto(userDocs, UserDocumentDto.class);
    }


    // Delete User Document : ____________________________________________________________________________________

    @Override
    public boolean deleteUserDoc(long id) {
        try {
            userDocRepository.deleteById(id);
        }catch (Exception ex){
            return false;
        }
        return true;
    }



    // Get User document By Id  : ____________________________________________________________________________________

    @Override
    public UserDocumentDto getUserDocById(long id) {
     UserDocument userDoc = userDocRepository.findById(id);
        return userDocMapping.convertToDto(userDoc, UserDocumentDto.class);
    }


    // Update User Document : ____________________________________________________________________________________

    @Override
    public UserDocumentDto updateUserDoc(UserDocumentDto userDocDto) {
        UserDocument userDoc =userDocMapping.convertToEntity(userDocDto, UserDocument.class);
        userDoc= userDocRepository.save( userDoc);

        return userDocMapping.convertToDto(userDoc, UserDocumentDto.class);
    }





}











