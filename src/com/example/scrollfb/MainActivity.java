package com.example.scrollfb;

import com.example.scrollfb.R;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ProgressBar;

public class MainActivity extends ListActivity implements OnScrollListener {

	private Adapter adapter;
	private ProgressBar loading;
	private boolean isAsyncTaskRunning = false;
	
    @Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		finish();
	}

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        loading = (ProgressBar) findViewById(R.id.progressBar1);
        loading.setVisibility(View.GONE);
        
        adapter = new Adapter();
        adapter.setContext(getApplicationContext());
        
        setListAdapter(adapter);
        getListView().setOnScrollListener(this);
    }

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub
		if (!isAsyncTaskRunning) {
			boolean loadMore = firstVisibleItem + visibleItemCount >= totalItemCount;
			if(loadMore) {
				new PingLocalhost().execute(visibleItemCount);
			}
		}
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub
		
	}
	
	private class PingLocalhost extends AsyncTask<Integer, Integer, Integer> {

		@Override
		protected void onPostExecute(Integer result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			
			isAsyncTaskRunning = false;
			loading.setVisibility(View.GONE);
			
			Adapter.ADD += result; // or any other amount
		    adapter.notifyDataSetChanged();
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			isAsyncTaskRunning = true;
			loading.setVisibility(View.VISIBLE);
		}

		@Override
		protected Integer doInBackground(Integer... params) {
			// TODO Auto-generated method stub
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return params[0];
		}
		
	}
}
