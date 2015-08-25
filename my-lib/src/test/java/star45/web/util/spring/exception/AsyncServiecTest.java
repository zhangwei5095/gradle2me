/** 
* @file     AsyncServiecTest.java 
* @brief    my-lib's file 
* @author   许立亢 
* @date     2015年8月25日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package star45.web.util.spring.exception;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SuccessCallback;

/**
 * @brief 类简短说明
 * @details 详细说明 
 * @warning 注意事项
 * @date 2015年8月25日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-asyncExceptionHandler.xml")
public class AsyncServiecTest {
	
	@Autowired
	private AsyncService asyncService;

	

	
	public void testAsync() throws Exception{
		asyncService.throwException();
        Thread.sleep(1000L);
	}

	/**
	 * Test method for {@link star45.web.util.spring.exception.AsyncService#asyncGet1()}.
	 * @throws Exception 
	 */
	@Test
	public final void testAsyncGet1() throws Exception {
		//异步任务直接返回，所以是null，不会有结果，如果想要获取结果请返回AsyncResult
        assertEquals(null, asyncService.asyncGet1());
        Thread.sleep(1000L);
	}

	@Test
    public void testAsyncGet2() throws Exception {
        ListenableFuture<String> listenableFuture = asyncService.asyncGet2();
        SuccessCallback<String> successCallback = new SuccessCallback<String>() {
            @Override
            public void onSuccess(String str) {
                System.out.println("异步回调成功了, return : " + str);
            }
        };
        FailureCallback failureCallback = new FailureCallback() {
            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("异步回调失败了, exception message : " + throwable.getMessage());
            }
        };

        listenableFuture.addCallback(successCallback, failureCallback);
        assertEquals("123", listenableFuture.get());
    }

	/** 
	 * @param asyncService 要设置的 asyncService 
	 */
	
	public void setAsyncService(AsyncService asyncService) {
		this.asyncService = asyncService;
	}
	
}
