package productcrudapp.controller;

import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;
import productcrudapp.dao.ProductDao;
import productcrudapp.model.Product;

@Controller
public class MainController {
	@Autowired
	public ProductDao productDao;
	
	@RequestMapping("/")
	public String home(Model m) {
		List<Product> products = productDao.getProduct();
		m.addAttribute("products" , products);
		return "index";
	
	}
	@RequestMapping("/add-product")
	public String addProduct(Model m) {
		m.addAttribute("title", "add product");
		return "add_product_form";
	}
	
	//handle add product form
	@RequestMapping(value = "/handle-product" ,method = RequestMethod.POST)
	public RedirectView handleProduct(@ModelAttribute Product product, HttpServletRequest request){
	System.out.println(product);
	productDao.createProduct(product);
	RedirectView redirectView	= new RedirectView();
	
	redirectView.setUrl(request.getContextPath() + "/");
	return redirectView;
		
	}
	
	// delete handler
	@RequestMapping(value = "/delete/{productId}")
	public RedirectView deleteProduct(@PathVariable("productId") int productId,HttpServletRequest request) {
		this.productDao.deleteProduct(productId);
		RedirectView redirectView	= new RedirectView();
		redirectView.setUrl(request.getContextPath() + "/");
		return redirectView;
			
	}

}
