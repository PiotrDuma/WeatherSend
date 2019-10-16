package weatherSend;

import java.net.MalformedURLException;
import java.net.*;
import java.util.Scanner;

public class GetWeather {

	private String city;
	private String weather;
	
	GetWeather(String city){
		this.city = city;
		weather = getJSONWeather();
	}
	
	private String getJSONWeather() {
		
		String urlcreator = "http://api.openweathermap.org/data/2.5/weather?q=" + city +",pl&APPID=ee10038ba3be0f51e07029a436148f6e";
		URL url;
		String result = new String();
		
		try {
			url = new URL(urlcreator);
			Scanner scan = null;
			try {
				scan = new Scanner(url.openStream());
				
				while(scan.hasNext()) {
					result += scan.nextLine();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				if(url != null) {
					scan.close();
				}
			}
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
		return result;
	}
	
	public String getWeather() {
		return weather;
	}
	
	public static void main(String[] args) {
		GetWeather getWeather = new GetWeather("wroclaw");
		System.out.println(new JsonWeather(getWeather.getWeather()));
	}
}
