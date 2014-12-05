package com.example.test;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

public class SecondActivity extends ActionBarActivity {

	public static final String Q1 = "com.example.Q1";
	public static final String Q2 = "com.example.Q2";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		
		String q1 = "Second Activity: ";
		
		if (savedInstanceState == null) {
			
		    Bundle extras = getIntent().getExtras();
		    
		    if(extras == null) {
		        q1 = null;
		    } else {
		    	// Goes in here because savedInstanceState is always null. Could change that
		    	for (String key : extras.keySet()){
		    		q1 += extras.getString(key) + " ";
		    	}
		    }
		} else {
		    q1 += (String) savedInstanceState.getSerializable(Q1);
		}
		
		Toast.makeText(SecondActivity.this,
        		q1, Toast.LENGTH_SHORT).show();
	}
	
	// Perform action on click
	public void clickEvent(View v){
        Intent intent = new Intent(getBaseContext(), ThirdActivity.class);
        
        // Adds all of the extras from this intent to the next intent
        Bundle extras = getIntent().getExtras();
        for (String key : extras.keySet()){
        	intent.putExtra(key, extras.getString(key));
        }
        
        // Add the new extras from this activity
        
        
        startActivity(intent);
        
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.second, menu);
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
}
