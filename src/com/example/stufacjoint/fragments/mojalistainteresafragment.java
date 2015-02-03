package com.example.stufacjoint.fragments;

import com.example.stufacjoint.R;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

@SuppressLint("NewApi")
public class mojalistainteresafragment extends Fragment  {
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		 return  inflater.inflate(R.layout.pregledpredloskafragment,container,false);
		 	
		 
			
	}	
	
	
	public void onStart() {
		super.onStart();
	}
	
}
