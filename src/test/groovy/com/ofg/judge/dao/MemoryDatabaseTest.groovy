package com.ofg.judge.dao

import com.ofg.twitter.controller.relations.CorrelationType
import com.ofg.twitter.controller.relations.Relation
import com.ofg.twitter.controller.relations.Relationship
import spock.lang.Specification

class MemoryDatabaseTest extends Specification {

    def "With empty database, new request should be the same"() {
        given:
            MemoryDatabase memoryDatabase = new MemoryDatabase()

        when:
            def result = memoryDatabase.updateRelationship(input)

        then:
            result == input

        where:
            input << [new Relationship(1, CorrelationType.PLACE, [new Relation(2, "foo")])]

    }
}
