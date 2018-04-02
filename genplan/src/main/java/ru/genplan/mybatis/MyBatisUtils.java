package ru.genplan.mybatis;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.apache.ibatis.datasource.DataSourceFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;

public class MyBatisUtils {
	private static SqlSessionFactory sqlSessionFactory;
	private static final Properties PROPERTIES = new Properties();
	static {
		try {
			InputStream is = DataSourceFactory.class.getResourceAsStream("/application.properties");
			PROPERTIES.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static SqlSessionFactory getSqlSessionFactory() {
		if(sqlSessionFactory==null) {
			InputStream inputStream;
			try {
				inputStream = Resources.getResourceAsStream("mybatis-config.xml");
				sqlSessionFactory = new	SqlSessionFactoryBuilder().build(inputStream);
			} catch (IOException e) {
				throw new RuntimeException(e.getCause());
			}
		}
		return sqlSessionFactory;
	}
	
	public static SqlSessionFactory getTestSqlSessionFactory() {
		if(sqlSessionFactory==null) {
			InputStream inputStream;
			try {
				inputStream = Resources.getResourceAsStream("mybatis-test-config.xml");
				sqlSessionFactory = new	SqlSessionFactoryBuilder().build(inputStream);
			} catch (IOException e) {
				throw new RuntimeException(e.getCause());
			}
		}
		return sqlSessionFactory;
	}
	
	public static SqlSession getSqlSession() {
		return getSqlSessionFactory().openSession();
	}
	
	public static SqlSession getTestSqlSession() {
		return getTestSqlSessionFactory().openSession();
	}
	
	public static Connection getConnection() {
		String driver = PROPERTIES.getProperty("jdbc.driverClassName");
		String url = PROPERTIES.getProperty("jdbc.url");
		String username = PROPERTIES.getProperty("jdbc.username");
		String password = PROPERTIES.getProperty("jdbc.password");
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,username,password);
		} catch (Exception e ) {
			throw new RuntimeException();
		}
		return conn;
	}
}

