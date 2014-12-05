package com.example.test;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	private static Toast toast;
	private RadioGroup[] radioGroups;
	private RadioGroup radioGroup1, radioGroup2, radioGroup3, radioGroup4, radioGroup5;
	private RadioButton radioButton;
	
	private static final int NUM_QUESTIONS = 2;
	public static final String EXTRA_SIGNITURE = "com.example.Q";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
    	Context context = getApplicationContext();
    	CharSequence text = "Hello toast!";
    	int duration = Toast.LENGTH_SHORT;

    	toast = Toast.makeText(context, text, duration);
    	
    	radioGroups = new RadioGroup[NUM_QUESTIONS];
    	
    	radioGroups[0] = (RadioGroup) findViewById(R.id.q1group);
    	radioGroups[1] = (RadioGroup) findViewById(R.id.q2group);
		
	}
	
	public void clickEvent(View v){
		// Perform action on click
		Intent intent = new Intent(getBaseContext(), SecondActivity.class);
		
	    // get selected radio button from radioGroup
		
		// Adds all of the selected answers to the toast message
		for (int i = 0; i < radioGroups.length; i++){
			if (radioGroups[i] != null){
				int radioButtonId = radioGroups[i].getCheckedRadioButtonId();

				radioButton = (RadioButton) findViewById(radioButtonId);

				intent.putExtra(EXTRA_SIGNITURE + (i + 1) , radioButton.getText());
			}
		}
		
        startActivity(intent);
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
}
