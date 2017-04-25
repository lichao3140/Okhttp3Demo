package com.lichao.okhttp3demo.utils;

import com.lichao.okhttp3demo.view.ProgressDialogView;

import android.content.Context;
import android.content.DialogInterface.OnCancelListener;

/**��ʾ�����ؽ��ȶԻ���Ĺ�����*/
public class ProgressDialogUtil {

	private static ProgressDialogView progressDialog;

	/**��ʾ�Զ���Ľ��ȶԻ��򣬲���ȡ��*/
	public static void showProgressDialog(Context context, String title, String message) {
		dismissProgressDialog();
		progressDialog = new ProgressDialogView(context);
		progressDialog.setTitle(title);
		progressDialog.setMessage(message);
		progressDialog.setCancelable(false);
		progressDialog.show();
	}

	/**��ʾ�Զ���Ľ��ȶԻ���*/
	public static void showProgressDialog(Context context, String title, String message, boolean cancelable, OnCancelListener cancelListener) {
		dismissProgressDialog();
		progressDialog = new ProgressDialogView(context);
		progressDialog.setTitle(title);
		progressDialog.setMessage(message);
		progressDialog.setCancelable(cancelable);
		progressDialog.setOnCancelListener(cancelListener);
		progressDialog.show();
	}

	/** ȡ����ʾ���������ĶԻ��� */
	public static void dismissProgressDialog() {
		if (progressDialog != null && progressDialog.isShowing()) {
			try {
				progressDialog.dismiss();
			} catch (IllegalArgumentException e) {
			}
		}
		progressDialog = null;
	}
}