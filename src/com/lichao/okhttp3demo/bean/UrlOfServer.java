package com.lichao.okhttp3demo.bean;

import android.os.Environment;

/**
 * URL����
 */
public class UrlOfServer {
	/**
	 * ���ֶһ�
	 */
	public static final String RQ_GUESS_EXCHARGE_TO_COIN = "/recreation/guess_exchange.php";

	/**
	 * ���ֶһ���Ϣ
	 */
	public static final String RQ_GUESS_EXCHARGE_INFOR = "/recreation/guess_exchange_list.php";

	/**
	 * ��Ƶ���
	 */
	public static final String RQ_MONITOR_VIDEO = "/log/video_log.php";

	/**
	 * ��ȡ�н���¼
	 */
	public static final String RQ_GET_LOTTERTY_RECORD = "/recreation/draw_record.php";
	/**
	 * ��ȡ.so��Ϣ
	 */
	public static final String RQ_GET_SO_VERSION = "/vedioplugins/down_vedio_plugins.php";

	/**
	 * ��ȡ������Ϣ
	 */
	public static final String RQ_GET_GIFTS_INFOR_V2 = "/gift/update_giftv2.php";

	/**
	 * ��ȡ������Ϣ
	 */
	public static final String RQ_GET_GIFTS_INFOR = "/gift/update_gift.php";

	/**
	 * ��ȡ���
	 */
	public static final String RQ_BUY_GIFT_URL = "/pay2/httpspay/gifts_payv4.php";
	/**
	 * �ٱ��û��ӿ�
	 */
	public static final String RQ_INFORM = "/report/put_report.php";
	/**
	 * ��������
	 */
	public static final String RQ_WEEKSTAR = "/hall_fame/week_gift.php";
	/**
	 * ����������������
	 */
	public static final String ANCHOR_WEEK_RANK = "/app/charts/week_star_ranking.php";

	/**
	 * ��ȡֱ����ַ
	 */
	public static final String RQ_LIVE_URL = "/api/getkey.php";

	/**
	 * ��ȡ�ǳ�״̬
	 */
	public static final String RQ_GET_NICK_NAME_STATE = "/user/check_nickname.php";

	/**
	 * ������״̬
	 */
	public static final String RQ_GIFT_BAG_STATE = "/app/giftpackge/get_giftpackge_id.php";

	/**
	 * ������Ʒ����6.1.0 �汾�ýӿڽ��������,�޸��ǳƹ��򣬹���vip, svip, ���ݣ�ʹ�������µĽӿ�
	 */
	public static final String RQ_BUY_GOODS = "/pay/pay_gold.php";

	/**
	 * 6.1.0�汾ʹ���½ӿ�, �������
	 */
	public static final String RQ_BUY_GOODS_NEW = "/app/mallbuy/gold_buy.php";

	/**
	 * ���˵��� ��ȡ������Ϣ
	 */
	public static final String RQ_DOC_ANCHOR_DOCUMENT = "/user/skip_room.php";

	/**
	 * ���˵���
	 */
	public static final String RQ_USER_DOCUMENT = "/myprofile/profileinfo.php";
	/**
	 * ��������
	 */
	public static final String SEARCH_ANCHOR = "/discovery/find_anchor.php";

	/**
	 * ��ע
	 */
	public static final String RQ_ATTENTION = "/user/edit_follow.php";

	/**
	 * ��������
	 */
	public static final String RQ_Living_Remind = "/user/playremind.php";

	/**
	 * �ϴ���Ƭ
	 */
	public static final String RQ_UPLOAD_PHOTO = "/user/photo.php";
	/**
	 * ��ע
	 */
	public static final String RQ_USER_ATTEN = "/myprofile/follow_list.php";

	/**
	 * �ҵ�
	 */
	public static final String RQ_MINE = "/myprofile/person_followdynamics.php";

	/**
	 * �������
	 */
	public static final String RQ_LATEST_VISIT = "/woxiu/visited.php";

