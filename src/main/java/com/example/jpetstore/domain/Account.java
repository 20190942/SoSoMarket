package com.example.jpetstore.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Account implements Serializable {

	/* Private Fields */

	private String accountId;
	private String password;
	private String nickname;
	private String phoneNumber;
	private String email;
	private String bankNumber;
	private String bankName;
	private String address;
	private String zipcode;
//  private String favouriteCategoryId;
//  private String languagePreference;
//  private boolean listOption;
//  private boolean bannerOption;
//  private String bannerName;

	/* JavaBeans Properties */

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBankNumber() {
		return bankNumber;
	}

	public void setBankNumber(String bankNumber) {
		this.bankNumber = bankNumber;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

//
//  public String getFavouriteCategoryId() { return favouriteCategoryId; }
//  public void setFavouriteCategoryId(String favouriteCategoryId) { this.favouriteCategoryId = favouriteCategoryId; }
//
//  public String getLanguagePreference() { return languagePreference; }
//  public void setLanguagePreference(String languagePreference) { this.languagePreference = languagePreference; }

//  public boolean isListOption() { return listOption; }
//  public void setListOption(boolean listOption) { this.listOption = listOption; }
//	public int getListOptionAsInt() { return listOption ? 1 : 0; }
//
//  public boolean isBannerOption() { return bannerOption; }
//  public void setBannerOption(boolean bannerOption) { this.bannerOption = bannerOption; }
//	public int getBannerOptionAsInt() { return bannerOption ? 1 : 0; }

//  public String getBannerName() { return bannerName; }
//  public void setBannerName(String bannerName) { this.bannerName = bannerName; }

}
