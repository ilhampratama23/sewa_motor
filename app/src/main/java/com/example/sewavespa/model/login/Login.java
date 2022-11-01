package com.example.sewavespa.model.login;

import com.google.gson.annotations.SerializedName;

public class Login {

	@SerializedName("admin")
	private String admin;

	@SerializedName("nama")
	private String nama;

	@SerializedName("token")
	private String token;

	public void setAdmin(String admin){
		this.admin = admin;
	}

	public String getAdmin(){
		return admin;
	}

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setToken(String token){
		this.token = token;
	}

	public String getToken(){
		return token;
	}
}