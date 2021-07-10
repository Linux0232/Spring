package web.project.spring.util;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderRequest;
import com.google.code.geocoder.model.GeocoderResult;
import com.google.code.geocoder.model.GeocoderStatus;
import com.google.code.geocoder.model.LatLng;

@Component
public class MapUtil {
	public static Float[] geoCoding(String location) {
		if (location == null) return null;
		System.out.println("location : " + location);

		GeocoderRequest geocoderRequest = new GeocoderRequestBuilder().setAddress(location).setLanguage("ko").getGeocoderRequest();
		System.out.println("geocoderRequest : " + geocoderRequest);
		// setAddress : 변환하려는 주소 (경기도 성남시 분당구 등)
		// setLanguate : 인코딩 설정
		
		try {
			System.out.println("들어왔니?");
			Geocoder geocoder = new Geocoder();
			GeocodeResponse geocoderResponse = geocoder.geocode(geocoderRequest);
			System.out.println("getStatus() : " + geocoderResponse.getStatus());
			System.out.println(!geocoderResponse.getResults().isEmpty());
			if (geocoderResponse.getStatus() == GeocoderStatus.OK & !geocoderResponse.getResults().isEmpty()) {
				System.out.println("if문은?");
				GeocoderResult geocoderResult=geocoderResponse.getResults().iterator().next();
				LatLng latitudeLongitude = geocoderResult.getGeometry().getLocation();
				System.out.println("geocoderResult : " + geocoderResult);
				System.out.println("latitudeLongitude : " + latitudeLongitude);
				
				Float[] coords = new Float[2];
				coords[0] = latitudeLongitude.getLat().floatValue();
				coords[1] = latitudeLongitude.getLng().floatValue();
				System.out.println(location + ": " + coords[0] + ", " + coords[1]);
				return coords;
			}
		} catch (IOException ex) {
			System.out.println("실패!!!");
			ex.printStackTrace();
		}
		return null;
	}
}
