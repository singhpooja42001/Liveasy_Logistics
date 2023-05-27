package com.example.liveasy.logistics.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.liveasy.logistics.dao.LoadLogisticsDAO;
import com.example.liveasy.logistics.entities.LoadLogistics;

import lombok.NonNull;

@Service
public class LoadService {

	@Autowired
	LoadLogisticsDAO loadDao;

	//try and handle all the exceptions 
	
	public ResponseEntity<String> addLoadDetail(LoadLogistics load) {
		

		LoadLogistics result =  loadDao.save(load);
		if(result != null)
		{
			return new ResponseEntity<String>("loads details added successfullly",HttpStatus.OK);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("loads not added");
	}

	
	public ResponseEntity<List<LoadLogistics>> getLoadByShipperId(@NonNull String shipperId)
	{
		List<LoadLogistics> loadsWithSameShipperId = new ArrayList<LoadLogistics>();
		
		try {
		for(LoadLogistics loads : loadDao.findAll())
		{
			if(loads.getShipperId().equals(shipperId))
			{
				boolean result = loadsWithSameShipperId.add(loads);
			}
		}
		}catch(NullPointerException e)
		{
			return ResponseEntity.badRequest().body(new ArrayList<LoadLogistics>());
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(loadsWithSameShipperId);
//		return ResponseEntity.of(Optional.of(loadsWithSameShipperId));
	}
	
	public ResponseEntity<LoadLogistics> getLoadByLoadId(@NonNull Integer id)
	{
		LoadLogistics result = loadDao.findById(id).get();
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
	}
	
	public ResponseEntity<String> updateLoadByLoadId(@NonNull Integer id,LoadLogistics load)
	{
		try
		{
			LoadLogistics existingProduct = loadDao.findById(id).get(); // DB
			/* If object of that ID is present then perform the operation otherwise return not updated succesfully.	*/
			if (existingProduct != null) {
				
		        existingProduct.setDate(load.getDate());
		        existingProduct.setLoadId(load.getLoadId());
		        existingProduct.setLoadingPoint(load.getLoadingPoint());
		        existingProduct.setNoOfTrucks(load.getNoOfTrucks());
		        existingProduct.setOptional(load.getOptional());
		        existingProduct.setProductType(load.getProductType());
		        existingProduct.setShipperId(load.getShipperId());
		        existingProduct.setTruckType(load.getTruckType());
		        existingProduct.setUnloadingPoint(load.getUnloadingPoint());
		        existingProduct.setWeight(load.getWeight());
		        loadDao.save(existingProduct);
			
			}
			
		}catch(NoSuchElementException e)
		{
			return new ResponseEntity<String>("loads not updated",HttpStatus.BAD_REQUEST); 
		}
		return new ResponseEntity<String>("loads updated succesfully",HttpStatus.OK);
	}

	public ResponseEntity<String> deleteLoadByLoadId(@NonNull Integer loadid) {
		for(LoadLogistics loads : loadDao.findAll())
		{
			if(loads.getLoadId()==loadid)
			{
				loadDao.deleteById(loadid);
				return new ResponseEntity<String>("loads deleted succesfully",HttpStatus.OK);
			}
		}
		return new ResponseEntity<String>("loads not deleted",HttpStatus.BAD_REQUEST);
	}

}
