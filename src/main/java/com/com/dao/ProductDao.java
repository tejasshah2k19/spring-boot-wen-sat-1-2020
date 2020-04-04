package com.com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.com.bean.ProductBean;

@Repository
public class ProductDao {

	@Autowired
	JdbcTemplate stmt;

	public void addProduct(ProductBean productBean) {

		stmt.update("insert into product (name,price) values (?,?)", productBean.getName(), productBean.getPrice());

	}

	public ProductBean getDataByProductId(int id) {
		ProductBean productBean = null;
		productBean = stmt.queryForObject("select * from product where productid = " + id,
				new BeanPropertyRowMapper<ProductBean>(ProductBean.class));
		return productBean;
	}

	public boolean deleteProduct(int id) {
		int i = stmt.update("delete from product where productid =" + id);
		if (i == 0)
			return false;
		else
			return true;
	}

	public List<ProductBean> getAllProducts() {
		List<ProductBean> products = new ArrayList<>();

		products = stmt.query("select * from product", new ProductRowMapper());

		return products;
	}

	class ProductRowMapper implements RowMapper<ProductBean> {

		@Override
		public ProductBean mapRow(ResultSet rs, int rowNum) throws SQLException {

			ProductBean productBean = new ProductBean();
			productBean.setProductId(rs.getInt("productid"));
			productBean.setName(rs.getString("name"));
			productBean.setPrice(rs.getInt("price"));

			return productBean;
		}

	}

	public boolean updateProduct(ProductBean productBean) {
		int i = stmt.update("update product set name = ? , price = ? where productid = ?", productBean.getName(),
				productBean.getPrice(), productBean.getProductId());
		if (i == 1)
			return true;
		else
			return false;
	}

}
