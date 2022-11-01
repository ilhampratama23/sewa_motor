package com.example.sewavespa.model.motor;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Motor {

	@SerializedName("data")
	private List<MotorDataItem> data;

	public void setData(List<MotorDataItem> data){
		this.data = data;
	}

	public List<MotorDataItem> getData(){
		return data;
	}
}