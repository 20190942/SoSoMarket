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

	private SosoMarketFacade petStore;

	@Autowired
	public void setPetStore(SosoMarketFacade petStore) {
		this.petStore = petStore;
	}

	@RequestMapping("/shop/deleteProduct.do")
	public ModelAndView handleRequest(HttpSession session,
			@RequestParam("productId") String productId,
			ModelMap model) throws Exception {
		//�α��� ����� != ��ǰ �Խ� ������� ��� error
		UserSession userSession = (UserSession) session.getAttribute("userSession");
		int pId = Integer.parseInt(productId);
		Product product = petStore.getProduct(pId);
		System.out.println("����α��� id : " + userSession.getAccount().getAccountId() + ", " + product.getSellerId());
		if (!userSession.getAccount().getAccountId().equals(product.getSellerId()))
			return new ModelAndView("Error", "message", 
					"������ ����� ��ǰ�� ������ �� �ֽ��ϴ�.");
		//��ǰ ������ ������ ��� ����
		else {
			this.petStore.deleteProduct(Integer.parseInt(productId));
		
			return new ModelAndView("index");
		}
	}
	
}
