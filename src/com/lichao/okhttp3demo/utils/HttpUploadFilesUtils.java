package com.lichao.okhttp3demo.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.Socket;
import java.net.URL;
import java.util.Map;
import java.util.UUID;
import android.util.Log;
import com.lichao.okhttp3demo.bean.FormFileBean;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

/** �ϴ��ļ������࣬ǧ��Ҫ��̫��ʱ���������棬ʵ�ʿ����в����������ַ�ʽ�ģ� */
public class HttpUploadFilesUtils {
	public static final String BOUNDARY = UUID.randomUUID().toString(); //�߽��ʶ���������
	public static final String PREFIX = "--";
	public static final String LINEND = "\r\n";//�ǡ�"\r\n"�����ǡ�"/r/n"��
	public static final String MULTIPART_FROM_DATA = "multipart/form-data";
	public static final String CHARSET = "UTF-8";
	protected static final String TAG = "HttpUploadFilesUtils";

	/**
	 * ͨ��ƴ�ӵķ�ʽ�����������ݣ�ʵ�ֲ��������Լ��ļ�����
	 */
	public static String post(String actionUrl, Map<String, String> params, Map<String, File> files) {

		try {
			URL uri = new URL(actionUrl);
			HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(3000);
			conn.setDoInput(true);// ��������
			conn.setDoOutput(true);// �������
			conn.setUseCaches(false); // ������ʹ�û���
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("Charsert", "UTF-8");
			conn.setRequestProperty("Content-Type", MULTIPART_FROM_DATA + ";boundary=" + BOUNDARY);

			// ������ƴString���͵Ĳ���
			StringBuilder sb = new StringBuilder();
			for (Map.Entry<String, String> entry : params.entrySet()) {
				sb.append(PREFIX + BOUNDARY + LINEND);
				sb.append("Content-Disposition: form-data; name=\"" + entry.getKey() + "\"" + LINEND);
				sb.append("Content-Type: text/plain; charset=" + CHARSET + LINEND);
				sb.append("Content-Transfer-Encoding: 8bit" + LINEND);
				sb.append(LINEND);
				sb.append(entry.getValue());
				sb.append(LINEND);
			}

			//��java������������д�롾���������DataOutputStream���У�������ͨ��������������DataInputStream�������ݶ���
			DataOutputStream outStream = new DataOutputStream(conn.getOutputStream());//����һ��������д��ָ�������OutputStream�����������
			outStream.write(sb.toString().getBytes());//������д���������췽���д���������
			//Ȼ����ƴFile���͵Ĳ���
			if (files != null) {
				for (Map.Entry<String, File> file : files.entrySet()) {
					StringBuilder sb1 = new StringBuilder();
					sb1.append(PREFIX + BOUNDARY + LINEND);
					// name��post�д��εļ���filename���ļ�������
					sb1.append("Content-Disposition: form-data; name=\"file1\"; filename=\"" + file.getKey() + "\"" + LINEND);
					sb1.append("Content-Type: multipart/form-data" + LINEND);
					sb1.append(LINEND);

					outStream.write(sb1.toString().getBytes());
					InputStream is = new FileInputStream(file.getValue());
					byte[] buffer = new byte[1024];
					int len = 0;
					while ((len = is.read(buffer)) != -1) {
						outStream.write(buffer, 0, len);
					}
					is.close();
					outStream.write(LINEND.getBytes());
				}

				// ���������־
				byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINEND).getBytes();
				outStream.write(end_data);
				outStream.flush();
				// �õ���Ӧ��
				int res = conn.getResponseCode();
				Log.i("bqt", "++++++" + res);//500���������쳣
				InputStream inputStream = conn.getInputStream();
				Log.i("bqt", "++++++" + 3);
				if (res == 200) {
					int ch;
					StringBuilder sb2 = new StringBuilder();
					while ((ch = inputStream.read()) != -1) {
						sb2.append((char) ch);
					}
					Log.i("bqt", "++++++" + sb2.toString());
					return sb2.toString();
				}
				outStream.close();
				conn.disconnect();
				return "��Ӧʧ��" + res;
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return "MalformedURLException";
		} catch (ProtocolException e) {
			e.printStackTrace();
			return "ProtocolException";
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return "FileNotFoundException";
		} catch (IOException e) {
			e.printStackTrace();
			return "IOException";
		}
		return "ʧ��";
	}

