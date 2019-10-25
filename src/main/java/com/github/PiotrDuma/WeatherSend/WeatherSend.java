package com.github.PiotrDuma.WeatherSend;

public class WeatherSend {

	public static void main(String[] args) {

		GetWeather getWeather = new GetWeather("wroclaw");
		JsonWeather jsonWeather = new JsonWeather(getWeather.getWeather());
		
		String message = jsonWeather.toString();
		System.out.println(message);
		
		//login and password deleted, type your own data
		String login = "yourEmail@email.com";
		String password = "YourPasswrd";
		
		String [] toArray = {"piotr_duma@interia.pl"};
		String mailSubject = jsonWeather.getCityName()+" weather";
		
		Mail mail = new Mail(login, password);
		mail.sendMail(toArray, mailSubject, message);
	}

}
