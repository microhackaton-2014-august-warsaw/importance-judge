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
        objecy.get("PLACE") == []
    }

}
