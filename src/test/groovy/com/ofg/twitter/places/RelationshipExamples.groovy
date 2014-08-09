package com.ofg.twitter.places;

import com.ofg.twitter.controller.relations.CorrelationType;
import com.ofg.twitter.controller.relations.Relation;
import com.ofg.twitter.controller.relations.Relationship;

public class RelationshipExamples {

    static Relationship withFooPlace() {
        return new Relationship(1, CorrelationType.PLACE, [new Relation(2, "foo")])
    }

    static Relationship withBarPlace() {
        return new Relationship(1, CorrelationType.PLACE, [new Relation(8, "bar")])
    }

    static Relationship withFooAndBarPlace() {
        return new Relationship(1, CorrelationType.PLACE, [new Relation(2, "foo"),new Relation(8, "bar")])
    }
}
