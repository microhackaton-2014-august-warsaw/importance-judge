package com.ofg.twitter.controller.relations.extractor

import com.ofg.infrastructure.discovery.ServiceResolver
import com.ofg.infrastructure.web.resttemplate.RestTemplate
import groovy.util.logging.Slf4j

@Slf4j
class RelationshipWorker {
    private final ServiceResolver serviceResolver
    private final RestTemplate restTemplate

    RelationshipWorker(ServiceResolver serviceResolver) {
        this.serviceResolver = serviceResolver
        this.restTemplate = restTemplate
    }

    void passRelations(String request) {
        restTemplate.putForObject("${serviceResolver.getUrl("mentor").get()}", request, String)
        log.debug("Sent json [$request] to collerator")
    }
}
