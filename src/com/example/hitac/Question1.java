package com.example.hitac;

import java.util.ArrayList;

import com.example.hitac.view.HomeActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Question1 extends Activity
{
	ArrayList<String> minutePickerList = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_question1);
		
		ListView minuteList = (ListView)findViewById(R.id.minutePicker);
		
		getMinutes();
		
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, minutePickerList);
		
		minuteList.setAdapter(arrayAdapter);
		
		minuteList.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3)
			{
				String selectedMinute = minutePickerList.get(arg2);
				Toast.makeText(getApplicationContext(), "You picked " + selectedMinute + " minutes", Toast.LENGTH_LONG).show();
				
				Intent intent = new Intent(getApplicationContext(),
		                   HomeActivity.class);
				
				String[] minuteSplit = selectedMinute.split(" ");
				
				int minute = Integer.parseInt(minuteSplit[0]);
				
				System.out.println("@@@@@@@" + minute);

				intent.putExtra("userPrefsPT", minute);
				
				startActivity(intent);
			}
		});
	}

	void getMinutes()
	{
		minutePickerList.add("1 minute");
		minutePickerList.add("2 minutes");
		minutePickerList.add("3 minutes");
		minutePickerList.add("5 minutes");
		minutePickerList.add("10 minutes");
		minutePickerList.add("15 minutes");
		minutePickerList.add("20 minutes");
		minutePickerList.add("25 minutes");
		minutePickerList.add("30 minutes");
		minutePickerList.add("35 minutes");
		minutePickerList.add("40 minutes");
		minutePickerList.add("45 minutes");
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.question1, menu);
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
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
}
