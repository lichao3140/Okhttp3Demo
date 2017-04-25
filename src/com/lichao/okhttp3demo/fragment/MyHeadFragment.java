package com.lichao.okhttp3demo.fragment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.lichao.okhttp3demo.utils.JsonFormatTool;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class MyHeadFragment extends Fragment {
	private Activity mContext;
	private TextView textView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mContext = getActivity();
		textView = new TextView(mContext);
		textView.setText("�û���ҳ��Fragment");
		textView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				textView.append("\n" + new SimpleDateFormat("yyyy-MM-dd E aHH:mm:ss", Locale.getDefault()).format(new Date()));
			}
		});

		Bundle bundle = getArguments();//��activity��������Bundle
		if (bundle != null) {
			boolean isSuccess = bundle.getBoolean("isSuccess");
			String result = bundle.getString("result");
			textView.append("\n�Ƿ��¼�ɹ�" + isSuccess + "��\n" + JsonFormatTool.formatJson(result));
		}
		return textView;
	}
}