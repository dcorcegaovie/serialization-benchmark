package com.daniel.binary.serializers;

import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.time.StopWatch;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class GsonS implements Serializer {
    public String serializer = "gson";
    private StopWatch gsonSw = new StopWatch();
    private Gson gsonMapper = new Gson();

    public long serialize(OutputStream out, Object data){
        gsonSw.reset();

        gsonSw.start();
        String json =  gsonMapper.toJson(data);
        gsonSw.stop();

        try {
            out.write(json.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return gsonSw.getTime();
    }

    public <T> long deserialize(InputStream in, Class<T> type) {
        gsonSw.reset();

        try {
            String json = IOUtils.toString(in, "UTF-8");

            gsonSw.start();
            gsonMapper.fromJson(json, type);
            gsonSw.stop();
        } catch (IOException e){
            e.printStackTrace();
        }

        return gsonSw.getTime();
    }

    public String getName(){
        return this.serializer;
    }

    public Boolean isSchemaRequired(){
        return false;
    }

    public <T> void schematize(Class<T> type){}
}
