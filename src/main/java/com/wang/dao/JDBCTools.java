package com.wang.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class JDBCTools {

	@Value("${spring.datasource.driver-class-name}")
    private String driveClassName;
	
	@Value("${spring.datasource.url}")
    private String url;
	
	@Value("${spring.datasource.username}")
    private String userName;
	
	@Value("${spring.datasource.password}")
    private String password;
	
	public Connection getConnection() throws Exception {
		Class.forName(driveClassName);
        return DriverManager.getConnection(url, userName, password);
	}
	
	public void release(Connection con , Statement state){//关闭数据库连接
        if(state != null) {
           try {
                  state.close();
           } catch (SQLException e) {
                  e.printStackTrace();
           }
        }
        if(con != null){
           try {
                  con.close();
           } catch (SQLException e) {
                  e.printStackTrace();
           }
        }
        
	}
	
	public void release(ResultSet rs , Connection con , Statement state){//关闭数据库连接
        if(rs != null) {
           try {
                  rs.close();
           } catch (SQLException e) {
                  e.printStackTrace();
           }
        }
        if(state != null){
           try {
                  state.close();
           } catch (SQLException e) {
                  e.printStackTrace();
           }
        }
        if(con != null){
           try {
                  con.close();
           } catch (SQLException e) {
                  e.printStackTrace();
           }
        }
	}
	
}