	/** 
	 * ֱ��ͨ��HTTPЭ���ύ���ݵ�������,ʵ����������ύ����: 
	 */
	public static boolean uploadFiles(String path, Map<String, String> params, FormFileBean[] files) throws Exception {
		final String BOUNDARY = "---------------------------7da2137580612"; //���ݷָ���  
		final String endline = "--" + BOUNDARY + "--\r\n";//���ݽ�����־  

		int fileDataLength = 0;
		if (files != null && files.length != 0) {
			for (FormFileBean uploadFile : files) {//�õ��ļ��������ݵ��ܳ���  
				StringBuilder fileExplain = new StringBuilder();
				fileExplain.append("--");
				fileExplain.append(BOUNDARY);
				fileExplain.append("\r\n");
				fileExplain.append("Content-Disposition: form-data;name=\"" + uploadFile.getParameterName() + "\";filename=\"" + uploadFile.getFilname() + "\"\r\n");
				fileExplain.append("Content-Type: " + uploadFile.getContentType() + "\r\n\r\n");
				fileExplain.append("\r\n");
				fileDataLength += fileExplain.length();
				if (uploadFile.getInStream() != null) {
					fileDataLength += uploadFile.getFile().length();
				} else {
					fileDataLength += uploadFile.getData().length;
				}
			}
		}
		StringBuilder textEntity = new StringBuilder();
		if (params != null && !params.isEmpty()) {
			for (Map.Entry<String, String> entry : params.entrySet()) {//�����ı����Ͳ�����ʵ������  
				textEntity.append("--");
				textEntity.append(BOUNDARY);
				textEntity.append("\r\n");
				textEntity.append("Content-Disposition: form-data; name=\"" + entry.getKey() + "\"\r\n\r\n");
				textEntity.append(entry.getValue());
				textEntity.append("\r\n");
			}
		}
		//���㴫�����������ʵ�������ܳ���  
		int dataLength = textEntity.toString().getBytes().length + fileDataLength + endline.getBytes().length;

		URL url = new URL(path);
		int port = url.getPort() == -1 ? 80 : url.getPort();
		Socket socket = new Socket(InetAddress.getByName(url.getHost()), port);
		OutputStream outStream = socket.getOutputStream();
		//�������HTTP����ͷ�ķ���  
		String requestmethod = "POST " + url.getPath() + " HTTP/1.1\r\n";
		outStream.write(requestmethod.getBytes());
		String accept = "Accept: image/gif, image/jpeg, image/pjpeg, image/pjpeg, application/x-shockwave-flash, application/xaml+xml, application/vnd.ms-xpsdocument, application/x-ms-xbap, application/x-ms-application, application/vnd.ms-excel, application/vnd.ms-powerpoint, application/msword, */*\r\n";
		outStream.write(accept.getBytes());
		String language = "Accept-Language: zh-CN\r\n";
		outStream.write(language.getBytes());
		String contenttype = "Content-Type: multipart/form-data; boundary=" + BOUNDARY + "\r\n";
		outStream.write(contenttype.getBytes());
		String contentlength = "Content-Length: " + dataLength + "\r\n";
		outStream.write(contentlength.getBytes());
		String alive = "Connection: Keep-Alive\r\n";
		outStream.write(alive.getBytes());
		String host = "Host: " + url.getHost() + ":" + port + "\r\n";
		outStream.write(host.getBytes());
		//д��HTTP����ͷ�����HTTPЭ����дһ���س�����  
		outStream.write("\r\n".getBytes());
		//�������ı����͵�ʵ�����ݷ��ͳ���  
		outStream.write(textEntity.toString().getBytes());
		//�������ļ����͵�ʵ�����ݷ��ͳ���  
		if (files != null && files.length != 0) {
			for (FormFileBean uploadFile : files) {
				StringBuilder fileEntity = new StringBuilder();
				fileEntity.append("--");
				fileEntity.append(BOUNDARY);
				fileEntity.append("\r\n");
				fileEntity.append("Content-Disposition: form-data;name=\"" + uploadFile.getParameterName() + "\";filename=\"" + uploadFile.getFilname() + "\"\r\n");
				fileEntity.append("Content-Type: " + uploadFile.getContentType() + "\r\n\r\n");
				outStream.write(fileEntity.toString().getBytes());
				if (uploadFile.getInStream() != null) {
					byte[] buffer = new byte[1024];
					int len = 0;
					while ((len = uploadFile.getInStream().read(buffer, 0, 1024)) != -1) {
						outStream.write(buffer, 0, len);
					}
					uploadFile.getInStream().close();
				} else {
					outStream.write(uploadFile.getData(), 0, uploadFile.getData().length);
				}
				outStream.write("\r\n".getBytes());
			}
		}
		//���淢�����ݽ�����־����ʾ�����Ѿ�����  
		outStream.write(endline.getBytes());

		BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		String codeString = reader.readLine();
		outStream.flush();
		outStream.close();
		reader.close();
		socket.close();
		if (codeString.indexOf("200") == -1) {//��ȡweb���������ص����ݣ��ж��������Ƿ�Ϊ200���������200����������ʧ��  
			return false;
		}
		return true;
	}

