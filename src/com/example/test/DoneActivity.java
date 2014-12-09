package com.example.test;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class DoneActivity extends ActionBarActivity {
	
	public static final String EXTRA_SIGNITURE = "com.example.Q";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_done);

		String q1 = "Fourth Activity: ";

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
			q1 += (String) savedInstanceState.getSerializable(EXTRA_SIGNITURE + "2");
		}

		Toast.makeText(DoneActivity.this,
				q1, Toast.LENGTH_SHORT).show();



	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.done, menu);
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
