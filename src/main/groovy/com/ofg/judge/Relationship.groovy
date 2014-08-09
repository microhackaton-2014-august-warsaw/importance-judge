package com.ofg.judge

import com.mongodb.BasicDBObject
import groovy.transform.Immutable
import groovy.transform.ToString

@ToString
@Immutable
class Relationship{
    final int pairId
    final CorrelationType correlatorType
    final ArrayList<Relation> relations

}

@ToString
@Immutable
class Relation implements Comparable<Relation>{
    final int score
    final String description

    @Override
    int compareTo(Relation that) {
        return that.score - this.score
    }

    public BasicDBObject toDbObject() {
        BasicDBObject document = new BasicDBObject()
        document.put("score", score)
        document.put("description", description)
        return document
    }

    public static Relation from(BasicDBObject obj) {
        return new Relation(obj.get("score"), obj.get("description"))
    }
}

//@JsonSerialize(using = CorrelationTypeSerializer.class)
enum CorrelationType {
//    PLACE, SENTENCE, TOPIC
    place, sentence, topic
	
//	@JsonValue
//	public String toValue() {
//		this.name().toLowerCase()
//	}
}

//class CorrelationTypeSerializer extends JsonSerializer<CorrelationType> {
//
//	@Override
//	public void serialize(CorrelationType value, JsonGenerator jgen,
//			SerializerProvider provider) throws IOException,
//			JsonProcessingException {
//		jgen.writeString(value.name)
//	}
//	
//}

