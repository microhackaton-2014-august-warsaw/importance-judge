package com.ofg.judge.dao

import com.ofg.judge.Relation
import com.ofg.judge.Relationship
import com.ofg.judge.model.AllRelationships

class MemoryDatabase implements JudgeDAO{
    final List<Relationship> relationships;

    MemoryDatabase() {
        this.relationships = Collections.synchronizedList(new ArrayList<>());
    }

    @Override
    AllRelationships updateRelationship(Relationship newRelationship) {
        Relationship workingRelationship = findRelationshipByPairAndCategory(newRelationship)

        if(workingRelationship == null){
            relationships.add(newRelationship)
            return new AllRelationships(newRelationship)
        }else{
            ArrayList<Relation> workingRelations = workingRelationship.getRelations()
            workingRelations.addAll(newRelationship.getRelations())

            relationships.remove(workingRelationship)
            Collections.sort(workingRelations)
            Relationship updatedRelationship = new Relationship(workingRelationship.pairId, workingRelationship.correlatorType, workingRelations)
            relationships.add(updatedRelationship)
            return new AllRelationships(updatedRelationship)
        }

    }

    private Relationship findRelationshipByPairAndCategory(newRelationship) {
        relationships.find({
            relationship -> relationship.pairId.equals(newRelationship.pairId) && relationship.correlatorType.equals(newRelationship.correlatorType)
        })
    }
}
