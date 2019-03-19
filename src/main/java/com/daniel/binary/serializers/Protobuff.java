package com.daniel.binary.serializers;

import com.fasterxml.jackson.dataformat.protobuf.ProtobufMapper;
import com.fasterxml.jackson.dataformat.protobuf.schema.ProtobufSchema;
import org.apache.commons.lang3.time.StopWatch;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Protobuff implements Serializer {
    public String serializer = "protobuff";
    private StopWatch protobuffSw = new StopWatch();
    private ProtobufMapper protobufMapper = new ProtobufMapper();
    private ProtobufSchema protobufSchema;

    public long serialize(OutputStream out, Object data){
        protobuffSw.reset();

        try {
            protobuffSw.start();
            protobufMapper
                    .writer(protobufSchema)
                    .writeValue(out, data);
            protobuffSw.stop();
        } catch (IOException e){
            e.printStackTrace();
        }

        return protobuffSw.getTime();
    }

    public <T> long deserialize(InputStream in, Class<T> type){
        protobuffSw.reset();

        try {
            protobuffSw.start();
            protobufMapper.readerFor(type)
                    .with(protobufSchema)
                    .readValue(in);
            protobuffSw.stop();
        } catch (IOException e){
            e.printStackTrace();
        }

        return protobuffSw.getTime();
    }

    public  <T> void schematize(Class<T> type){
        try {
            protobufSchema = protobufMapper.generateSchemaFor(type);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getName(){
        return this.serializer;
    }

    public Boolean isSchemaRequired(){
        return true;
    }
}
