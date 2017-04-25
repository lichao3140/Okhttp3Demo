package com.lichao.okhttp3demo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lichao.okhttp3demo.bean.UrlOfServer;
import com.lichao.okhttp3demo.utils.AsyncHttpHelper.OnHttpListener;
import com.lichao.okhttp3demo.utils.AsyncXiuHttpHelper;
import com.lichao.okhttp3demo.utils.HttpUploadFilesUtils;
import com.lichao.okhttp3demo.utils.HttpUrlClientUtils;
import com.lichao.okhttp3demo.utils.JsonFormatTool;
import com.lichao.okhttp3demo.utils.ProgressDialogUtil;
import com.loopj.android.http.RequestParams;

/**ʵ�ʿ����漰�ļ��ϴ������ض������Լ�д��Щ���룬һ��Ἧ�ɵ�����������ɣ�����android-async-http��okhttp��xUtils��*/
public class UploadFileActivity extends ListActivity {
	private ImageView imageView;
	private TextView tv_info;

	public static final int uid = 10415362;
	private String session_id = "�����������ݣ�˵���㻹û��¼";//ÿ�ε�¼����һ��
	public static final String path = Environment.getExternalStorageDirectory().getPath() + File.separator;
	private Bitmap bitmap;
	/**�ü�ͼƬ��ķ�����*/
	public static final int REQUEST_CROP_IMG_CODE_TRUE = 100;
	public static final int REQUEST_CROP_IMG_CODE_FALSE = 101;

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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		String[] array = { "�����ҵ�ͷ��", "ʹ��������ղ�����ȡ����Ƭ", "ʹ��������ղ����ü�����Ƭ", "��ͼ���ȡ���ü���Ƭ", "��ͼ���ȡ���ü���Ƭ2", //
				"ʹ��AsyncHttpClient�ϴ�ͼƬ", "ͨ��HttpUrlConƴ�ӻ�HTTPЭ��(ʧ��)" };
		for (int i = 0; i < array.length; i++) {
			array[i] = i + "��" + array[i];
		}
		imageView = new ImageView(this);
		getListView().addFooterView(imageView);
		tv_info = new TextView(this);
		tv_info.setTextColor(Color.BLUE);
		tv_info.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
		tv_info.setPadding(20, 10, 20, 10);
		getListView().addFooterView(tv_info);
		setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>(Arrays.asList(array))));

		//��ʼ��
		if (MyApplication.getApplication().getUser() != null) session_id = MyApplication.getApplication().getUser().getuSessionId();
		tv_info.setText("session_id=" + session_id);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		switch (position) {
		case 0:
			loadMyImg();
			break;
		case 1:
			startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE), 11);//����ϵͳ�������
			break;
		case 2:
			Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(path + "temp.png")));//�����ص����ݱ��浽ָ��λ��
			startActivityForResult(intent, 12);
			break;
		case 3:
			Intent intent3 = new Intent(Intent.ACTION_PICK);
			intent3.setType("image/*");
			startActivityForResult(intent3, 13);
			break;
		case 4:
			Intent intent4 = new Intent(Intent.ACTION_PICK);
			intent4.setType("image/*");
			startActivityForResult(intent4, 14);
			break;
		case 5:
			asyncUploadFile();
			break;
		case 6:
			httpURLConUploadFile();
			break;
		}
	}

	/**�����ҵ�ͷ��Ҳ�����ϴ���ͼƬ*/
	private void loadMyImg() {
		if (MyApplication.getApplication().getUser() != null) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					String imgUrl = MyApplication.getApplication().getUser().getuAvatarUrl();//ͷ����λ��
					InputStream inputStream = HttpUrlClientUtils.getInputStreamFromUrl(imgUrl);
					Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
					mHandler.sendMessage(Message.obtain(mHandler, MSG_WHAT_BITMAP, bitmap));
				}
			}).start();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case 11:// ����ͨ��onActivityResult�е�Intent���ء����صĲ���ԭͼ����Ƭ���Զ�����ѹ����
			if (data == null) return;
			bitmap = (Bitmap) data.getExtras().get("data");
			imageView.setImageBitmap(bitmap);
			tv_info.setText("���ص�Bitmap�ߴ�Ϊ" + bitmap.getWidth() + " * " + bitmap.getHeight());
			break;
		case 12://����ͨ��ָ����Uri���ء�ע�⣬���������ѷ��ص������ط��������Ǳ��浽��SD���У����������ﷵ�ص�data == null
			tv_info.setText("��Ƭ�ѱ��浽ָ����Uri�У�" + (data == null));
			cropImageReturnTrue(this, Uri.fromFile(new File(path + "temp.png")), 72 * 4, 72 * 4);
			break;
		case 13://����ͨ��ϵͳĬ�ϵ�Uri���ء�data.getData()�Ľ��Ϊ��content://media/external/images/media/15767��
			if (data == null) return;
			tv_info.setText("18-�ѻ�ȡ������Uri�е���Ƭ��Uri=��" + data.getData());
			cropImageReturnTrue(this, data.getData(), 72 * 4, 72 * 4);
			break;
		case 14://����ͨ��ϵͳĬ�ϵ�Uri����
			if (data == null) return;
			tv_info.setText("19-�ѻ�ȡ������Uri�е���Ƭ��Uri=��" + data.getData());
			cropImageReturnFalse(this, data.getData(), 72 * 4, 72 * 4);
			break;
		case REQUEST_CROP_IMG_CODE_TRUE: // �ü����ͼƬͨ��onActivityResult�е�Intent����
			bitmap = (Bitmap) data.getParcelableExtra("data");
			imageView.setImageBitmap(bitmap);
			tv_info.setText("1���ü���ĳߴ�Ϊ��" + bitmap.getWidth() + " * " + bitmap.getHeight());
			break;
		case REQUEST_CROP_IMG_CODE_FALSE: //�ü����ͼƬͨ��ָ����Uri����(�����Ǳ��浽SD��)
			try {
				bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.fromFile(new File(path + "temp.png")));
				imageView.setImageBitmap(bitmap);
				tv_info.setText("2���ü���ĳߴ�Ϊ��" + bitmap.getWidth() + " * " + bitmap.getHeight());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		}
	}

	//******************************************************************************************************************************************
	//																												��ȡͼƬ
	//******************************************************************************************************************************************

	/**
	   * ����ͼ��APP�Ĳü�ͼƬ���ܣ���ָ��Uri�е�ͼƬ�ü�Ϊָ����С
	   */
	public static void cropImageReturnTrue(Activity context, Uri uri, int desWidth, int desHeight) {
		if (uri == null) return;
		//ָ��action��ʹ��ϵͳͼ��ü�ͼƬ
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		//�����ڿ�����Intent�У���ʾ��VIEW�ɲü�
		intent.putExtra("crop", "true");
		//���òü��������ü�ͼƬ�ľ�����
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		intent.putExtra("outputX", desWidth);
		intent.putExtra("outputY", desHeight);
		//�����Ƿ���������
		intent.putExtra("scale", true);
		intent.putExtra("scaleUpIfNeeded", true);
		//���������ʽ
		intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
		//�����Ƿ���Ҫ����ʶ��Ĭ�ϲ���Ҫ����
		intent.putExtra("noFaceDetection", true);
		//�����Ƿ񷵻����ݡ����Ҫ�ڸ�����uri�з���ͼƬ�����������Ϊfalse���������Ϊtrue����ô�㲻���ڸ�����uri�л�ȡ���ü���ͼƬ
		//��������ֱ�ӽ����ݷ��ص���onActivityResult�е�intent���ˣ�����Ҫ���ص�������uri�У�������Ϊtrue
		intent.putExtra("return-data", true);
		context.startActivityForResult(intent, REQUEST_CROP_IMG_CODE_TRUE);
	}

	/**
	 * ����ͼ��APP�Ĳü�ͼƬ���ܣ���ָ��Uri�е�ͼƬ�ü�Ϊָ����С.��ʽ��
	 */
	public static void cropImageReturnFalse(Activity context, Uri uri, int desWidth, int desHeight) {
		if (uri == null) return;
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		intent.putExtra("crop", "true");
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		intent.putExtra("outputX", desWidth);
		intent.putExtra("outputY", desHeight);
		intent.putExtra("scale", true);
		//        intent.putExtra("scaleUpIfNeeded", true);
		intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
		intent.putExtra("noFaceDetection", true);
		intent.putExtra("return-data", false);//������Ψһ��ͬ�ĵط�
		//�����ݷ��ص�ָ����uri�С�ע�⣺���ü�ʱ��ͼƬ��uri�뱣��ͼƬ��uri��ͬ���������ͻ�����²ü���ɺ�ͼƬ�Ĵ�С���0Byte��
		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(path + "temp.png")));
		context.startActivityForResult(intent, REQUEST_CROP_IMG_CODE_FALSE);
	}

	//******************************************************************************************************************************************
	//																												AsyncHttpClient
	//******************************************************************************************************************************************
	/**
	 * ����AsyncHttpClient�ϴ�ͼƬ��ֻ����post��ʽ
	 */
	private void asyncUploadFile() {
		if (bitmap == null) {
			Toast.makeText(this, "��û����ͼƬ��", Toast.LENGTH_SHORT).show();
			return;
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);//����ѹ��
		RequestParams params = new RequestParams();
		//add�������Ը�ͬһ��keyֵ�����value���������һ��list���棻��put����ֻ�ܸ�ͬһ��key��Ψһ��һ��value��������������һ�����滻��ǰһ��
		params.put("uid", uid);
		params.put("session_id", session_id);
		params.put("image", new ByteArrayInputStream(baos.toByteArray()), "headImg", "image/*");//���Ĵ���
		//params.put("image", new String(Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT)));//�����������ַ�ʽ��������������Ҫ�ͷ���������ȷ��

		ProgressDialogUtil.showProgressDialog(this, "", "�ύ��...");
		AsyncXiuHttpHelper.post(UrlOfServer.RQ_MSG_EDIT, params, new OnHttpListener<JSONObject>() {
			@Override
			public void onHttpListener(boolean httpSuccessed, JSONObject obj) {
				ProgressDialogUtil.dismissProgressDialog();
				tv_info.setText(JsonFormatTool.formatJson(obj.toString()));
				if (httpSuccessed) {
					Toast.makeText(UploadFileActivity.this, "�ɹ�", Toast.LENGTH_SHORT).show();
					reLogin();
				} else Toast.makeText(UploadFileActivity.this, "ʧ��", Toast.LENGTH_SHORT).show();
			}
		});
	}

	/**���µ�¼*/
	protected void reLogin() {
		Bundle loginBundle = new Bundle();
		loginBundle.putString("account", "103468");
		loginBundle.putString("password", "103468");
		Intent intent = new Intent(this, LoginActivity.class);
		intent.putExtras(loginBundle);
		startActivity(intent);
		imageView.setImageResource(R.drawable.ic_launcher);
	}

	//******************************************************************************************************************************************
	//																												HttpURLConn
	//******************************************************************************************************************************************
	/**
	 * ����HttpURLConn�ϴ�ͼƬ��δ�ɹ�
	 */
	private void httpURLConUploadFile() {
		final File file = new File(Environment.getExternalStorageDirectory(), "a.png");
		if (!file.exists() || file.length() == 0) {
			Toast.makeText(this, "�ļ�������", Toast.LENGTH_SHORT).show();
			return;
		}
		//��ͨ����
		final Map<String, String> strMap = new HashMap<String, String>();
		strMap.put("session_id", session_id);
		strMap.put("uid", uid + "");
		//�ļ�����
		final Map<String, File> fileMap = new HashMap<String, File>();
		fileMap.put("image", file);
		final String url = "http://" + AsyncXiuHttpHelper.SERVER_URL + UrlOfServer.RQ_MSG_EDIT;

		ProgressDialogUtil.showProgressDialog(this, "", "������...", false, null);
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					HttpUploadFilesUtils.post(url, strMap, fileMap);
				} catch (Exception e) {
					e.printStackTrace();
				}

				final String str = HttpUploadFilesUtils.post(url, strMap, fileMap);
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						ProgressDialogUtil.dismissProgressDialog();
						try {
							tv_info.setText(JsonFormatTool.formatJson(new JSONObject(str).toString()));
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
				});
			}
		}).start();
	}
}