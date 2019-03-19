package com.daniel.binary.serializers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.ion.IonObjectMapper;
import org.apache.commons.lang3.time.StopWatch;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Ion implements Serializer {
    public String serializer = "ion";
    private StopWatch ionSw = new StopWatch();
    private ObjectMapper ionMapper = new IonObjectMapper();

    public long serialize(OutputStream out, Object data){
        ionSw.reset();
        try {
            ionSw.start();
            ionMapper.writeValue(out, data);
            ionSw.stop();
        } catch (IOException e){
            e.printStackTrace();
        }

        return ionSw.getTime();
    }

    public <T> long deserialize(InputStream in, Class<T> type){
        ionSw.reset();

        try {
            ionSw.start();
            ionMapper.readValue(in, type);
            ionSw.stop();
        } catch (IOException e){
            e.printStackTrace();
        }

        return ionSw.getTime();
    }

    public String getName() {
        return this.serializer;
    }

    public Boolean isSchemaRequired(){
        return false;
    }

    public <T> void schematize(Class<T> type){}
}
