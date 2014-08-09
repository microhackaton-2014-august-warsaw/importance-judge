package com.ofg.twitter.controller.relations.extractor

import com.ofg.infrastructure.discovery.ServiceResolver
import com.ofg.infrastructure.web.filter.correlationid.CorrelationIdHolder
import com.ofg.infrastructure.web.resttemplate.RestTemplate
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component

@Slf4j
@Component
class RelationshipWorker {
    private final ServiceResolver serviceResolver
    private final RestTemplate restTemplate

    @Autowired
    RelationshipWorker(ServiceResolver serviceResolver, RestTemplate restTemplate) {
        this.serviceResolver = serviceResolver
        this.restTemplate = restTemplate
    }

    void passRelations(String request) {
        try{
            String adress = serviceResolver.getUrl("gui").get() + "/rest/results/result"
            log.debug("Adress: $adress and request: $request")
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/vnd.gui.v1+json");
            headers.set("correlationId", CorrelationIdHolder.get())

            HttpEntity<String> entity = new HttpEntity<String>(request,headers);

            restTemplate.put(adress, entity)
        } catch(Exception e){
            log.error("Catch log", e)
        }
        log.debug("Sent json [$request] to collerator")
    }
}
