package com.lichao.okhttp3demo;

import com.lichao.okhttp3demo.utils.HandlerManager;

import android.app.DownloadManager;
import android.app.DownloadManager.Query;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

/** ע��һ���㲥����������������Ϻ���յ�һ��android.intent.action.DOWNLOAD_COMPLETE�Ĺ㲥��������ȡ���������������񣬽��а�װ*/
public class DownloadCompleteReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, 0);
		Handler mHandler = HandlerManager.getHandler();
		mHandler.sendMessage(Message.obtain(mHandler, DownloadManagerActivity.MSG_WHAT_DOWNLOAD_ID, id));
		if (intent.getAction().equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)) {
			Toast.makeText(context, "��� " + id + " �����������Ѿ���ɣ�", Toast.LENGTH_SHORT).show();
		} else if (intent.getAction().equals(DownloadManager.ACTION_NOTIFICATION_CLICKED)) {
			Toast.makeText(context, "��Ϲ��-" + id, Toast.LENGTH_SHORT).show();
		} else if (intent.getAction().equals(DownloadManager.ACTION_VIEW_DOWNLOADS)) {
			Toast.makeText(context, "�鿴������-" + id, Toast.LENGTH_SHORT).show();
		}
		//������ص���APK�ļ������Զ���װ
		DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
		Query query = new DownloadManager.Query();
		query.setFilterById(id);
		Cursor cursor = downloadManager.query(query);
		if (cursor != null) {
			if (cursor.moveToFirst()) {
				int status = cursor.getInt(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_STATUS));
				String name = cursor.getString(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_LOCAL_FILENAME));
				String uri = cursor.getString(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_LOCAL_URI));
				String end = name.substring(name.lastIndexOf("."));
				if (end != null && end.equals(".apk") && status == DownloadManager.STATUS_SUCCESSFUL) {
					Intent mIntent = new Intent(Intent.ACTION_VIEW);
					mIntent.setDataAndType(Uri.parse(uri), "application/vnd.android.package-archive");
					mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					context.startActivity(mIntent);
				}
			}
			cursor.close();
		}
	}
}