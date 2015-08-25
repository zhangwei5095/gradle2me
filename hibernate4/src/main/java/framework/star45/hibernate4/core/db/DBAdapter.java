/**
 * Project Name: scmcenter
 * File Name: SqlAdapter.java
 * Package : com.scm.center.db
 * Author : 徐帆
 * Date : 2013-8-12 下午04:42:07
 * Copyright (c) 2013 , xufan@sbr-info.com All Rights Reserved
 */


package framework.star45.hibernate4.core.db;


/**
 * 数据库适配器</br> 
 * 类名: SqlAdapter </br> 
 * 日期: 2013-8-12 下午04:42:07 </br> 
 * @author 徐帆 
 * @version 1.0
 */

public class DBAdapter {

	private static DBAdapter instance = null;
	
	private static final int MYSQL = 1;
	
	private static final int SQLSERVER = 2;
	
	private static final int ORACLE = 3;
	
	
	private int dialect = MYSQL;
	
	private String ip;
	
	private int port;
	
	private String dbName ;
	
	private String userName ;
	
	private String password ;
	/**
	 * 私有构造方法</br> 
	 * @author 徐帆 
	 * 日期:2013-8-17 上午10:36:16 
	 */
	private DBAdapter(int dialect,String ip,int port,String dbName,String userName,String password) {
		this.dialect = dialect;
		this.ip = ip;
		this.port = port;
		this.dbName = dbName;
		this.userName = userName;
		this.password = password;
	}
	
	/**
	 * 初始化方法</br>
	 * 日期:2013-8-17 上午10:43:28
	 * @author 徐帆 
	 * @param dialect
	 * @param ip
	 * @param port
	 * @param dbName
	 */
	public static void init(int dialect,String ip,int port,String dbName,String userName,String password)
	{
		instance = new DBAdapter(dialect ,ip ,port ,dbName ,userName ,password);
	}
	
	/**
	 * 获取实例</br>
	 * @author 徐帆 
	 * 日期:2013-8-17 上午10:48:13
	 * @return
	 */
	public static DBAdapter getInstance()
	{
		if(instance == null)
			throw new RuntimeException("instance is not init!");
		return instance;
	}
	
	
	public int getDialect() {
		return dialect;
	}

	public String getDialect4Hibernate() {
		switch (dialect) {
		case MYSQL:
			return "org.hibernate.dialect.MySQLDialect";//MySQL with InnoDB:org.hibernate.dialect.MySQLInnoDBDialect
		case SQLSERVER:
			//return "org.hibernate.dialect.SQLServerDialect";
			
			return "org.hibernate.dialect.SQLServerDialect";
		case ORACLE:
			return "org.hibernate.dialect.Oracle9Dialect";//Oracle (any version):org.hibernate.dialect.OracleDialect
		default:
			throw new RuntimeException("default dialect is not support!");
		}
	}
	
	public String getIp() {
		return ip;
	}

	public int getPort() {
		return port;
	}

	public String getDbName() {
		return dbName;
	}

	
	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	/**
	 * 获取链接url</br>
	 * 日期:2013-8-17 上午10:48:30
	 * @author 徐帆 
	 * @return
	 */
	public String getURL()
	{
		switch (dialect) {
		case MYSQL:
			return "jdbc:mysql://" + ip + ":" + port + "/" + dbName 
					+"?useUnicode=true&characterEncoding=utf8&mysqlEncoding=utf8";
		case SQLSERVER:
			//return "jdbc:microsoft:sqlserver://" + ip + ":" + port + ";DatabaseName=" + dbName;
			return "jdbc:sqlserver://" + ip + ":" + port + ";DatabaseName=" + dbName;
		case ORACLE:
			return "jdbc:oracle:thin:@" + ip + ":" + port + ":orcl";
		default:
			throw new RuntimeException("default dialect is not support!");
		}
	}
	/**
	 * 获取DriverClass</br>
	 * 日期:2013-8-17 下午02:07:34
	 * @author 徐帆
	 * @return DriverClass
	 */
	public String getDriverClass()
	{
		switch (dialect) {
		case MYSQL:
			return "com.mysql.jdbc.Driver";
		case SQLSERVER:
			return "com.microsoft.sqlserver.jdbc.SQLServerDriver";//2000版本com.microsoft.jdbc.sqlserver.SQLServerDriver
			//return "com.microsoft.jdbc.sqlserver.SQLServerDriver";
		case ORACLE:
			return "oracle.jdbc.driver.OracleDriver";
		default:
			throw new RuntimeException("default dialect is not support!");
		}
	}
	
	/**
	 * **********************************************以下是sql适配转换************************************
	 */
	

	
	/**
	 * 日期转换器</br>
	 * @author 徐帆 
	 * 日期:2013-8-17 上午10:42:31
	 * @param dataStr
	 * @return
	 */
	public String date(String dataStr){
		switch (dialect) {
		case MYSQL:
			return "'"+dataStr+"'";
		case SQLSERVER:
			return "'"+dataStr+"'";
		case ORACLE:
			throw new RuntimeException("ORACLE dialect is not support!");
		default:
			throw new RuntimeException("default dialect is not support!");
		}
	}
	

	
	private static final String ASC = "asc";
	private static final String DESC = "desc";
	private static final String ORDER = "order";
	private static final String BY = "by";
	
