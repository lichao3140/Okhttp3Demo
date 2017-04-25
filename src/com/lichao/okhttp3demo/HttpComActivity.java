package com.lichao.okhttp3demo;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import org.json.JSONException;
import org.json.JSONObject;
import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.lichao.okhttp3demo.bean.UrlOfPics;
import com.lichao.okhttp3demo.bean.UrlOfServer;
import com.lichao.okhttp3demo.utils.AsyncHttpHelper.OnHttpListener;
import com.lichao.okhttp3demo.utils.AsyncXiuHttpHelper;
import com.lichao.okhttp3demo.utils.HttpUrlClientUtils;
import com.lichao.okhttp3demo.utils.JsonFormatTool;
import com.lichao.okhttp3demo.utils.ProgressDialogUtil;
import com.loopj.android.http.RequestParams;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.message.BasicNameValuePair;

/**
 * ����ʹ�����ַ�ʽ��AsyncHttpClient��httpURLCon��httpClient���ֱ���get��post��ʽ���ʷ�����
 * @author ��Ǭ��
 */
public class HttpComActivity extends ListActivity {
	private TextView tv_result;
	private ImageView imageView;

	private String session_id;//ÿ�ε�¼����һ��
	private int uid;//10415362
	private String picUrl;//ÿ�ε�¼����һ��

