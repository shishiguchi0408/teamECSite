package com.internousdev.blue.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.blue.dao.DestinationInfoDAO;
import com.opensymphony.xwork2.ActionSupport;

public class CreateDestinationCompleteAction extends ActionSupport implements SessionAware{
	private Map<String,Object> session;

	public String execute(){
		if(!session.containsKey("tempUserId") && !session.containsKey("userId")){
			return "sessionTimeout";
		}

		String result = ERROR;
		DestinationInfoDAO didao = new DestinationInfoDAO();//didaoはDestinationInfoDAOの略
		int count = didao.insert(session.get("userId").toString(),
								 session.get("familyName").toString(),
								 session.get("firstName").toString(),
								 String.valueOf(session.get("familyNameKana")),
								 String.valueOf(session.get("firstNameKana")),
								 String.valueOf(session.get("email")),
								 String.valueOf(session.get("telNumber")),
								 session.get("userAddress").toString());
								//toStringとvalueOfはほとんど同じ。valueOfはnullでも平気

		if(count > 0){
			result = SUCCESS;
		}

		session.remove("familyName");
		session.remove("firstName");
		session.remove("familyNameKana");
		session.remove("firstNameKana");
		session.remove("email");
		session.remove("telNumber");
		session.remove("userAddress");

		return result;
	}

	public Map<String,Object>getSession(){
		return session;
	}
	public void setSession(Map<String,Object> session){
		this.session = session;
	}
}
