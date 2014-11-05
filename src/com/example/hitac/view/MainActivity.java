package com.example.hitac.view;

import com.example.hitac.R;
import com.example.hitac.model.UserPrefs;

import android.support.v7.app.ActionBarActivity;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity
{
	public UserPrefs up;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		TextView welcome = (TextView)findViewById(R.id.textView2);
		
		AccountManager accountManager = AccountManager.get(this);

	    Account[] accounts =
	    accountManager.getAccountsByType("com.google");
		
		welcome.setText("Welcome, " + accounts[0].name);
		
		up = new UserPrefs();
		up = new UserPrefs(8, 30, "Geenestraat 65", "Thuis");
		System.out.println("Done!: " + up.toString());
		
		Button getButton = (Button)findViewById(R.id.getButton);
        getButton.setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(getApplicationContext(),
						                   HomeActivity.class);
				
				intent.putExtra("userPrefsPT", up.getPrepTime());
				intent.putExtra("userPrefsSH", up.getSleepHours());
				
				System.out.println(up.toString());
				
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
