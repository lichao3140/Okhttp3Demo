package com.lichao.okhttp3demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

/** ������ */
public class MainActivity extends ListActivity {
	private List<String> mData;//����
	private ListAdapter mAdapter;//������

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mData = new ArrayList<String>(Arrays.asList("ʹ���˺������¼", "�����˻�������Զ���¼", "ͨ��http��get��post��ʽ�������ͨѶ",//
				"�������ͼ����ȡ���ü�ͼƬ�����ϴ�������", "ʹ��HttpURLConnection�����̣߳������ļ�", "ʹ��DownloadManager�����ļ�"));
		mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mData);
		setListAdapter(mAdapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		switch (position) {
		case 0:
			startActivity(new Intent(this, LoginActivity.class));
			break;
		case 1:
			Bundle loginBundle = new Bundle();
			loginBundle.putString("account", "103468");
			loginBundle.putString("password", "103468");
			Intent intent = new Intent(this, LoginActivity.class);
			intent.putExtras(loginBundle);
			startActivity(intent);
			break;
		case 2:
			startActivity(new Intent(this, HttpComActivity.class));
			break;
		case 3:
			startActivity(new Intent(this, UploadFileActivity.class));
			break;
		case 4:
			startActivity(new Intent(this, HttpDownloadActivity.class));
			break;
		case 5:
			startActivity(new Intent(this, DownloadManagerActivity.class));
			break;
		}
	}
}