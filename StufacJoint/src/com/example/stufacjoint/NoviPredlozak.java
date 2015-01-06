package com.example.stufacjoint;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class NoviPredlozak extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_novi_predlozak);
		
		Button odustani=(Button)findViewById(R.id.vratiNalistuInteresa);
		odustani.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i=new Intent(getApplicationContext(), ListaInteresa.class);
				startActivity(i);
				
			}
		});
		
		 Button spremi=(Button)findViewById(R.id.spremigumb);
		  	spremi.setOnClickListener(new OnClickListener() {
				
				@SuppressLint("ShowToast")
				@Override
				public void onClick(View arg0) {
					Toast.makeText(getApplicationContext(), "Ovaj gumb trenutno nije u funkciji", Toast.LENGTH_SHORT).show();
					
				}
			});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.novi_predlozak, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			Toast.makeText(NoviPredlozak.this, "Dio aplikacije koji je jo� u razvoju...", Toast.LENGTH_LONG).show();
			return true;
			
		}else if(id==R.id.o_aplikaciji){
			o_razvoju sd = new o_razvoju(this);
			sd.show();
			return true;
			
		}
		return super.onOptionsItemSelected(item);
	}
}
