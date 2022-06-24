package com.example.filrouge_loubna.services.UserDocuments;

import com.example.filrouge_loubna.dto.model.UserDocumentDto;

import java.util.List;

public interface IUserDocumentService {
    UserDocumentDto AddUserDoc(UserDocumentDto userDocument);
    List<UserDocumentDto> getAllDocs();
    boolean deleteUserDoc(long id);
    UserDocumentDto updateUserDoc( UserDocumentDto userDocument);
    UserDocumentDto getUserDocById(long id);
}
