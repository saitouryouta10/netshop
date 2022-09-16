package Model.buy;

import java.util.List;

import Model.buy.db.BuyDao;
import Model.cart.db.CartDto;

public class BuyBusinessLogic {

	public boolean executeBuy(List<CartDto> dtoList) {
		
		BuyDao dao = new BuyDao();
		boolean isSuccess = dao.doBuy(dtoList);
		
		return isSuccess;
	}

}