	/**ƴ�ӵ�URL��ַ*/
	private static String URL_HEAD = "http://" + AsyncXiuHttpHelper.SERVER_URL;
	public static final int GET_ASYNC_HTTP = 0;
	public static final int POST_ASYNC_HTTP = 1;
	public static final int GET_HTTP_URL_CON = 2;
	public static final int POST_HTTP_URL_CON = 3;
	public static final int GET_HTTP_CLIENT = 4;
	public static final int POST_HTTP_CLIENT = 5;
	public static final int LOAD_PIC_BY_HANDLER = 6;
	public static final int LOAD_PIC_BY_ASYNCTASK = 7;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		String[] array = { "ʹ��AsyncHttpClient��get��ʽ", "ʹ��AsyncHttpClient��post��ʽ", //
				"ʹ��httpURLCon��get��ʽ", "ʹ��httpURLCon��post��ʽ", //
				"ʹ��httpClient��get��ʽ", "ʹ��httpClient��post��ʽ",//
				"ʹ��httpURLCon+handler����ͼƬ", "ʹ��httpURLCon+AsyncTask����ͼƬ" };
		for (int i = 0; i < array.length; i++) {
			array[i] = i + "��" + array[i];
		}
		imageView = new ImageView(this);
		getListView().addFooterView(imageView);
		tv_result = new TextView(this);// ��������ʾ��TextView��
		tv_result.setTextColor(Color.BLUE);
		tv_result.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
		tv_result.setPadding(20, 10, 20, 10);
		getListView().addFooterView(tv_result);
		setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>(Arrays.asList(array))));

		//��ʼ��
		if (MyApplication.getApplication().getUser() != null) {
			session_id = MyApplication.getApplication().getUser().getuSessionId();//�������Ϳ��Ի�ȡ��
			uid = MyApplication.getApplication().getUser().getuId();
			tv_result.setText("session_id=" + session_id + "\n" + "uid=" + uid);
		} else tv_result.setText("���ȵ�¼�ٲ���");
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		picUrl = UrlOfPics.urls[new Random().nextInt(UrlOfPics.urls.length)];
		switch (position) {
		case GET_ASYNC_HTTP:
			asyncGetNotifaction();
			break;
		case POST_ASYNC_HTTP:
			asyncModifySignature();
			break;
		case GET_HTTP_URL_CON:
			httpURLConGet();
			break;
		case POST_HTTP_URL_CON:
			httpURLConPost();
			break;
		case GET_HTTP_CLIENT:
			httpClientGet();
			break;
		case POST_HTTP_CLIENT:
			httpClientPost();
			break;
		case LOAD_PIC_BY_HANDLER:
			loadPicByHandler();
			break;
		case LOAD_PIC_BY_ASYNCTASK:
			new MyImageLoadTask().execute(picUrl);
			break;
		}
	}

	//**************************************************************************************************************************
	//                                                                 		ʹ���첽���AsyncHttpClient���ʷ�����
	//**************************************************************************************************************************

	/**
	 * ��ȡ�֪ͨ���ݣ�get
	 */
	private void asyncGetNotifaction() {
		RequestParams params = new RequestParams();
		params.put("session_id", session_id);
		params.put("uid", uid);

		ProgressDialogUtil.showProgressDialog(HttpComActivity.this, "", "������...");
		AsyncXiuHttpHelper.get(UrlOfServer.RQ_NOTIFICATION, params, new OnHttpListener<JSONObject>() {
			@Override
			public void onHttpListener(boolean httpSuccessed, final JSONObject obj) {//����UI�߳���
				ProgressDialogUtil.dismissProgressDialog();
				tv_result.setText(JsonFormatTool.formatJson(obj.toString()));
				new Thread() {//���߳�����������
					@Override
					public void run() {
						//final String img = obj.getJSONArray("news").getJSONObject(0).getString("img");
						InputStream stream = HttpUrlClientUtils.getInputStreamFromUrl(picUrl);//����ʹ����һ��ͼƬ��ʾ
						final Bitmap bitmap = BitmapFactory.decodeStream(stream);
						runOnUiThread(new Runnable() {//���߳��ܸ���UI��������ݱȽϸ��ӣ�Ӧ����handler����
							@Override
							public void run() {
								imageView.setImageBitmap(bitmap);
							}
						});
					}
				}.start();
			}
		});
	}

	/**
	 * �ύ�û�����ǩ����post
	 */
	private void asyncModifySignature() {
		RequestParams params = new RequestParams();
		params.put("session_id", session_id);
		params.put("uid", uid);
		params.put("sig_data", "ǩ�����ݣ�" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date()));

		ProgressDialogUtil.showProgressDialog(HttpComActivity.this, "", "������...");
		AsyncXiuHttpHelper.post(UrlOfServer.RQ_USER_SIGNATURE, params, new OnHttpListener<JSONObject>() {
			@Override
			public void onHttpListener(boolean httpSuccessed, JSONObject obj) {
				ProgressDialogUtil.dismissProgressDialog();
				tv_result.setText(JsonFormatTool.formatJson(obj.toString()));
				imageView.setImageResource(R.drawable.ic_launcher);
			}
		});
	}

	//**************************************************************************************************************************
	//                                                                         	ʹ��HttpURLConnection���ʷ�����
	//**************************************************************************************************************************
	/**
	 * ʹ��java.net.HttpURLConnection��ġ�GET���ķ�ʽ����ȡ�֪ͨ����
	 */
	private void httpURLConGet() {
		ProgressDialogUtil.showProgressDialog(HttpComActivity.this, "", "������...");
		new Thread(new Runnable() {//���߳�����������
					@Override
					public void run() {
						String parameters = "session_id=" + session_id + "&uid=" + uid;//�������
						final String strJson = HttpUrlClientUtils.httpURLConGet(URL_HEAD + UrlOfServer.RQ_NOTIFICATION, parameters);

						//final String img = new JSONObject(strJson).getJSONArray("news").getJSONObject(0).getString("img");
						InputStream stream = HttpUrlClientUtils.getInputStreamFromUrl(picUrl);//����ʹ����һ��ͼƬ��ʾ
						final Bitmap bitmap = BitmapFactory.decodeStream(stream);

						runOnUiThread(new Runnable() {//���߳��и���UI
							@Override
							public void run() {
								ProgressDialogUtil.dismissProgressDialog();
								tv_result.setText(JsonFormatTool.formatJson(strJson));
								imageView.setImageBitmap(bitmap);
							}
						});
					}
				}).start();
	}

	/**
	 * ʹ��java.net.HttpURLConnection��ġ�POST���ķ�ʽ���ύ�û�����ǩ��
	 */
	private void httpURLConPost() {
		ProgressDialogUtil.showProgressDialog(HttpComActivity.this, "", "������...");
		new Thread(new Runnable() {
			@Override
			public void run() {

				String sig_data = "ǩ�����ݣ�" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
				String requestBody = "session_id=" + session_id + "&uid=" + uid + "&sig_data=" + sig_data;//�������е����ݼ�Ϊget����URL·��?���������
				final String str = HttpUrlClientUtils.httpURLConPost(URL_HEAD + UrlOfServer.RQ_USER_SIGNATURE, requestBody);
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						ProgressDialogUtil.dismissProgressDialog();
						imageView.setImageResource(R.drawable.ic_launcher);
						try {
							tv_result.setText(JsonFormatTool.formatJson(new JSONObject(str).toString()));
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
				});
			}
		}).start();

	}

	//**************************************************************************************************************************
	//                                                                                  	ʹ��HttpClient���ʷ�����
	//**************************************************************************************************************************
	/**
	 * ʹ��org.apache.http.client.HttpClient�ġ�Get���ķ�ʽ��¼
	 */
	private void httpClientGet() {
		ProgressDialogUtil.showProgressDialog(HttpComActivity.this, "", "������...");
		new Thread(new Runnable() {

			@Override
			public void run() {
				String parameters = "session_id=" + session_id + "&uid=" + uid;//�������
				final String strJson = HttpUrlClientUtils.httpClientGet(URL_HEAD + UrlOfServer.RQ_NOTIFICATION, parameters);

				//final String img = new JSONObject(strJson).getJSONArray("news").getJSONObject(0).getString("img");
				InputStream stream = HttpUrlClientUtils.getInputStreamFromUrl(picUrl);//����ʹ����һ��ͼƬ��ʾ
				final Bitmap bitmap = BitmapFactory.decodeStream(stream);

				runOnUiThread(new Runnable() {//���߳��и���UI
					@Override
					public void run() {
						ProgressDialogUtil.dismissProgressDialog();
						tv_result.setText(JsonFormatTool.formatJson(strJson));
						imageView.setImageBitmap(bitmap);
					}
				});
			}
		}).start();
	}

	/**
	 * ʹ��org.apache.http.client.HttpClient�ġ�Post���ķ�ʽ��¼
	 */
	private void httpClientPost() {
		ProgressDialogUtil.showProgressDialog(HttpComActivity.this, "", "������...");
		new Thread(new Runnable() {
			@Override
			public void run() {

				String sig_data = "ǩ�����ݣ�" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
				// ����post����Ĳ����������͸���������ʵ���е�����
				List<NameValuePair> parameters = new ArrayList<NameValuePair>();
				parameters.add(new BasicNameValuePair("session_id", session_id));
				parameters.add(new BasicNameValuePair("uid", uid + ""));
				parameters.add(new BasicNameValuePair("sig_data", sig_data));

				//����������Ϊ��HttpEntity���ӿڣ���ʵ����UrlEncodedFormEntity�е�Ԫ��Ϊ��ֵ����ʽ�ġ�BasicNameValuePair������
				final String str = HttpUrlClientUtils.httpClientPost(URL_HEAD + UrlOfServer.RQ_USER_SIGNATURE, parameters);
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						ProgressDialogUtil.dismissProgressDialog();
						imageView.setImageResource(R.drawable.ic_launcher);
						try {
							tv_result.setText(JsonFormatTool.formatJson(new JSONObject(str).toString()));
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
				});
			}
		}).start();
	}

	public static final int MSG_WHAT_BITMAP = 1;
	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case MSG_WHAT_BITMAP:
				imageView.setImageBitmap((Bitmap) (msg.obj));
				break;
			}
		}
	};

	private void loadPicByHandler() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				InputStream inputStream = HttpUrlClientUtils.getInputStreamFromUrl(picUrl);
				Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
				mHandler.sendMessage(Message.obtain(mHandler, MSG_WHAT_BITMAP, bitmap));
			}
		}).start();
	}

	class MyImageLoadTask extends AsyncTask<String, Bitmap, Bitmap> {
		protected Bitmap doInBackground(String... params) {
			InputStream inputStream = HttpUrlClientUtils.getInputStreamFromUrl(params[0]);
			Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
			return bitmap;
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			super.onPostExecute(result);
			imageView.setImageBitmap(result);

		}
	}
}