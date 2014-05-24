package com.adarrivi.springmvc;

import com.adarrivi.springmvc.core.domain.Supplier;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.IOException;

//Helper class to facilitate the object creation for testing proposes
public class TestMvcBuilder {

    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();

    private TestMvcBuilder() {
        //Static class, should not be instantiated
    }

    public static Supplier createSupplier(int id) {
        return new Supplier(id, "hertz");
    }

    public static String toJsonString(Object jsonObject) {
        try {
            return JSON_MAPPER.writeValueAsString(jsonObject);
        } catch (IOException ex) {
            throw new AssertionError("Could not parse object into json string: ", ex);
        }
    }

    public static <T> T toJsonObject(String jsonString, Class<T> objectClass) {
        try {
            return JSON_MAPPER.readValue(jsonString, objectClass);
        } catch (IOException ex) {
            throw new AssertionError("Could not parse back string into json object: ", ex);
        }
    }

    public static <L> L toTypedJsonObject(String jsonString, Class<L> typedClass, Class<?> objectClass) {
        try {
            return JSON_MAPPER.readValue(jsonString, TypeFactory.defaultInstance().constructParametricType(typedClass, objectClass));
        } catch (IOException ex) {
            throw new AssertionError("Could not parse back string into json object: ", ex);
        }
    }
}
