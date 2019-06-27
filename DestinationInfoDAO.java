package com.internousdev.blue.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.internousdev.blue.dto.DestinationInfoDTO;
import com.internousdev.blue.util.DBConnector;

public class DestinationInfoDAO {

	public int insert(String userId, String familyName, String firstName, String familyNameKana, String firstNameKana, String email, String telNumber, String userAddress){
		DBConnector dbc = new DBConnector();
		Connection con = dbc.getConnection();
		int count=0;

		// 入力された情報を登録するsql文
	String sql="insert into destination_info(User_id,family_name,first_name,family_name_kana,first_name_kana,email,tel_number,user_address,regist_date,update_date) VALUES(?,?,?,?,?,?,?,?,now(),now())";

	try{
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1,userId);
		ps.setString(2,familyName);
		ps.setString(3,firstName);
		ps.setString(4,familyNameKana);
		ps.setString(5,firstNameKana);
		ps.setString(6,email);
		ps.setString(7,telNumber);
		ps.setString(8,userAddress);

		count = ps.executeUpdate();
	}catch(SQLException e){
		e.printStackTrace();
	}finally{
		try{
			con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	return count;

	}

	public List<DestinationInfoDTO> getDestinationInfo(String userId){
		DBConnector dbc = new DBConnector();
		Connection con = dbc.getConnection();
		List<DestinationInfoDTO> destinationInfoDTOList = new ArrayList<DestinationInfoDTO>();

		String sql = "SELECT id, family_name,first_name,family_name_kana,first_name_kana,user_address,tel_number,email FROM destination_info WHERE user_id = ?";

		try{
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			ResultSet rs = ps.executeQuery();

			while(rs.next()){//didto = DestinationInfoDTOの略
				DestinationInfoDTO didto = new DestinationInfoDTO();
				didto.setId(rs.getInt("id"));
				didto.setFamilyName(rs.getString("family_name"));
				didto.setFirstName(rs.getString("first_name"));
				didto.setFamilyNameKana(rs.getString("family_name_kana"));
				didto.setFirstNameKana(rs.getString("first_name_kana"));
				didto.setUserAddress(rs.getString("user_address"));
				didto.setEmail(rs.getString("email"));
				didto.setTelNumber(rs.getString("tel_number"));
				destinationInfoDTOList.add(didto);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return destinationInfoDTOList;
	}

	public DestinationInfoDTO getDestinationInfo(String id, String userId){
		DBConnector dbc = new DBConnector();
		Connection con = dbc.getConnection();
		DestinationInfoDTO didto = new DestinationInfoDTO();

		String sql = "SELECT id, family_name,first_name,family_name_kana,first_name_kana,user_address,tel_number,email,user_id FROM destination_info WHERE id = ? and user_id = ?";

		try{
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, userId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){//didto = DestinationInfoDTOの略

				didto.setId(rs.getInt("id"));
				didto.setFamilyName(rs.getString("family_name"));
				didto.setFirstName(rs.getString("first_name"));
				didto.setFamilyNameKana(rs.getString("family_name_kana"));
				didto.setFirstNameKana(rs.getString("first_name_kana"));
				didto.setUserAddress(rs.getString("user_address"));
				didto.setEmail(rs.getString("email"));
				didto.setTelNumber(rs.getString("tel_number"));
				didto.setUserId(rs.getString("user_id"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return didto;
	}

	public int deleteDestination(String id){
		DBConnector dbc = new DBConnector();
		Connection con = dbc.getConnection();

		//宛先を消すsql文
		String sql = "DELETE FROM destination_info WHERE id=?";
		int count=0;
		try{
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id);
			count = ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return count;
	}
}
