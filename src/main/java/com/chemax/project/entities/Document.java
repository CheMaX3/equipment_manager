package com.chemax.project.entities;

import lombok.Data;

import javax.persistence.Id;

@Data
@org.springframework.data.mongodb.core.mapping.Document(collection = "passports")
public class Document {
    @Id
    private String id;

    private String name;
    private String type;
    private String size;
    private Integer equipmentId;
    private byte[] content;

    public Document(String id, String name, String type, String size, byte[] content) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.size = size;
        this.content = content;
    }

    public Document(String id, String name, String type, String size, Integer equipmentId, byte[] content) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.size = size;
        this.equipmentId = equipmentId;
        this.content = content;
    }

    public Document(String id, String name, byte[] content) {
        this.id = id;
        this.name = name;
        this.content = content;
    }

    public Document(String name) {
        this.name = name;
    }

    public Document() {
    }
}
