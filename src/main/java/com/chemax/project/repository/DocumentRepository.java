package com.chemax.project.repository;

import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends MongoRepository<Document, String> {
    public Document findByName(String name);
    public MongoDatabase findDb(String dbName);
}
