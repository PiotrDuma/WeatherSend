package weatherSend;

public class WeatherSend {

	public static void main(String[] args) {

		GetWeather getWeather = new GetWeather("wroclaw");
		JsonWeather jsonWeather = new JsonWeather(getWeather.getWeather());
		
		String message = jsonWeather.toString();
		System.out.println(message);
		
		//login and password deleted, type your own data
		String login = "login@email.com";
		String password = "password";
		
		String [] toArray = {"piotr_duma@interia.pl","piotr.duma2014@gmail.com"};
		String mailSubject = jsonWeather.getCityName()+" weather";
		
		Mail mail = new Mail(login, password);
		mail.sendMail(toArray, mailSubject, message);
	}

}
