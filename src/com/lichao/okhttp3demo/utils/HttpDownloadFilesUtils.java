package com.lichao.okhttp3demo.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import com.lichao.okhttp3demo.HttpDownloadActivity;

import android.os.Handler;
import android.os.Message;
import android.widget.ProgressBar;


/** �´��ļ�������*/
public class HttpDownloadFilesUtils {

	/**ֱ��ʹ��URLConnection.openStream()������������,Ȼ����д�뵽�ļ���*/
	public static void simpleDownLoad(String fileUrl, String filePath, boolean isUseUrlName, Handler mHandler) {
		String fileName;
		if (isUseUrlName) fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);//��ȡ�ļ�������׺��
		else fileName = new SimpleDateFormat("yyyy.MM.dd HH-mm-ss", Locale.CHINA).format(new Date()) + fileUrl.substring(fileUrl.lastIndexOf("."));
		fileName = fileName == null ? "bqt" : fileName;
		try {
			InputStream inputStream = new URL(fileUrl).openStream();
			OutputStream outputStream = new FileOutputStream(new File(filePath + fileName));
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = inputStream.read(buffer)) > 0) {
				outputStream.write(buffer, 0, len);
			}
			inputStream.close();
			outputStream.close();
			mHandler.sendMessage(Message.obtain(mHandler, HttpDownloadActivity.MSG_WHAT_DOWNLOAD_OK, filePath + fileName));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//*****************************************************************************************************************
	//																							���߳�����
	//*****************************************************************************************************************
	public static final int THREAD_COUNT = 3;// �̵߳�����
	public static long blocksize;// ÿ����������Ĵ�С
	public static int runningTHREAD_COUNT;// �������е��̵߳�����

	/**
	 * ���߳�����
	 *  ��ʽ1:ʹ�ö��̷ֱ߳������ļ��Ĳ�ͬ���֣����ѡ��ϲ�����һ���ļ�(Ч�ʸ�)����ʽ2:ʹ��java�ṩ�ġ�RandomAccessFile����ʵ�ֶ��̵߳�����(��)��
	 * @param fileUrl		������·��
	 * @param filePath	���汾��·��
	 * @param isUseUrlName	�Ƿ�ʹ�÷�����·���е��ļ�������Ϊfalse��ʹ�õ�ǰʱ����Ϊ�ļ���
	 * @param mHandler	ͨ��mHandler��UI�߳�ͨѶ
	 * @param pbs		�����߳�ֱ�Ӹ��¸��߳����ؽ��ȣ���Ϊnull�򲻿���
	 */
	public static void mutileThreadDownload(String fileUrl, String filePath, boolean isUseUrlName, Handler mHandler, ArrayList<ProgressBar> pbs) {
		try {
			HttpURLConnection conn = (HttpURLConnection) new URL(fileUrl).openConnection();//��ȡ����
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5000);
			if (conn.getResponseCode() == 200) {
				// 1���ڱ��ش���һ����С��������һģһ���Ŀհ��ļ�
				long fileSize = conn.getContentLength();// �õ�������ļ��Ĵ�С
				String info = "\n������ļ��Ĵ�С:" + fileSize + " ( " + fileSize / 1024 / 1024 + "M )";
				mHandler.sendMessage(Message.obtain(mHandler, HttpDownloadActivity.MSG_WHAT_DOWNLOAD_INFO, info));
				String fileName;
				if (isUseUrlName) fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);//��ȡ�ļ�������׺��
				else fileName = new SimpleDateFormat("yyyy.MM.dd HH-mm-ss", Locale.CHINA).format(new Date()) + fileUrl.substring(fileUrl.lastIndexOf("."));
				fileName = fileName == null ? "bqt" : fileName;
				RandomAccessFile raf = new RandomAccessFile(filePath + fileName, "rw");//���Դ�ָ��λ�ÿ�ʼ����д�ļ���ģʽ��r��rw��rws��rwd
				raf.setLength(fileSize);//�趨��С
				raf.close();

				// 2���������ɸ����̷ֱ߳�ȥ���ض�Ӧ����Դ
				blocksize = fileSize / THREAD_COUNT; // ÿ����������Ĵ�С
				runningTHREAD_COUNT = THREAD_COUNT; //���е��߳�����

				for (int i = 1; i <= THREAD_COUNT; i++) {
					long startIndex = (i - 1) * blocksize;//��ʼλ�ã���0��ʼ
					long endIndex = i * blocksize - 1;//����λ��
					if (i == THREAD_COUNT) endIndex = fileSize - 1;// ���һ���̵߳Ľ���λ��Ϊ size - 1����ֵ������Ҳ�������쳣��ʵ�����ش�СҲ��  size - 1
					info = "\n�����߳� " + i + " ,���ط�Χ:" + startIndex + "~" + endIndex;
					mHandler.sendMessage(Message.obtain(mHandler, HttpDownloadActivity.MSG_WHAT_DOWNLOAD_INFO, info));
					//ProgressBar��SeekBar��ProgressDialog ��Щ���ǿ��������߳�ֱ�Ӹ��½��ȵ�
					if (pbs != null && pbs.get(i - 1) != null) pbs.get(i - 1).setMax((int) (endIndex - startIndex));//���ø����̵߳Ľ����������ֵ
					//3������������߼���ɶ��߳�����
					new DownloadThread(fileUrl, filePath + fileName, i, startIndex, endIndex, mHandler, pbs).start();
				}
			}
			conn.disconnect();//ȡ������
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static class DownloadThread extends Thread {//�߳�Thread������
		private String fileUrl;
		private String filePath;
		private int threadId;
		private long startIndex;
		private long endIndex;
		private Handler mHandler;
		private ArrayList<ProgressBar> pbs;
		/**����һ����¼��ǰ�߳��������ļ��Ĵ�С����ʱ�ļ������ļ����������ͷ���أ�����Ӽ�¼��λ�ü������أ�д��ʱ�����װΪRandomAccessFile*/
		private File positionFile;

		/***
		* @param fileUrl		������·��
		* @param filePath	�����ļ�����·��
		* @param threadId		�߳�id����ʹ��0��1��2��3������ʽ�����밴˳������
		* @param startIndex	��ǰ�߳̿�ʼ���ص�λ��
		* @param endIndex	��ǰ�߳̽������ص�λ��
		* @param mHandler	ͨ��mHandler��UI�߳�ͨѶ
		* @param pbs		�����߳�ֱ�Ӹ��¸��߳����ؽ��ȣ���Ϊnull�򲻿���
		*/
		public DownloadThread(String fileUrl, String filePath, int threadId, long startIndex, long endIndex, Handler mHandler, ArrayList<ProgressBar> pbs) {
			this.fileUrl = fileUrl;
			this.filePath = filePath;
			this.threadId = threadId;
			this.startIndex = startIndex;
			this.endIndex = endIndex;
			this.mHandler = mHandler;
			this.pbs = pbs;
		}

		public void run() {
			String info;
			try {
				// 1����¼��ǰ�߳������ص��ܴ�С
				int total = 0;// ��ʼֵ��Ϊ0���������ز����ļ������ȡ�����ز����ļ��Ĵ�С�����¸�ֵ
				positionFile = new File(filePath + "-" + threadId);
				if (positionFile.exists() && positionFile.length() > 0) {
					BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(positionFile)));
					String totalstr = bufferedReader.readLine();// ��ȡ��ǰ�߳��ϴ����ص��ܡ���С���Ƕ���
					total = Integer.valueOf(totalstr);
					info = "\n�ϴ��߳�" + threadId + "���ص��ܴ�С��" + total;
					mHandler.sendMessage(Message.obtain(mHandler, HttpDownloadActivity.MSG_WHAT_DOWNLOAD_INFO, info));
					startIndex += total;//ÿ���̼߳�������ʱ�Ŀ�ʼ��λ�á�ע��startIndex��ֵΪ��ʼ���صġ�λ�á�����һ��������total��ֵΪʵ�����ص��ļ��ġ���С��
					bufferedReader.close();
				}
				//2����ȡ���ӣ��������ӵĲ�����Ϣ
				HttpURLConnection conn = (HttpURLConnection) new URL(fileUrl).openConnection();
				conn.setRequestMethod("GET");
				conn.setConnectTimeout(5000);
				conn.setRequestProperty("RANGE", "bytes=" + startIndex + "-" + endIndex);//ָ��ÿ���̴߳��ļ���ʲôλ�ÿ�ʼ���أ����ص�ʲôλ��Ϊֹ
				// ע�⣬���ط�������ĳһ�������ݷ�������206�������á�code/100==2�����ж�
				InputStream inputStream = conn.getInputStream();//���ط���˷��ص���

				//3�������е�����д���ļ���
				RandomAccessFile randomAccessFile = new RandomAccessFile(filePath, "rw");
				randomAccessFile.seek(startIndex);// ָ���ļ���ʼд��λ�ã�ָ��ƫ��������
				info = "\n�� " + threadId + " ���̣߳�д�ļ��Ŀ�ʼλ�ã�" + String.valueOf(startIndex);
				mHandler.sendMessage(Message.obtain(mHandler, HttpDownloadActivity.MSG_WHAT_DOWNLOAD_INFO, info));

				int len = 0;
				byte[] buffer = new byte[1024];
				while ((len = inputStream.read(buffer)) != -1) {
					randomAccessFile.write(buffer, 0, len);//��randomAccessFile��д���ȡ�������е�����
					//�����ļ���װ��Ϊһ��RandomAccessFile�������ò���rwdģʽ����ʹ�ϵ�Ҳ���ᶪʧ��Ϣ��
					RandomAccessFile rf = new RandomAccessFile(positionFile, "rwd");
					total += len;//��¼���ص��ܴ�С
					rf.write(String.valueOf(total).getBytes());
					rf.close();
					if (pbs != null && pbs.get(threadId - 1) != null) pbs.get(threadId - 1).setProgress(total);//���õ�ǰ�������Ľ���ֵ
				}
				inputStream.close();
				randomAccessFile.close();
			} catch (Exception e) {
			} finally {
				//4�� ���е��̶߳�������Ϻ�ɾ����¼�ļ�
				synchronized (HttpDownloadFilesUtils.class) {//�̰߳�ȫ����
					info = "\n�߳� " + threadId + " ���������";
					mHandler.sendMessage(Message.obtain(mHandler, HttpDownloadActivity.MSG_WHAT_DOWNLOAD_INFO, info));
					runningTHREAD_COUNT--;
					if (runningTHREAD_COUNT < 1) {
						info = "\n�����߳���������ϣ�ɾ����ʱ�ļ�";
						mHandler.sendMessage(Message.obtain(mHandler, HttpDownloadActivity.MSG_WHAT_DOWNLOAD_INFO, info));
						for (int i = 1; i <= THREAD_COUNT; i++) {
							File temFile = new File(filePath + "-" + i);
							info = "\nɾ����ʱ�ļ� " + i + " ,״̬: " + temFile.delete();//ɾ����¼�ļ�
							mHandler.sendMessage(Message.obtain(mHandler, HttpDownloadActivity.MSG_WHAT_DOWNLOAD_INFO, info));
						}
						mHandler.sendMessage(Message.obtain(mHandler, HttpDownloadActivity.MSG_WHAT_DOWNLOAD_OK, filePath));
					}
				}
			}
		}
	}
}