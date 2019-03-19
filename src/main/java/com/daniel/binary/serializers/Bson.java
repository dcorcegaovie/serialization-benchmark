package com.daniel.binary.serializers;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.undercouch.bson4jackson.BsonFactory;
import org.apache.commons.lang3.time.StopWatch;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Bson implements Serializer {
    public String serializer = "bson";
    private StopWatch bsonSw = new StopWatch();
    private ObjectMapper bsonMapper = new ObjectMapper(new BsonFactory());

    public long serialize(OutputStream out, Object data){
        bsonSw.reset();
        try {
            bsonSw.start();
            bsonMapper.writeValue(out, data);
            bsonSw.stop();
        } catch (IOException e){
            e.printStackTrace();
        }

        return bsonSw.getTime();
    }

    public <T> long deserialize(InputStream in, Class<T> type){
        bsonSw.reset();

        try {
            bsonSw.start();
            bsonMapper.readValue(in, type);
            bsonSw.stop();
        } catch (IOException e){
            e.printStackTrace();
        }

        return bsonSw.getTime();
    }

    public String getName(){
        return this.serializer;
    }

    public Boolean isSchemaRequired(){
        return false;
    }

    public <T> void schematize(Class<T> type){}
}