	/**
	 * ����ǩ��
	 */
	public static final String RQ_USER_SIGNATURE = "/user/edit_signature.php";

	/**
	 * �����Ի��˺��Ƿ񵯴�
	 */
	public static final String PERSIONAL_CHEEK = "/user/verify_alter.php";

	/**
	 * �����Ի��˺��޸�
	 */
	public static final String PERSIONAL_MODIFY = "/user/alter_info.php";

	/**
	 * �ҵĶ�̬
	 */
	public static final String RQ_PERSON_DYNAMIC = "/circle/person_dynamics.php";

	/**
	 * �л�����
	 */
	public static final String RQ_SWITCH_MOUNT = "/woxiu/choose_car.php";

	/**
	 * �������ǰ��
	 */
	public static final String RQ_WEEK_STAR_CHART = "/hall_fame/week_v2.php";

	/**
	 * ���Ǹ�����
	 */
	public static final String RQ_WEEK_WEALTH_CHART = "/hall_fame/week_star_rich.php";

	/**
	 * ������
	 */
	public static final String RQ_CHARM_CHART = "/hall_fame/charm.php";

	/**
	 * ���ǰ�
	 */
	public static final String RQ_STAR_CHART = "/hall_fame/star.php";

	/**
	 * ������
	 */
	public static final String RQ_CHARM_POPULAR = "/hall_fame/popularity.php";

	/**
	 * ������
	 */
	public static final String RQ_WEALTH_CHART = "/hall_fame/rich.php";

	/**
	 * ������̬
	 */
	public static final String RQ_SEND_DYNAMIC = "/circle/add_weibo.php";

	/**
	 * ֱ������5.x
	 */
	public static final String RQ_LIVE = "/show/anchor_list_v3.php";

	/**
	 * ����ظ�
	 */
	public static final String RQ_DYNAMIC_REPLY = "/circle/add_comment.php";

	/**
	 * ɾ����̬
	 */
	public static final String RQ_DYNAMIC_DELETE = "/circle/delete_weibo.php";

	/**
	 * ��̬����
	 */
	public static final String RQ_DYNAMIC_PRAISE = "/circle/ding.php";

	/**
	 * �Ƽ������б�
	 */
	public static final String RQ_RECOMMENT_ANCHOR = "/circle/recommend_list.php";

	/**
	 * ��̬
	 */
	public static final String RQ_DYNAMIC = "/circle/dynamics.php";

	/**
	 * ��̬����
	 */
	public static final String RQ_DYNAMIC_DETAIL = "/circle/weibo_detail.php";

	/**
	 * ��������
	 */
	public static final String RQ_SERVER_CENTER = "/api/customerServerInfo.php";

	/**
	 * ���ֱ���
	 */
	public static final String RQ_NEWER_HELP = "/api/rookiehelp.php";

	/**
	 * ���°汾
	 */
	public static final String RQ_VERSION_UPDATE = "/api/update.php";

	/**
	 * 4.0.6�汾�����ýӿ�
	 */
	public static final String RQ_REVERIFY_USER = "/user/sessionv2.php";

	/** 
	 * ���ֽ���
	 */
	public static final String RQ_TASK_INTEGRAL = "/task/integral.php";

	/**
	 * ������--ÿ�շ���
	 */
	public static final String RQ_TASK_SHARE = "/task/share.php";

	/**
	 * apk����·��
	 */
	public static final String APK_PATH = Environment.getExternalStorageDirectory() + "/95xiu/game";

	/**
	 * ��ע�б�����uid Ϊ�û�ID get��ʽ 
	 */
	public static final String RQ_GIRL_ATTENTION = "/woxiu/my_keep_anchor.php";

	/**
	 * ���̨�����赥�б�4.0.6��ʼ�½ӿ�
	 */
	public static final String SONG_STATE_LIST = "/user/song_listv2.php";

	/**
	 * �ѵ�赥�Խ�GET
	 */
	public static final String SONG_READY = "/user/song_ordering.php";

