package Model.cart;

import java.util.List;

import Model.cart.db.CartDao;
import Model.cart.db.CartDto;

public class CartBusinessLogic {

	public boolean executeInsert(CartDto dto) {

		CartDao dao = new CartDao();
		boolean isSuccess = dao.doInsert(dto);

		return isSuccess;
	}

	public List<CartDto> executeSelect(int id) {

		CartDao dao = new CartDao();
		List<CartDto> dto = dao.doAllSelect(id);

		return dto;
	}

	public boolean executeUpdate(int cartId, int number, int userId) {

		CartDao dao = new CartDao();
		boolean isSuccess = dao.doUpdate(cartId, number, userId);

		return isSuccess;
	}

	public boolean executeDlete(int cartId, int userId) {

		CartDao dao = new CartDao();
		boolean isDelete = dao.doDelete(cartId, userId);

		return isDelete;
	}
}
