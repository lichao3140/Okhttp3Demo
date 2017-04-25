package com.lichao.okhttp3demo.bean;

import java.io.Serializable;
import org.json.JSONObject;

/**
 * �û�����
 */
public class UserBean implements Serializable {
	private static final long serialVersionUID = 7667191114299135243L;

	/**
	 * �û�id
	 */
	private int uId;

	/**
	 * �û���
	 */
	private String uName;

	/**
	 * �û��ǳ�
	 */
	private String uNickName;

	/**
	 * �û�ͷ���ַ
	 */
	private String uAvatarUrl;

	/**
	 * ��ʱ�û�
	 */
	private String uTempName;

	/**
	 * �û����ͣ�0 ��ͨ�û���1 ������2 Ѳ�ܣ�3 ����
	 */
	private int uType;

	/**
	 * �û��ֻ�
	 */
	private String uPhone;

	/**
	 * �û�QQ
	 */
	private String uQQ;

	/**
	 * �û��Ա�1�У�2Ů
	 */
	private int uSex;

	/**
	 * �û�����
	 */
	private String uPassword;

	/**
	 * ���ǵȼ�
	 */
	private int uStarLev;

	/**
	 * �Ƹ��ȼ�
	 */
	private int uWealthLev;

	/**
	 * vip ���ͣ�0  �ޣ�1 vip��2 svip��3 dvip
	 */
	private int vipType;

	/**
	 * vipʣ������
	 */
	private int vipExpires;

	/**
	 * �û�ע������
	 * 1 �ֻ��û� 
	 * 2 ��Ѷ΢�� 
	 * 3 ����΢��
	 * 4 һ��ע��
	 * 5 QQ 
	 * 6 �Զ�ע��
	 * 7 ΢�ŵ�¼
	 */
	private int uRegisterType;

	/**
	 * ����ǩ��
	 */
	private String uSignature;

	/**
	 * �û����
	 */
	private int uCoin;

	/**
	 * �������
	 */
	private int uFreeGift;

	/**
	 * �û���ע���ͱ�ǩ
	 */
	private String uAttnTags;

	/**
	 * �û�����ע����
	 */
	private int uAttnedCount;

	/**
	 * �û���ע����������
	 */
	private int uAttnOthersCount;

	/**
	 * ����ʱ��
	 */
	private String uCarTime;

	/**
	 * ����
	 */
	private int uCarId;

	/**
	 * �û���������
	 */
	private String uAllCars;

	/**
	 * �û���ǰ������������ֵ
	 */
	private long uCurStarExps;

	/**
	 * �û��´����������ľ���ֵ
	 */
	private long uStarLevNextExps;

	/**
	 * �û���ǰ�Ƹ���������ֵ
	 */
	private long uCurWealthExps;

	/**
	 * �û��´βƸ������ľ���ֵ
	 */
	private long uWealthLevNextExps;

	/**
	 * �û�Session
	 */
	private String uSessionId;

	/**
	 * �û����й�ע�˵�ids
	 */

	private String uAttnOthersIds;

	/**
	 * �û����������˵�ids
	 */
	private String uLivingIds;

	/**
	 * �Ƿ���ֻ�
	 */
	private boolean uIsBindPhone;

	/**
	 * �Ƿ���ͬ��
	 */
	private boolean uIsBuyTC;

	/**
	 * �������û�id
	 */
	private String third_uid;

	/**
	 * ������token
	 */
	private String third_token;

	/**
	 * ���˶�̬
	 */
	private String uDynamicJSON;

	/**
	 * �������
	 */
	private String uAlbumJSON;

	/**
	 * ��������Чʱ��
	 */
	private long third_expires;

	/**
	 * ��ʱ�������1
	 */
	private String extra_identity;

	/**
	 * ��ʱ�������2
	 */
	private String extra_identity2;

	/**
	 * ��������
	 */
	private String extraOther;

	/**
	 * ��ֵ״̬�� 0 δ��ֵ��1�Ѿ���ֵ 
	 */
	private int chargeStatus;

