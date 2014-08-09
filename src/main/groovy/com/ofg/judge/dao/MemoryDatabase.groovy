package com.ofg.judge.dao

import com.ofg.twitter.controller.relations.Relationship

class MemoryDatabase implements JudgeDAO{
    @Override
    Relationship updateRelationship(Relationship newRelationship) {
        return newRelationship
    }
}
