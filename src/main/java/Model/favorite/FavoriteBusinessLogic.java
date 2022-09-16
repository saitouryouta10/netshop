package Model.favorite;

import java.util.List;

import Model.favorite.db.FavoriteDao;
import Model.favorite.db.FavoriteDto;

public class FavoriteBusinessLogic {
	public boolean executeInsert(int userId, int itemId) {
		
		FavoriteDao dao = new FavoriteDao();
		boolean isSuccess = dao.doInsert(userId, itemId);
		
		return isSuccess;
	}
	
	public boolean executeDelete(int userId, int itemId) {
		
		FavoriteDao dao = new FavoriteDao();
		boolean isSuccess = dao.doDelete(userId, itemId);
		
		return isSuccess;
	}
	
	public List<FavoriteDto> executeSelect(int userId) {
		
		FavoriteDao dao = new FavoriteDao();
		List<FavoriteDto> dtoList = dao.doSelect(userId);
		
		return dtoList;
	}
	
}
