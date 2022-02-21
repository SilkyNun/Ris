package util;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateAdapter extends TypeAdapter<LocalDate> {
    @Override
    public void write(JsonWriter jsonWriter, LocalDate date) throws IOException {
        jsonWriter.jsonValue(date.format(DateTimeFormatter.ISO_DATE));
    }

    @Override
    public LocalDate read(JsonReader jsonReader) throws IOException {
        jsonReader.beginObject();
        jsonReader.nextName();
        LocalDate parse = LocalDate.parse(jsonReader.nextString());
        jsonReader.endObject();

        return parse;
    }
}
