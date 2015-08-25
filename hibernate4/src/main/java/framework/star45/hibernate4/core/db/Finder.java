package framework.star45.hibernate4.core.db;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.Type;


/**
 * HQL语句查询条件组装器，支持分页，构建器模式</br> 
 * 类名: Finder </br> 
 * 日期: 2013-5-14 下午04:55:26 </br> 
 * @author 徐帆 
 * @version 1.0
 */
public class Finder {
	/**
	 * 无参构造方法</br> 
	 * 日期:2013-5-14 下午04:56:23
	 */
	protected Finder() {
		hqlBuilder = new StringBuilder();
	}

	/**
	 * 通过hql字符串构造方法</br> 
	 * 日期:2013-5-14 下午04:56:59
	 * @param hql hql语句
	 */
	protected Finder(String hql) {
		hqlBuilder = new StringBuilder(hql);
	}

	/**
	 * 创建器</br>
	 * 日期:2013-5-14 下午04:57:44
	 * @return 本身,Finder对象
	 */
	public static Finder create() {
		return new Finder();
	}

	/**
	 * 带参创建器</br>
	 * 日期:2013-5-14 下午04:58:16
	 * @param hql hql语句
	 * @return 本身,Finder对象
	 */
	public static Finder create(String hql) {
		return new Finder(hql);
	}

	/**
	 * 追加hql语句</br>
	 * 日期:2013-5-14 下午04:58:45
	 * @param hql 要追加的hql语句
	 * @return 本身,Finder对象
	 */
	public Finder append(String hql) {
		hqlBuilder.append(hql);
		return this;
	}

	/**
	 * 获得原始hql语句</br>
	 * 日期:2013-5-14 下午04:59:18
	 * @return hql语句
	 */
	public String getOrigHql() {
		return hqlBuilder.toString();
	}

	/**
	 * 获得查询数据库记录数的hql语句</br>
	 * 日期:2013-5-14 下午04:59:35
	 * @return 查询数据库记录数的hql语句
	 */
	public String getRowCountHql() {
		String hql = hqlBuilder.toString();

		int fromIndex = hql.toLowerCase().indexOf(FROM);

		hql = hql.substring(fromIndex);
		
		String rowCountHql = hql.replace(HQL_FETCH, "");
		
		int index = rowCountHql.toLowerCase().indexOf(ORDER_BY);
		
		if (index > 0) {
			rowCountHql = rowCountHql.substring(0, index);
		}
		
		String distinctPk = this.getDISTINCT_PK();
		
		if(!"".equals(distinctPk)){
			
			return " select count(DISTINCT "+distinctPk+")" + rowCountHql;
		}else{
			
			return ROW_COUNT + rowCountHql;
		}
	}

	/**
	 * 获得第一条记录位置</br>
	 * 日期:2013-5-14 下午05:00:26
	 * @return 第一条记录位置
	 */
	public int getFirstResult() {
		return firstResult;
	}

	/**
	 * 设置第一条记录位置</br>
	 * 日期:2013-5-14 下午05:01:35
	 * @param firstResult 记录位置
	 */
	public void setFirstResult(int firstResult) {
		this.firstResult = firstResult;
	}

	/**
	 * 获得查询数量</br>
	 * 日期:2013-5-14 下午05:02:00
	 * @return 查询数量
	 */
	public int getMaxResults() {
		return maxResults; 
	}

	/**
	 * 设置查询数量</br>
	 * 日期:2013-5-14 下午05:02:47
	 * @param maxResults 要设置的查询数量
	 */
	public void setMaxResults(int maxResults) {
		this.maxResults = maxResults;
	}

	/**
	 * 是否使用查询缓存</br>
	 * 日期:2013-5-14 下午05:03:39
	 * @return ture 使用 ，false 不使用
	 */
	public boolean isCacheable() {
		return cacheable;
	}

	/**
	 * 设置是否使用查询缓存</br>
	 * 日期:2013-5-14 下午05:04:53
	 * @param cacheable 要设置的值:ture 使用 ，false 不使用
	 */
	public void setCacheable(boolean cacheable) {
		this.cacheable = cacheable;
	}


	/**
	 * 设置单个参数</br>
	 * 日期:2013-5-14 下午05:05:27
	 * @param param 参数名
	 * @param value 参数值
	 * @return 本身,Finder对象
	 */
	public Finder setParam(String param, Object value) {
		return setParam(param, value, null);
	}

	/**
	 * 设置单个参数</br>
	 * 日期:2013-5-14 下午05:06:15
	 * @param param 参数名
	 * @param value 参数值
	 * @param type 参数类型
	 * @return 本身,Finder对象
	 */
	public Finder setParam(String param, Object value, Type type) {
		getParams().add(param);
		getValues().add(value);
		getTypes().add(type);
		return this;
	}


