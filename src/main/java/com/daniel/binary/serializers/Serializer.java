package com.daniel.binary.serializers;

import java.io.InputStream;
import java.io.OutputStream;

public interface Serializer {

    long serialize(OutputStream out, Object data);
    <T> long deserialize (InputStream in, Class<T> type);
    <T> void schematize(Class<T> type);
    String getName();
    Boolean isSchemaRequired();
}
