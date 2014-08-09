package com.ofg.judge.model

import com.mongodb.DBObject
import com.ofg.judge.CorrelationType
import com.ofg.judge.Relation
import com.ofg.judge.Relationship
import org.bson.BSONObject

class AllRelationships {

    final int pairId
    final Map<CorrelationType, List<Relation>> relations = new EnumMap<>(CorrelationType)

    AllRelationships() {
    }

    AllRelationships(Relationship relationship) {
        this.pairId = relationship.pairId
        this.relations.put(relationship.correlatorType, relationship.relations)
    }

    static AllRelationships from(BSONObject bsonObject) {
        final String pairId = bsonObject.get("_id")
        def collect = CorrelationType.values().collect { type ->
            bsonObject.get(type.toString())
        }
        collect
        return new AllRelationships()
    }

    DBObject toDbObject() {
        return null
    }
}