	/**
	 * �Ƿ�����ֱ��
	 */
	private int uIsLiving;

	/**
	 * �Ƿ����ػ�
	 */
	private boolean isGuider;

	/**
	 * �������
	 */
	private String giftPack;

	/**
	 * δ����Ϣ
	 */
	private int uUnreadNewsCount;

	/**
	 * ��������
	 */
	private int uDiamond;

	/**
	 * �û����£�","������
	 */
	private String badgeIds;

	/**
	 * �û�����ʣ��ʱ�䣨","������
	 */
	private String badgeTimes;

	/**
	 * ����״̬ ��0 ����״̬�� 1 ����
	 */
	private int hideStatus;

	/**
	 * ��ʼ���û���Ϣ
	 */
	public UserBean(JSONObject json) {
		if (json == null || json.toString().equals("{}")) return;

		setVipExpires(json.optInt("viptime", 0));
		setuAllCars(json.optString("allcar", ""));
		setuCarTime(json.optString("allcar_time", ""));
		//updateBackPkgGiftsHashMap(json.optJSONArray("backpack_gifts"));// ����

		JSONObject msg = json.optJSONObject("msg");
		if (msg != null) {
			setuAttnOthersIds(msg.optString("follow_list", ""));
			setuLivingIds(msg.optString("playremind_list", ""));
			setuId(msg.optInt("id", 0));
			setuIsLiving(msg.optInt("is_play", 0));
			setuType(msg.optInt("user_type", 0));
			setuName(msg.optString("username", ""));
			setuNickName(msg.optString("nickname", ""));
			setuPhone(msg.optString("phone", ""));
			setuQQ(msg.optString("qq", ""));
			setuSex(msg.optInt("sex", 1));
			setuAvatarUrl(msg.optString("head_image", ""));
			setuStarLev(msg.optInt("star_level", 0));
			setuWealthLev(msg.optInt("wealth_level", 0));
			setuRegisterType(msg.optInt("register_type", 4));
			setuSignature(msg.optString("signature", ""));
			setuCoin(msg.optInt("gold", 0));
			setuFreeGift(msg.optInt("free_gift", 0));
			setuIsBindPhone(msg.optInt("is_binding", 0) == 0 ? false : true);
			setuIsBuyTC(msg.optInt("tc_buy", 0) == 0 ? false : true);
			setuSessionId(msg.optString("session_id", ""));
			setuAttnTags(msg.optString("tags", ""));
			setuAttnedCount(msg.optInt("at_follow_num", 0));
			setuAttnOthersCount(msg.optInt("follow_num", 0));
			setuCurStarExps(msg.optLong("star_integral", 0L));
			setuStarLevNextExps(msg.optLong("star_next_integral", 0L));
			setuCurWealthExps(msg.optLong("wealth_integral", 0L));
			setuWealthLevNextExps(msg.optLong("wealth_next_integral", 0L));
			setChargeStatus(msg.optInt("firstPay", 0));
			setuUnreadNewsCount(msg.optInt("news_count", 0));
			setDiamond(msg.optInt("diamond", 0));
			setHideStatus(msg.optInt("dvip_hidden"));
		}

		JSONObject ownObj = json.optJSONObject("userown");
		if (ownObj != null) {
			setVipType(ownObj.optInt("vip", 0));
			setuCarId(ownObj.optInt("car", 0));
		}
		// ���˶�̬
		setuDynamicJSON(json.optJSONObject("dynamic").toString());
		// ���
		setuAlbumJSON(json.optJSONArray("albums").toString());

		setThird_uid("");
		setThird_token("");
		setThird_expires(0L);
		setGiftPack(msg.optString("giftPackage95MM", "[]"));
		//setUserFamily( FamilyBean.parseFromJson(json.optJSONObject("family_info")));//����
	}

	//**************************************************************************************************************************
	//
	//																											get��set����
	//
	//**************************************************************************************************************************

	public int getuId() {
		return uId;
	}

	public void setuId(int uId) {
		this.uId = uId;
	}

	public String getuName() {
		return uName;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}

