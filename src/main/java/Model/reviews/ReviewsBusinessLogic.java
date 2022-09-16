package Model.reviews;

import java.util.List;

import Model.reviews.db.ReviewsDao;
import Model.reviews.db.ReviewsDto;

public class ReviewsBusinessLogic {

	public List<ReviewsDto> executeSelect(int id) {
		
		ReviewsDao dao = new ReviewsDao();
		List<ReviewsDto> dto = dao.doSelect(id);
		
		return dto;
	}
	
	public List<ReviewsDto> executeAllSelect(int id) {
		
		ReviewsDao dao = new ReviewsDao();
		List<ReviewsDto> dto = dao.doAllSelect(id);
		
		return dto;
	}
	
	public boolean executeInsert(ReviewsDto dto) {
		
		ReviewsDao dao = new ReviewsDao();
		boolean isSuccess = dao.doInsert(dto);
		
		return isSuccess;
	}
	
	public boolean executeDelete(int reviewId, int userId) {
		
		ReviewsDao dao = new ReviewsDao();
		boolean isDelete = dao.doDelete(reviewId, userId);
		
		return isDelete;
	}
}
