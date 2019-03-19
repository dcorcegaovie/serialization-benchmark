package com.daniel.binary.serializers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.smile.SmileFactory;
import org.apache.commons.lang3.time.StopWatch;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Smile implements Serializer {
    public String serializer = "smile";
    private StopWatch smileSw = new StopWatch();
    private ObjectMapper smileMapper = new ObjectMapper(new SmileFactory());

    public long serialize(OutputStream out, Object data){
        smileSw.reset();
        try {
            smileSw.start();
            smileMapper.writeValue(out, data);
            smileSw.stop();
        } catch (IOException e){
            e.printStackTrace();
        }

        return smileSw.getTime();
    }

    public <T> long deserialize(InputStream in, Class<T> type){
        smileSw.reset();

        try {
            smileSw.start();
            smileMapper.readValue(in, type);
            smileSw.stop();
        } catch (IOException e){
            e.printStackTrace();
        }

        return smileSw.getTime();
    }

    public String getName(){
        return this.serializer;
    }

    public Boolean isSchemaRequired(){
        return false;
    }

    public <T> void schematize(Class<T> type){}
}
