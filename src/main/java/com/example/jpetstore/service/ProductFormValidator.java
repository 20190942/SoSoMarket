package com.example.jpetstore.service;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.example.jpetstore.controller.ProductForm;
import com.example.jpetstore.domain.Product;
/**
 * @author Juergen Hoeller
 * @since 01.12.2003
 * @modified by Changsup Park
 */
@Component
public class ProductFormValidator implements Validator {
	
	public boolean supports(Class<?> clazz) {
		return Product.class.isAssignableFrom(clazz);
	}

	public void validate(Object obj, Errors errors) {
		ProductForm productForm = (ProductForm)obj; 
		Product product = productForm.getProduct();
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "product.title", "PRODUCT_TITLE_REQUIRED", "������ �Է����ּ���.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "product.categoryId", "CATEGORY_REQUIRED", "ī�װ��� �Է����ּ���.");
	    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "product.description", "DESCRIPTION_REQUIRED", "��ǰ ������ �Է����ּ���.");
	    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "product.price", "PRICE_REQUIRED", "������ �Է����ּ���."); 
	    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "product.shipping", "SHIPPING_REQUIRED", "��۹���� �Է����ּ���.");
	    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "product.shippingFee", "SHIPPING_FEE_REQUIRED", "��ۺ� �Է����ּ���.");
	    
	    if (product.getPrice() < 1000) {
        	errors.rejectValue("product.price", "PRICE_TOO_LOW", "1000�� �Ʒ��δ� ����� �Ұ����մϴ�.");
        }
	    
	    if (errors.hasFieldErrors() && !errors.hasGlobalErrors()) {
	    	errors.reject("ERRORS_OCCURED", "�Է� �������� ������ �߻��߽��ϴ�. �ٽ� Ȯ�����ּ���.");
	    }
	}
}