package com.example.collaborative_editor.data;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@lombok.Data
@Entity
public class Data {

    @ElementCollection(fetch = jakarta.persistence.FetchType.EAGER)
    private List<Op> ops;

    @Id
    @Column
    private String documentID;
}
