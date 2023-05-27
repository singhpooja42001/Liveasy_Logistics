package com.example.liveasy.logistics.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.liveasy.logistics.entities.LoadLogistics;
import com.example.liveasy.logistics.service.LoadService;

@RestController
public class LoadController {

	@Autowired
	LoadService loadservice;
	
	/*	add the load details to the database.	POST : "/load"	Response : loads details added successfully*/
	@PostMapping("/load")
	public ResponseEntity<String> addLoadDetails(@RequestBody LoadLogistics load)
	{
		return loadservice.addLoadDetail(load);
		
	}
	
	
	/*	get the details of the loads from the database.  GET : "/load"	As we have to get loads by shipperId then shipperid cannot be null either it will throw error at the equals part .Therefore add the constraint of @NotNull from hibernate.*/
	@GetMapping("/load")
	public ResponseEntity<List<LoadLogistics>> getloadbyshipperId(@RequestBody String shipperId)
	{
		return loadservice.getLoadByShipperId(shipperId);
		
	}
	
	
	/*  get loads by loadId . GET : "/load/{loadid}	*/
	@GetMapping("/load/{loadid}")
	public ResponseEntity<LoadLogistics> getLoadByLoadId(@PathVariable Integer loadid)
	{
		return loadservice.getLoadByLoadId(loadid);
	}
	
	
	/*  update loads by loadId . PUT : "/load/{loadid}	*/
	@PutMapping("/load/{loadid}")
	public ResponseEntity<String> updateLoadByLoadId(@PathVariable Integer loadid, @RequestBody  LoadLogistics load)
	{
		return loadservice.updateLoadByLoadId(loadid,load);
	}
	

	/*  delete loads by loadId .DELETE : "/load/{loadid}	*/
	@DeleteMapping("/load/{loadid}")
	public ResponseEntity<String> deleteLoadByLoadId(@PathVariable Integer loadid)
	{
		return loadservice.deleteLoadByLoadId(loadid);
	}
}
