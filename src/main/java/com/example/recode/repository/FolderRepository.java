package com.example.recode.repository;

import com.example.recode.domain.Folder;
import com.example.recode.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FolderRepository extends JpaRepository<Folder, Long> {
    List<Folder> findAllByUser(Users users);
}
