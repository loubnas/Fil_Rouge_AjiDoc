package com.example.filrouge_loubna.repositories;

import com.example.filrouge_loubna.entities.UserDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  UserDocumentRepository extends JpaRepository<UserDocument,Long> {
    UserDocument findById(long id);
    Void deleteById(long id);
}
