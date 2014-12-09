package com.example.test;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ThirdActivity extends ActionBarActivity {
	
	private RadioGroup[] radioGroups;
	private RadioButton radioButton;
	
	public static final int NUM_QUESTIONS = 3;
	public static final String EXTRA_SIGNITURE = "com.example.Q";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_third);
		
		String q1 = "Third Activity: ";
		
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
			q1 += (String) savedInstanceState.getSerializable(EXTRA_SIGNITURE + "3");
		}
		
		Toast.makeText(ThirdActivity.this,
        		q1, Toast.LENGTH_SHORT).show();
		
		radioGroups = new RadioGroup[NUM_QUESTIONS];
		
		radioGroups[0] = (RadioGroup) findViewById(R.id.q9group);
    	radioGroups[1] = (RadioGroup) findViewById(R.id.q10group);
    	radioGroups[2] = (RadioGroup) findViewById(R.id.q11group);
	}
	
	// Perform action on click
	public void clickEvent(View v){
        Intent intent = new Intent(getBaseContext(), FourthActivity.class);
        
        // Adds all of the extras from this intent to the next intent
        Bundle extras = getIntent().getExtras();
        for (String key : extras.keySet()){
        	intent.putExtra(key, extras.getString(key));
        }
        
        // Add the new extras from this activity
		// Adds all of the selected answers to the toast message
		for (int i = 0; i < radioGroups.length; i++){
			if (radioGroups[i] != null){
				int radioButtonId = radioGroups[i].getCheckedRadioButtonId();
				
				radioButton = (RadioButton) findViewById(radioButtonId);

				intent.putExtra(EXTRA_SIGNITURE + (i + 9) , radioButton.getText());
			}
		}
        
        startActivity(intent);
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.third, menu);
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
