package com.model2.mvc.service.purchase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.product.dao.ProductDao;


public interface PurchaseDao {

	public Purchase findPurchase(int tranNo) throws Exception;
	
	public Map<String, Object> getPurchaseList(Search search, String buyerId) throws Exception;
	
	public Map<String, Object> getSaleList(Search search) throws Exception;
	
	public void insertPurchase(Purchase purchase) throws Exception;
	
	public void updateTranCode(Purchase purchase) throws Exception;
	
	public void updatePurchase(Purchase purchase) throws Exception;
	
}