	private static final String INNER_TABLE = "my_table";
	private static final String MID_TABLE = "tempT1";
	private static final String OUTTER_TABLE = "tempT2";
	
	/**
	 * 分页适配方法</br>
	 * 日期:2013-12-13 下午01:08:41
	 * @param sql
	 * @param firstResult
	 * @param pageSize
	 * @param pageNum
	 * @param totalCount
	 * @return
	 */
	public String queryForPageSql(String sql, int firstResult, int pageSize, int pageNum, int totalCount ){
		switch (dialect) {
		case MYSQL:
			return  "SELECT * FROM ( "
					+ "    SELECT my_table.*  FROM ( " + sql
					+ "    ) my_table limit " + firstResult +","+pageSize
					+ ") abc ";
		case SQLSERVER:
			
			//pageNum最小值为1 （导出时pageNum为0，这样导不出来数据）
			if(0 == pageNum){
				pageNum = 1;
			}
			
			
			String [] order = parseOrder(sql);
			if(order == null)
				throw new RuntimeException("sql server page need a order!");

			String realAsc =  parseSort(sql);//实际顺序
			String maskAsc = DESC;//为了分页功能的顺序
			if(DESC.equals(realAsc))
				maskAsc = ASC;
			
			boolean isLastPage = false;
			int pageCount = totalCount/pageSize; //总页数
			if(totalCount%pageSize > 0){
				pageCount = pageCount+1;
			}
			if(pageCount == pageNum){
				isLastPage = true;
			}
			int topNum = pageSize;//要获取的记录数
			if(isLastPage){
				if(totalCount == pageSize){
					topNum = pageSize;
				}else{
					topNum = totalCount%pageSize;
				}
			}
			//去除order
			sql = sql.toLowerCase();
			int orderIndex = sql.indexOf(ORDER);
			if (orderIndex > 0) {
				sql = sql.substring(0, orderIndex);
			}
			
			return 
			"SELECT * FROM ( "
			+ "    SELECT top "+topNum+" *  FROM ( select top " + pageSize*pageNum + " * from  (" + sql
			+ "    ) " + INNER_TABLE + " order by " + makeOrderStr(order,INNER_TABLE) + " " + realAsc
			+ " ) as " + MID_TABLE + " order by " + makeOrderStr(order,MID_TABLE) + " " + maskAsc
			+ ") as " + OUTTER_TABLE + " order by " + makeOrderStr(order,OUTTER_TABLE) + " " + realAsc;
		case ORACLE:
			throw new RuntimeException("ORACLE dialect is not support!");
		default:
			throw new RuntimeException("default dialect is not support!");
		}
	}

	/**
	 * 通过表别名组装多个排序字段</br>
	 * 日期:2013-12-13 下午04:09:09
	 * @param order
	 * @param tableName
	 * @return
	 */
	private String makeOrderStr(String[] order, String tableName) {
		StringBuilder orderStr = new StringBuilder();
		if(order != null)
		{
			int i = 1;
			for(String itemOrder : order)
			{
				orderStr.append(tableName);
				orderStr.append(".");
				orderStr.append(itemOrder);
				if(i < order.length)
					orderStr.append(",");
				i++;
			}
		}
		return orderStr.toString();
	}

	/**
	 * 获取排序字段</br>
	 * 日期:2013-12-13 上午11:45:14
	 * @param sql
	 * @return 
	 */ 
	private String [] parseOrder(String sql) {
		String [] orderArray = null;
		String order = null;
		sql = sql.toLowerCase();
		int index = sql.lastIndexOf(ORDER);
		if (index > 0) {
			order = sql.substring(index + ORDER.length(), sql.length());
			int byIndex = order.lastIndexOf(BY);
			order = order.substring(byIndex + BY.length(), order.length());
			
			int descIndex = order.lastIndexOf(DESC);
			if(descIndex > 0)
				order = order.substring(0, descIndex);
			
			int ascIndex = order.lastIndexOf(ASC);
			if(ascIndex > 0)
				order = order.substring(0, ascIndex);
			
			orderArray = order.split(",");
			for(int i = 0 ; i<orderArray.length ; i++)
			{
				orderArray[i] = orderArray[i].trim();
				int signIndex = orderArray[i].lastIndexOf(".");
				if(signIndex > 0)
					orderArray[i] = orderArray[i].substring(signIndex+".".length(), orderArray[i].length());
				
			}
		}
		
		return orderArray;
	}
	
	/**
	 * 获取排序顺序</br>
	 * 日期:2013-12-13 上午11:45:16
	 * @param sql
	 * @return 
	 */ 
	private String parseSort(String sql) {
		String sort = null;
		sql = sql.toLowerCase();
		int index = sql.lastIndexOf(ORDER);
		if (index > 0) {
			sort = sql.substring(index, sql.length());
		}
		if(sort.contains(DESC))
			return DESC;
		else
			return ASC;
	}

	public static void main(String [] args)
	{
		String order = "fda1, ";
		String [] orderArray = null;
		orderArray = order.split(",");
		for(String str : orderArray)
		{
			System.out.println("o:"+str);
		}
		
	}

}
