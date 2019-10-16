package weatherSend;
import org.json.*;

public class JsonWeather {
	private JSONObject object = null;
	private String weather;
	
	private String name;
	private String description;
	private float temp;
	private int pressure;
	private int humidity;
	private int clouds;
	private float windSpeed;
	
	JsonWeather(String weather){
		 this.weather = weather;
		 parse();
	}
	
	private void parse() {
		//build object
		this.object = new JSONObject(weather);
	    
	    description = object.getJSONArray("weather").getJSONObject(0).getString("description");
	    name = object.getString("name");
	    
	    JSONObject mainArray = object.getJSONObject("main");
	    temp = mainArray.getFloat("temp");
	    pressure = mainArray.getInt("pressure");
	    humidity = mainArray.getInt("humidity");
	    
	    clouds = object.getJSONObject("clouds").getInt("all");
	    windSpeed = object.getJSONObject("wind").getFloat("speed");
	}

	public String toString() {
		return "Weather in "+name + ":\n"+
				description+ "\n"+
				"temperature: "+ temp +" K\n"+
				"pressure:" + pressure+" hPa\n"+
				"humidity:" + humidity  +" %\n"+
				"clouds:" + clouds  +" %\n"+
				"wind: speed: "+ windSpeed +" km/h";
	}	
	
	public String getCityName() {
		return name;
	}
	
}
