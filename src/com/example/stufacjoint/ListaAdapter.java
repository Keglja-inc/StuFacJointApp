package com.example.stufacjoint;

import java.util.HashMap;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ListaAdapter extends BaseExpandableListAdapter {

	@SuppressWarnings("unused")
	private Activity activity;
	private Context _context;
	@SuppressWarnings("unused")
	private LayoutInflater inflater;
	private List<String> _listDataHeader; // header titles
	// child data in format of header title, child title
	private HashMap<String, List<String>> _listDataChild;
	
	
	public void setInflater(LayoutInflater inflater,Activity activity) {
		this.activity = activity;
		this.inflater= inflater;
	}

	public ListaAdapter(Context _context, List<String> _listDataHeader,
			HashMap<String, List<String>> _listDataChild) {
		super();
		this._context = _context;
		this._listDataHeader = _listDataHeader;
		this._listDataChild = _listDataChild;
	}
	
	

	@SuppressLint("InflateParams")
	@Override
	public Object getChild(int groupPosition, int childPosititon) {
		return this._listDataChild.get(this._listDataHeader.get(groupPosition))
				.get(childPosititon);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@SuppressLint("InflateParams")
	@Override
	public View getChildView(int groupPosition, final int childPosition,
		
		boolean isLastChild, View convertView, ViewGroup parent) {

	
		
		final String childText = (String) getChild(groupPosition, childPosition);

		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this._context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.pomocni_prozor, null);
		}

		TextView txtListChild = (TextView) convertView
				.findViewById(R.id.ime_fakulteta);

		txtListChild.setText(childText);
	
		convertView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Toast.makeText(_context," Nadogradnja na otvaranje opisa fragmenta u tijeku...",Toast.LENGTH_LONG).show();
				
			}
		});
		return convertView;
	}
	
	@Override
	public int getChildrenCount(int groupPosition) {
		return this._listDataChild.get(this._listDataHeader.get(groupPosition))
				.size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return this._listDataHeader.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return this._listDataHeader.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@SuppressLint("InflateParams")
	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		String headerTitle = (String) getGroup(groupPosition);
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this._context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.listafakulteta, null);
		}

		TextView lblListHeader = (TextView) convertView
				.findViewById(R.id.ime_sveucilista);
		lblListHeader.setTypeface(null, Typeface.BOLD);
		lblListHeader.setText(headerTitle);

		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

}