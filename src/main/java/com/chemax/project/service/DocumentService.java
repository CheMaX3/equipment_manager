package com.chemax.project.service;

import com.chemax.project.repository.DocumentRepository;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class DocumentService {
    @Autowired
    private final DocumentRepository repository;

    public DocumentService(DocumentRepository repository) {
        this.repository = repository;
    }

    public void createDocument(Document document) throws IOException {
        InputStream stream = new FileInputStream(String.valueOf(document));
        byte [] fileBytes = stream.readAllBytes();
        Binary binData = new Binary(fileBytes);
        Document doc = new Document("_id", 1).append("file", binData);
        MongoDatabase db = repository.findDb("files");
        MongoCollection<Document> files = db.getCollection("passports");
        files.insertOne(doc);
    }

}
