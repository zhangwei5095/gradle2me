package framework.star45.hibernate4.core.db.page;

/**
 * 分页接口</br> 
 * 类名: Paginable </br> 
 * 日期: 2013-5-15 上午09:06:10 </br> 
 * @author 徐帆 
 * @version 1.0
 */
public interface Paginable {
	/**
	 * 方法描述：获得总记录数
	 * @return 总记录数
	 */
	public int getTotalCount();

	/**
	 * 
	 * 方法描述：获得总页数
	 * @return 总页数
	 */
	public int getTotalPage();

	/**
	 * 方法描述：获得每页记录数
	 * @return 每页记录数
	 */
	public int getPageSize();

	/**
	 * 方法描述：获得当前页号
	 * @return 当前页号
	 */
	public int getPageNo();

	/**
	 * 方法描述：判断是否是第一页
	 * @return true 是 ，false 否
	 */
	public boolean isFirstPage();

	/**
	 * 方法描述：判断是否是最后一页
	 * @return true 是 ，false 否
	 */
	public boolean isLastPage();

	/**
	 * 方法描述：返回下页的页号
	 * @return 下页的页号
	 */
	public int getNextPage();

	/**
	 * 
	 * 方法描述：返回上页的页号
	 * @return 上页的页号
	 */
	public int getPrePage();
}