	public String getuNickName() {
		return uNickName;
	}

	public void setuNickName(String uNickName) {
		this.uNickName = uNickName;
	}

	public String getuAvatarUrl() {
		return uAvatarUrl;
	}

	public void setuAvatarUrl(String uAvatarUrl) {
		this.uAvatarUrl = uAvatarUrl;
	}

	public int getuWealthLev() {
		return uWealthLev;
	}

	public void setuWealthLev(int uWealthLev) {
		this.uWealthLev = uWealthLev;
	}

	public int getVipType() {
		return vipType;
	}

	public void setVipType(int vipType) {
		this.vipType = vipType;
	}

	public int getuType() {
		return uType;
	}

	public void setuType(int uType) {
		this.uType = uType;
	}

	public String getuPhone() {
		return uPhone;
	}

	public void setuPhone(String uPhone) {
		this.uPhone = uPhone;
	}

	public String getuQQ() {
		return uQQ;
	}

	public void setuQQ(String uQQ) {
		this.uQQ = uQQ;
	}

	public String getuPassword() {
		return uPassword;
	}

	public void setuPassword(String uPassword) {
		this.uPassword = uPassword;
	}

	public int getuSex() {
		return uSex;
	}

	public void setuSex(int uSex) {
		this.uSex = uSex;
	}

	public int getuStarLev() {
		return uStarLev;
	}

	public void setuStarLev(int uStarLev) {
		this.uStarLev = uStarLev;
	}

	public int getVipExpires() {
		return vipExpires;
	}

	public void setVipExpires(int vipExpires) {
		this.vipExpires = vipExpires;
	}

	public int getuRegisterType() {
		return uRegisterType;
	}

	public void setuRegisterType(int uRegisterType) {
		this.uRegisterType = uRegisterType;
	}

	public String getuSignature() {
		return uSignature;
	}

	public void setuSignature(String uSignature) {
		this.uSignature = uSignature;
	}

	public int getuCoin() {
		return uCoin;
	}

	public void setuCoin(int uCoin) {
		this.uCoin = uCoin;
	}

	public String getuAttnTags() {
		return uAttnTags;
	}

	public void setuAttnTags(String uAttnTags) {
		this.uAttnTags = uAttnTags;
	}

	public int getuAttnedCount() {
		return uAttnedCount;
	}

	public void setuAttnedCount(int uAttnedCount) {
		this.uAttnedCount = uAttnedCount;
	}

	public int getuAttnOthersCount() {
		return uAttnOthersCount;
	}

	public void setuAttnOthersCount(int uAttnOthersCount) {
		this.uAttnOthersCount = uAttnOthersCount;
	}

	public String getuCarTime() {
		return uCarTime;
	}

	public void setuCarTime(String uCarTime) {
		this.uCarTime = uCarTime;
	}

	public int getuCarId() {
		return uCarId;
	}

	public void setuCarId(int uCarId) {
		this.uCarId = uCarId;
	}

	public String getuAllCars() {
		return uAllCars;
	}

	public void setuAllCars(String uAllCars) {
		this.uAllCars = uAllCars;
	}

	public long getuCurStarExps() {
		return uCurStarExps;
	}

	public void setuCurStarExps(long uCurStarExps) {
		this.uCurStarExps = uCurStarExps;
	}

	public long getuStarLevNextExps() {
		return uStarLevNextExps;
	}

	public void setuStarLevNextExps(long uStarLevNextExps) {
		this.uStarLevNextExps = uStarLevNextExps;
	}

	public long getuCurWealthExps() {
		return uCurWealthExps;
	}

	public void setuCurWealthExps(long uCurWealthExps) {
		this.uCurWealthExps = uCurWealthExps;
	}

	public long getuWealthLevNextExps() {
		return uWealthLevNextExps;
	}

	public void setuWealthLevNextExps(long uWealthLevNextExps) {
		this.uWealthLevNextExps = uWealthLevNextExps;
	}

	public String getuSessionId() {
		return uSessionId;
	}

