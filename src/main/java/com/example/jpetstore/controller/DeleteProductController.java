package com.example.jpetstore.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.example.jpetstore.domain.Auction;
import com.example.jpetstore.domain.Item;
import com.example.jpetstore.domain.Product;
import com.example.jpetstore.service.SosoMarketFacade;

/**
 * @author Juergen Hoeller
 * @since 30.11.2003
 * @modified-by Changsup Park
 */
@Controller
@SessionAttributes({"category", "productList"})
public class DeleteProductController { 

	private SosoMarketFacade sosomarket;

	@Autowired
	public void setPetStore(SosoMarketFacade petSsosomarkettore) {
		this.sosomarket = sosomarket;
	}

	@RequestMapping("/shop/deleteProduct.do")
	public ModelAndView deleteProduct(HttpSession session,
			@RequestParam("productId") String productId,
			ModelMap model) throws Exception {
		//�α��� ����� != ��ǰ �Խ� ������� ��� error
		UserSession userSession = (UserSession) session.getAttribute("userSession");
		Product product = sosomarket.getProduct(Integer.parseInt(productId));
		System.out.println("����α��� id : " + userSession.getAccount().getAccountId() + ", " + product.getSellerId());
		if (!userSession.getAccount().getAccountId().equals(product.getSellerId()))
			return new ModelAndView("Error", "message", 
					"������ ����� ��ǰ�� ������ �� �ֽ��ϴ�.");
		//��ǰ ������ ������ ��� ����
		else {
			this.sosomarket.deleteProduct(Integer.parseInt(productId));
		
			return new ModelAndView("SearchProductList");
		}
	}
	
	@RequestMapping("/shop/deleteAuction.do")
	public ModelAndView deleteAuctionProduct(HttpSession session,
			@RequestParam("auctionId") String auctionId,
			ModelMap model) throws Exception {
		//�α��� ����� != ��ǰ �Խ� ������� ��� error
		UserSession userSession = (UserSession) session.getAttribute("userSession");
		Auction auction = sosomarket.getAuction(Integer.parseInt(auctionId));
		System.out.println("����α��� id : " + userSession.getAccount().getAccountId() + ", " + auction.getProduct().getSellerId());
		if (!userSession.getAccount().getAccountId().equals(auction.getProduct().getSellerId()))
			return new ModelAndView("Error", "message", 
					"������ ����� ��ǰ�� ������ �� �ֽ��ϴ�.");
		//��ǰ ������ ������ ��� ����
		else {
			this.sosomarket.deleteProduct(Integer.parseInt(auctionId));
		
			return new ModelAndView("SearchAuctionProductList");
		}
	}
	
}
