package com.example.stufacjoint;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.stufacjoint.db.DBAdapter;
import com.example.stufacjoint.db.Profil;

public class SignUpAktivnost extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup_aktivnost);
		
		DBAdapter.init(this);
		
		 		 
		 
		 
		 
		 Button spremi=(Button)findViewById(R.id.spremi);
		 spremi.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				EditText _ime=(EditText)findViewById(R.id.ime);
				String __ime=(String)_ime.getText().toString();
				EditText _prezime=(EditText)findViewById(R.id.prezime);
				String __prezime=(String)_prezime.getText().toString();
				EditText _mail=(EditText)findViewById(R.id.mailadress);
				String __mail=(String)_mail.getText().toString();
				EditText _lozinka=(EditText)findViewById(R.id.password);
				String __lozinka=(String)_lozinka.getText().toString();
				EditText _lozinka2=(EditText)findViewById(R.id.password2);
				String __lozinka2=(String)_lozinka2.getText().toString();
				
				
				if(__lozinka.equals(__lozinka2)){
					
					DBAdapter.addUserData(new Profil(__ime, __prezime, __mail, __lozinka));
					Toast.makeText(getBaseContext(), "Uspješno ste se regristrirali...", Toast.LENGTH_LONG).show();
					Intent i=new Intent(getApplicationContext(), LoginAktivnost.class);
					startActivity(i);
				}else{
					
					Toast.makeText(getBaseContext(), "Niste unijeli dobru lozinku, ponovite...", Toast.LENGTH_LONG).show();
				}
	
			}
		});
		 
		 
		 
	        
		
		
		Button odustani=(Button)findViewById(R.id.odustanigumb);
		odustani.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent i=new Intent(getApplicationContext(), LoginAktivnost.class);
				startActivity(i);
				
			}
		});
		
		
	}

	

}
