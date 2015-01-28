package com.frame.fragment;

import com.frame.activity.R;
import com.frame.data.model.DataSourceTest;
import com.frame.data.model.Exercise;
import com.frame.data.model.Information;
import com.frame.data.model.Material;
import com.frame.view.util.MyAdapter;
import com.frame.view.util.ViewGenerator;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TabHost;

public class EssenseFragment extends Fragment {
	private ShowDetail showDetail;
	private View rootView;
	private TabHost tabhost;
	private ListView listView1;
	private ListView listView2;
	private ListView listView3;
	private MyAdapter<Material> materialAdapter;
	private MyAdapter<Information> informationAdapter;
	private MyAdapter<Exercise> exercisesAdapter;
	private boolean isInit = true;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle saveInstanceState) {
		if (null == rootView) {
			rootView = inflater.inflate(R.layout.fragment_essense, container,
					false);
		}
		initVariable(rootView);
		return rootView;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if (!(activity instanceof ShowDetail)) {
			throw new IllegalStateException("error");
		}
		showDetail = (ShowDetail) activity;
	}

	public interface ShowDetail {
		public void switchToDetail(ViewGenerator viewGenerator);
	}

	// *********init variable*********

	private void initVariable(View view) {
		findViews(view);
		if (isInit) {
			initTabHost();
			initListViews();
		}
		isInit = false;
		setListener();
	}

	private void findViews(View view) {
		tabhost = (TabHost) view.findViewById(R.id.tabhost);
		listView1 = (ListView) view.findViewById(R.id.listView1);
		listView2 = (ListView) view.findViewById(R.id.listView2);
		listView3 = (ListView) view.findViewById(R.id.listView3);
	}

	private void initListViews() {
		Context context = getActivity().getBaseContext();
		materialAdapter = new MyAdapter<Material>(context,
				DataSourceTest.DATATYPE[0]);
		listView1.setAdapter(materialAdapter);
		informationAdapter = new MyAdapter<Information>(context,
				DataSourceTest.DATATYPE[1]);
		listView2.setAdapter(informationAdapter);
		exercisesAdapter = new MyAdapter<Exercise>(context,
				DataSourceTest.DATATYPE[2]);
		listView3.setAdapter(exercisesAdapter);
	}

	private void initTabHost() {
		// Call setup() before adding tabs if loading TabHost using
		// findViewById(). However: You do not need to call setup() after
		// getTabHost() in TabActivity.
		tabhost.setup();
		tabhost.addTab(tabhost.newTabSpec("tag1").setIndicator("tag1")
				.setContent(R.id.tab1));
		tabhost.addTab(tabhost.newTabSpec("tag2").setIndicator("tag2")
				.setContent(R.id.tab2));
		tabhost.addTab(tabhost.newTabSpec("tag3").setIndicator("tag3")
				.setContent(R.id.tab3));
	}

	private void setListener() {
		listView1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				showDetail.switchToDetail(null);
			}
		});
		listView2.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				showDetail.switchToDetail(null);
			}
		});
		listView3.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				showDetail.switchToDetail(null);
			}
		});
	}
}
