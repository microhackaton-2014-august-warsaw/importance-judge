package com.ofg.judge.event

import com.ofg.judge.model.AllRelationships

class RelationshipEvent {
	
	final public AllRelationships relationship

	public RelationshipEvent(AllRelationships relationship) {
		this.relationship = relationship
	}
}
