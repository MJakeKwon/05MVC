package com.model2.mvc.service.product.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.dao.ProductDao;

@Repository("productDaoImpl")
public class ProductDaoImpl implements ProductDao {
	
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public ProductDaoImpl() {
		System.out.println(this.getClass());
	}
	
	public void insertProduct(Product product) throws Exception{
		sqlSession.insert("ProductMapper.insertProduct",product);
	}
	
	public Product findProduct(int prodNo) throws Exception{
		return sqlSession.selectOne("ProductMapper.findProduct", prodNo);
	}
	
	public List<Object> getProductList(Search search) throws Exception{
		return sqlSession.selectList("ProductMapper.getProductList", search);
	}
	
	public void updateProduct(Product product) throws Exception{
		sqlSession.update("ProductMapper.updateProduct", product);
	}
	
	// 게시판 Page 처리를 위한 전체 Row(totalCount)  return
	public int getTotalCount(Search  search) throws Exception{
		return sqlSession.selectOne("ProductMapper.getTotalCount", search);
	}

}

