package com.com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.com.bean.ProductBean;
import com.com.bean.ResponseBean;
import com.com.dao.ProductDao;

@RestController
public class ProductController {
	@Autowired
	ProductDao productDao;

	@PostMapping("/product")
	public ProductBean addProduct(ProductBean productBean) {
		productDao.addProduct(productBean);
		return productBean;
	}

	@GetMapping("/product/{id}")
	public ResponseBean<ProductBean> addProduct(@PathVariable("id") int id) {
		ProductBean productBean = productDao.getDataByProductId(id);
		ResponseBean<ProductBean> rb = new ResponseBean();
		rb.setCode(200);
		rb.setMessage("product added");
		rb.setData(productBean);

		return rb;
	}

	@DeleteMapping("/product/{id}")
	public String deleteProduct(@PathVariable("id") int id) {
		boolean flag = productDao.deleteProduct(id);
		if (flag) {
			return "Prodcut was Removed";
		} else {
			return "Something went Wrong";
		}
	}

	@GetMapping("/products")
	public List<ProductBean> getAllProducts() {
		List<ProductBean> products = productDao.getAllProducts();
		return products;
	}

	@PutMapping("/product")
	public ProductBean updateProduct(ProductBean productBean) {
		productDao.updateProduct(productBean);
		return productBean;
	}

}
