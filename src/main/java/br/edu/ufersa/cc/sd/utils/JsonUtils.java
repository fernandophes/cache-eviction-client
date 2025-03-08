package br.edu.ufersa.cc.sd.utils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import br.edu.ufersa.cc.sd.models.Order;

public interface JsonUtils {

    final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    final ObjectMapper MAPPER = new ObjectMapper().registerModule(new JavaTimeModule());
    final ObjectReader READER = MAPPER.reader();
    final ObjectWriter WRITER = MAPPER.writer().withDefaultPrettyPrinter();

    final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new TypeAdapter<LocalDateTime>() {
                @Override
                public void write(final JsonWriter out, final LocalDateTime value) throws IOException {
                    out.value(value.format(FORMATTER));
                }

                @Override
                public LocalDateTime read(final JsonReader in) throws IOException {
                    return FORMATTER.parse(in.nextString(), LocalDateTime::from);
                }
            })
            .registerTypeAdapter(Class.class, new TypeAdapter<Class<Order>>() {
                @Override
                public void write(final JsonWriter out, final Class<Order> value) throws IOException {
                    out.value(value.getName());
                }

                @Override
                public Class<Order> read(final JsonReader in) throws IOException {
                    return Order.class;
                }
            })
            .setPrettyPrinting()
            .create();

    public static String toJson(final Object object) {
        try {
            return WRITER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static <T> T fromJson(final String json) {
        try {
            return READER.readValue(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

}
