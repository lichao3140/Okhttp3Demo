package com.lichao.okhttp3demo.view;

import com.lichao.okhttp3demo.R;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

public class ProgressDialogView extends Dialog {
	private TextView tv_messag;

	public ProgressDialogView(Context context) {
		super(context, R.style.DialogTheme);
		setContentView(R.layout.progress_dialog);
		tv_messag = (TextView) findViewById(R.id.tv_messag);
	}

	@Override
	public void setTitle(CharSequence title) {
		super.setTitle(title);
	}

	@Override
	public void setCancelable(boolean flag) {
		super.setCancelable(flag);
	}

	@Override
	public void setOnCancelListener(OnCancelListener listener) {
		super.setOnCancelListener(listener);
	}

	public void setMessage(String string) {
		tv_messag.setVisibility(View.VISIBLE);
		tv_messag.setText(string);
	}
}