	/**
	 * ��Ӻ�ȡ�� ��ע
	 */
	public static final String RQ_GIRL_KEEP_ATTENTION = "/woxiu/keep_anchor.php";

	/**
	 * ������������
	 */
	public static final String RQ_KAIBO_ATTEN = "/user/anchor_remind.php";

	/**
	 * ��½��5.x��ʼ����
	 */
	public static final String RQ_LOGIN = "/user/loginv2.php";

	/**
	 * �޸�����
	 */
	public static final String RQ_MODIFY_PASS = "/myprofile/editpassword.php";

	/**
	 * �༭����
	 */
	public static final String RQ_MSG_EDIT = "/myprofile/editinfo.php";

	/**
	 * ��ȡ��֤��
	 */
	public static final String RQ_CHECK_NUM = "/myprofile/sendsms.php";

	/**
	 * ��������
	 */
	public static final String RQ_RESET_PASS = "/user/resetpass.php";

	/**
	 * ��֤������ע��
	 */
	public static final String CHECK_NUM_FOR_REG = "1";

	/**
	 * ��֤�������һ�����
	 */
	public static final String CHECK_NUM_FOR_GETPW = "2";

	/**
	 * ���ֻ�
	 */
	public static final String CHECK_NUM_BINDING = "3";

	/**
	 * ��֤ԭ�ֻ�
	 */
	public static final String CHECK_NUM_OLD_BINDING = "4";

	/**
	 * ��֤����֤
	 */
	public static final String RQ_VERIFY = "/user/verify.php";

	/**
	 * �ֻ��û��һ�����
	 */
	public static final String FIND_PASS_WORD = "/myprofile/findpassword.php";

	/**
	 * ��ȡ������Ϊ�û�������û���
	 */
	public static final String RQ_GET_TEMP_USER_ID = "/user/getuser.php";

	public static final String RQ_REG_TEMP = "/user/reg.php";

	/**
	 * ���½����Ϣ���ؽ��
	 */
	public static final String RQ_UPDATA_MONEY = "/api/shop.php";

	/**
	 * ���ֻ�
	 */
	public static final String RQ_BINDING = "/user/binding.php";

	/**
	 * ����ֻ��󶨣��������°��ֻ�ʱ��Ҫ����֤���ԭ���ֻ��İ�
	 */
	public static final String RQ_UNBIND_PHONE = "/myprofile/unbin.php";

	/**
	 * ע��ӿ�
	 */
	public static final String RQ_REGISTER = "/user/registerv2.php";

	/**
	 * ��ʷ��¼�Ľӿ�
	 */
	public static final String RQ_RECGIRL = "/woxiu/anchor_isstart.php";

	/**
	 * ͬ������
	 */
	public static final String RQ_TCGIRL = "/woxiu/anchor_city.php";

	/**
	 * ͳ�� �������
	 */
	public static final String CLICK = "/woxiu/anchor_click.php";

	/**
	 * ��ȡ��������
	 */
	public static final String CHECK_ORDER = "/api/check_order.php";

	/**
	 * �л� ���ݣ�����uid = �û�id������car = ����id
	 */
	public static final String RQ_CHOOSE_CAR = "/woxiu/choose_car.php";

	/**
	 * �޸��ǳ�
	 */
	public static final String REPLACE_NICK = "/user/edit_nickname.php";

	/**
	 * ������ҳ
	 */
	public static final String FAMILY_MAIN = "/family/index.php";

	/**
	 * ��������/��Ա�ӿ� 5.1.0�汾�Ͽ�ʼʹ��
	 */
	public static final String FAMILY_ANCHOR_MEMBER = "/app/family/family_member.php";

	/**
	 * �������
	 */
	public static final String FAMILY_APPLY_JOIN = "/family/join_family.php";

	/**
	 *  �������
	 */
	public static final String FAMILY_SEARCH = "/app/family/familysearch.php";

	/**
	 * ��ȡ������Ϣ
	 */
	public static final String CHECK_CAR_INFOS = "/car/update_car.php";

