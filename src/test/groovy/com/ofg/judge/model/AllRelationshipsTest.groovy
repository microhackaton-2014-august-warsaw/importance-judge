package com.ofg.judge.model

import com.ofg.judge.CorrelationType
import com.ofg.judge.Relation
import spock.lang.Specification

class AllRelationshipsTest extends Specification {

    def "should turn all relationships into DB object"() {
        given:
        def relationships = new AllRelationships(42)
        relationships.relations.put(CorrelationType.place, [new Relation(5, "Foo")])

        when:
        def object = relationships.toDbObject()

        then:
        object.get("_id") == relationships.pairId
    }

    def "should load back from DB object"() {
        given:
        def relationships = new AllRelationships(42)
        relationships.relations.put(CorrelationType.place, [new Relation(5, "Foo")])

        when:
        def object = relationships.toDbObject()
        def loaded = AllRelationships.from(object)

        then:
        loaded.pairId == relationships.pairId
        loaded.relations.get(CorrelationType.place) == relationships.relations.get(CorrelationType.place)
        loaded.relations.get(CorrelationType.sentence) == relationships.relations.get(CorrelationType.sentence)
        loaded.relations.get(CorrelationType.topic) == relationships.relations.get(CorrelationType.topic)
    }

}
