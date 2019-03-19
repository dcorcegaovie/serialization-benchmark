package com.daniel.binary.serializers;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import org.apache.commons.lang3.time.StopWatch;

import java.io.InputStream;
import java.io.OutputStream;

public class KryoS implements Serializer {
    public String serializer = "kryo";
    private StopWatch kryoSw = new StopWatch();
    private Kryo kryoMapper = new Kryo();

    public long serialize(OutputStream out, Object data){
        kryoSw.reset();
        kryoMapper.setRegistrationRequired(false);
        Output o = new Output(out);

        kryoSw.start();
        kryoMapper.writeObject(o, data);
        kryoSw.stop();

        o.close();

        return kryoSw.getTime();
    }

    public <T> long deserialize(InputStream in, Class<T> type) {
        kryoSw.reset();
        Input i = new Input(in);

        kryoSw.start();
        kryoMapper.readObject(i, type);
        kryoSw.stop();

        return kryoSw.getTime();
    }

    public String getName(){
        return this.serializer;
    }

    public Boolean isSchemaRequired(){
        return false;
    }

    public <T> void schematize(Class<T> type){}
}
