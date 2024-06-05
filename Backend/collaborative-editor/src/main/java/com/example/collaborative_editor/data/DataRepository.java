package com.example.collaborative_editor.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataRepository extends JpaRepository<Data, String> {
    List<Data> findAllByDocumentID(String documentID);
}
