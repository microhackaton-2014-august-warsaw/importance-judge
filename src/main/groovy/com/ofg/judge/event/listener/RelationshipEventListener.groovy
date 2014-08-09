package com.ofg.judge.event.listener

import org.codehaus.jackson.map.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import groovy.util.logging.Slf4j;

import com.google.common.eventbus.EventBus
import com.google.common.eventbus.Subscribe;
import com.ofg.judge.event.RelationshipEvent;
import com.ofg.twitter.controller.relations.AllRelationships
import com.ofg.twitter.controller.relations.extractor.RelationshipWorker

@Slf4j
@Repository
class RelationshipEventListener {
	
	final private RelationshipWorker relationshipWorker
	final private EventBus eventBus
	final private ObjectMapper objectMapper
	
	@Autowired
	public RelationshipEventListener(EventBus eventBus, ObjectMapper objectMapper, RelationshipWorker relationshipWorker) {
		this.relationshipWorker = relationshipWorker
		this.objectMapper = objectMapper
		eventBus.register(this)
	}
	
	@Subscribe
	public handleRelationshipEvent(RelationshipEvent event) {
		AllRelationships relationship = event.relationship
		
		String relationshipSerialized = objectMapper.writeValueAsString(event.relationship)
		log.debug(relationshipSerialized)
		
//		relationshipWorker.passRelations(relationshipSerialized)
	}
}
