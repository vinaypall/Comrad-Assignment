package com.konrad.kbnb.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LookUpTreeNode implements JsonSerializable {
    private Map<Character, LookUpTreeNode> children;
    private LookUpMatch match;

    public LookUpTreeNode() {
        children = new HashMap<>();
    }

    public Map<Character, LookUpTreeNode> getChildren() {
        return children;
    }

    public LookUpMatch getMatch() {
        return match;
    }

    public boolean containsNode(char character){
        return children.containsKey(character);
    }

    public LookUpTreeNode addNode(char character, LookUpTreeNode node){
        return children.put(character, node);
    }

    public LookUpTreeNode getNode(char character){
        return children.get(character);
    }

    public void setMatch(LookUpMatch match) {
        this.match = match;
    }

    //Json serialization because jackson doesnt like lookup tree format DO NOT TOUCH
    @Override
    public void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (this.children.isEmpty()){
            jsonGenerator.writeStartObject();
            jsonGenerator.writeEndObject();
        }
        int count = 0;
        for (Map.Entry<Character, LookUpTreeNode> entry : this.getChildren().entrySet()) {
            if (count==0){
                jsonGenerator.writeStartObject();
                count++;
            }
            jsonGenerator.writeFieldName(entry.getKey().toString());
            writeNodes(entry.getValue(), jsonGenerator,serializerProvider);
            jsonGenerator.writeEndObject();
        }
    }

    public void writeNodes(LookUpTreeNode lookUpTreeNode, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException{
        if (lookUpTreeNode.getMatch()!=null) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("match");
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("name");
            jsonGenerator.writeString(lookUpTreeNode.getMatch().getName());
            jsonGenerator.writeFieldName("id");
            jsonGenerator.writeNumber(lookUpTreeNode.getMatch().getId());
            jsonGenerator.writeEndObject();
        }
        else {
            int count = 0;
            for (Map.Entry<Character, LookUpTreeNode> entry : lookUpTreeNode.getChildren().entrySet()) {
                if (count==0){
                    jsonGenerator.writeStartObject();
                    count++;
                }
                jsonGenerator.writeFieldName(entry.getKey().toString());
                writeNodes(entry.getValue(), jsonGenerator,serializerProvider);
                jsonGenerator.writeEndObject();
            }
        }
    }
    @Override
    public void serializeWithType(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException {

    }
}
