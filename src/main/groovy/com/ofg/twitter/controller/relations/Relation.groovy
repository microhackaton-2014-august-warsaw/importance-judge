package com.ofg.twitter.controller.relations

import groovy.transform.Immutable
import groovy.transform.ToString

@ToString
@Immutable
class Relationships{
    final int pairId
    final CorrelationType correlationType
    final ArrayList<Relation> relations
}

@ToString
@Immutable
class Relation {
    final int score
    final String description
}


enum CorrelationType {
    PLACE, SENTENCE, TOPIC
}

