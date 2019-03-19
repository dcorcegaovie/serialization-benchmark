package com.daniel.binary.serializers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.time.StopWatch;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Json implements Serializer {
    public String serializer = "json";
    private StopWatch jsonSw = new StopWatch();
    private ObjectMapper jsonMapper = new ObjectMapper();

    public long serialize(OutputStream out, Object data){
        jsonSw.reset();
        try {
            jsonSw.start();
            jsonMapper.writeValue(out, data);
            jsonSw.stop();
        } catch (IOException e){
            e.printStackTrace();
        }

        return jsonSw.getTime();
    }

    public <T> long deserialize(InputStream in, Class<T> type){
        jsonSw.reset();

        try {
            jsonSw.start();
            jsonMapper.readValue(in, type);
            jsonSw.stop();
        } catch (IOException e){
            e.printStackTrace();
        }

        return jsonSw.getTime();
    }

    public String getName(){
        return this.serializer;
    }

    public Boolean isSchemaRequired(){
        return false;
    }

    public <T> void schematize(Class<T> type){}
}