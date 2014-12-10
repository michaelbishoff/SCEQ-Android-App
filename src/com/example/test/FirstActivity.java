package com.example.test;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class FirstActivity extends ActionBarActivity {

	private EditText catalogNumber, professor;
	
	public static final String EXTRA_SIGNITURE = "com.example.";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first);
		
		
		catalogNumber = (EditText) findViewById(R.id.catalogNumber);
		professor = (EditText) findViewById(R.id.professor);
	}
	
	// Perform action on click
	public void clickEvent(View v){
		
		String errorMessage = "";
//		Toast.makeText(FirstActivity.this, catalogNumber.getText()., Toast.LENGTH_SHORT).show();
		if (catalogNumber.getText().toString().equals("")){
			errorMessage += "Catalog Number";
			
			if (professor.getText().toString().equals("")){
				errorMessage += " and Professor are required";
			}
			else {
				errorMessage += " is required";
			}
			
			Toast.makeText(FirstActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
		}
		else if (professor.getText().toString().equals("")){
			errorMessage += "Professor is required";
			
			Toast.makeText(FirstActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
		}
		else {
			
			Intent intent = new Intent(getBaseContext(), MainActivity.class);

			// Add the new extras from this activity
			// Adds all of the selected answers to the toast message
			intent.putExtra(EXTRA_SIGNITURE + "catalogNumber", catalogNumber.getText().toString());
			intent.putExtra(EXTRA_SIGNITURE + "professor", professor.getText().toString());

			startActivity(intent);

		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.first, menu);
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
