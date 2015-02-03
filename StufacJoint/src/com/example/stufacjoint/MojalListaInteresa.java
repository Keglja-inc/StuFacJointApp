package com.example.stufacjoint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;


public class MojalListaInteresa extends ListActivity {
	
	
	private ProgressDialog pDialog;

	// URL to get contacts JSON
	private static String url = "http://stufacjoint.us.wak-apps.com/interesi?fakultet=1";

	// JSON Node names
	private static final String TAG_PODACI = "podaci";
	private static final String TAG_idInteresa = "idInteresa";
	private static final String TAG_NAZIV = "naziv";
	private static final String TAG_DATA="data";
	private static final String TAG_NAZIV2="naziv";
	private static final String TAG_POMOCNI="pomocni";

	ListAdapter adapter=null;
	// contacts JSONArray
	JSONArray podaci = null;
	
	JSONArray podaci2=null;

	// Hashmap for ListView
	ArrayList<HashMap<String, String>> contactList;
	
	
	
	ArrayList<HashMap<String,String>> listafakulteta;

	public String a;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mojal_lista_interesa);
		
		
		SharedPreferences preferences =PreferenceManager.getDefaultSharedPreferences(this);
		final boolean ugasiDialog = preferences.getBoolean("pristup_serveru", true);
		
		
		contactList = new ArrayList<HashMap<String, String>>();
		
		listafakulteta = new ArrayList<HashMap<String,String>>();

		final ListView lv = getListView();
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			@SuppressLint("ShowToast")
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				
				
				String selectedFromList =lv.getItemAtPosition(position).toString();
				
				String novi=selectedFromList.substring(1, selectedFromList.length()-1);
				
				String[] dijelovi=novi.split(",");
				String[] idDio=dijelovi[1].split("=");
				
				@SuppressWarnings("unused")
				String nova = idDio[1].toString();
				
				@SuppressWarnings("unused")
				Object za_poslati=new Object();
				a=idDio[1];
				
				
				if(ugasiDialog){
					new Loader().execute();
					}else{
						
					}
				
				//new Loader().execute();
				
				try {
					
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				
			}
		});

		
		
	
		new GetInterests().execute();


		
	}
	
	private class GetInterests extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Showing progress dialog
			pDialog = new ProgressDialog(MojalListaInteresa.this);
			pDialog.setMessage("Dohvaæanje podataka...prièekajte molim...");
			
			pDialog.show();

		}

		@Override
		protected Void doInBackground(Void... arg0) {
			// Creating service handler class instance
			ServiceHandler sh = new ServiceHandler();

			// Making a request to url and getting response
			String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);

			Log.d("Response: ", "> " + jsonStr);

			if (jsonStr != null) {
				try {
					JSONObject jsonObj = new JSONObject(jsonStr);
					
					// Getting JSON Array node
					podaci = jsonObj.getJSONArray(TAG_PODACI);

					// looping through All Contacts
					for (int i = 0; i < podaci.length(); i++) {
						JSONObject c = podaci.getJSONObject(i);
						
						String id = c.getString(TAG_idInteresa);
						String name = c.getString(TAG_NAZIV);
						

						

						// tmp hashmap for single contact
						HashMap<String, String> contact = new HashMap<String, String>();

						// adding each child node to HashMap key => value
						contact.put(TAG_idInteresa, id);
						contact.put(TAG_NAZIV, name);
						
						

						// adding contact to contact list
						contactList.add(contact);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			} else {
				Log.e("ServiceHandler", "Couldn't get any data from the url");
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			// Dismiss the progress dialog
			if (pDialog.isShowing())
				pDialog.dismiss();
			/**
			 * Updating parsed JSON data into ListView
			 * */
			adapter = new SimpleAdapter(
					MojalListaInteresa.this, contactList,
					R.layout.list_item, new String[] {TAG_NAZIV
							 }, new int[] {R.id.name });

			setListAdapter(adapter);
			
			
		}
		
	}
	
	class Loader extends AsyncTask<Void, Void, JSONObject>{
		ProgressDialog dialog;
		
		
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			
			Toast.makeText(getApplicationContext(), "Dohvaæanje liste fakulteta", Toast.LENGTH_SHORT).show();
		}

		

		@Override
		protected JSONObject doInBackground(Void... params) {
			
			return postJsonObject("http://stufacjoint.us.wak-apps.com/1bb74c94d73fdfe3801f197bab6ffb93", makingJson());
		}

		@Override
		protected void onPostExecute(JSONObject result) {
			super.onPostExecute(result);
			
			if (result!=null) {
				
				try {
					podaci2=result.getJSONArray(TAG_DATA);
					
					for(int i=0;i<podaci2.length();i++){
						
						JSONObject h=podaci2.getJSONObject(i);
						
						
						String naziv=h.getString(TAG_NAZIV2);
						String pomocni="pomocni";
						
						
						

						HashMap<String, String> contact2 = new HashMap<String, String>();
						
						
						contact2.put(TAG_NAZIV2, naziv);
						contact2.put(TAG_POMOCNI, pomocni);
						
						
						listafakulteta.add(contact2);
						
						
							
					}
					
					
					
					ListAdapter adapter2 = new SimpleAdapter(
							MojalListaInteresa.this, listafakulteta,
							R.layout.list_item, new String[] {TAG_NAZIV2
									 }, new int[] {R.id.ime_fakulteta });
						Log.e("moje", "DO ovde radi");
					setListAdapter(adapter2);
					
					//Toast.makeText(getApplicationContext(), naziv, Toast.LENGTH_LONG).show();
					
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
			}else {
				Toast.makeText(MojalListaInteresa.this, "Successfully post json object", Toast.LENGTH_LONG).show();
			}
		}
		
	}
	
	public JSONObject makingJson() {

		JSONObject jObj = new JSONObject();
		
		try {
			
			jObj.put("id",a);
			
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jObj;
	}
	
	
	public JSONObject postJsonObject(String url, JSONObject loginJobj){
        InputStream inputStream = null;
        String result = "";
        try {
 
            
            HttpClient httpclient = new DefaultHttpClient();
 
            // 2. make POST request to the given URL
            
            
            HttpPost httpPost = new HttpPost(url);
            
            System.out.println(url);
            String json = "";
 
            // 4. convert JSONObject to JSON to String
            
            json = loginJobj.toString();
            
            System.out.println(json);
            // 5. set json to StringEntity
            StringEntity se = new StringEntity(json);
 
            // 6. set httpPost Entity
            httpPost.setEntity(se);
 
            // 7. Set some headers to inform server about the type of the content   
            //httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");
 
            // 8. Execute POST request to the given URL
            HttpResponse httpResponse = httpclient.execute(httpPost);
 
            // 9. receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();
 
           
            // 10. convert inputstream to string
            if(inputStream != null)
            {
                result = convertInputStreamToString(inputStream);
            	
            }
            	else
                result = " Did not work!";
 
        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }
 
        JSONObject json = null;
		try {
		
			json = new JSONObject(result);
		} catch (JSONException e) {
			e.printStackTrace();
		}
        // 11. return result
		
        return json;
    }
	
	private String convertInputStreamToString(InputStream inputStream) throws IOException{
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;
 
        inputStream.close();
        
      
        return result;
    }
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mojal_lista_interesa, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		SharedPreferences preferences =PreferenceManager.getDefaultSharedPreferences(this);
		boolean ugasi_dialog = preferences.getBoolean("otvaranje_dialoga", true);
		
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			Intent novi=new Intent(this, SettingsActivity.class);
			startActivity(novi);
			return true;
		}else if(id==R.id.o_aplikaciji){
			if(ugasi_dialog){
			ORazvoju sd = new ORazvoju(this);
			sd.show();
			}else{
				Toast.makeText(this, "Opcija je onemoogućena", Toast.LENGTH_LONG).show();
			}
			return true;
			
			
		}
		return super.onOptionsItemSelected(item);
	}
	
	float x1 = 0, x2 = 0;
	public boolean onTouchEvent(MotionEvent touchevent) {		
		// check if swipe is enabled in the preferences by the user
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
		boolean swipeEnabled = preferences.getBoolean("pomicanje svajpova", true);		
		if(!swipeEnabled) return false;
		
		switch (touchevent.getAction()) {
			case MotionEvent.ACTION_DOWN: {
				x1 = touchevent.getX();
				break;
			}
			case MotionEvent.ACTION_UP: {
				x2 = touchevent.getX();
				if (x1 < x2) {
					onBackPressed();
					Intent i=new Intent(getApplicationContext(),MojalListaInteresa.class);
					startActivity(i);
					//setListAdapter(adapter);
				}
			}
		}
		return false;
	}

}
