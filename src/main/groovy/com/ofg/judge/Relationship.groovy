package com.ofg.judge

import java.io.IOException;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.annotate.JsonValue;
import org.codehaus.jackson.map.JsonSerializer
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.annotate.JsonSerialize;

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

