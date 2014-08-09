package com.ofg.judge.rest

import com.ofg.twitter.controller.relations.Relationship
import groovy.util.logging.Slf4j
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
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
    void getPlacesFromTweets(@RequestBody @NotNull Relationship relationship) {
        log.info("" + relationship)
    }

}
