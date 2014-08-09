package com.ofg.twitter.places;

import com.ofg.judge.CorrelationType;
import com.ofg.judge.Relation;
import com.ofg.judge.Relationship;

public class RelationshipExamples {

    static Relationship withFooPlace() {
        return new Relationship(1, CorrelationType.place, [new Relation(2, "foo")])
    }

    static Relationship withBarPlace() {
        return new Relationship(1, CorrelationType.place, [new Relation(8, "bar")])
    }

    static Relationship withWarsawPlace() {
        return new Relationship(1, CorrelationType.place, [new Relation(4, "warsaw")])
    }

    static Relationship withFooAndBarPlace() {
        return new Relationship(1, CorrelationType.place, [new Relation(8, "bar"), new Relation(2, "foo")])
    }

    static Relationship withFooAndBarAndWarsawPlace() {
        return new Relationship(1, CorrelationType.place, [new Relation(8, "bar"), new Relation(4, "warsaw"), new Relation(2, "foo")])
    }

    static Relationship withFooSentence() {
        return new Relationship(1, CorrelationType.sentence, [new Relation(8, "foo")])
    }
}
