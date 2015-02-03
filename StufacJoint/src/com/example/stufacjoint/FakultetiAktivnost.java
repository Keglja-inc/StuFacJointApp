package com.example.stufacjoint;

import android.app.ListActivity;
import android.os.Bundle;

public class FakultetiAktivnost extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fakulteti_aktivnost);
		
		/*
		ListAdapter adapter2 = new SimpleAdapter(
				getApplicationContext(), listafakulteta,
				R.layout.activity_fakulteti_aktivnost, new String[] {TAG_NAZIV
						 }, new int[] {R.id.ime_fakulteta });
			
		Log.e("moje", "DO ovde radi");
		setListAdapter(adapter2);
		*/
	}
}
