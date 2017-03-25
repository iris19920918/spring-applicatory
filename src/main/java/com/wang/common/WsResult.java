package com.wang.common;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WsResult implements Serializable{

	private static final long serialVersionUID = 6067293224506425116L;

	private Integer errorCode = WStatus.SUCCESS.getCode();
	
	private String errorMessage;
	
	private Object data;
	
	private List<Map<String,Object>> results = new ArrayList<Map<String,Object>>();

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	public List<Map<String, Object>> getResults() {
		return results;
	}

	public void setResults(List<Map<String, Object>> results) {
		this.results = results;
	}

	public boolean hasError(){
		return errorCode!=9000;
	}
	
	public void addData(Map<String,Object> data){
		results.add(data);
	}
	@SuppressWarnings({"all"})
	public void addData(String key,Object value){
		if(null==data){
			data = new HashMap<String, Object>();
		}
		if(data instanceof Map){
			Map<String,Object> m = (Map)data;
			m.put(key, value);
		}		
	}
	
	@SuppressWarnings({"all"})
	public Object getDataValue(String key){
		if(null!=data && data instanceof Map){
			Map<String,Object> m = (Map)data;
			return m.get(key);
		}
		return null;
	}
	
	@SuppressWarnings({"unchecked","rawtypes"})
	public void putData(Map<String,Object> in){
		if(null==data){
			data = new HashMap<String, Object>();
		}
		if(data instanceof Map){
			Map<String,Object> m = (Map)data;
			m.putAll(in);
		}
	}
	
	public void setStatus(WStatus status){
		this.errorCode = status.getCode();
		this.errorMessage = status.getMsg();
	}
	

	public static WsResult create(WStatus status){
		WsResult wr = new WsResult();
		wr.setStatus(status);
		return wr;
	}
	
	public static WsResult create(WStatus status,String msg){
		WsResult wr = new WsResult();
		wr.setStatus(status);
		wr.setErrorMessage(msg);
		return wr;
	}
	
	public static WsResult create(Integer errorCode,String msg){
		WsResult wr = new WsResult();
		wr.setErrorCode(errorCode);
		wr.setErrorMessage(msg);
		return wr;
	}
	
	public static WsResult create(){
		return create(WStatus.SUCCESS);
	}

	public List<Object> getListDataWithKey(String key){
		List<Object> list = new ArrayList<Object>();
		for(Map<String,Object> row : results){
			list.add(row.get(key));
		}
		return list;
	}

}