	/**
	 * ͬ������
	 */
	public static final String SAME_CITY_ANCHORS = "/discovery/city_wide.php";

	/**
	 * ��������¼�ӿ�
	 */
	public static final String GROUP_CREATE = "/app/log/user_bang.php";

	/**
	 * ���������ӿ�
	 */
	public static final String RQ_BANGHUI_REPUTATION = "/app/charts/family_reputationv2.php";

	/**
	 * �������б�ӿ�
	 */
	public static final String RQ_FAMILY_JOIN_LIST = "/app/family/familyjoinlist.php";

	/**
	 *����ǽ�ӿ�
	 */
	public static final String RQ_FAMILY_HONOR_WALL = "/app/family/honor_wall.php";

	/**
	 * �������
	 */
	public static final String PERSIONAL_PHOTO = "/myprofile/viewphotos.php";

	/**
	 * ����������
	 */
	public static final String MAIN_TAB_FIND = "/discovery/index.php";

	/**
	 * ��ʱ�����
	 */
	public static final String RQ_DISCOVERY_SORT_GIFT_ANCHORS = "/discovery/gif_topanchor.php";

	/**
	 * ��Ϣ
	 */
	public static final String RQ_NOTIFICATION = "/app/news/index.php";

	/**
	 * ������Ϣ��ϵͳ��Ϣ  ��ͬһ���ӿڣ�
	 */
	public static final String RQ_ACTIVITY_NOTIFICATION_OR_SYSTEM_NOTIFICAITON = "/app/news/news_details.php";

	/**
	 * ɾ�����Ϣ��ϵͳ��Ϣ  ��ͬһ���ӿڣ�
	 */
	public static final String RQ_DELETE_ACTIVITY_NOTIFICATION_OR_SYSTEM_NOTIFICAITON = "/app/news/edit_news.php";

	/**
	 * ��ᴴ������[���]�ӿ�-bqt
	 */
	public static final String GROUP_CREATE_URL = "/app/family/familycreate.php";

	/**
	 * ��ᴴ����Ϣ[��д]�ӿ�-bqt
	 */
	public static final String GROUP_REGISTER_URL = "/app/family/familyregister.php";

	/**
	 * ���[�޸�]��Ϣ��д�ӿ�-bqt
	 */
	public static final String GROUP_ALTER_URL = "/app/family/familygangeditinfo.php";

	/**
	 * ���[�޸�����]��Ϣ�ӿ�-bqt
	 */
	public static final String GROUP_ALTER_NAME = "/app/family/familyname_edit.php";

	/**
	 * �������������[�б�]-bqt
	 */
	public static final String GROUP_APPLY_LIST = "/app/family/familygangjoin_list.php";

	/**
	 * ��������[�б�]-bqt
	 */
	public static final String GROUP_FAMILY_BANG_LIST = "/app/family/checkallfamilybang_detail.php";

	/**
	 * [���]������������Ϣ-bqt
	 */
	public static final String GROUP_APPLY_CLEAN = "/app/family/clearalljoinnews.php";

	/**
	 * ����������[���]�ӿ�-bqt
	 */
	public static final String GROUP_APPLY_DEAL = "/app/family/familygangjoin_deal.php";

	/**
	 * ����һ��ӿ�
	 */
	public static final String RQ_COINS_EXCHARGE = "/user2/diamond_exchange.php";

	/**
	 * �����������ӿ�
	 */
	public static final String RQ_ASSISTANT_MANAGER = "/app/family/familydeputyleader.php";

	/**
	 * ����������ӿ�
	 */
	public static final String RQ_DISMISS_MANAGER = "/app/family/deputyleaderquite.php";

	/**
	 * ����Ա�߳���Ա�ӿ�
	 */
	public static final String RQ_FIRE_MEMBER = "/app/family/familymemberkick.php";

	/**
	 * ���������Ϣ
	 */
	public static final String RQ_FAMILY_DETAIL = "/app/family/familyprofile.php";

