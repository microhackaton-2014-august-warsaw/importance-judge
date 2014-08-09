package com.ofg.judge.model

import com.ofg.judge.CorrelationType
import com.ofg.judge.Relation
import spock.lang.Specification

class AllRelationshipsTest extends Specification {

    def "should turn all relationships into DB object"() {
        given:
        def relationships = new AllRelationships(42)
        relationships.relations.put(CorrelationType.PLACE, [new Relation(5, "Foo")])

        when:
        def object = relationships.toDbObject()

        then:
        object.get("_id") == relationships.pairId
    }

    def "should load back from DB object"() {
        given:
        def relationships = new AllRelationships(42)
        relationships.relations.put(CorrelationType.PLACE, [new Relation(5, "Foo")])

        when:
        def object = relationships.toDbObject()
        def loaded = AllRelationships.from(object)

        then:
        loaded.pairId == relationships.pairId
        loaded.relations.get(CorrelationType.PLACE) == relationships.relations.get(CorrelationType.PLACE)
        loaded.relations.get(CorrelationType.SENTENCE) == relationships.relations.get(CorrelationType.SENTENCE)
        loaded.relations.get(CorrelationType.TOPIC) == relationships.relations.get(CorrelationType.TOPIC)
    }

}
