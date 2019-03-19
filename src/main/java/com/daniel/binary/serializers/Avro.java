package com.daniel.binary.serializers;

import com.fasterxml.jackson.dataformat.avro.AvroMapper;
import com.fasterxml.jackson.dataformat.avro.AvroSchema;
import com.fasterxml.jackson.dataformat.avro.schema.AvroSchemaGenerator;
import org.apache.commons.lang3.time.StopWatch;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Avro implements Serializer {
    public String serializer = "avro";
    private StopWatch avroSw = new StopWatch();
    private AvroMapper avroMapper = new AvroMapper();
    private AvroSchema avroSchema;

    public long serialize(OutputStream out, Object data){
        avroSw.reset();

        try {
            avroSw.start();
            avroMapper
                    .writer(avroSchema)
                    .writeValue(out, data);
            avroSw.stop();
        } catch (IOException e){
            e.printStackTrace();
        }

        return avroSw.getTime();
    }

    public <T> long deserialize(InputStream in, Class<T> type){
        avroSw.reset();

        try {
            avroSw.start();
            avroMapper.readerFor(type)
                    .with(avroSchema)
                    .readValue(in);
            avroSw.stop();
        } catch (IOException e){
            e.printStackTrace();
        }

        return avroSw.getTime();
    }

    public  <T> void schematize(Class<T> type){
        try {
            AvroSchemaGenerator gen = new AvroSchemaGenerator();
            avroMapper.acceptJsonFormatVisitor(type, gen);
            avroSchema = gen.getGeneratedSchema();
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
