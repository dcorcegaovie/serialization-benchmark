package com.daniel.binary;

import com.daniel.binary.serializers.*;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Benchmark {
    final int BIG_FILE_ROWS = 100000;

    private CsvMapper csvMapper = new CsvMapper();
    private CsvSchema csvSchema;
    private ArrayList<Integer> cases = new ArrayList<>( Arrays.asList(1, 10, 100, 1000, 10000, 100000) );
    ByteArrayOutputStream out = new ByteArrayOutputStream();

    public void run(){
        //INIT
        csvMapper.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);
        csvSchema = csvMapper.schemaFor(Result.class).withHeader();

        try {
            //LOAD TEST DATA - this is done using jackson to load response time dump from listing sort
            List<HashMap> dumpMap = new ArrayList<>();
            List<UserQuestions> dumpClass = new ArrayList<>();
            ObjectMapper testDataMapper = new ObjectMapper();
            MappingIterator<HashMap> mapIt = testDataMapper.readerFor(HashMap.class)
                    .readValues(new File("dump-users-questions.csv"));
            int i = 0;
            while (mapIt.hasNext() && i < BIG_FILE_ROWS) {
                dumpMap.add(mapIt.next());
                dumpClass.add( testDataMapper.convertValue(dumpMap.get(i), UserQuestions.class));
                i++;
            }
            mapIt.close();

            OutputStream csv = new FileOutputStream("benchmark.csv");

            benchSchemaless(dumpMap, csv);
            benchSchema(dumpClass, csv);

            csv.close();

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void benchSchemaless(List<HashMap> dumpMap, OutputStream csv){
        ArrayList<Serializer> schemalessSerializers = new ArrayList<>();
        registerSchemaless(schemalessSerializers);
        DescriptiveStatistics outTimeStatistics = new DescriptiveStatistics();
        DescriptiveStatistics inTimeStatistics = new DescriptiveStatistics();
        Object data;
        Class type;

        for (Serializer s: schemalessSerializers) {

            for (Integer rownums: cases) {

                if (rownums == 1){
                    type = HashMap.class;
                    data = dumpMap.get(0);
                } else {
                    type = dumpMap.getClass();
                    data = dumpMap.subList(0, rownums);
                }

                for (int j = 0; j < 100; j++) {
                    out.reset();
                    outTimeStatistics.addValue(s.serialize(out, data));
                    inTimeStatistics.addValue(s.deserialize(out.toInputStream(), type));
                }

                writeLine(csvMapper, csv, rownums, out.size(), csvSchema, s, outTimeStatistics, inTimeStatistics);
                csvSchema = csvMapper.schemaFor(Result.class).withoutHeader();

                outTimeStatistics.clear();
                inTimeStatistics.clear();
            }
        }
    }

    public void benchSchema(List<UserQuestions> dumpClass, OutputStream csv){
        ArrayList<Serializer> schemaSerializers = new ArrayList<>();
        registerSchema(schemaSerializers);
        DescriptiveStatistics outTimeStatistics = new DescriptiveStatistics();
        DescriptiveStatistics inTimeStatistics = new DescriptiveStatistics();
        Object data;
        Class type;

        for (Serializer s: schemaSerializers) {

            for (Integer rownums: cases) {

                if (rownums == 1){
                    type = UserQuestions.class;
                    data = dumpClass.get(0);
                } else {
                    type = DummyArray.class;
                    data = new DummyArray();
                    ((DummyArray) data).userQuestions = new ArrayList<>(dumpClass.subList(0, rownums));
                }

                s.schematize(type);

                for (int j = 0; j < 100; j++) {
                    out.reset();
                    outTimeStatistics.addValue(s.serialize(out, data));
                    inTimeStatistics.addValue(s.deserialize(out.toInputStream(), type));
                    break;
                }

                writeLine(csvMapper, csv, rownums, out.size(), csvSchema, s, outTimeStatistics, inTimeStatistics);
                csvSchema = csvMapper.schemaFor(Result.class).withoutHeader();

                outTimeStatistics.clear();
                inTimeStatistics.clear();
            }
        }
    }

    public void writeLine(CsvMapper csvMapper, OutputStream csv, Integer rownums, Integer size, CsvSchema schema, Serializer s, DescriptiveStatistics outTimeStatistics, DescriptiveStatistics inTimeStatistics){
        try {
            csvMapper.writer(schema).writeValue(csv, new Result(
                    s.getName(),
                    rownums,
                    size,
                    outTimeStatistics.getMean(),
                    outTimeStatistics.getStandardDeviation(),
                    inTimeStatistics.getMean(),
                    inTimeStatistics.getStandardDeviation()
            ));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void registerSchemaless(List<Serializer> serializers) {
        serializers.add(new Json());
        serializers.add(new Ion());
        serializers.add(new Bson());
        serializers.add(new GsonS());
        serializers.add(new KryoS());
        serializers.add(new Smile());
        serializers.add(new MessagePack());
    }

    public void registerSchema(List<Serializer> serializers) {
        serializers.add(new Protobuff());
        serializers.add(new Avro());
    }
}
