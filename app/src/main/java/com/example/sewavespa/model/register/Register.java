package com.example.sewavespa.model.register;

import com.google.gson.annotations.SerializedName;

public class Register {

	@SerializedName("nama")
	private String nama;

	@SerializedName("token")
	private String token;

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