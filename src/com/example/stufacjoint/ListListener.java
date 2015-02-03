package com.example.stufacjoint;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.example.stufacjoint.rsss.RssItem;

public class ListListener implements OnItemClickListener{

	List<RssItem> listItems;
	Activity activity;
	/**
	 * 
	 * @param listItems
	 * @param activity
	 */
	public ListListener(List<RssItem> listItems, Activity activity){
		
		this.listItems=listItems;
		this.activity=activity;
		
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(listItems.get(position).getLink()));
		activity.startActivity(i);
		
	}

}
