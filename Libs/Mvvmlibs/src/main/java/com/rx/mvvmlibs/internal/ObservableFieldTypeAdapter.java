package com.rx.mvvmlibs.internal;

import android.databinding.ObservableField;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * @ClassName: ObservableFieldTypeAdapter
 * @author create by Tang
 * @date date 16/11/14 下午5:06
 * @Description: ObservableField类的TypeAdapter
 * 功能是使Gson支持ObservableField的序列化和反序列化
 */

public class ObservableFieldTypeAdapter extends TypeAdapter<ObservableField>{
    @Override
    public ObservableField read(JsonReader in) throws IOException {
        ObservableField observableField = new ObservableField();
        JsonToken peek = in.peek();
        if (peek == JsonToken.NULL) {
            in.nextNull();
            return null;
        }
      /* coerce booleans to strings for backwards compatibility */
        if (peek == JsonToken.BOOLEAN) {
            observableField.set(in.nextBoolean());
            return observableField;
        }
        observableField.set(in.nextString());
        return observableField;
    }
    @Override
    public void write(JsonWriter out, ObservableField value) throws IOException {
        if (value.get() instanceof String){
            out.value(String.valueOf(value.get()));
        }else if (value.get() instanceof Integer){
            out.value((Integer)value.get());
        }else if (value.get() instanceof Boolean){
            out.value((Boolean)value.get());
        }else if (value.get() instanceof Float){
            out.value((Float) value.get());
        }else if (value.get() instanceof Double){
            out.value((Double) value.get());
        }
    }
}
