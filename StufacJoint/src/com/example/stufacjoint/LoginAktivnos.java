package com.example.stufacjoint;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("ShowToast")
public class LoginAktivnos extends Activity {
	
	private EditText  ime=null;
	   private EditText  lozinka=null;
	   private TextView attempts;
	   private Button login;
	   int counter = 3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_aktivnos);
		
		  ime = (EditText)findViewById(R.id.editText1);
	      lozinka = (EditText)findViewById(R.id.editText2);
	      attempts = (TextView)findViewById(R.id.textView5);
	      attempts.setText(Integer.toString(counter));
	      login = (Button)findViewById(R.id.logingumb);
	      ImageButton facebook=(ImageButton)findViewById(R.id.facebookgumb);
		  	facebook.setOnClickListener(new OnClickListener() {
				
				@SuppressLint("ShowToast")
				@Override
				public void onClick(View arg0) {
					Toast.makeText(getApplicationContext(), "Ovaj gumb trenutno nije u funkciji", Toast.LENGTH_SHORT).show();
					
				}
			});
		
		
	}
	
	public void login(View view){
	      if(ime.getText().toString().equals("admin") && lozinka.getText().toString().equals("admin")){
	      
	      login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent i=new Intent(getApplicationContext(), Glavna_aktivnost.class);
				startActivity(i);
				
			}
		});
	   }	
	   else{
	      Toast.makeText(getApplicationContext(), "Pokušajte ponovno",
	      Toast.LENGTH_SHORT).show();
	      attempts.setBackgroundColor(Color.RED);	
	      counter--;
	      attempts.setText(Integer.toString(counter));
	      if(counter==0){
	         login.setEnabled(false);
	      }

	   }
	      
	      
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login_aktivnos, menu);
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
