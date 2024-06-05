package com.example.collaborative_editor.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DataService {
    private final DataRepository dataRepository;

    public List<Data> findAllByDocumentID(String documentID) {
        return dataRepository.findAllByDocumentID(documentID);
    }

    public Data find(String id) {
        return dataRepository.findById(id).get();
    }

    public void save(Data data) {
        dataRepository.save(data);
    }
}
