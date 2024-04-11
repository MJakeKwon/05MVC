package com.model2.mvc.service.purchase.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.product.dao.ProductDao;

@Repository("purchaseDaoImpl")
public class PurchaseDaoImpl {
	
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public PurchaseDaoImpl() {
		System.out.println(this.getClass());
	}
	
	public Purchase findPurchase(int tranNo) throws Exception{
		return sqlSession.selectOne("PurchaseMapper.findProduct", tranNo);
	}
	
	public List<Object> getPurchaseList(Search search, String buyerId) throws Exception {
		
		List<Object> list = new ArrayList<Object>();
		list.add(search);
		list.add(buyerId);
		
		return sqlSession.selectList("PurchaseMapper.getPurchaseList", list);
		
	}
	
	public List<String> getSaleList(int prodNo) throws Exception{
		return sqlSession.selectList("PurchaseMapper.getSaleList",prodNo);
	}
	
	public void insertPurchase(Purchase purchase) throws Exception{
		sqlSession.insert("PurchaseMapper.insertProduct",purchase);
	}
	
	public void updatePurchase(Purchase purchase) throws Exception{
		sqlSession.update("PurchaseMapper.updatePurchase", purchase);
	}
	
	public void updateTranCode(Purchase purchase) throws Exception{
		sqlSession.update("PurchaseMapper.updateTranCode", purchase);
	}
	
}