	//**************************************************************************************************************************
	//ͨ��HttpURLConnection�ֶ��������������������£��Դ�������ݽ��й淶����ƴ��
	public static String uploadByHttpURLConnection(String filePath, String strUrl) {
		int TIME_OUT = 5 * 1000; //��ʱʱ��
		String CHARSET = "utf-8"; //���ñ���
		String result = null;//������Ӧ������
		String BOUNDARY = UUID.randomUUID().toString(); //�߽��ʶ���������
		String PREFIX = "--", LINE_END = "\r\n";
		String CONTENT_TYPE = "multipart/form-data"; //��������
		try {
			URL url = new URL(strUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(TIME_OUT);
			conn.setConnectTimeout(TIME_OUT);
			conn.setDoInput(true); //����������
			conn.setDoOutput(true); //���������
			conn.setUseCaches(false); //������ʹ�û���
			conn.setRequestMethod("POST"); //����ʽ
			conn.setRequestProperty("Charset", CHARSET); //���ñ���
			conn.setRequestProperty("connection", "keep-alive");
			conn.setRequestProperty("Content-Type", CONTENT_TYPE + ";boundary=" + BOUNDARY);
			//conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			// conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			//���ļ���Ϊ�գ����ļ���װ�����ϴ�
			File file = new File(filePath);
			if (file.exists() && file.length() > 0) {
				DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
				StringBuffer sb = new StringBuffer();
				sb.append(PREFIX);
				sb.append(BOUNDARY);
				sb.append(LINE_END);
				//�����ص�ע�⣺name�����ֵΪ����������Ҫkey,ֻ�����key �ſ��Եõ���Ӧ���ļ�,filename���ļ������֣�������׺����
				sb.append("Content-Disposition: form-data; name=\"img\"; filename=\"" + file.getName() + "\"" + LINE_END);
				sb.append("Content-Type: application/octet-stream; charset=" + CHARSET + LINE_END);
				sb.append(LINE_END);
				dos.write(sb.toString().getBytes());
				InputStream is = new FileInputStream(file);
				byte[] bytes = new byte[1024];
				int len = 0;
				while ((len = is.read(bytes)) != -1) {
					dos.write(bytes, 0, len);
				}
				is.close();
				dos.write(LINE_END.getBytes());
				byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINE_END).getBytes();
				dos.write(end_data);
				dos.flush();
				//��ȡ��Ӧ��  200=�ɹ�,����Ӧ�ɹ�����ȡ��Ӧ����  
				int res = conn.getResponseCode();
				Log.e(TAG, "response code:" + res);
				if (res == 200) {
					Log.e(TAG, "�ɹ�");
					InputStream input = conn.getInputStream();
					StringBuffer sb1 = new StringBuffer();
					int ss;
					while ((ss = input.read()) != -1) {
						sb1.append((char) ss);
					}
					result = sb1.toString();
					Log.e(TAG, "result : " + result);
				} else Log.e(TAG, "ʧ��");
			} else Log.e(TAG, "�ļ�������" + filePath);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	//**************************************************************************************************************************
	//���õ������첽���AsynchHttpClient�ϴ��ļ�
	public static void uploadByAsyncHttpClient(String filePath, String strUrl) {
		File file = new File(filePath);
		if (file.exists() && file.length() > 0) {
			AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
			RequestParams requestParams = new RequestParams();
			try {
				requestParams.put("profile_picture", file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			asyncHttpClient.post(strUrl, requestParams, new AsyncHttpResponseHandler() {

				@Override
				public void onFailure(int arg0, Header[] arg1, byte[] arg2,
						Throwable arg3) {
					Log.e(TAG, "ʧ��");
					
				}

				@Override
				public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
					Log.e(TAG, "�ɹ�");
					
				}
			});
		} else Log.e(TAG, "�ļ�������" + filePath);
	}
}