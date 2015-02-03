package com.example.stufacjoint;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import com.example.stufacjoint.fragments.SettingsFragment;

@SuppressLint("NewApi")
public class SettingsActivity extends Activity {
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		
		// replace the entire content of the activity with fragments' layout
		getFragmentManager().beginTransaction()
        .replace(android.R.id.content, new SettingsFragment())
        .commit();
	}

}
