package com.example.jpetstore.service;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.example.jpetstore.controller.AuctionForm;
import com.example.jpetstore.domain.Auction;
/**
 * @author Juergen Hoeller
 * @since 01.12.2003
 * @modified by Changsup Park
 */
@Component
public class AuctionFormValidator implements Validator {

	@Autowired
	private SosoMarketFacade sosomarket;
	
	public boolean supports(Class<?> clazz) {
		return Auction.class.isAssignableFrom(clazz);
	}

	public void validate(Object obj, Errors errors) {
		AuctionForm auctionForm = (AuctionForm)obj; 
		Auction auction = auctionForm.getAuction();
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "auction.product.title", "PRODUCT_TITLE_REQUIRED", "������ �Է����ּ���.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "auction.product.categoryId", "CATEGORY_REQUIRED", "ī�װ��� �Է����ּ���.");
	    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "auction.product.description", "DESCRIPTION_REQUIRED", "��ǰ ������ �Է����ּ���.");
	    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "auction.product.shipping", "SHIPPING_REQUIRED", "��۹���� �Է����ּ���.");
	    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "auction.product.shippingFee", "SHIPPING_FEE_REQUIRED", "��ۺ� �Է����ּ���.");
	    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "auction.startPrice", "STARTPRICE_REQUIRED", "���۰��� �Է����ּ���.");
	    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "auction.deadLine", "DEADLINE_REQUIRED", "���� ��¥�� �Է����ּ���.");
	    
	    if (auction.getStartPrice() < 1000) {
        	errors.rejectValue("auction.startPrice", "PRICE_TOO_LOW", "1000�� �Ʒ��δ� ����� �Ұ����մϴ�.");
        }
	    
//	    String date = auction.getDeadLine().toString();
//	    System.out.println(date);
//	    if (!date.matches("^(?:\\d{4})-(?:\\d{2})-(?:\\d{2})/s(?:\\d{2}):(?:\\d{2})$")) {
//	    	errors.rejectValue("auction.deadLine", "DEADLINE_MISMATCH", "��¥ ������ �ùٸ��� �ʽ��ϴ�.");
//	    }  // �õ��غôµ� �ȵ�....
	    
	    if (errors.hasFieldErrors() && !errors.hasGlobalErrors()) {
	    	errors.reject("ERRORS_OCCURED", "�Է� �������� ������ �߻��߽��ϴ�. �ٽ� Ȯ�����ּ���.");
	    }
	}
}