	public void setuSessionId(String uSessionId) {
		this.uSessionId = uSessionId;
	}

	public String getuAttnOthersIds() {
		return uAttnOthersIds;
	}

	public void setuAttnOthersIds(String uAttnOthersIds) {
		this.uAttnOthersIds = uAttnOthersIds;
	}

	public String getuLivingIds() {
		return uLivingIds;
	}

	public void setuLivingIds(String uLivingIds) {
		this.uLivingIds = uLivingIds;
	}

	public boolean getuIsBindPhone() {
		return uIsBindPhone;
	}

	public void setuIsBindPhone(boolean uIsBindPhone) {
		this.uIsBindPhone = uIsBindPhone;
	}

	public boolean getuIsBuyTC() {
		return uIsBuyTC;
	}

	public void setuIsBuyTC(boolean uIsBuyTC) {
		this.uIsBuyTC = uIsBuyTC;
	}

	public String getThird_uid() {
		return third_uid;
	}

	public void setThird_uid(String third_uid) {
		this.third_uid = third_uid;
	}

	public String getThird_token() {
		return third_token;
	}

	public void setThird_token(String third_token) {
		this.third_token = third_token;
	}

	public long getThird_expires() {
		return third_expires;
	}

	public void setThird_expires(long third_expires) {
		this.third_expires = third_expires;
	}

	public String getuTempName() {
		return uTempName;
	}

	public void setuTempName(String uTempName) {
		this.uTempName = uTempName;
	}

	public String getuDynamicJSON() {
		return uDynamicJSON;
	}

	public void setuDynamicJSON(String uDynamicJSON) {
		this.uDynamicJSON = uDynamicJSON;
	}

	public String getuAlbumJSON() {
		return uAlbumJSON;
	}

	public void setuAlbumJSON(String uAlbumJSON) {
		this.uAlbumJSON = uAlbumJSON;
	}

	public String getExtra_identity() {
		return extra_identity;
	}

	public void setExtra_identity(String extra_identity) {
		this.extra_identity = extra_identity;
	}

	public String getExtra_identity2() {
		return extra_identity2;
	}

	public void setExtra_identity2(String extra_identity2) {
		this.extra_identity2 = extra_identity2;
	}

	public String getExtraOther() {
		return extraOther;
	}

	public void setExtraOther(String extraOther) {
		this.extraOther = extraOther;
	}

	public int getuIsLiving() {
		return uIsLiving;
	}

	public void setuIsLiving(int uIsLiving) {
		this.uIsLiving = uIsLiving;
	}

	public int getChargeStatus() {
		return chargeStatus;
	}

	public void setChargeStatus(int chargeStatus) {
		this.chargeStatus = chargeStatus;
	}

	public boolean isGuider() {
		return isGuider;
	}

	public void setGuider(boolean isGuider) {
		this.isGuider = isGuider;
	}

	public int getuFreeGift() {
		return uFreeGift;
	}

	public void setuFreeGift(int uFreeGift) {
		this.uFreeGift = uFreeGift;
	}

	public int getuUnreadNewsCount() {
		return uUnreadNewsCount;
	}

	public void setuUnreadNewsCount(int uUnreadNewsCount) {
		this.uUnreadNewsCount = uUnreadNewsCount;
	}

	public String getGiftPack() {
		return giftPack;
	}

	public void setGiftPack(String giftPack) {
		this.giftPack = giftPack;
	}

	public int getDiamond() {
		return uDiamond;
	}

	public void setDiamond(int uDiamond) {
		this.uDiamond = uDiamond;
	}

	public String getBadgeIds() {
		return badgeIds;
	}

	public void setBadgeIds(String badgeIds) {
		this.badgeIds = badgeIds;
	}

	public String getBadgeTimes() {
		return badgeTimes;
	}

	public void setBadgeTimes(String badgeTimes) {
		this.badgeTimes = badgeTimes;
	}

	public int getHideStatus() {
		return hideStatus;
	}

	public void setHideStatus(int hideStatus) {
		this.hideStatus = hideStatus;
	}
}