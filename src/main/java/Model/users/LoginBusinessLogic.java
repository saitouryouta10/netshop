package Model.users;

import Model.users.db.LoginDao;
import Model.users.db.LoginDto;

public class LoginBusinessLogic {
	
	public LoginDto executeLogin (String email, String pass) {
		
		LoginDao dao = new LoginDao();
		LoginDto dto = dao.doLogin(email, pass);
		
		return dto;
	}
	
	public boolean executeCreate (LoginDto dto) {
		
		LoginDao dao = new LoginDao();
		boolean isSuccess = dao.doInsert(dto);
		
		return isSuccess;
	}
	
	public boolean executeUpdate (LoginDto dto) {
		
		LoginDao dao = new LoginDao();
		boolean isSuccess = dao.doUpdate(dto);
		
		return isSuccess;
	}
	
	public boolean executeUpdatePass (String pass, int id) {
		LoginDao dao = new LoginDao();
		boolean isSuccess = dao.doUpdatePass(pass, id);
		
		return isSuccess;
	}
}
