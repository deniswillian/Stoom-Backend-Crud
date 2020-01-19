package com.stoom.backend.util;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.stoom.backend.exception.ResourceNotFoundException;

@Service
public class GoogleMapService {

	@Value("${google.api.maps.key}")
	private String apiKey;

	public GeocodingResult getCoordinatesFromAddress(String place) {

		GeocodingResult geocodingResult = null;

		try {

			GeocodingResult[] geocodingResultList;

			geocodingResultList = GeocodingApi.geocode(builderContext(), place).await();

			if (geocodingResultList.length <= 0)
				throw new ResourceNotFoundException("Coordinates", "place", place);

			geocodingResult = geocodingResultList[0];

		} catch (ApiException | InterruptedException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return geocodingResult;

	}

	private GeoApiContext builderContext() {

		GeoApiContext context = new GeoApiContext.Builder().apiKey(apiKey).build();

		return context;
	}

}
