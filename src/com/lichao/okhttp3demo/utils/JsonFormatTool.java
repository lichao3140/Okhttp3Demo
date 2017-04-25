package com.lichao.okhttp3demo.utils;

/**��Json��ʽ��Ϊ�׶���ʽ���ַ���*/
public class JsonFormatTool {
	//��λ�����ַ�����Ҳ����ָ��Ϊtab
	private static String SPACE = "    ";

	/** 
	 * ���ظ�ʽ�����JSON�ַ���
	 */
	public static String formatJson(String json) {
		StringBuffer result = new StringBuffer();
		int length = json.length();
		int number = 0;
		char key = 0;
		//�������json�ַ�����  
		for (int i = 0; i < length; i++) {
			//1����ȡ��ǰ�ַ���  
			key = json.charAt(i);
			//2�������ǰ�ַ��� [ �� {          �����´���  
			if ((key == '[') || (key == '{')) {
				//��1�����ǰ�滹���ַ��������ַ�Ϊ������ӡ�����к������ַ��ַ�����  
				if ((i - 1 > 0) && (json.charAt(i - 1) == ':')) {
					result.append('\n');
					result.append(indent(number));
				}
				//��2����ӡ����ǰ�ַ���  
				result.append(key);
				//��3��ǰ�����š�ǰ�����ţ��ĺ�����뻻�С���ӡ�����С�  
				result.append('\n');
				//��4��ÿ����һ��ǰ�����š�ǰ�����ţ�������������һ�Ρ���ӡ������������  
				number++;
				result.append(indent(number));
				//��5��������һ��ѭ����  
				continue;
			}
			//3�������ǰ�ַ��� ] �� }            �����´���  
			if ((key == ']') || (key == '}')) {
				//��1�������š������ţ���ǰ����뻻�С���ӡ�����С�  
				result.append('\n');
				//��2��ÿ����һ�κ����š������ţ�������������һ�Ρ���ӡ��������  
				number--;
				result.append(indent(number));
				//��3����ӡ����ǰ�ַ���  
				result.append(key);
				//��4�������ǰ�ַ����滹���ַ��������ַ���Ϊ "," �� "]" �� "}"����ӡ�����С�  
				if (((i + 1) < length) && (json.charAt(i + 1) != ',') && (json.charAt(i + 1) != ']') && (json.charAt(i + 1) != '}')) {
					result.append('\n');
				}
				//��5��������һ��ѭ����  
				continue;
			}
			//4�������ǰ�ַ��� , ���Һ󷽵��ַ��� " �� [         ���ź��滻�в����������ı�����������
			if ((key == ',') && (json.charAt(i + 1) == '"' || (json.charAt(i + 1) == '{'))) {
				result.append(key);
				result.append('\n');
				result.append(indent(number));
				continue;
			}
			//5����ӡ��ǰ�ַ���  
			result.append(key);
		}
		return result.toString();
	}

	/** 
	 * ����ָ�������������ַ�����ÿһ������һ��SPACE�� 
	 * @param number ���������� 
	 */
	private static String indent(int number) {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < number; i++) {
			result.append(SPACE);
		}
		return result.toString();
	}
}