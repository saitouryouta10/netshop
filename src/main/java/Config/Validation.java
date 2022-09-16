package Config;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
	
//	バリデーション

//	半角数字
	private final String NUM_MATCH = ".*[0-9].*";
//	口コミほし
	private final String STAR_MATCH = ".*[1-5].*";
//	記号
	private final String SYMBOL_MATCH = ".*[ -/:-@\\[-\\`\\{-\\~].*";
//	カタカナ
	private final String KANA_MATCH = "^[\\u30a0-\\u30ff]+$";
	
//	郵便番号
	private final String ZIP_MATCH = "^[0-9]{3}-[0-9]{4}$";
	
//	誕生日
	private final String BIRTH_MATCH = "^[0-9]{4}-[0-9]{2}-[0-9]{2}$";
	
//	電話番号
	private final String TELL_MATCH = "^(070|080|090)-\\d{4}-\\d{4}$";
	
//	パスワード
	private final String PASS_MATCH = "^[A-Za-z0-9]+$";
	
	public String h(String val) {
		
		val = val.replace("&", "&amp");
		val = val.replace(">", "&gt");
		val = val.replace("\"", "&quot");
		val = val.replace("'", "&#039");
		val = val.replace("<", "&lt");
		
		return val;
	}
	
	public boolean ValidationName(String name) {
		
		boolean flag = true;
		if (name == null || name.equals("") || name.length() > 20) {
			flag = false;
//			System.out.println("a");
		}
		if (name.matches(NUM_MATCH) || name.matches(SYMBOL_MATCH)) {
			flag = false;
//			System.out.println("i");
		}
		
		return flag;
	}
	
	public boolean ValidationNameKana(String name) {
		
		boolean flag = true;
		if (name == null || name.equals("") || name.length() > 30) {
			flag = false;
//			System.out.println("u");
		}
		if (!(name.matches(KANA_MATCH))) {
			flag = false;
//			System.out.println("e");
		}
		
		return flag;
	}
	
	public boolean ValidationNickName(String name) {
		
		boolean flag = true;
		if (name == null || name.equals("") || name.length() > 20) {
			flag = false;
//			System.out.println("o");
		}
		
		return flag;
	}
	
	public boolean ValidationSex(String sex) {
		
		boolean flag = true;
		
		if (sex == null || !(sex.matches("[0-2]"))) {
			flag = false;
//			System.out.println("1");
		}
		return flag;
	}
	
	public boolean ValidationBirthday(String birthday) {
		
		boolean flag = true;
		
		if (birthday == null || !(birthday.matches(BIRTH_MATCH))) {
			flag = false;
//			System.out.println("2");
		}
		return flag;
	}
	
	public boolean ValidationZipcode(String zipcode) {
		
		boolean flag = true;
		
		if (zipcode == null || zipcode.equals("")) {
			flag = false;
//			System.out.println("3");
		}
		
		if (!(zipcode.matches(ZIP_MATCH))) {
			flag = false;
//			System.out.println("4");
		}
		
		return flag;
	}
	
	public boolean ValidationAddress(String address) {
		
		boolean flag = true;
		
		if (address == null || address.equals("")) {
			flag = false;
//			System.out.println("5");
		}
		
		return flag;
	}
	
	public boolean ValidationTell(String tell) {
		
		boolean flag = true;
		
		if (tell == null || tell.equals("")) {
			flag = false;
//			System.out.println("6");
		}
		
		if (!(tell.matches(TELL_MATCH))) {
			flag = false;
//			System.out.println("7");
		}
		
		return flag;
	}
	
	public boolean ValidationPass(String pass, String passConf) {
		
		boolean flag = true;
		
		if (pass == null || pass.equals("") || pass.length() < 8) {
			flag = false;
//			System.out.println("8");
		}
		
		if (!(pass.matches(PASS_MATCH))) {
			flag = false;
//			System.out.println("9");
		}
		
		if (!(pass.equals(passConf))) {
			flag = false;
//			System.out.println("!");
		}
		
		return flag;
	}
	
	public boolean ValidationComment(String comment, int star) {
		
		boolean flag = true;
		String string_star = String.valueOf(star);
		
		if (comment == null || comment.equals("") || comment.length() >= 251) {
			flag = false;
		}
		
		if (!(string_star.matches(STAR_MATCH))) {
			flag = false;
		}
		
		return flag;
	}
	
	public boolean ValidationNumber(String stock) {
		
		boolean flag = true;
		
		if (stock.equals(null) || stock.equals("")) {
			flag = false;
		}
		
//		System.out.println("上" + flag);
		
		Pattern p = Pattern.compile(NUM_MATCH);
		Matcher m = p.matcher(stock);
		if (!m.find()) {
			flag = false;
//			System.out.println("i");
		}
//		System.out.println(stock + flag);
		return flag;
	}
	
	public boolean ValidationInquiry(String name, String title, String email, String inquiry) {
		
		boolean flag = true;
		
		if (name.length() >= 20 || title.length() >= 30 || email.length() >= 30 || inquiry.length() >= 250) {
			flag = false;
		}
		
		if (name == "" || title == "" || email == "" || inquiry == "" || name == null || title == null || email == null || inquiry == null) {
			flag = false;
		}
		
		return flag;
	}
	
	public boolean ValidationPicture(String extension) {
		
		boolean flag = true;
		
		
		if (!(extension.equals(".png") || extension.equals(".jpg"))) {
			flag = false;
		}
		
		return flag;
	}
	
	
	
}
