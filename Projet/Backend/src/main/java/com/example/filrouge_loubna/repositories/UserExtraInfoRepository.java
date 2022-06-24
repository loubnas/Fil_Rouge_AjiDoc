package com.example.filrouge_loubna.repositories;

import com.example.filrouge_loubna.entities.UserExtraInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserExtraInfoRepository extends JpaRepository<UserExtraInfo,Long> {
    UserExtraInfo findById(long id);
    Void deleteById(long id);

    @Query("SELECT DISTINCT e.specialite FROM UserExtraInfo e")
    List<String> getSpecialites();
}
