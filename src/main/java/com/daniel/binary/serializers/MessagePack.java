package com.daniel.binary.serializers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.time.StopWatch;
import org.msgpack.jackson.dataformat.MessagePackFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MessagePack implements Serializer {
    public String serializer = "messagePack";
    private StopWatch messagePackSw = new StopWatch();
    private ObjectMapper messagePackMapper = new ObjectMapper(new MessagePackFactory());

    public long serialize(OutputStream out, Object data){
        messagePackSw.reset();
        try {
            messagePackSw.start();
            messagePackMapper.writeValue(out, data);
            messagePackSw.stop();
        } catch (IOException e){
            e.printStackTrace();
        }

        return messagePackSw.getTime();
    }

    public <T> long deserialize(InputStream in, Class<T> type){
        messagePackSw.reset();

        try {
            messagePackSw.start();
            messagePackMapper.readValue(in, type);
            messagePackSw.stop();
        } catch (IOException e){
            e.printStackTrace();
        }

        return messagePackSw.getTime();
    }

    public String getName(){
        return this.serializer;
    }

    public Boolean isSchemaRequired(){
        return false;
    }

    public <T> void schematize(Class<T> type){}
}