package com.example.stufacjoint;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
/**
 * 
 * @author Alen
 * @version 1.3
 *
 */
public class ORazvoju extends Dialog  {

	public Context context;
	/**
	 * Postavlja tekst u dialog
	 * @param context - kontekst koji puni dialog
	 */
	public ORazvoju(Context context) {
		super(context);
		this.context = context;
		this.setTitle(R.string.o_aplikaciji);
	}
	
	/**
	 * 
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.o_razvoju);
		
	}
}
