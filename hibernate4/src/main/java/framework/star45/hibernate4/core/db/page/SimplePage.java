package framework.star45.hibernate4.core.db.page;

/**
 * 简单分页控制器，不包含分页数据</br> 
 * 类名: SimplePage </br> 
 * 日期: 2013-5-15 上午09:07:43 </br> 
 * @author 徐帆 
 * @version 1.0
 */
public class SimplePage implements Paginable {
	
	private static final long serialVersionUID = 1L;
	/**
	 * 默认每页显示记录数
	 */
	public static final Integer PAGE_SIZE = 15;

	/**
	 * 构造方法
	 * 创建一个新的实例 SimplePage.
	 * @param pageNo 当前页码
	 * @param pageSize 每页显示条数
	 * @param totalCount 总记录数
	*/
	public SimplePage(Integer pageNo, Integer pageSize, int totalCount) {
		setTotalCount(totalCount);
		setPageSize(pageSize);
		setPageNo(pageNo);
		adjustPageNo();
	}

	/**
	 * 方法描述：调整页码，使不超过最大页数
	 */
	public void adjustPageNo() {
		if (pageNo == 1) {
			return;
		}
		int tp = getTotalPage();
		if (pageNo > tp) {
			pageNo = tp;
		}
	}

	/**
	 * 获取当前页码</br> 
	 * @return 当前页码
	 * @see framework.star45.hibernate4.core.db.page.Paginable#getPageNo()
	 */
	public int getPageNo() {
		return pageNo;
	}

	/**
	 * 获取每页记录数</br> 
	 * @return 每页记录数
	 * @see framework.star45.hibernate4.core.db.page.Paginable#getPageSize()
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 获取总记录数</br> 
	 * @return 总记录数
	 * @see framework.star45.hibernate4.core.db.page.Paginable#getTotalCount()
	 */
	public int getTotalCount() {
		return totalCount;
	}

	/**
	 * 获取总页数</br> 
	 * @return 总页数
	 * @see framework.star45.hibernate4.core.db.page.Paginable#getTotalPage()
	 */
	public int getTotalPage() {
		int totalPage = totalCount / pageSize;
		if (totalPage == 0 || totalCount % pageSize != 0) {
			totalPage++;
		}
		return totalPage;
	}
	
	/**
	 * 判断是否是第一页</br> 
	 * @return true 是 ，false 否
	 * @see framework.star45.hibernate4.core.db.page.Paginable#isFirstPage()
	 */
	public boolean isFirstPage() {
		return pageNo <= 1;
	}

	/**
	 * 判断是否是最后一页</br> 
	 * @return true 是 ，false 否
	 * @see framework.star45.hibernate4.core.db.page.Paginable#isLastPage()
	 */
	public boolean isLastPage() {
		return pageNo >= getTotalPage();
	}

	/**
	 * 获取下一页页码</br> 
	 * @return 下一页页码
	 * @see framework.star45.hibernate4.core.db.page.Paginable#getNextPage()
	 */
	public int getNextPage() {
		if (isLastPage()) {
			return pageNo;
		} else {
			return pageNo + 1;
		}
	}

	/**
	 * 获取上一页页码</br> 
	 * 日期:2013-5-15 上午09:11:48
	 * @return 上一页页码
	 * @see framework.star45.hibernate4.core.db.page.Paginable#getPrePage()
	 */
	public int getPrePage() {
		if (isFirstPage()) {
			return pageNo;
		} else {
			return pageNo - 1;
		}
	}

	protected int totalCount = 0;
	protected int pageSize = PAGE_SIZE;
	protected int pageNo = 1;

	/**
	 * 设置总记录数,if totalCount<0 then totalCount=0</br>
	 * @param totalCount 总记录数
	 */
	public void setTotalCount(int totalCount) {
		if (totalCount < 0) {
			this.totalCount = 0;
		} else {
			this.totalCount = totalCount;
		}
	}

	/**
	 * 设置总页数：if pageSize< 1 then pageSize=DEF_COUNT</br>
	 * 日期:2013-5-15 上午09:13:03
	 * @param pageSize 总页数
	 */
	public void setPageSize(Integer pageSize) {
		if (pageSize == null || pageSize < 1) {
			this.pageSize = PAGE_SIZE;
		} else {
			this.pageSize = pageSize;
		}
	}

	/**
	 * 设置当前页码 ：if pageNo < 1 then pageNo=1
	 * @param pageNo 当前页码
	 */
	public void setPageNo(Integer pageNo) {
		if (pageNo == null || pageNo < 1) {
			this.pageNo = 1;
		} else {
			this.pageNo = pageNo;
		}
	}
}
