package com.mongodb.repositories;

import org.springframework.data.repository.CrudRepository;

import com.mongodb.domain.Campaign;


public interface CampaignRepositoryDao extends CrudRepository<Campaign, String> {
	
	
	

}