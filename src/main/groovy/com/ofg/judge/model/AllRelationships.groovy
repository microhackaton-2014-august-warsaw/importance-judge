package com.ofg.judge.model
import com.mongodb.BasicDBList
import com.mongodb.BasicDBObject
import com.mongodb.DBObject
import com.ofg.judge.CorrelationType
import com.ofg.judge.Relation
import com.ofg.judge.Relationship
import org.bson.BSONObject

class AllRelationships {

    final int pairId
    final Map<CorrelationType, List<Relation>> relations

    AllRelationships() {
        this.relations = new EnumMap<>(CorrelationType)
        CorrelationType.values().each {relations.put(it, [])}
    }

    AllRelationships(int pairId) {
        this()
        this.pairId = pairId
    }

    AllRelationships(int pairId, Map<CorrelationType, List<Relation>> relations) {
        this.pairId = pairId
        this.relations = relations
    }

    AllRelationships(Relationship relationship) {
        this()
        this.pairId = relationship.pairId
        this.relations.put(relationship.correlatorType, relationship.relations)
    }

    static AllRelationships from(BSONObject bsonObject) {
        final int pairId = bsonObject.get("_id") as int
        Map<CorrelationType, List<Relation>> map = new EnumMap<>(CorrelationType)
        List<Relation> parsed = CorrelationType.values().collect { type ->
            BasicDBList relationsList = bsonObject.get(type.toString()) as BasicDBList
            def relations = relationsList.collect {
                return Relation.from(it)
            }
            map.put(type, relations)
        }
        return new AllRelationships(pairId, map)
    }

    DBObject toDbObject() {
        def object = new BasicDBObject("_id", pairId)
        CorrelationType.values().collect {
            def relationList = relations.get(it)
            def list = relationList == null ? [] : relationList.collect { it.toDbObject() }
            def all = new BasicDBList()
            all.addAll(list)
            object.put(it.toString(), all)
        }
        return object
    }


    @Override
    public String toString() {
        return com.google.common.base.Objects.toStringHelper(this)
                .add("pairId", pairId)
                .add("relations", relations)
                .toString();
    }

    AllRelationships append(Relationship relationship) {
        def list = this.relations.get(relationship.correlatorType)
        list.addAll(relationship.relations)
        return this
    }
}
