package com.example.jpetstore.service;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.example.jpetstore.controller.AccountForm;
import com.example.jpetstore.domain.Account;
/**
 * @author Juergen Hoeller
 * @since 01.12.2003
 * @modified by Changsup Park
 */
@Component
public class AccountFormValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return Account.class.isAssignableFrom(clazz);
	}

	public void validate(Object obj, Errors errors) {
		AccountForm accountForm = (AccountForm)obj; 
		Account account = accountForm.getAccount();

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "account.password", "PASSWORD_REQUIRED", "��й�ȣ�� �Է����ּ���.");
	    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "repeatedPassword", "REPEATED_PASSWORD_REQUIRED", "��й�ȣ Ȯ���� �Է����ּ���.");
	    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "account.nickname", "NICKNAME_REQUIRED", "�г����� �Է����ּ���.");
	    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "account.email", "EMAIL_ADDRESS_REQUIRED", "�̸����� �Է����ּ���."); 
	    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "account.phoneNumber", "PHONE_NUMBER_REQUIRED", "��ȭ��ȣ�� �Է����ּ���.");
	    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "account.address", "MY_ADDRESS_REQUIRED", "�ּҸ� �Է����ּ���.");
	    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "account.zipcode", "MY_ZIPCODE_REQUIRED", "�����ȣ�� �Է����ּ���.");
	    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "account.bankName", "BANK_NAME_REQUIRED", "������� �Է����ּ���.");
	    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "account.bankNumber", "BANK_NUMBER_REQUIRED", "���¹�ȣ�� �Է����ּ���.");

	    if (account.getPassword().length() < 6 && account.getPassword() != null && account.getPassword().length() > 0) {
	    	errors.rejectValue("account.password", "PASSWORD_LENGTH", "��й�ȣ�� �ּ� 6�ڸ��� �����ּ���.");
	    }
	    if (account.getZipcode().length() != 5 && account.getZipcode() != null && account.getZipcode().length() > 0) {
	    	errors.rejectValue("account.zipcode", "ZIPCODE_LENGTH", "�����ȣ�� 5�ڸ��Դϴ�.");
	    }
	    if (account.getEmail() != null && account.getEmail().length() > 0) {
	    	if (!account.getEmail().matches("(.*)@(.*)") || !account.getEmail().matches("(.*)\\.(.*)")) {
		    	errors.rejectValue("account.email", "EMAIL_MISMATCH", "�̸��� ������ �߸��Ǿ����ϴ�.");
		    }
	    }
	    if (!account.getPhoneNumber().matches("^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$")
	    		&& account.getPhoneNumber() != null && account.getPhoneNumber().length() > 0) {
	    	errors.rejectValue("account.phoneNumber", "PHONE_NUMBER_MISMATCH", "��ȭ��ȣ ������ �߸��Ǿ����ϴ�.");
	    }
	    if (accountForm.isNewAccount()) {
	    	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "account.accountId", "ACCOUNT_ID_REQUIRED", "���̵� �Է����ּ���.");
	        if (account.getPassword() == null || account.getPassword().length() < 1
	        		|| !account.getPassword().equals(accountForm.getRepeatedPassword())) {
	        	errors.reject("PASSWORD_MISMATCH", "��й�ȣ�� ���ų� ��ġ���� �ʽ��ϴ�.");
	        }
	    } else if (account.getPassword() != null && account.getPassword().length() > 0) {
	    	if (!account.getPassword().equals(accountForm.getRepeatedPassword())) {
	    		errors.rejectValue("repeatedPassword", "PASSWORD_MISMATCH", "��й�ȣ�� ��ġ���� �ʽ��ϴ�. �ٽ� Ȯ�����ּ���.");
	    	}
	    }
	    if (errors.hasFieldErrors() && !errors.hasGlobalErrors()) {
	    	errors.reject("ERRORS_OCCURED", "�Է� �������� ������ �߻��߽��ϴ�. �ٽ� Ȯ�����ּ���.");
	    }
	}
}