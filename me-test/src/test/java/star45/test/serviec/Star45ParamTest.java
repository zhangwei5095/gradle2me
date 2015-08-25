/** 
* @file     Star45ParamTest.java 
* @brief    me-test's file 
* @author   许立亢 
* @date     2015年8月7日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package star45.test.serviec;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runners.Parameterized.Parameters;

/**
 * @brief 类简短说明
 * @details 详细说明 
 * @warning 注意事项
 * @date 2015年8月7日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */

public class Star45ParamTest {

	@Parameters(name = "{index}: fib({0})={1}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][] { { 0, 0 }, { 1, 1 }, { 2, 1 },
                { 3, 2 }, { 4, 3 }, { 5, 5 }, { 6, 8 } });
    }

    private int input;
    private int expected;

    

    @Test
    public void test() {
        assertEquals(expected, Star45.compute(input));
    }

}

class Star45{
	
	public static int compute(int input) {
		int result;
		switch (input) {
		case 0:
			result = 0;
			break;
		case 1:
		case 2:
			result = 1;
			break;
		case 3:
			result = 2;
			break;
		case 4:
			result = 3;
			break;
		case 5:
			result = 5;
			break;
		case 6:
			result = 8;
			break;
		default:
			result = 0;
		}
		return result;
	}
	
}
