package com.mongodb.service;

import java.util.List;
import java.util.UUID;
import javax.annotation.Resource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.mongodb.model.Employee;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

@Service
@Component
public class EmployeeServiceImpl implements EmployeeService {

	@Resource
	MongoTemplate mongoTemplate;

	@Override
	public Boolean addEmployee(Employee employee) {

		try {
			if (employee.getEmpId() != null && employee.getEmpId() != "") {

				// find an entry where empid matches the id
				DBObject query1 = new BasicDBObject();
				query1.put("empId", employee.getEmpId());
				DBObject query2 = new BasicDBObject();
				query2.put("empId", employee.getEmpId());
				query2.put("empName", employee.getEmpName());
				query2.put("empAge", employee.getEmpAge());
				query2.put("salary", employee.getSalary());
				query2.put("empAddress", employee.getEmpAddress());
				mongoTemplate.getDb().getCollection("employee").update(query1, query2);
			} else {
				employee.setEmpId(UUID.randomUUID().toString());
				mongoTemplate.save(employee);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Employee> listEmployeess() {

		List<Employee> employees = mongoTemplate.findAll(Employee.class);
		return employees;
	}

	@Override
	public Employee getEmployee(String empid) {
		Employee employee = new Employee();

		// find an entry empid matches the empId
		DBObject query = new BasicDBObject();
		query.put("empId", empid);
		DBObject cursor = mongoTemplate.getDb().getCollection("employee").findOne(query);
		employee.setEmpId(cursor.get("empId").toString());
		employee.setEmpName(cursor.get("empName").toString());
		employee.setEmpAge(cursor.get("empAge").toString());
		employee.setSalary(cursor.get("salary").toString());
		employee.setEmpAddress(cursor.get("empAddress").toString());
		return employee;
	}

	@Override
	public Boolean deleteEmployee(String empid) {
		// Query query=new Query(where("empId").is(empid));
		// mongoTemplate.remove(query);
		DBObject query = new BasicDBObject();
		query.put("empId", empid);
		mongoTemplate.getDb().getCollection("employee").findAndRemove(query);
		return true;
	}

}
