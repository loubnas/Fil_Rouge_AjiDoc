package com.example.filrouge_loubna.repositories;

import com.example.filrouge_loubna.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
 public interface  UserRepository extends PagingAndSortingRepository<User,Long> {
 Page<User> findAllByOrderByIdAsc(Pageable p);
  User findById(long id);
  Void deleteById(long id);
  User findByEmail(String email);
 @Query("SELECT DISTINCT e.ville FROM User e")
  List<String> getVilles();
}
