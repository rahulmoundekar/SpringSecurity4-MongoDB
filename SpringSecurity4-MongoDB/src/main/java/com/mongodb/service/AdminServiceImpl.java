package com.mongodb.service;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.domain.User;

@Service
@Component
public class AdminServiceImpl implements AdminService{

	@Resource
	MongoTemplate mongoTemplate;

	@Override
	public Boolean changeStatus(String id) {
		
		// find an entry where userid matches the id
		DBObject query = new BasicDBObject();
		query.put("_id", id);
		DBObject cursor = mongoTemplate.getDb().getCollection("user").findOne(query);
		DBObject query2 = new BasicDBObject();
		query2.put("_id", id);
		query2.put("name", cursor.get("name").toString());
		query2.put("surname",cursor.get("surname").toString());
		query2.put("age", cursor.get("age").toString());
		query2.put("username", cursor.get("username").toString());
		query2.put("password", cursor.get("password").toString());
		query2.put("role",cursor.get("role").toString());
		if(cursor.get("status").toString().equals("Active"))
		query2.put("status","Inactive");
		else
		query2.put("status","Active");
		mongoTemplate.getDb().getCollection("user").update(query, query2);
		return true;
	}

}
