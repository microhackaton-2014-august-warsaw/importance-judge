package com.ofg.judge.event

import com.ofg.judge.Relationship

class RelationshipEvent {
	
	final public Relationship relationship

	public RelationshipEvent(Relationship relationship) {
		this.relationship = relationship
	}
}
