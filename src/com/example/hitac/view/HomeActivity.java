package com.example.hitac.view;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.json.JSONObject;

import com.example.hitac.R;
import com.example.hitac.model.Place;
import com.example.hitac.model.UserPrefs;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class HomeActivity extends ActionBarActivity
{
	private float currentLongitude;
	private float currentLatitude;
	private int tCount;
	private int dCount;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		int prepTime = getIntent().getIntExtra("userPrefsPT", 30);
		
		System.out.println("@@@@@@@@@@@@" + prepTime);
        
        UserPrefs uf = new UserPrefs(8, prepTime, "Rachelsmolen", "School");
        
        ArrayList<Place> places = new ArrayList<Place>();
        places = uf.getPlaces();
        
        for (Place p : places)
        {
        	new LatLongURL().execute(p);
        }
        
        // Get current location
        LocationManager locationManager = (LocationManager)
        		getSystemService(Context.LOCATION_SERVICE);
        
        Location lastKnown = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        
        if (lastKnown == null)
        {
        	System.out.println("uk lastKnown");
        }
        else
        {
        	currentLongitude = (float) lastKnown.getLongitude();
        	currentLatitude = (float) lastKnown.getLatitude();

            // Get time till school
        	TimeURL time = new TimeURL(places.get(0), currentLongitude, currentLatitude);
            time.execute();
        }
        
        Locale current = Locale.getDefault();
		
		// Create all textviews.
		TextView today = (TextView)findViewById(R.id.tvToday);
		TextView summary = (TextView)findViewById(R.id.tvSummary);
		TextView tomorrow = (TextView)findViewById(R.id.tvTomorrow);
		TextView dayAfter = (TextView)findViewById(R.id.tvDayAfter);
		
		String todayText = "TODAY";
		
		String summaryText = String.format(current,
				"Hours of sleep: %d, preparation time: %d minutes",
				uf.getSleepHours(), uf.getPrepTime());
		
		String tomorrowText = "TOMORROW";
		
		// Get the day after tomorrow.
		Date now = new Date();  
		Calendar cal = Calendar.getInstance();  
		cal.setTime(now);  
		cal.add(Calendar.DAY_OF_YEAR, 2); 
		SimpleDateFormat sdf = new SimpleDateFormat("EEEE", current);
		String dayAfterString = sdf.format(cal.getTime());
		
		// Set the texts.
        today.setText(todayText);
        summary.setText(summaryText);
        tomorrow.setText(tomorrowText);
        dayAfter.setText(dayAfterString.toUpperCase(current));
        
        // Set listeners
        tomorrow.setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View v)
			{
				// Go to overview
				TextView tomorrow = (TextView)findViewById(R.id.tvTomorrow);
				
				String current = (String) tomorrow.getText();
				tomorrow.setText(current.toUpperCase() + " // Alarm: 9:00");
				
				TextView dayAfter = (TextView)findViewById(R.id.tvDayAfter);

				Date now = new Date();  
				Calendar cal = Calendar.getInstance();  
				cal.setTime(now);  
				cal.add(Calendar.DAY_OF_YEAR, 2); 
				SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
				String dayAfterString = sdf.format(cal.getTime());
				
				dayAfter.setText(dayAfterString.toUpperCase());
			}
        });
        
        dayAfter.setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View v)
			{
				// Go to overview
				TextView dayAfter = (TextView)findViewById(R.id.tvDayAfter);
				
				String current = (String) dayAfter.getText();
				dayAfter.setText(current + " // Alarm: 11:00");
				
				TextView tomorrow = (TextView)findViewById(R.id.tvTomorrow);
				
				tomorrow.setText("TOMORROW");
			}
        });
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings)
		{
			/*
			final String[] EVENT_PROJECTION = new String[] {
				    Calendars._ID,                           // 0
				    Calendars.ACCOUNT_NAME,                  // 1
				    Calendars.CALENDAR_DISPLAY_NAME,         // 2
				    Calendars.OWNER_ACCOUNT                  // 3
				};
				  
				// The indices for the projection array above.
				final int PROJECTION_ID_INDEX = 0;
				final int PROJECTION_ACCOUNT_NAME_INDEX = 1;
				final int PROJECTION_DISPLAY_NAME_INDEX = 2;
				final int PROJECTION_OWNER_ACCOUNT_INDEX = 3;
				
				Cursor cur = null;
				ContentResolver cr = getContentResolver();
				Uri uri = Calendars.CONTENT_URI;   
				String selection = "((" + Calendars.ACCOUNT_NAME + " = ?) AND (" 
				                        + Calendars.ACCOUNT_TYPE + " = ?) AND ("
				                        + Calendars.OWNER_ACCOUNT + " = ?))";
				String[] selectionArgs = new String[] {"bas.broek@live.nl", "com.google",
				        "bas.broek@live.nl"};
				
				// Submit the query and get a Cursor object back. 
				cur = cr.query(uri, EVENT_PROJECTION, selection, selectionArgs, null);
				
				while (cur.moveToNext())
				{
				    long calID = 0;
				    String displayName = null;
				    String accountName = null;
				    String ownerName = null;
				      
				    // Get the field values
				    calID = cur.getLong(PROJECTION_ID_INDEX);
				    displayName = cur.getString(PROJECTION_DISPLAY_NAME_INDEX);
				    accountName = cur.getString(PROJECTION_ACCOUNT_NAME_INDEX);
				    ownerName = cur.getString(PROJECTION_OWNER_ACCOUNT_INDEX);
				              
				    // Do something with the values...
				    System.out.println(calID + displayName + accountName + ownerName);
				}
			*/
			
			return true;
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	private class TimeURL extends AsyncTask<Void, Void, String>
	{
		Place place;
		float longitude;
		float latitude;
		
		public TimeURL(Place place, float longitude, float latitude)
		{
			this.place = place;
			this.longitude = longitude;
			this.latitude = latitude;
		}
		
		@Override
		protected String doInBackground(Void... arg0)
		{
			String distance = getDistance(place, longitude, latitude);
			
			return distance;
		}
		
		@Override
		protected void onPostExecute(String result)
		{
			TextView summary = (TextView)findViewById(R.id.tvSummary);
			
			if (result.contains("destination"))
			{
				String current = (String) summary.getText();
				summary.setText(current + "\n\n" + result);
			}
			else
			{
				String current = (String) summary.getText();
				summary.setText(current + "\n\n" + "It will take you " + result + " to get to school.");
			}
		}
		
		public String getDistance(Place place, float longitude, float latitude)
	    {
	        String language = "us";
	        
	        String geoStartString = Float.toString(latitude) + "," + Float.toString(longitude);
	        String geoEndString = place.getLongitude().toString() + "," + place.getLatitude().toString();
	        
	        String request;
	        request = String.format(
	                "http://maps.googleapis.com/maps/api/distancematrix/json?" +
	                "origins=%s" +
	                "&destinations=%s" +
	                "&language=%s", geoStartString, geoEndString, language);
	        System.out.println("URL: " + request);
	        
	        String distance = handleRequest(request);
	        
	        return distance;
	    }
	    
	    public String handleRequest(String request)
	    {
	        String distance = "";
	        
	        try
	        {
	            String jsonAsText = getURL(request);
	            
	            JSONObject json = new JSONObject(jsonAsText);
	            // Check status
	            if (!json.getString("status").equals("OK"))
	            {
	                return null;
	            }
	            JSONObject elements = json.getJSONArray("rows").getJSONObject(0);
	            JSONObject moreElements = elements.getJSONArray("elements").getJSONObject(0);
	            JSONObject durationJSON = moreElements.getJSONObject("duration");
	            
	            distance = durationJSON.getString("text");
	            
	            int current = durationJSON.getInt("value");
	            
	            if (current < 5)
	            {
	            	distance = "You are at your destination.";
	            }
	        }
	        catch (Exception ex)
	        {
	            System.out.println(ex.getMessage());
	        }
	        
	        return distance;
	    }
	    
	    private String getURL(String urlString) throws Exception
	    {
	        BufferedReader reader = null;
	        try
	        {
	            URL url = new URL(urlString);
	            
	            // Must be done using an Async.
	            reader = new BufferedReader(new InputStreamReader(url.openStream()));
	            StringBuilder buffer = new StringBuilder();
	            int read;
	            char[] chars = new char[1024];
	            while ((read = reader.read(chars)) != -1)
	            {
	                buffer.append(chars, 0, read);
	            }
	            return buffer.toString();
	        }
	        finally
	        {
	            if (reader != null)
	            {
	                reader.close();
	            }
	        }
	    }
	}
	
	private class LatLongURL extends AsyncTask<Place, Void, String>
	{
		@Override
		protected String doInBackground(Place... params)
		{
			checkGeoLocation(params[0]);
			return null;
		}
		
		@Override
		protected void onPostExecute(String result)
		{
			System.out.println("done");
		}
		
		public void checkGeoLocation(Place p)
		{
			if (p.getLatitude() == null || p.getLatitude() == "" ||
					p.getLongitude() == null || p.getLongitude() == "")
			{
				// Reset longitude and latitude.
				p.setLatitude(null);
				p.setLongitude(null);
				
				String[] geoLocation = calculateGeoLocation(p.getAddress());
				
				// Set them.
				p.setLatitude(geoLocation[0]);
				p.setLongitude(geoLocation[1]);
				
				System.out.println(geoLocation[0] + " - " + geoLocation[1]);
			}
		}
		
		public String[] calculateGeoLocation(String address)
	    {
	        String language = "nl";
	        String countryAbbr = "NL";
	        
	        String formattedAddress = address.replaceAll("\\s", "+");
	        
	        String request = String.format(
	                "https://maps.googleapis.com/maps/api/geocode/json?" +
	                "address=%s," +
	                "&components=country:%s" +
	                "&language=%s", formattedAddress, countryAbbr, language);
	        
	        System.out.println("URL: " + request);
	        
	        return handleRequest(request);
	    }
		
		public String[] handleRequest(String request)
	    {
	        String longitude = null;
	        String latitude = null;
	        try
	        {
	            String jsonAsText = getURL(request);
	            
	            JSONObject json = new JSONObject(jsonAsText);
	            System.out.println("json: " + json);
	            
	            // Check status
	            if (!json.getString("status").equals("OK"))
	            {
	            	return null;
	            }
	            
	            JSONObject results = json.getJSONArray("results").getJSONObject(0);
	            JSONObject location = results.getJSONObject("geometry").getJSONObject("location");
	            
	            longitude = String.valueOf(location.get("lng"));
	            latitude = String.valueOf(location.get("lat"));
	        }
	        catch (Exception ex)
	        {
	            System.out.println("handleRequest(): " + ex.getMessage());
	        }
	        
	        return new String[]{longitude, latitude};
	    }
		
		private String getURL(String urlString) throws Exception
	    {
	        BufferedReader reader = null;
	        try
	        {
	            URL url = new URL(urlString);
	            
	            // Must be done using an Async.
	            reader = new BufferedReader(new InputStreamReader(url.openStream()));
	            StringBuilder buffer = new StringBuilder();
	            int read;
	            char[] chars = new char[1024];
	            while ((read = reader.read(chars)) != -1)
	            {
	                buffer.append(chars, 0, read);
	            }
	            return buffer.toString();
	        }
	        finally
	        {
	            if (reader != null)
	            {
	                reader.close();
	            }
	        }
	    }
	}
}
