package com.lichao.okhttp3demo.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.client.ClientProtocolException;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.entity.UrlEncodedFormEntity;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;

/** 
 * HttpURLConnection��HttpClient������
 * HttpURLConnection��java.net�е��࣬���ڱ�׼��java�ӿڣ���4.4�汾��Դ���б�OkHttp�滻���ˣ�HttpClient����Apache�ṩ�ģ��Ѿ���Google�����ˡ�
 * һ������ʵ�ʿ����ж���ʹ�ñ��˷�װ�õĵ��������������ܣ����磺Volley��android-async-http��loopj�ȡ�
 * */
public class HttpUrlClientUtils {
	//*********************************************************************************************************************************************
	//																										         HttpURLConnection
	//*********************************************************************************************************************************************
	/** ʹ��java.net.HttpURLConnection��ġ�GET���ķ�ʽ��¼	 */
	public static String httpURLConGet(String strUrl, String parameters) {
		HttpURLConnection httpURLConnection = null;
		try {
			URL url = new URL(strUrl + "?" + parameters);//"?"��������ݶ���������ͷ�е����ݣ���������ȡ��������Ϣ��ͨ��"&"���������
			httpURLConnection = (HttpURLConnection) url.openConnection();//��ָ��URL������
			httpURLConnection.setRequestMethod("GET"); // get����post�����д
			httpURLConnection.setConnectTimeout(3000); // ���ӵĳ�ʱʱ�䣬�Ǳ���
			httpURLConnection.setReadTimeout(3000); // �����ݵĳ�ʱʱ�䣬�Ǳ���
			httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Win64; x64; Trident/5.0)");//�Ǳ���

			int responseCode = httpURLConnection.getResponseCode();//��ȡ��Ӧ��
			if (responseCode == 200) {
				InputStream is = httpURLConnection.getInputStream();
				String result = getStringFromInputStream(is);
				return result;
			} else return "ʧ��";
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return "MalformedURLException";
		} catch (ProtocolException e) {
			e.printStackTrace();
			return "ProtocolException";
		} catch (IOException e) {
			e.printStackTrace();
			return "IOException";
		} finally {
			if (httpURLConnection != null) httpURLConnection.disconnect(); // �ر�����
		}
	}

	/** ʹ��java.net.HttpURLConnection��ġ�POST���ķ�ʽ��¼	 */
	public static String httpURLConPost(String strUrl, String requestBody) {
		HttpURLConnection httpURLConnection = null;
		try {
			URL url = new URL(strUrl);//����1��URL��ͬ��POST������·����������?���������
			httpURLConnection = (HttpURLConnection) url.openConnection();
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setDoInput(true);
			httpURLConnection.setDoOutput(true); // �������ô˷�����Ĭ�������, ϵͳ��������������������
			httpURLConnection.setConnectTimeout(3000); // ���ӵĳ�ʱʱ�䣬�Ǳ���
			httpURLConnection.setReadTimeout(3000); // �����ݵĳ�ʱʱ�䣬�Ǳ���
			httpURLConnection.setUseCaches(false);//Post��ʽ���ܻ��棬�Ǳ���

			// �������ͷ���Ǳ���
			httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Win64; x64; Trident/5.0)");//�����
			httpURLConnection.setRequestProperty("Accept-Charset", "utf-8");//����
			httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");//��������
			//	httpURLConnection.setRequestProperty("Content-Length", requestBody.length() + "");//��֪��Ϊʲô�����ô����Ժ�ᱨProtocolException��

			OutputStream out = httpURLConnection.getOutputStream();// ���һ��������� �����������д����
			out.write(requestBody.getBytes());//���õ���ƽ̨Ĭ�ϵ��ַ�������UTF-8���������е����ݼ�Ϊget����URL·��?���������	
			out.close();
			int responseCode = httpURLConnection.getResponseCode();
			if (responseCode == 200) {
				InputStream is = httpURLConnection.getInputStream();
				String state = getStringFromInputStream(is);
				return state;
			} else return "ʧ��";
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return "MalformedURLException";
		} catch (ProtocolException e) {
			e.printStackTrace();
			return "ProtocolException";
		} catch (IOException e) {
			e.printStackTrace();
			return "IOException";
		} finally {
			if (httpURLConnection != null) httpURLConnection.disconnect();
		}
	}

	//*********************************************************************************************************************************************
	//																										         HttpClient
	//*********************************************************************************************************************************************
	/** ʹ��org.apache.http.client.HttpClient�ġ�Get���ķ�ʽ��¼	 */
	public static String httpClientGet(String strUrl, String parameters) {
		HttpClient httpClient = null;
		try {
			httpClient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(strUrl + "?" + parameters);
			HttpResponse response = httpClient.execute(httpGet); //HttpResponse�����а����˷�������Ӧ��ȫ����Ϣ
			int statusCode = response.getStatusLine().getStatusCode();// ��÷�������Ӧ�еġ���Ӧ���е���Ӧ�롿
			if (statusCode == 200) {
				HttpEntity entity = response.getEntity();
				InputStream is = entity.getContent();//��ȡʵ������
				String text = getStringFromInputStream(is);
				return text;
			} else return "ʧ��";
		} catch (IllegalStateException e) {
			e.printStackTrace();
			return "IllegalStateException";
		} catch (IOException e) {
			e.printStackTrace();
			return "IOException";
		} finally {
			if (httpClient != null) httpClient.getConnectionManager().shutdown(); // �ر�����, �ͷ���Դ
		}
	}

	/** ʹ��org.apache.http.client.HttpClient�ġ�Post���ķ�ʽ��¼	 */
	public static String httpClientPost(String strUrl, List<NameValuePair> parameters) {
		HttpClient httpClient = null;
		try {
			httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(strUrl);
			HttpEntity entity = new UrlEncodedFormEntity(parameters, "utf-8");
			httpPost.setEntity(entity);//����ʵ������
			HttpResponse response = httpClient.execute(httpPost);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode == 200) {
				InputStream is = response.getEntity().getContent();
				String text = getStringFromInputStream(is);
				return text;
			} else return "ʧ��";
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "UnsupportedEncodingException";
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			return "ClientProtocolException";
		} catch (IllegalStateException e) {
			e.printStackTrace();
			return "IllegalStateException";
		} catch (IOException e) {
			e.printStackTrace();
			return "IOException";
		} finally {
			if (httpClient != null) httpClient.getConnectionManager().shutdown(); // �ر����Ӻ��ͷ���Դ
		}
	}

	//*********************************************************************************************************************************************
	//																										          ��������
	//*********************************************************************************************************************************************
	/**�����ж�ȡ���ݣ������ֽ�����*/
	public static byte[] getBytesFromInputStream(InputStream is) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();// �ֽ����������(�ڴ������)�����Բ����ڴ滺���������ݣ�ת�����ֽ�����
		byte[] buffer = new byte[1024];
		int len = -1;
		try {
			while ((len = is.read(buffer)) != -1) {
				baos.write(buffer, 0, len);
			}
			is.close();
			return baos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (baos != null) {
				try {
					baos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**  ��������ת�����ַ�����Ϣ */
	public static String getStringFromInputStream(InputStream is) {
		byte[] bytes = getBytesFromInputStream(is);
		String temp = new String(bytes);//Ĭ��ΪUTF8����
		if (temp.contains("utf-8")) return temp;
		else if (temp.contains("gb2312") || temp.contains("gbk")) {
			try {
				return new String(bytes, "gbk");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return temp;
	}

	/** ��ָ��URL��URLConnection���󣬻�ȡһ��InputStream */
	public static InputStream getInputStreamFromUrl(String url) {
		try {
			URLConnection conn = new URL(url).openConnection();
			conn.setConnectTimeout(3 * 1000);
			conn.setReadTimeout(3 * 1000);
			return conn.getInputStream();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}