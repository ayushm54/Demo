package com.example.wheatherjason;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

import org.json.JSONObject;

import android.util.JsonReader;

public class HandleJason {
	
	   private String country = "country";
	   private String temperature = "temperature";
	   private String humidity = "humidity";
	   private String pressure = "pressure";
	   private String urlString = null;
	   public volatile boolean parsingcomplete=true;   
	   
	   public HandleJason(String url){
		   this.urlString=url;
	   }
	   
	   public String getCountry(){
		   return country;
	   }
	   
	   public String getTemperature(){
		   return temperature;
	   }
	   
	   public String getHumidity(){
		   return humidity;
	   }
	   
	   public String getPressure(){
		   return pressure;
	   }
	   
	   public void readAndParseJason(String data){
		   try{
			   JSONObject reader=new JSONObject(data);
			   JSONObject sys=reader.getJSONObject("sys");
			   country=sys.getString("country");
			   JSONObject main=reader.getJSONObject("main");
			   temperature=main.getString("temp");
			   humidity=main.getString("humidity");
			   pressure=main.getString("pressure");
			   parsingcomplete=false;
		   }
		   catch(Exception e){
			   e.printStackTrace();
		   }
	   }
	   
	   public void fetchJason(){
		   Thread thread=new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try{
					URL url=new URL(urlString);
					HttpURLConnection con=(HttpURLConnection) url.openConnection();
					con.setConnectTimeout(15000);//Argument in msec
					con.setReadTimeout(10000);//Argument in msec
					con.setRequestMethod("GET");
					con.setDoInput(true);   //to fetch response data
					
					con.connect();  //connecting to google wheather api and retrieve jason data
					
					InputStream stream=con.getInputStream();
					BufferedReader br=new BufferedReader(new InputStreamReader(stream));
					readAndParseJason(br.readLine());
					br.close();
					stream.close();
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}});
		   thread.start();
	   }
}
