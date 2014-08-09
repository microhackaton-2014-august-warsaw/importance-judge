package com.ofg.twitter.controller.relations

import com.mongodb.BasicDBObject
import groovy.transform.Immutable
import groovy.transform.ToString

@ToString
@Immutable
class Relationship{
    final int pairId
    final CorrelationType correlatorType
    final ArrayList<Relation> relations

    public BasicDBObject toDbObject() {
        String id = pairId.toString()
        BasicDBObject document = new BasicDBObject("_id", id)
        document.put("correlatorType", correlatorType.toString())

        def relations = relations.collect { it.toDbObject() }
        document.put("relations", relations)
        return document
    }

}

@ToString
@Immutable
class Relation implements Comparable<Relation>{
    final int score
    final String description

    @Override
    int compareTo(Relation that) {
        return that.score - this.score
    }

    public BasicDBObject toDbObject() {
        BasicDBObject document = new BasicDBObject()
        document.put("score", score)
        document.put("description", description)
        return document
    }
}


enum CorrelationType {
    PLACE, SENTENCE, TOPIC
}

