package com.lichao.okhttp3demo.utils;

import org.json.JSONObject;

import com.lichao.okhttp3demo.utils.AsyncHttpHelper.OnHttpListener;
import com.loopj.android.http.RequestParams;


/**AsyncHttp�ٷ�װ��ļ��׹�����*/
public class AsyncXiuHttpHelper {

	//	public static final String SERVER_URL = "api.95xiu.com";
	//	public static final String WEB_SERVER_URL = "chat.95xiu.com";
	//	public static final int LIVE_WEB_PORT = 3016;

	public static final String SERVER_URL = "tapi.95xiu.com";
	public static final String WEB_SERVER_URL = "tapi.95xiu.com";
	public static final int LIVE_WEB_PORT = 3014;

	/**
	 * get ����
	 * @param relativeUrl  ��Ե�ַ����"/user/loginv2.php"
	 * @param params   �������
	 * @param onHttpListner   �ɹ���ʧ��ʱ�ļ������ڻص������л�ȡ���ص�����
	 */
	public static void get(final String relativeUrl, RequestParams params, final OnHttpListener<JSONObject> onHttpListner) {
		params = FormatRequestParams(params);
		AsyncHttpHelper.get(SERVER_URL, relativeUrl, params, onHttpListner);
	}

	/**
	 *  post ����
	 * @param relativeUrl  ��Ե�ַ����"/user/loginv2.php"
	 * @param params   �������
	 * @param onHttpListner   �ɹ���ʧ��ʱ�ļ������ڻص������л�ȡ���ص�����
	 */
	public static void post(final String relativeUrl, RequestParams params, final OnHttpListener<JSONObject> onHttpListner) {
		params = FormatRequestParams(params);
		AsyncHttpHelper.post(SERVER_URL, relativeUrl, params, onHttpListner);
	}

	/**
	 * ��������˻�����Ϣ��RequestParams
	 */
	private static RequestParams FormatRequestParams(RequestParams params) {
		if (params == null) params = new RequestParams();
		//params.put("imei", Properties.IMEI);
		//params.put("channel", Properties.CN);
		//params.put("session_id", AppUser.getInstance().getUser().getuSessionId());
		//params.put("version_code", Properties.VERSION_CODE);
		return params;
	}
}