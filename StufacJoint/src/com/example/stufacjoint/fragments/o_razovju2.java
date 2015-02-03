package com.example.stufacjoint.fragments;


import com.example.stufacjoint.R;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

public class o_razovju2 extends Dialog{
	
	

	public Context context;

		public o_razovju2(Context context) {
			super(context);
			this.context = context;
			this.setTitle(R.string.o_aplikaciji);}



		
		
		
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.o_razvoju);
			
		}

}
