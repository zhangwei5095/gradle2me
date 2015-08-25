/** 
* @file     ExceptionTest.java 
* @brief    me-test's file 
* @author   许立亢 
* @date     2015年8月7日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package star45.test.serviec;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.Timeout;

/**
 * @brief 类简短说明
 * @details 详细说明 
 * @warning 注意事项
 * @date 2015年8月7日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */

public class ExceptionTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Ignore
    @Test
    public void shouldTestExceptionMessage() throws IndexOutOfBoundsException {
        List<Object> list = new ArrayList<Object>();

        thrown.expect(IndexOutOfBoundsException.class);
        thrown.expectMessage("下标: 0, 长度: 0");
        list.get(0);
        Assert.assertEquals(1, list.get(0));
    }
    
    public static String log;

//    @Rule
//    public Timeout globalTimeout = new Timeout(1000); // 10 seconds max per method tested

    @Test
    public void testInfiniteLoop1() {
        log += "ran1";
        for (;;) {
        }
    }

    @Test
    public void testInfiniteLoop2() {
        log += "ran2";
        for (;;) {
        }
    }
    
}
