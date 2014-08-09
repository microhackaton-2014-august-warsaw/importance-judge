package com.ofg.judge.dao

import com.ofg.judge.Relationship
import com.ofg.judge.model.AllRelationships

public interface JudgeDAO {

    public AllRelationships updateRelationship(Relationship newRelationship)

}