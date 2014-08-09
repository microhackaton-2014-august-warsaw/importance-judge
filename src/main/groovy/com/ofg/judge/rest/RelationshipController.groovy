package com.ofg.judge.rest
import com.ofg.twitter.controller.relations.CorrelationType
import com.ofg.twitter.controller.relations.Relation
import com.ofg.twitter.controller.relations.Relationship
import groovy.transform.Immutable
import groovy.transform.ToString
import groovy.util.logging.Slf4j
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

import javax.validation.constraints.NotNull

import static org.springframework.web.bind.annotation.RequestMethod.PUT

@Slf4j
@RestController
@RequestMapping('/relationship')
class RelationshipController {

    @RequestMapping(
            method = PUT,
            consumes = 'application/vnd.com.ofg.importance-judge.v1+json',
            produces = 'application/vnd.com.ofg.importance-judge.v1+json')
    void getPlacesFromTweets(@RequestBody @NotNull RelationshipDto relationship) {
        String correlatorType = relationship.correlatorType.toUpperCase()
        validateScores(relationship)
        final CorrelationType correlationType = parseCorrelatorType(correlatorType)
        new Relationship(relationship.pairId, correlationType, relationship.relationships)
    }

    private static Iterable<Relation> validateScores(RelationshipDto relationship) {
        relationship.relationships.each {
            if (it.score < 0 || it.score > 10) {
                throw new ValidationError("Bad score: " + it.score)
            }
        }
    }

    private CorrelationType parseCorrelatorType(String correlatorType) {
        try {
            return CorrelationType.valueOf(correlatorType)
        } catch (IllegalArgumentException e) {
            throw new ValidationError("Bad correlation typy: " + correlatorType)
        }
    }

}

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
class ValidationError extends RuntimeException {
    ValidationError(String s) {
        super(s)
    }
}

@ToString
@Immutable
class RelationshipDto {
    final int pairId
    final String correlatorType
    final ArrayList<Relation> relationships
}
