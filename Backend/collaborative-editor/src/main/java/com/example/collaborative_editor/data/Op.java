package com.example.collaborative_editor.data;

import jakarta.persistence.Embeddable;

@lombok.Data
@Embeddable
public class Op {
    private String insert;
    private Long retain;
    private Long delete;
    private Attribute attributes;
}
