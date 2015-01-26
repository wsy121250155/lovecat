package com.mobile.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MFragment extends Fragment {
	private boolean created = false; //View是否已被创建
	private LayoutInflater inflater;
	private View contextView;
	private ViewGroup viewgroup;
	
	protected void create(Bundle savedInstanceState) {
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
		this.inflater = inflater;
		this.viewgroup = viewGroup;
		
		if(!this.created) {//如果没有创建UI在这里创建
			this.create(savedInstanceState);
			this.created = true;
		}
		
		return this.contextView;
	}
	
	public void setContentView(int contextView) {
		this.contextView = this.inflater.inflate(contextView, this.viewgroup, false);
	}
	
	/**
	 * 便于操作
	 * @param id
	 * @return
	 */
	public View findViewById(int id) {
		View res = null;
		if(this.contextView != null)
			res = this.contextView.findViewById(id);
		return res;
	}
	
	public Context getContext(){
		return getActivity();
	}
	
	/*
	 * setter & getter
	 */
	public LayoutInflater getInflater() {
		return inflater;
	}

	public void setInflater(LayoutInflater inflater) {
		this.inflater = inflater;
	}

	public View getContextView() {
		return contextView;
	}

	public void setContextView(View contextView) {
		this.contextView = contextView;
	}

	public ViewGroup getViewgroup() {
		return viewgroup;
	}

	public void setViewgroup(ViewGroup viewgroup) {
		this.viewgroup = viewgroup;
	}

	public boolean isCreated() {
		return created;
	}

	public void setCreated(boolean created) {
		this.created = created;
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	
	public void setActionBar(ActionBar actionBar, Context context) {
		
	}
}
