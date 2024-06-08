package com.example.collaborative_editor.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Embeddable;

@lombok.Data
@Embeddable
public class Attribute {
    private Long header;
    private String font;
    private String list;
    private Boolean bold;
    private Boolean italics;
    private Boolean underline;
    private String color;
    private String background;
    private String script;
    private String align;
    private Boolean blockquote;

    @JsonProperty("code-block")
    private String code_block;
}
