package com.example.sewavespa.model.bayar;

import com.google.gson.annotations.SerializedName;

public class Bayar{

	@SerializedName("data")
	private String data;

	public void setData(String data){
		this.data = data;
	}

	public String getData(){
		return data;
	}
}