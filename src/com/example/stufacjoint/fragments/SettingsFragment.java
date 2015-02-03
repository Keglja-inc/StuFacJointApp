package com.example.stufacjoint.fragments;


import com.example.stufacjoint.R;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.preference.PreferenceFragment;

@SuppressLint("NewApi")
public class SettingsFragment extends PreferenceFragment {
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.main_preferences);

}
}
