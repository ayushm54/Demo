package com.example.wheatherjason;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
	
	private String url1 = "http://api.openweathermap.org/data/2.5/weather?q=";
	   private EditText location,country,temperature,humidity,pressure;
	   private HandleJason obj;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		location=(EditText)findViewById(R.id.txtloc);
		country=(EditText)findViewById(R.id.txtcountry);
		temperature=(EditText)findViewById(R.id.txttemp);
		humidity=(EditText)findViewById(R.id.txthumidity);
		pressure=(EditText)findViewById(R.id.txtpressure);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void open(View view){
		String url = location.getText().toString();
	    String finalUrl = url1 + url;
	    country.setText(finalUrl);
	    
	    obj=new HandleJason(finalUrl);
	    obj.fetchJason();
	    while(obj.parsingcomplete){
	    	country.setText(obj.getCountry());
	    	temperature.setText(obj.getTemperature());
	    	humidity.setText(obj.getHumidity());
	    	pressure.setText(obj.getPressure());
	    }
	}
}
