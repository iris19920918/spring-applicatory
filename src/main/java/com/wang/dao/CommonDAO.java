//package com.wang.dao;
//
//import com.hikvision.util.SalzburgUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.collections.CollectionUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import java.sql.*;
//import java.util.*;
//import java.util.Map.Entry;
//
//@Slf4j
//@Repository
//public class CommonDAO {
//
//	@Autowired
//	private JDBCTools JDBCTools;
//
//	/**
//	 * 根据sql返回结果列表
//	 * @param sql
//	 * @return
//	 */
//	public List<Map<String, Object>> get(String sql) throws Exception {
//		Connection conn = null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//
//		try {
//			//建立连接
//			conn = JDBCTools.getConnection();
//			//创建语句
//			ps = conn.prepareStatement(sql);
//			//返回结果
//			List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
//			//执行查询
//			if(ps.execute()) {
//				rs = ps.getResultSet();
//				while(rs.next()) {
//					//遍历结果集的元数据类型
//					Map<String, Object> m = new HashMap<String, Object>();
//					ResultSetMetaData metaData = rs.getMetaData();
//					for(int i = 1; i <= metaData.getColumnCount(); i++) {
//						if(rs.getObject(i) !=null) {
//							if(rs.getObject(i).toString().contains("&quot;")) {
//								m.put(metaData.getColumnLabel(i), rs.getObject(i).toString().replaceAll("&quot;", "\""));
//							}else {
//								m.put(metaData.getColumnLabel(i), rs.getObject(i));
//							}
//						}else {
//							m.put(metaData.getColumnLabel(i), rs.getObject(i));
//						}
//					}
//					if(CollectionUtils.isNotEmpty(m.values())) {
//						result.add(m);
//					}
//				}
//			}
//			return result;
//		}catch(SQLException e){
//			log.error("查询语句有误SQL："+sql, e);
//			throw e;
//		}finally {
//			JDBCTools.release(rs, conn, ps);
//		}
//
//	}
//
//	/**
//	 * 保存数据，返回主键
//	 * @param sql	SQL语句
//	 * @return
//	 * @throws Exception
//	 */
//	public List<Map<String, Object>> insert(String sql) throws Exception {
//		Connection conn = null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//
//		try {
//			//建立连接
//			conn = JDBCTools.getConnection();
//			//通过传入第二个参数,就会产生主键返回给我们
//			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//			ps.executeUpdate();
//
//			//获取主键
//			rs = ps.getGeneratedKeys();
//			//主键
//			int id = 0;
//			if(rs.next()) {
//				id = rs.getInt(1);
//			}
//			//返回结果
//			List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
//			Map<String, Object> m = new HashMap<String, Object>();
//			m.put("id", id);
//			result.add(m);
//			return result;
//		}catch(SQLException e){
//			log.error("新增语句有误SQL："+sql, e);
//			throw e;
//		}finally {
//			JDBCTools.release(rs, conn, ps);
//		}
//	}
//
//	/**
//	 * 保存数据，返回主键
//	 * @param tableName		表名
//	 * @param jsonData		json数据格式的数据
//	 * @return
//	 * @throws Exception
//	 */
//	public List<Map<String, Object>> insert(String tableName, String jsonData) throws Exception {
//		Connection conn = null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//
//		String sql = null;
//		try {
//			//建立连接
//			conn = JDBCTools.getConnection();
//			//通过传入第二个参数,就会产生主键返回给我们
//			sql = SalzburgUtil.formatInsertSQL(tableName, jsonData);
//			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//			ps.executeUpdate();
//
//			//获取主键
//			rs = ps.getGeneratedKeys();
//			//主键
//			int id = 0;
//			if(rs.next()) {
//				id = rs.getInt(1);
//			}
//			//返回结果
//			List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
//			Map<String, Object> m = new HashMap<String, Object>();
//			m.put("id", id);
//			result.add(m);
//			return result;
//		}catch(SQLException e){
//			log.error("新增语句有误SQL："+sql, e);
//
//			throw e;
//		}finally {
//			JDBCTools.release(rs, conn, ps);
//		}
//	}
//
//	/**
//	 * 更新数据
//	 * @param sql
//	 * @return
//	 * @throws Exception
//	 */
//	public int update(String sql) throws Exception {
//		Connection conn = null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//
//		try {
//			//建立连接
//			conn = JDBCTools.getConnection();
//			//执行sql语句
//			ps = conn.prepareStatement(sql);
//			return ps.executeUpdate();
//		}catch(SQLException e){
//			log.error("更新语句有误SQL："+sql, e);
//
//			throw e;
//		}finally {
//			JDBCTools.release(rs, conn, ps);
//		}
//	}
//
//	/**
//	 * 多个表更新
//	 * @param tableName
//	 * @param sqls
//	 * @return
//	 * @throws Exception
//	 */
//	public int updateMuchTable(List<String> tableNames, List<String> sqls) throws Exception{
//		Connection conn = null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//
//		int result = -1;
//		try {
//			//建立连接
//			conn = JDBCTools.getConnection();
//			int count = 0;
//			//执行sql语句
//			conn.setAutoCommit(false);
//			for(int i=0; i< tableNames.size(); i++) {
//				try{
//					ps = conn.prepareStatement(SalzburgUtil.formatUpdateSQL(tableNames.get(i), sqls.get(i)));
//				    result = ps.executeUpdate();
//				}finally {
//					 if(ps != null){
//			            try {
//			                  ps.close();
//			            } catch (SQLException e) {
//			                  e.printStackTrace();
//			            }
//				     }
//				}
//
//			    if(result == -1) {
//			    	count = -1;
//			    	break;
//			    }
//			    count +=result;
//			}
//			if(result != -1) {
//				conn.commit();
//			}
//			return count;
//		}catch(SQLException e) {
//			log.error("更新语句有误SQL"+sqls, e);
//
//			conn.rollback();
//			throw e;
//		}catch(Exception e){
//			conn.rollback();
//			throw e;
//		}finally {
//			JDBCTools.release(rs, conn, ps);
//		}
//	}
//
//	/**
//	 * 更新数据
//	 * @param tableName
//	 * @param jsonData
//	 * @return
//	 * @throws Exception
//	 */
//	public int update(String tableName, String jsonData) throws Exception {
//		Connection conn = null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		String sql = null;
//		try {
//			//建立连接
//			conn = JDBCTools.getConnection();
//			//执行sql语句
//			sql = SalzburgUtil.formatUpdateSQL(tableName, jsonData);
//			ps = conn.prepareStatement(sql);
//			return ps.executeUpdate();
//		}catch(SQLException e){
//			log.error("更新sql异常：" + sql, e);
//
//			throw e;
//		}finally {
//			JDBCTools.release(rs, conn, ps);
//		}
//	}
//
//	/**
//	 * 更新file字段
//	 * @param tableName
//	 * @param data
//	 * @return
//	 * @throws Exception
//	 */
//	public int updateFileColumn(String tableName, Map<String, Object> data) throws Exception {
//		Connection conn = null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//
//		try {
//			//建立连接
//			conn = JDBCTools.getConnection();
//
//			int num = -1;
//			StringBuilder sql = new StringBuilder();
//			sql.append("update ").append(tableName).append(" set ");
//			StringBuilder where = new StringBuilder();
//
//			data = new LinkedHashMap(data);
//			Set<Entry<String, Object>> dataSet = data.entrySet();
//			if(CollectionUtils.isEmpty(dataSet)) {
//				return num;
//			}
//			if(!data.containsKey("id")) {
//				return num;
//			}
//
//			for(Entry<String, Object> entry : data.entrySet()) {
//				if(entry.getKey().equalsIgnoreCase("id")) {
//					where.append(" where id = ").append(entry.getValue());
//					continue;
//				}
//				sql.append(entry.getKey()).append("=?").append(",");
//			}
//			sql.deleteCharAt(sql.lastIndexOf(","));
//			sql.append(where.toString());
//
//			//执行sql语句
//			ps = conn.prepareStatement(sql.toString());
//
//			int i = 0;
//			for(Entry<String, Object> entry : data.entrySet()) {
//				if(entry.getKey().equalsIgnoreCase("id")) {
//					continue;
//				}
//				i++;
//				ps.setBytes(i, (byte[])entry.getValue());
//			}
//
//			return ps.executeUpdate();
//		}finally {
//			JDBCTools.release(rs, conn, ps);
//		}
//	}
//
//	/**
//	 * 删除数据
//	 * @param sql
//	 * @return
//	 * @throws Exception
//	 */
//	public int delete(String sql) throws Exception {
//		Connection conn = null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//
//		try {
//			//建立连接
//			conn = JDBCTools.getConnection();
//			//执行sql语句
//			ps = conn.prepareStatement(sql);
//			return ps.executeUpdate();
//		}catch(SQLException e){
//			log.error("删除语句有误SQL：" + sql, e);
//
//			throw e;
//		}finally {
//			JDBCTools.release(rs, conn, ps);
//		}
//	}
//	/**
//	 * 多条数据一起删除
//	 * @param sqls
//	 * @return
//	 * @throws Exception
//	 */
//	public int delete(List<String> sqls) throws Exception {
//		Connection conn = null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//
//		try {
//			//建立连接
//			conn = JDBCTools.getConnection();
//			int count = 0;
//			int result = 0;
//			conn.setAutoCommit(false);
//			//执行sql语句
//			for(int i=0; i< sqls.size(); i++) {
//				try{
//					ps = conn.prepareStatement(sqls.get(i));
//					result = ps.executeUpdate();
//					count += result;
//				}finally {
//					 if(ps != null){
//			            try {
//			                  ps.close();
//			            } catch (SQLException e) {
//			                  e.printStackTrace();
//			            }
//				     }
//				}
//
//			}
//			conn.commit();
//			return count;
//		}catch(SQLException e){
//			log.error("删除语句有误SQL："+ sqls);
//			conn.rollback();
//			throw e;
//		}catch(Exception e){
//			conn.rollback();
//			throw e;
//		}finally {
//			JDBCTools.release(rs, conn, null);
//		}
//	}
//
//	/**
//	 * 删除数据
//	 * @param tableName
//	 * @param jsonData
//	 * @return
//	 * @throws Exception
//	 */
//	public int delete(String tableName, String jsonData) throws Exception {
//		Connection conn = null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//
//		String sql = null;
//		try {
//			//建立连接
//			conn = JDBCTools.getConnection();
//			//执行sql语句
//			ps = conn.prepareStatement(SalzburgUtil.formatDeleteSQL(tableName, jsonData));
//			return ps.executeUpdate();
//		}catch(SQLException e){
//			log.error("删除语句有误SQL：" + sql, e);
//			throw e;
//		}finally {
//			JDBCTools.release(rs, conn, ps);
//		}
//	}
//
//	/**
//	 * 调用无返回值的存储过程
//	 * @param sql example: {call proc_test(1,2,3)}
//	 * @throws Exception
//	 */
//	public void callNoReturnProc(String sql) throws Exception {
//		Connection conn = null;
//		CallableStatement cs = null;
//
//		try {
//			//建立连接
//			conn = JDBCTools.getConnection();
//			//执行sql语句
//			cs = conn.prepareCall(sql);
//			cs.execute();
//		}catch(SQLException e){
//			log.error("执行存储过程有误SQL：" + sql, e);
//			throw e;
//		}finally {
//			JDBCTools.release(conn, cs);
//		}
//	}
//
//	/**
//	 * 调用带返回值的存储过程
//	 * @param sql				example:"{call test(1,2,?)}"
//	 * @param pos
//	 * @param sqlType
//	 * example callReturnProc("{call test(1,2,?)}", 1, Types.INTEGER)
//	 * @return
//	 * @throws Exception
//	 */
//	public Object callReturnProc(String sql, int pos, int sqlType) throws Exception {
//		Connection conn = null;
//		CallableStatement cs = null;
//
//		try {
//			//建立连接
//			conn = JDBCTools.getConnection();
//			//执行sql语句
//			cs = conn.prepareCall(sql);
//			cs.registerOutParameter(pos, sqlType);
//			cs.execute();
//
//			//获取返回值
//			return cs.getObject(pos);
//		}catch(SQLException e){
//			log.error("执行存储过程有误SQL：" + sql, e);
//			throw e;
//		}finally {
//			JDBCTools.release(conn, cs);
//		}
//	}
//
//}
