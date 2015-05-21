package com.example.scrollfb;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Adapter extends BaseAdapter {
	public static int ADD = 10;
	private Context context;

	public void setContext(Context context) {
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return ADD;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View child, ViewGroup parent) {
		// TODO Auto-generated method stub
		LinearLayout view = new LinearLayout(context);
		view.setPadding(30, 10, 30, 10);
		
		TextView tv = new TextView(context);
		tv.setText(String.valueOf(position));
		tv.setTextSize(20);
		
		view.addView(tv);
		
		return view;
	}

}
