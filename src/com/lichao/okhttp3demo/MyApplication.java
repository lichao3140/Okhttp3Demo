package com.lichao.okhttp3demo;

import com.lichao.okhttp3demo.bean.UserBean;

import android.app.Application;

/**��������ȫ�ֱ���*/
public class MyApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		mApplication = this;
	}

	private static MyApplication mApplication = null;

	public static MyApplication getApplication() {
		return mApplication;
	}

	private UserBean mUser;

	public UserBean getUser() {
		return mUser;
	}

	public void setUser(UserBean mUser) {
		this.mUser = mUser;
	}

	@Override
	public void onTerminate() {//����ֹӦ�ó������ʱ���ã�����֤һ��������
		super.onTerminate();
		System.gc();
	}
}