	/**
	 * ���ǩ��
	 */
	public static final String RQ_FAMILY_SIGN = "/app/family/familysign.php";

	/**
	 * �����Ƿ�����
	 */
	public static final String RQ_SETTING_HIDE = "/app/user/divp_hidden.php";

	/**
	 * ͷ���������
	 */
	public static final String RQ_HEADLINE_GIFT = "/app/user/get_headlines_gift.php";

	/**
	 *  �Ƽ�Ӧ��
	 */
	public static final String RQ_RECOMMD_APP = "/api/game.php";

	/**
	 * ���ط���
	 */
	public static final String SEND_DOWNLOAD_STATUS = "/api/gameDown.php";

	//**************************************************************************************************************************
	//                                                                                                               ����
	//**************************************************************************************************************************

	/**
	 * һԪ����ַ
	 */
	public static final String WEB_ONE_BUY = "http://www.95xiu.com/one/phone";

	/**
	 * ˢ��
	 */
	public static final String FRESH_MM = "http://api.234wan.com/api/newactive.php";

	public static final String FRUIT_GAME = "http://fruit.92xiu.com?key=";

	/**
	 * ���������
	 */
	public static final String GROUP_INTRODUCTION_URL = "http://api2.95xiu.com/web/active.php?id=107";

	//**************************************************************************************************************************
	//                                                                                                               ����
	//**************************************************************************************************************************

	/**
	 * ����ɹ�
	 */
	public static final int REQUEST_SUCCESS = 1;
	/**
	 * ����ʧ��
	 */
	public static final int REQUEST_FAIL = 0;

	/**
	 * ���ض���
	 */
	public static final String ACTION = "android.intent.action.95xiu.download";

	/**
	 * ���صȴ���
	 */
	public static final int DOWNLOAD_WAIT = 0;

	/**
	 * ���ؿ�ʼ
	 */
	public static final int DOWNLOAD_START = 1;

	/**
	 * ������������
	 */
	public static final int DOWNLOAD_DOWNLOAD = 2;

	/**
	 * ������
	 */
	public static final int DOWNLOAD_DOWNLOADING = 3;

	/**
	 * ������ͣ
	 */
	public static final int DOWNLOAD_PAUSE = 4;

	/**
	 * ����ȡ��
	 */
	public static final int DOWNLOAD_CANCEL = 6;

	/**
	 * ����ʧ��
	 */
	public static final int DOWNLOAD_FAIL = 7;

	/**
	 * ���سɹ�
	 */
	public static final int DOWNLOAD_SUCCESS = 8;

	/**
	 * ������װ
	 */
	public static final int DOWNLOAD_INSTALL = 9;

	/**
	 * ��װ�ɹ�
	 */
	public static final int INSTALL_SUCCESS = 10;

	/**
	 * ��װʧ��
	 */
	public static final int INSTALL_FAIL = 11;

	/**
	 * �����Ѵ���
	 */
	public static final int TASK_EXIST = 12;

	/**
	 * �������
	 */
	public static final int TASK_ADD = 13;

	/**
	 * �����ȡʧ��
	 */
	public static final int TASK_FAIL = 14;

	/**
	 * ��������ʼ
	 */
	public static final int TASK_START = 15;

	/**
	 * �������Ƿ�ɱ��ʱ����
	 */
	public static final long START_SERVICE_TIME = 20000L;

	/**
	 * ��֤����Ч��2����
	 */
	public static final int TIME_OUT = 120;

	/**
	 *  �й��ƶ�
	 */
	public static final int CMCC = 1;

	/**
	 * �й�����
	 */
	public static final int Telecom = 2;

	/**
	 * �й���ͨ
	 */
	public static final int Unicom = 3;

	/**
	 * δ֪��Ӫ��
	 */
	public static final int Un_known = 0;

	public static String FAMILY_INFO = "family_info";
	public static final int TOP_RANK = 0;
	public static final int UNTOP_RANK = 1;
}