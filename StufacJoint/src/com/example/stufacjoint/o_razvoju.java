package com.example.stufacjoint;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

public class o_razvoju extends Dialog  {

	public Context context;
	
	public o_razvoju(Context context) {
		super(context);
		this.context = context;
		this.setTitle(R.string.o_aplikaciji);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.o_razvoju);
		
	}
}
