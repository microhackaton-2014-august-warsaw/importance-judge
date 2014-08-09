package com.ofg.judge.dao.mongodb
import com.mongodb.*
import com.ofg.judge.Relationship
import com.ofg.judge.dao.JudgeDAO
import com.ofg.judge.model.AllRelationships
import groovy.transform.TypeChecked
import groovy.util.logging.Slf4j
import org.bson.BSONObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

import javax.annotation.PostConstruct

@Slf4j
@TypeChecked
@Repository
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
        AllRelationships doc = findOrUpdate(newRelationship, relationships)
        doc.append(newRelationship)
        log.debug("Storing {}", doc)
        DBObject document = doc.toDbObject()
        relationships.save(document)
        return doc
    }

    private AllRelationships findOrUpdate(Relationship newRelationship, DBCollection relationships) {
        BasicDBObject idDoc = new BasicDBObject("_id", newRelationship.pairId)
        DBCursor existing = relationships.find(idDoc)
        existing.count() == 0 ?
                insertNew(newRelationship) :
                append(existing.next())
    }


    AllRelationships append(BSONObject bsonObject) {
        return AllRelationships.from(bsonObject)
    }

    AllRelationships insertNew(Relationship relationship) {
        return new AllRelationships(relationship.pairId)
    }
}
