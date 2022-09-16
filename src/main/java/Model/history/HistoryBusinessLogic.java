package Model.history;

import java.util.List;

import Model.history.db.HistoryDao;
import Model.history.db.HistoryDto;

public class HistoryBusinessLogic {
	
	public List<HistoryDto> executeSelect(int userId) {
		HistoryDao dao = new HistoryDao();
		List<HistoryDto> dto = dao.doSelect(userId);
		
		return dto;
	}
	
	public List<HistoryDto> executeSelectConstrict(String time, String money, int userId) {
		HistoryDao dao = new HistoryDao();
		List<HistoryDto> dto = dao.doSelectConstrict(time, money, userId);
		
		return dto;
	}
}
