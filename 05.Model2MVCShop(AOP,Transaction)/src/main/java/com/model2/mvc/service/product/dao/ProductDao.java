package com.model2.mvc.service.product.dao;

import java.util.List;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;

public interface ProductDao {
	
	public void insertProduct(Product product) throws Exception;
	
	public Product findProduct(int prodNo) throws Exception;
	
	public List<Object> getProductList(Search search) throws Exception;
	
	public void updateProduct(Product product) throws Exception;

	public int getTotalCount(Search  search) throws Exception;

}

