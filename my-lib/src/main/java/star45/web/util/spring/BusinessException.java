/** 
* @file     BusinessException.java 
* @brief    my-lib's file 
* @author   许立亢 
* @date     2015年8月20日
* @par Copyright (c) 2015 , xulikang@sbr-info.com All Rights Reserved
*/ 

package star45.web.util.spring;

/**
 * @brief 自定义业务异常处理类    友好提示 
 * @details 详细说明 
 * @warning 注意事项
 * @date 2015年8月20日
 * @author 许立亢 
 * @version 1.0
 * @ingroup g_scmcc_power_model
 */

public class BusinessException extends RuntimeException {
	private static final long serialVersionUID = 3152616724785436891L;  
	  
    public BusinessException(String frdMessage)  
    {  
        super(createFriendlyErrMsg(frdMessage));  
    }  
  
    public BusinessException(Throwable throwable)  
    {  
        super(throwable);  
    }  
  
    public BusinessException(Throwable throwable, String frdMessage)  
    {  
        super(throwable);  
    }  
  
    private static String createFriendlyErrMsg(String msgBody)  
    {  
        String prefixStr = "抱歉，";  
        String suffixStr = " 请稍后再试或与管理员联系！";  
  
        StringBuffer friendlyErrMsg = new StringBuffer("");  
  
        friendlyErrMsg.append(prefixStr);  
  
        friendlyErrMsg.append(msgBody);  
  
        friendlyErrMsg.append(suffixStr);  
  
        return friendlyErrMsg.toString();  
    }  
}
