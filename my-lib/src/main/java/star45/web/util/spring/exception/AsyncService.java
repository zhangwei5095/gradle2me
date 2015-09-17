/** 
* @file     AsyncServiec.java 
* @brief    my-lib's file 
* @author   许立亢 
* @date     2015年8月25日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package star45.web.util.spring.exception;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * @brief 类简短说明
 * @details 详细说明 
 * @warning 注意事项
 * @date 2015年8月25日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */
@Service
@Async
public class AsyncService {
	public void throwException() {
        throw new RuntimeException("error");
    }

    public String asyncGet1() {
        return "123";
    }

    //返回值必须是ListenableFuture/Future，因为是实现的问题（return ((AsyncListenableTaskExecutor) executor).submitListenable(task);）
    public ListenableFuture<String> asyncGet2() {
        return new AsyncResult<String>("123");
    }
}
