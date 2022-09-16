package Model.items;

import java.util.List;

import Model.items.db.ItemsDao;
import Model.items.db.ItemsDto;

public class ItemsBusinessLogic {
	
	public List<ItemsDto> executeAllSelect() {
		
		ItemsDao dao = new ItemsDao();
		List<ItemsDto> dto = dao.allSelect();
		
		
		return dto;
	}
	
	public ItemsDto executeSelect(int id) {
		
		ItemsDao dao = new ItemsDao();
		ItemsDto dto = dao.doSelect(id);
		
		return dto;
	}
	
	public List<ItemsDto> executeSearchSelect(String itemName) {
		
		ItemsDao dao = new ItemsDao();
		List<ItemsDto> dto = dao.searchSelect(itemName);
		
		return dto;
	}
	
	public boolean executeDelete(int itemId) {
		ItemsDao dao = new ItemsDao();
		boolean isDelete = dao.doDelete(itemId);
		
		return isDelete;
	}
	
	public boolean executeInsert(ItemsDto dto) {
		ItemsDao dao = new ItemsDao();
		boolean isSuccess = dao.doInsert(dto);
		
		return isSuccess;
	}
}

