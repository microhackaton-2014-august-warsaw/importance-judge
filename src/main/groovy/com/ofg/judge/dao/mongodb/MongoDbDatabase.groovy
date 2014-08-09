package com.ofg.judge.dao.mongodb

import com.mongodb.*
import com.ofg.judge.Relationship
import com.ofg.judge.dao.JudgeDAO
import com.ofg.judge.model.AllRelationships
import groovy.transform.TypeChecked
import groovy.util.logging.Slf4j
import org.bson.BSONObject
import org.springframework.beans.factory.annotation.Autowired

import javax.annotation.PostConstruct

@Slf4j
@TypeChecked
class MongoDbDatabase implements JudgeDAO {

    private final MongoClient mongoClient
    private DB db

    @Autowired
    MongoDbDatabase(MongoClient mongoClient) {
        this.mongoClient = mongoClient
    }

    @PostConstruct
    public void start() {
        db = mongoClient.getDB("importance-judge")
    }

    @Override
    AllRelationships updateRelationship(Relationship newRelationship) {
        final DBCollection relationships = db.getCollection("relationships")
        BasicDBObject idDoc = new BasicDBObject("_id", newRelationship.pairId.toString())
        DBCursor existing = relationships.find(idDoc)

        AllRelationships doc = findOrUpdate(existing, newRelationship)
        log.debug("Storing {}", doc)
        DBObject document = doc.toDbObject()
        relationships.save(document)
    }

    private AllRelationships findOrUpdate(DBCursor existing, Relationship newRelationship) {
        AllRelationships all = existing.count() == 0 ?
                insertNew(newRelationship) :
                append(existing.next(), newRelationship)
        all
    }

    AllRelationships append(BSONObject bsonObject, def relationship) {
        return AllRelationships.from(bsonObject)
    }

    AllRelationships insertNew(Relationship relationship) {
        AllRelationships allRelationships = new AllRelationships(relationship)

    }
}
