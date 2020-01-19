package com.stoom.backend.util;

import java.lang.reflect.Type;
import java.util.List;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Component
public class MapperObjectUtil {

	public <T> T fromJson(String json, Class<T> anyClass) {
		return new Gson().fromJson(json, anyClass);
	}

	public <T> List<T> fromJsonList(String json, Class<T> anyClass) {

		Type collectionType = TypeToken.getParameterized(List.class, anyClass).getType();

		return new Gson().fromJson(json, collectionType);
	}

	public String toJson(Object object) {
		return new Gson().toJson(object);
	}

}
