package framework.star45.hibernate4.core.db.dao;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.util.Assert;

import framework.star45.hibernate4.core.db.DBAdapter;
import framework.star45.hibernate4.core.db.SessionFactoryProxy;
import framework.star45.hibernate4.core.db.page.Pagination;

/**
 * 基本jdbc dao，提供基于标准sql的查询方法 </br> 
 * 类名: JdbcDao </br> 
 * 日期: 2013-5-14 下午06:33:46 </br> 
 * @author 徐帆 
 * @version 1.0
 */
public abstract class JdbcDao implements InitializingBean {
	protected Logger log = Logger.getLogger(getClass());
	
	private SessionFactory sessionFactory;
	
	/**
	 * spring容器注入的 SessionFactory</br>
	 * 日期:2013-5-14 下午06:33:59
	 * @param sessionFactory hibernate SessionFactory对象
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected NamedParameterJdbcTemplate namedJdbcTemplate;
	
	protected JdbcTemplate jdbcTemplate;
	
	/**
	 * spring 容器加载完毕该对象，并设置属性后调用.用于设置JdbcTemplate对象</br> 
	 * 日期:2013-5-14 下午06:34:44
	 * @throws Exception spring运行时异常
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.isTrue(sessionFactory instanceof SessionFactoryProxy);
		
		namedJdbcTemplate = new NamedParameterJdbcTemplate(((SessionFactoryProxy)sessionFactory).getDataSource());
		jdbcTemplate = (JdbcTemplate)namedJdbcTemplate.getJdbcOperations();
	}
	
	/**
	 * 
	 * 不带参数的分页查询
	 * @param sql sql语句
	 * @param pageSize 每页条数
	 * @param pageNo 当前页码
	 * @return 分页信息，其中包含有数据列表:List<Map<String, Object>>
	 */
	public Pagination queryForPage(String sql, Integer pageSize, Integer pageNo) {
		return queryForPage(sql, pageSize, pageNo, null);
	}
	
	/**
	 * 带参数的分页查询
	 * @param sql sql语句
	 * @param pageSize 每页条数
	 * @param pageNo 当前页码
	 * @param paramSource 查询条件参数
	 * @return 分页信息，其中包含有数据列表:List<Map<String, Object>>
	 */
	@SuppressWarnings("unchecked")
	public Pagination queryForPage(String sql, Integer pageSize, Integer pageNo, MapSqlParameterSource paramSource) {
		int totalCount = namedJdbcTemplate.queryForInt(getRowCountSql(sql), paramSource);
		Pagination p = new Pagination(pageNo, pageSize, totalCount);
		if (totalCount < 1) {
			p.setList(new ArrayList());
			return p;
		}
		
		sql = "SELECT * FROM ( "
			+ "    SELECT my_table.*, rownum  my_rownum FROM ( " + sql
			+ "    ) my_table WHERE rownum <= " + (p.getPageSize() * p.getPageNo())
			+ ") WHERE my_rownum > " + p.getFirstResult();

	
		
		p.setList(namedJdbcTemplate.queryForList(sql, paramSource));
		return p;
	}
	
	/**
	 * 
	 * 获得查询数据库记录数的sql语句
	 * @param sql sql语句
	 * @return 记录数量
	 
	private String getRowCountSql(String sql) {
		int fromIndex = sql.toLowerCase().indexOf("from");
		String rowCountSql = sql.substring(fromIndex);
		
		int index = rowCountSql.indexOf("order");
		if (index > 0) {
			rowCountSql = rowCountSql.substring(0, index);
		}
		return " select count(*) " + rowCountSql;
	}
	*/
	
	/**
	 * 
	 * 获得查询数据库记录数的sql语句
	 * @param sql sql语句
	 * @return 记录数量
	 */
	private String getRowCountSql(String sql) {
		String rowCountSql = sql.toLowerCase();
		int index = rowCountSql.indexOf("order");
		if (index > 0) {
			rowCountSql = rowCountSql.substring(0, index);
		}
		return " select count(*) from (" + rowCountSql + ") tempT ";
	}
	
	protected SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	

	
	/**
	 * 
	 * 新增带有rowMapper的分页查询
	 * @param sql 查询sql语句
	 * @param params 参数列表
	 * @param rowMapper jdbc结果集跟bean的映射器
	 * @param pageSize 每页记录数
	 * @param pageNo 当前页数
	 * @return 分页对象，里面包含分页数据和分页控制器等信息
	 
	@SuppressWarnings("unchecked")
	public Pagination queryForPageHaveRowMapper(String sql,Object[] params,RowMapper<?> rowMapper,Integer pageSize, Integer pageNo) {
		int totalCount = jdbcTemplate.queryForInt(getRowCountSql(sql), params);
		Pagination p = new Pagination(pageNo, pageSize, totalCount);
		if (totalCount < 1) {
			p.setList(new ArrayList());
			return p;
		}
		sql = "SELECT * FROM ( "
			+ "    SELECT my_table.*, rownum  my_rownum FROM ( " + sql
			+ "    ) my_table WHERE rownum <= " + (p.getPageSize() * p.getPageNo())
			+ ") WHERE my_rownum > " + p.getFirstResult();
		p.setList(jdbcTemplate.query(sql, params,rowMapper));
		return p;
	}
	 */
	
	/**
	 * 
	 * 新增带有rowMapper的分页查询 ---MYSQL
	 * @param sql 查询sql语句
	 * @param params 参数列表
	 * @param rowMapper jdbc结果集跟bean的映射器
	 * @param pageSize 每页记录数
	 * @param pageNo 当前页数
	 * @return 分页对象，里面包含分页数据和分页控制器等信息
	 */
	@SuppressWarnings("unchecked")
	public Pagination queryForPageHaveRowMapper(String sql,Object[] params,RowMapper<?> rowMapper,Integer pageSize, Integer pageNo) {
		
		String sqlRowCount = getRowCountSql(sql);
		int totalCount = jdbcTemplate.queryForInt(sqlRowCount, params);
		Pagination p = new Pagination(pageNo, pageSize, totalCount);
		if (totalCount < 1) {
			p.setList(new ArrayList());
			return p;
		}
		String sqlExec = DBAdapter.getInstance().queryForPageSql(sql, p.getFirstResult(), pageSize, pageNo, totalCount);
		
		p.setList(jdbcTemplate.query(sqlExec, params, rowMapper));
		return p;
	}
	
	
	/**
	 * 根据sql查询返回SqlRowSet对象</br>
	 * 日期:2013-5-14 下午06:37:20
	 * @param sql sql语句
	 * @return SqlRowSet对象
	 */
	public SqlRowSet query(String sql) {
		return jdbcTemplate.queryForRowSet(sql);
	}
}