	/**
	 * 通过参数名-参数值映射列表设置多个参数</br>
	 * 日期:2013-5-14 下午05:06:46
	 * @param paramMap 参数名-参数值映射列表
	 * @return 本身,Finder对象
	 */
	public Finder setParams(Map<String, Object> paramMap) {
		for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
			setParam(entry.getKey(), entry.getValue());
		}
		return this;
	}


	/**
	 * 设置参数列表</br>
	 * 日期:2013-5-14 下午05:07:51
	 * @param name 参数名
	 * @param vals 参数集合
	 * @param type 参数类型
	 * @return 本身,Finder对象
	 */
	public Finder setParamList(String name, Collection<Object> vals, Type type) {
		getParamsList().add(name);
		getValuesList().add(vals);
		getTypesList().add(type);
		return this;
	}


	/**
	 * 设置参数列表</br>
	 * 日期:2013-5-14 下午05:10:23
	 * @param name 参数名
	 * @param vals 参数集合
	 * @return 本身,Finder对象
	 */
	public Finder setParamList(String name, Collection<Object> vals) {
		return setParamList(name, vals, null);
	}

	/**
	 * 设置参数列表</br>
	 * 日期:2013-5-14 下午05:11:01
	 * @param name 参数名
	 * @param vals 参数数组
	 * @param type 参数类型
	 * @return 本身,Finder对象
	 */
	public Finder setParamList(String name, Object[] vals, Type type) {
		getParamsArray().add(name);
		getValuesArray().add(vals);
		getTypesArray().add(type);
		return this;
	}


	/**
	 * 设置参数列表</br>
	 * 日期:2013-5-14 下午05:11:29
	 * @param name 参数名
	 * @param vals 参数数组
	 * @return 本身,Finder对象
	 */
	public Finder setParamList(String name, Object[] vals) {
		return setParamList(name, vals, null);
	}


	/**
	 * 把参数列表设置到Hibernate Query对象</br>
	 * 日期:2013-5-14 下午05:11:55
	 * @param query 要设置的Query对象
	 * @return 设置过的参数的Query对象
	 */
	public Query setParamsToQuery(Query query) {
		if (params != null) {
			for (int i = 0; i < params.size(); i++) {
				if (types.get(i) == null) {
					query.setParameter(params.get(i), values.get(i));
				} else {
					query.setParameter(params.get(i), values.get(i), types
							.get(i));
				}
			}
		}
		if (paramsList != null) {
			for (int i = 0; i < paramsList.size(); i++) {
				if (typesList.get(i) == null) {
					query
							.setParameterList(paramsList.get(i), valuesList
									.get(i));
				} else {
					query.setParameterList(paramsList.get(i),
							valuesList.get(i), typesList.get(i));
				}
			}
		}
		if (paramsArray != null) {
			for (int i = 0; i < paramsArray.size(); i++) {
				if (typesArray.get(i) == null) {
					query.setParameterList(paramsArray.get(i), valuesArray
							.get(i));
				} else {
					query.setParameterList(paramsArray.get(i), valuesArray
							.get(i), typesArray.get(i));
				}
			}
		}
		return query;
	}


	/**
	 * 用传入的hibernate Session对象创建Query对象，并设置参数</br>
	 * 日期:2013-5-14 下午05:16:21
	 * @param s hibernate Session对象
	 * @return 设置过的参数的Query对象
	 */
	public Query createQuery(Session s) {
		Query query = setParamsToQuery(s.createQuery(getOrigHql()));
		if (getFirstResult() > 0) {
			query.setFirstResult(getFirstResult());
		}
		if (getMaxResults() > 0) {
			query.setMaxResults(getMaxResults());
		}
		if (isCacheable()) {
			query.setCacheable(true);
		}
		return query;
	}

	private List<String> getParams() {
		if (params == null) {
			params = new ArrayList<String>();
		}
		return params;
	}

	private List<Object> getValues() {
		if (values == null) {
			values = new ArrayList<Object>();
		}
		return values;
	}

	private List<Type> getTypes() {
		if (types == null) {
			types = new ArrayList<Type>();
		}
		return types;
	}

	private List<String> getParamsList() {
		if (paramsList == null) {
			paramsList = new ArrayList<String>();
		}
		return paramsList;
	}

	private List<Collection<Object>> getValuesList() {
		if (valuesList == null) {
			valuesList = new ArrayList<Collection<Object>>();
		}
		return valuesList;
	}

	private List<Type> getTypesList() {
		if (typesList == null) {
			typesList = new ArrayList<Type>();
		}
		return typesList;
	}

	private List<String> getParamsArray() {
		if (paramsArray == null) {
			paramsArray = new ArrayList<String>();
		}
		return paramsArray;
	}

	private List<Object[]> getValuesArray() {
		if (valuesArray == null) {
			valuesArray = new ArrayList<Object[]>();
		}
		return valuesArray;
	}

	private List<Type> getTypesArray() {
		if (typesArray == null) {
			typesArray = new ArrayList<Type>();
		}
		return typesArray;
	}

	private StringBuilder hqlBuilder;

	private List<String> params;
	private List<Object> values;
	private List<Type> types;

	private List<String> paramsList;
	private List<Collection<Object>> valuesList;
	private List<Type> typesList;

	private List<String> paramsArray;
	private List<Object[]> valuesArray;
	private List<Type> typesArray;

	private int firstResult = 0;

	private int maxResults = 0;

	private boolean cacheable = false;

	/**
	 * 查询数量hql起始字符串,用于截取和拼接hql
	 */
	public static final String ROW_COUNT = "select count(*) ";
	/**
	 * hql from字符串,用于截取和拼接hql
	 */
	public static final String FROM = "from";
	/**
	 * hql distinct字符串,用于截取和拼接hql
	 */
	public static final String DISTINCT = "distinct";
	/**
	 * hql fetch字符串,用于截取和拼接hql
	 */
	public static final String HQL_FETCH = "fetch";
	/**
	 * hql order字符串,用于截取和拼接hql
	 */
	public static final String ORDER_BY = "order";
	
	public String DISTINCT_PK = "";
	
	/**
	 * 在sql拼接器</br>
	 * 日期:2013-5-17 下午06:01:45
	 * @param condition
	 * @param finder 
	 */ 
	public void doOrder(Map<String, Object> condition, Finder finder) {
		if(condition.get("sort")!=null){
			finder.append(" order by " +condition.get("sort")+" ");
		}
		if(condition.get("order")!=null){
			finder.append(" " + condition.get("order")+" ");
		}
	}

	public String getDISTINCT_PK() {
		return DISTINCT_PK;
	}

	public void setDISTINCT_PK(String dISTINCTPK) {
		DISTINCT_PK = dISTINCTPK;
	}
}