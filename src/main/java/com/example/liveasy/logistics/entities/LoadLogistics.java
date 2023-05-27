package com.example.liveasy.logistics.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoadLogistics {
	
	/* 		POJO Class	*/
	
	@Id
	@NonNull
	Integer loadId;
	//see how load id is getting changed by itself.
	
	String loadingPoint;
	String unloadingPoint;
	String productType;
	String truckType;
	Integer noOfTrucks;
	Long weight;
    String optional;
    @NonNull
	String shipperId;
	String Date;
	
}
