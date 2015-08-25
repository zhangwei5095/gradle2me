package framework.star45.hibernate4.core.db.page;

import java.util.List;


/**
 * 分页控制器,包含分页数据，用于com.scmmid.bs.core.db.dao中的</br>
 * JdbcDao、HibernateSimpleDao和HibernateBaseDao</br> 
 * 类名: Pagination </br> 
 * 日期: 2013-5-15 上午09:14:21 </br> 
 * @author 徐帆 
 * @version 1.0
 */
public class Pagination extends SimplePage implements java.io.Serializable, Paginable {

	private static final long serialVersionUID = 1L;

	/**
	 * 类的构造方法
	 * 创建一个新的实例 Pagination.
	 * @param pageNo 当前页码
	 * @param pageSize 每页显示条数
	 * @param totalCount 总记录数
	*/
	public Pagination(Integer pageNo, Integer pageSize, int totalCount) {
		super(pageNo, pageSize, totalCount);
	}

	/**
	 * 类的构造方法
	 * 创建一个新的实例 Pagination.
	 * @param pageNo 当前页码
	 * @param pageSize 每页显示条数
	 * @param totalCount 总记录数
	 * @param list 分页内容
	*/
	public Pagination(Integer pageNo, Integer pageSize, int totalCount, List<?> list) {
		super(pageNo, pageSize, totalCount);
		this.list = list;
	}

	/**
	 * 获取当前页第一条数据位于总记录数的位置
	 * @return 第一条数据位置
	 */
	public int getFirstResult() {
		return (pageNo - 1) * pageSize;
	}

	/**
	 * 当前页的数据
	 */
	private List<?> list;

	/**
	 * 获得分页内容
	 * @return 分页数据
	 */
	public List<?> getList() {
		return list;
	}

	/**
	 * 设置分页内容
	 * @param list 分页数据
	 */
	public void setList(List<?> list) {
		this.list = list;
	}
}
