package com.example.jpetstore.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.util.WebUtils;

import com.example.jpetstore.domain.Product;
import com.example.jpetstore.service.SosoMarketFacade;

@Controller
@RequestMapping({ "/shop/newAuctionForm.do", "/shop/newAuction.do" })
@SessionAttributes("auctionForm")
public class NewAuctionController implements ApplicationContextAware {

	@Value("NewAuctionForm")
	private String formViewName;

	@Value("index")
	private String successViewName;

	@Autowired
	private SosoMarketFacade sosomarket;

	@Value("/upload/")
	private String uploadDirLocal;

	private WebApplicationContext context;
	private String uploadDir;

	public void setSosomarket(SosoMarketFacade sosomarket) {
		this.sosomarket = sosomarket;
	}

	UserSession userSession;
	public String sellerId;
	public Product product;

	@Override // life-cycle callback method
	public void setApplicationContext(ApplicationContext appContext) throws BeansException {
		this.context = (WebApplicationContext) appContext;
		this.uploadDir = context.getServletContext().getRealPath(this.uploadDirLocal);
		System.out.println("���ϰ��:" + this.uploadDir);
	}

//	@Autowired
//	private ProductFormValidator validator;
//	public void setValidator(ProductFormValidator validator) {
//		this.validator = validator;
//	}

	@ModelAttribute("auctionForm")
	public AuctionForm formBackingObject(HttpServletRequest request) throws Exception {

		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");

		AuctionForm af = new AuctionForm();
		product = new Product();
		af.setProduct(product);
		sellerId = userSession.getAccount().getAccountId();
		af.setSellerId(sellerId);

		return af;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String form() {
		return formViewName;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String onSubmit(HttpServletRequest request, HttpSession session,
			@ModelAttribute("auctionForm") AuctionForm auctionForm,
			@ModelAttribute("userSession") UserSession userSession, MultipartHttpServletRequest multiRequest,
			BindingResult result) throws Exception {

		// �̹���
		MultipartFile imageFile = multiRequest.getFile("imageFile");
//				System.out.println(productForm);

		String filename = uploadFile(imageFile);
		auctionForm.getAuction().getProduct().setImage(this.uploadDirLocal + filename);

		sosomarket.insertProduct(auctionForm.getAuction().getProduct());

		String accountId = auctionForm.getAuction().getProduct().getSellerId();
		String title = auctionForm.getAuction().getProduct().getTitle();
		Product product2 = sosomarket.getProduct(accountId, title);

		auctionForm.getAuction().setAuctionId(product2.getProductId());
		sosomarket.insertAuction(auctionForm.getAuction());

		return successViewName;
	}
	
	private String uploadFile(MultipartFile imageFile) {
		String filename = UUID.randomUUID().toString() 
						+ "_" + imageFile.getOriginalFilename();
		System.out.println("���ε� �� ����: "	+ filename);
		File file = new File(this.uploadDir + filename);
		try {
			imageFile.transferTo(file);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		return filename;
	}
}