package framework.star45.hibernate4.core.db.page;

import java.io.Serializable;
/**
 * 分页控制器，用于com.scmmid.bs.core.db.dao.BaseDao的分页操作</br> 
 * 类名: Page </br> 
 * 日期: 2013-5-14 下午06:39:15 </br> 
 * @author 徐帆 
 * @version 1.0
 */
public class Page
  implements Serializable
{
  private static final long serialVersionUID = 4329683329578284885L;
  /**
   * 默认每页记录数
   */
  public static final int DEFAULT_PAGE_SIZE = 30;
  private int page;
  private int rp;
  private int total;
  private int pageCount;

  public Page()
  {
  }

  /**
   * 获取当前页码</br>
   * 日期:2013-5-15 上午08:55:52
   * @return
   */
  public int getPage()
  {
    return this.page;
  }
  /**
   * 设置当前页码</br>
   * 日期:2013-5-15 上午08:56:06
   * @param page 页码
   */
  public void setPage(int page) {
    this.page = page;
  }
  /**
   * 设置每页记录数</br>
   * 日期:2013-5-15 上午08:56:25
   * @param rp 每页记录数
   */
  public void setRp(int rp) {
    this.rp = rp;
  }

  /**
   * 通过当前页、每页记录数构造分页控制器</br> 
   * 日期:2013-5-15 上午08:57:19
   * @param page 当前页码
   * @param rp 每页记录数
   */
  public Page(int page, int rp) {
    if (page < 0)
      page = 1;
    if (rp < 1)
      rp = 1;
    this.page = page;
    this.rp = rp;
  }

  /**
   * 通过当前页码构造分页控制器</br> 
   * 日期:2013-5-15 上午09:00:31
   * @param pageIndex 当前页码
   */
  public Page(int pageIndex) {
    this(pageIndex, DEFAULT_PAGE_SIZE);
  }
  /**
   * 通过字符串形式当前页、每页记录数参数构造分页控制器</br> 
   * 日期:2013-5-15 上午09:00:55
   * @param SpageIndex 当前页
   * @param SpageSize 每页记录数
   */
  public Page(String SpageIndex, String SpageSize) {
    int pageIndex = 1;

    int pageSize = 30;
    try {
      pageIndex = Integer.parseInt(SpageIndex);
    } catch (Exception e) {
      pageIndex = 1;
    }
    try {
      pageSize = Integer.parseInt(SpageSize);
    }
    catch (Exception localException1) {
    }
    if (pageIndex <= 0)
      pageIndex = 1;
    if (pageSize < 1)
      pageSize = 1;
    this.page = pageIndex;
    this.rp = pageSize;
  }
  /**
   * 获取每页记录数</br>
   * 日期:2013-5-15 上午08:56:52
   * @return 每页记录数
   */
  public int getRp() {
    return this.rp;
  }
  
  /**
   * 获取总页数</br>
   * 日期:2013-5-15 上午09:01:54
   * @return 总页数
   */
  public int getPageCount() { return this.pageCount; } 
  
  /**
   * 获取总记录数</br>
   * 日期:2013-5-15 上午09:03:22
   * @return 总记录数
   */
  public int getTotal() {
    return this.total;
  }
  /**
   * 获得当前页第一条记录在总记录数中的位置</br>
   * 日期:2013-5-15 上午09:02:10
   * @return
   */
  public int getFirstResult() { return (this.page - 1) * this.rp; }

  /**
   * 设置总记录数</br>
   * 日期:2013-5-15 上午09:02:53
   * @param i 总记录数
   */
  public void setTotal(int i) {
    int totalCount = i;
    if (totalCount < 0)
      totalCount = 0;
    this.total = totalCount;
    this.pageCount = (totalCount / this.rp + (totalCount % this.rp == 0 ? 0 : 1));

    if (totalCount == 0)
      this.page = 1;
    else if (this.page > this.pageCount)
      this.page = this.pageCount;
  }

  /**
   * 是否为空记录数</br>
   * 日期:2013-5-15 上午09:03:53
   * @return true 空，false 有记录
   */
  public boolean isEmpty()
  {
    return this.total == 0;
  }

  /**
   * 是否处于最后一页</br>
   * 日期:2013-5-15 上午09:04:27
   * @return true 是， false 否
   */
  public boolean isLastPage() {
    if (getPageCount() == 0) {
      return true;
    }
    return this.page == getPageCount();
  }

  /**
   * 是否处于第一页</br>
   * 日期:2013-5-15 上午09:04:58
   * @return true 是， false 否
   */
  public boolean isFirstPage() {
    return this.page <= 1;
  }
}
