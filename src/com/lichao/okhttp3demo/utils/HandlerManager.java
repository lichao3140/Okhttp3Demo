package com.lichao.okhttp3demo.utils;

import android.os.Handler;

/**��UI�߳��о�����ʹ�ô�Handler����Activity��Service��BroadcastReceiver�������õĶ���ͬһ�����󣬿��Է������໥ͨѶ
 * ThreadLocal��Ϊÿ��ʹ�øñ������߳��ṩ�����ı�������������ÿһ���̶߳����Զ����ظı��Լ��ĸ�����������Ӱ�������߳�����Ӧ�ĸ�����
 */
public class HandlerManager {
	private static ThreadLocal<Handler> threadLocal = new ThreadLocal<Handler>();

	public static Handler getHandler() {
		return threadLocal.get();//���ص�ǰ�߳�����Ӧ���ֲ߳̾�����
	}

	public static void setHandler(Handler mHandler) {
		threadLocal.set(mHandler);//���õ�ǰ�̵߳��ֲ߳̾�������ֵ
	}
}