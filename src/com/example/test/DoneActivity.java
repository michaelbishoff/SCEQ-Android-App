package com.example.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.support.v7.app.ActionBarActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class DoneActivity extends ActionBarActivity {
	
	public static final String EXTRA_SIGNITURE = "com.example.";
	public static Bundle extras;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_done);

		String q1 = "Fourth Activity: ";

		if (savedInstanceState == null) {
			
			extras = getIntent().getExtras();

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

		final TextView results = (TextView) findViewById(R.id.textView1);
		
//		Toast.makeText(DoneActivity.this, q1, Toast.LENGTH_SHORT).show();
				
		
		AsyncTask<Void, Void, Void> asyncTask = new AsyncTask<Void, Void, Void>() {
			@Override
	        protected Void doInBackground(Void... params) {
				HttpClient httpclient = new DefaultHttpClient();
			    HttpPost httppost = new HttpPost("http://userpages.umbc.edu/~bishoff1/CMSC331/Project2/data.php");
			    
			    try {
			        // Add your data
			        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			        nameValuePairs.add(new BasicNameValuePair("class", extras.getString(EXTRA_SIGNITURE + "catalogNumber")));
			        nameValuePairs.add(new BasicNameValuePair("professor", extras.getString(EXTRA_SIGNITURE + "professor")));
			        
			        // Calculates the professor's rating based on specific
			        // questions that contribute to a good professor
			        double averageScore = 0;
			        
			        averageScore += Integer.parseInt(extras.getString(EXTRA_SIGNITURE + "Q1"))
			        		+ Integer.parseInt(extras.getString(EXTRA_SIGNITURE + "Q2"))
			        		+ Integer.parseInt(extras.getString(EXTRA_SIGNITURE + "Q10"))
			        		+ Integer.parseInt(extras.getString(EXTRA_SIGNITURE + "Q11"))
			        		+ Integer.parseInt(extras.getString(EXTRA_SIGNITURE + "Q12"));
			        
			        averageScore /= 5;
			        
			        nameValuePairs.add(new BasicNameValuePair("rating", String.valueOf(averageScore)));
			        
			        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			        // Execute HTTP Post Request
			        HttpResponse response = httpclient.execute(httppost);
			        
			        // Converts the HttpResponse to a readable form
			        HttpEntity httpEntity = response.getEntity();
			        InputStream is = httpEntity.getContent();
			        
			        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			        String currentAverageScore = reader.readLine();
			        
			        // Reads the response from the HTTP Request and puts the score on the screen
			        results.setText(currentAverageScore);
			        
			        
			    } catch (ClientProtocolException e) {
			        // TODO Auto-generated catch block
			    } catch (IOException e) {
			        // TODO Auto-generated catch block
			    }
	            
	            return null;
	        }

	        @Override
	        protected void onPostExecute(Void aVoid) {
	            // Notifies UI when the task is done
//	            textView.setText("Insert finished!");
	        	Toast.makeText(DoneActivity.this, "Done", Toast.LENGTH_SHORT).show();
	        }
	    }.execute();
	    
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
