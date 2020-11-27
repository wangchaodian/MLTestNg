package com.qa.test;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.restclient.KeyMethod;
import com.qa.restclient.RestClient;


public class WowoTest extends TestBase {

//	private final static Logger log = Logger.getLogger(CountryTest.class);

	TestBase testBase;
	String host;
	String excelUrl;
	String Sheet_Post, Sheet_Get;
	RestClient restClient;
	CloseableHttpResponse clos;

	@BeforeClass
	public void setUp() throws Exception {
		testBase = new TestBase();
//		host = prop.getProperty("WowoHost");   // 测试环境
		host = prop.getProperty("WowoHost1");   // 线上环境
		
//		excelUrl = prop.getProperty("ExcelWowoUrl");    //项目内调用Excel地址
		excelUrl = prop.getProperty("ExcelWowoUrl1");  //Jenkins调用Excel地址
		
		Sheet_Post = prop.getProperty("Sheet_Post");
		Sheet_Get = prop.getProperty("Sheet_Get");
	}

	// timeOut = 3000 -- 方法超过3s抛出异常 ，invocationTimeOut = 5000 -- 执行几次后超过5s抛出异常
	@Test(groups = "user_post",invocationCount = 1)
	public void loginTest() throws Exception {

		KeyMethod.excel(excelUrl, Sheet_Post, host);
//		Reporter.log("状态码***********：" + KeyMethod.statusCode, true);

	}

	//  --- 不填写则是（priority = 0 --- 数字越小越先执行）,dependsOnGroups 执行后面跟的方法后再执行@Test
	@Test(groups = "user_get",enabled = true ,dependsOnGroups = {"user_post"},invocationCount = 1)  // invocationCount = 5 -- 执行次数
	public void getUserTest() throws Exception {                                

		KeyMethod.excel(excelUrl, Sheet_Get, host);
//		Reporter.log("状态码***********：" + KeyMethod.statusCode, true);
	}

}
