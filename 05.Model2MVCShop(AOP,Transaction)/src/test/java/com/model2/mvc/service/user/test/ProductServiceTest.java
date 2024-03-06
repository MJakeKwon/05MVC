package com.model2.mvc.service.user.test;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.user.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {	"classpath:config/context-common.xml",
														"classpath:config/context-aspect.xml",
														"classpath:config/context-mybatis.xml",
														"classpath:config/context-transaction.xml" })

public class ProductServiceTest {

	//==>@RunWith,@ContextConfiguration 이용 Wiring, Test 할 instance DI
		@Autowired
		@Qualifier("productServiceImpl")
		private ProductService productService;
		
		//@Test
		public void testInsertProduct() throws Exception{
			
			Product product =new Product();
			product.setProdName("testProdName");
			product.setProdDetail("testProdDetail");
			product.setManuDate("ManuDate");
			product.setPrice(1120);
			product.setFileName("testFileName");
			
			productService.addProduct(product);
			
			//System.out.println(product)
			
			Assert.assertEquals("testProdName", product.getProdName());
			Assert.assertEquals("testProdDetail", product.getProdDetail());
			Assert.assertEquals("ManuDate", product.getManuDate());
			Assert.assertEquals(1120, product.getPrice());
			Assert.assertEquals("testFileName", product.getFileName());
		}
		
		//@Test
		public void testGetProduct() throws Exception {
			
			Product product = new Product();
			product = productService.getProduct(10020);
			
			//System.out.println(product);
			
			//==> API 확인
			Assert.assertEquals(10020, product.getProdNo());
			Assert.assertEquals("루시", product.getProdName());
			Assert.assertEquals("루루", product.getProdDetail());
			Assert.assertEquals("20240301", product.getManuDate());
			Assert.assertEquals(4000, product.getPrice());

			
			//Assert.assertNotNull(productService.getProduct(10344));
		}
		//@Test
		 public void testUpdateProduct() throws Exception{
			 
			Product product = productService.getProduct(10020);
			
//			Assert.assertEquals("testProdName", product.getProdName());
//			Assert.assertEquals("testProdDetail", product.getProdDetail());
//			Assert.assertEquals("ManuDate", product.getManuDate());
//			Assert.assertEquals(1120, product.getPrice());
//			Assert.assertEquals("testFileName", product.getFileName());

			product.setProdName("루시");
			product.setProdDetail("룰루룰루");
			product.setManuDate("20240301");
			product.setPrice(4320);
			product.setFileName(" ");
			
			productService.updateProduct(product);
			
			product = productService.getProduct(10020);
			//System.out.println(product);
			
			Assert.assertEquals("슈가", product.getProdName());
			Assert.assertEquals("슉슉 루", product.getProdDetail());
			Assert.assertEquals("20231116", product.getManuDate());
			Assert.assertEquals(4502, product.getPrice());
			Assert.assertEquals(" ", product.getFileName());
		 }
		 
		
		 //@Test
		 public void testGetProductList() throws Exception{
			 
		 	Search search = new Search();
		 	search.setCurrentPage(1);
		 	search.setPageSize(3);
		 	Map<String,Object> map = productService.getProductList(search);
		 	
		 	List<Object> list = (List<Object>)map.get("list");
		 	Assert.assertEquals(5, list.size());
		 	
		 	Integer totalCount = (Integer)map.get("totalCount");
		 	System.out.println(totalCount);
		 	
		 	System.out.println("=======================================");
		 	
		 	search.setCurrentPage(1);
		 	search.setPageSize(3);
		 	search.setSearchCondition("0");
		 	search.setSearchKeyword("10020");
		 	map = productService.getProductList(search);
		 	
		 	list = (List<Object>)map.get("list");
		 	Assert.assertEquals(1, list.size());
		 	
		 	totalCount = (Integer)map.get("totalCount");
		 	System.out.println(totalCount);
		 }
		 
		//@Test
		 public void testGetProductListByPrice() throws Exception{
			 
		 	Search search = new Search();
		 	search.setCurrentPage(1);
		 	search.setPageSize(3);
		 	search.setSearchCondition("2");
		 	search.setSearchKeyword("333");
		 	Map<String,Object> map = productService.getProductList(search);
		 	
		 	List<Object> list = (List<Object>)map.get("list");
		 	Assert.assertEquals(1, list.size());
		 	
			//==> console 확인
		 	//System.out.println(list);
		 	
		 	Integer totalCount = (Integer)map.get("totalCount");
		 	System.out.println(totalCount);
		 	
		 	System.out.println("=======================================");
		 	
		 	search.setSearchCondition("0");
		 	search.setSearchKeyword(""+System.currentTimeMillis());
		 	map = productService.getProductList(search);
		 	
		 	list = (List<Object>)map.get("list");
		 	Assert.assertEquals(0, list.size());
		 	
			//==> console 확인
		 	//System.out.println(list);
		 	
		 	totalCount = (Integer)map.get("totalCount");
		 	System.out.println(totalCount);
		 }
		 
		 //@Test
		 public void testGetProductListByProdName() throws Exception{
			 
		 	Search search = new Search();
		 	search.setCurrentPage(1);
		 	search.setPageSize(3);
		 	search.setSearchCondition("1");
		 	search.setSearchKeyword("루시");
		 	Map<String,Object> map = productService.getProductList(search);
		 	
		 	List<Object> list = (List<Object>)map.get("list");
		 	Assert.assertEquals(2, list.size());
		 	
			//==> console 확인
		 	System.out.println(list);
		 	
		 	Integer totalCount = (Integer)map.get("totalCount");
		 	System.out.println(totalCount);
		 	
		 	System.out.println("=======================================");
		 	
		 	search.setSearchCondition("1");
		 	search.setSearchKeyword(""+System.currentTimeMillis());
		 	map = productService.getProductList(search);
		 	
		 	list = (List<Object>)map.get("list");
		 	Assert.assertEquals(0, list.size());
		 	
			//==> console 확인
		 	System.out.println(list);
		 	
		 	totalCount = (Integer)map.get("totalCount");
		 	System.out.println(totalCount);
		 }	 
	}