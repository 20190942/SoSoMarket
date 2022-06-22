package com.example.jpetstore.controller;

import org.springframework.beans.support.PagedListHolder;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.example.jpetstore.domain.Category;
import com.example.jpetstore.domain.Product;
import com.example.jpetstore.domain.Auction;
import com.example.jpetstore.domain.Bidding;
import com.example.jpetstore.service.SosoMarketFacade;

/**
 * @author Juergen Hoeller
 * @since 30.11.2003
 * @modified-by Changsup Park
 */
@Controller
@SessionAttributes({"productList", "auctionList"})
public class ViewProductListController { 
	private SosoMarketFacade sosomarket;

	@Autowired
	public void setPetStore(SosoMarketFacade sosomarket) {
		this.sosomarket = sosomarket;
	}
	
	//�Ϲ� ��ǰ ����Ʈ
	@RequestMapping("/shop/viewProductList.do")
	public String productHandleRequest(ModelMap model
			) throws Exception {
//		System.out.println("productlist:");
//		for (Product p : this.sosomarket.getAllProduct()) {
//			System.out.println(p);
//		}
		PagedListHolder<Product> productList = new PagedListHolder<Product>(this.sosomarket.getAllProduct());
		productList.setPageSize(4);
		System.out.println(productList);
		model.put("productList", productList);
		return "SearchProductList";
	}

	//������ �ѱ�
	@RequestMapping("/shop/viewProductList2.do")
	public String productHandleRequest2(
			@RequestParam("page") String page,
			@ModelAttribute("productList") PagedListHolder<Product> productList, 
			BindingResult result) throws Exception {
		if ("next".equals(page)) {
			productList.nextPage();
		}
		else if ("previous".equals(page)) {
			productList.previousPage();
		}
		return "SearchProductList";
	}
	
	//��� ��ǰ ����Ʈ
	@RequestMapping("/shop/viewAuctionList.do")
	public String auctionHandleRequest(ModelMap model
			) throws Exception {
		System.out.println("auction list:");
		for (Auction a : this.sosomarket.getAllAuction()) {
			System.out.println(a);
		}
		PagedListHolder<Auction> auctionList = new PagedListHolder<Auction>(this.sosomarket.getAllAuction());
		auctionList.setPageSize(4);
		model.put("auctionList", auctionList);
		return "SearchAuctionList";
	}

	//������ �ѱ�
	@RequestMapping("/shop/viewAuctionList2.do")
	public String auctionHandleRequest2(
			@RequestParam("page") String page,
			@ModelAttribute("auctionList") PagedListHolder<Product> auctionList,
			BindingResult result) throws Exception {
		if ("next".equals(page)) {
			auctionList.nextPage();
		}
		else if ("previous".equals(page)) {
			auctionList.previousPage();
		}
		return "SearchAuctionList";
	}
	
}
