package Model.inquiry;

import Model.inquiry.db.InquiryDao;
import Model.inquiry.db.InquiryDto;

public class InquiryBusinessLogic {

	public boolean executeInsert (InquiryDto dto) {
		InquiryDao dao = new InquiryDao();
		boolean isSuccess = dao.doInsert(dto);
		
		return isSuccess;
	}
}
