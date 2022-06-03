package productcrudapp.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import productcrudapp.model.Product;

@Component
public class ProductDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	//create 
	@Transactional(readOnly = false)
	public void createProduct(Product product) {
		this.hibernateTemplate.save(product);
		}
	
	//to get all product
	public List<Product> getProduct(){
		List<Product> product =  this.hibernateTemplate.loadAll(Product.class);
		return product;
	}
	
	//delete the single product
	public void deleteProduct(int pid) {
		Product p =  this.hibernateTemplate.load(Product.class, pid);
		this.hibernateTemplate.delete(p);
	}
	
	public Product getProduct(int pid){
		return this.hibernateTemplate.get(Product.class, pid);
	}
}
