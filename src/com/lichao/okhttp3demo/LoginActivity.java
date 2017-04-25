package com.lichao.okhttp3demo;

import com.lichao.okhttp3demo.fragment.LoginFragment;
import com.lichao.okhttp3demo.fragment.MyHeadFragment;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ScrollView;

/**��¼���漰��½���û���ҳ���棬ʹ������Fragmentʵ��*/
public class LoginActivity extends Activity implements LoginInterface {
	/** ���Fragment������ */
	private View fragmentContainer;
	/** ��ʹ��replace�滻Fragment����Ҫһ��id */
	public static final int ROOT_ID = 0x0011223344;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		fragmentContainer = new ScrollView(this);//���Թ���
		fragmentContainer.setBackgroundColor(0xfffbf7ed);
		fragmentContainer.setId(ROOT_ID);
		setContentView(fragmentContainer);

		LoginFragment loginFragment = new LoginFragment();
		getFragmentManager().beginTransaction().replace(ROOT_ID, loginFragment).commit();
		loginFragment.setLoginInterface(this);//����¼����ûص���ʽ���¼����û���Ϣ

		//��������Activityʱ�г����˻����룬��ֱ�ӵ�¼
		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			String account = bundle.getString("account");
			String password = bundle.getString("password");
			loginFragment.login(this, account, password);
		}
	}

	@Override
	/**���������á��������ߡ�ʵ�ִ˽ӿڣ�����ʵ�ֵķ������Զ����Լ��Ļص��߼�*/
	public void loginComplete(boolean isSuccess, String result) {
		MyHeadFragment headFragment = new MyHeadFragment();
		Bundle bundle = new Bundle();
		bundle.putString("result", result);
		bundle.putBoolean("isSuccess", isSuccess);
		headFragment.setArguments(bundle);
		getFragmentManager().beginTransaction().replace(ROOT_ID, headFragment).commitAllowingStateLoss();
		//��ʹ��commit����ʱ��ϵͳ������״̬�жϣ����״̬��mStateSaved���Ѿ����棬������"Can not perform this action after onSaveInstanceState"����
	}
}