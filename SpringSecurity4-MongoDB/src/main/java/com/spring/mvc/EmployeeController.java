package com.spring.mvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mongodb.bean.EmployeeBean;
import com.mongodb.model.Employee;
import com.mongodb.service.EmployeeService;

@Controller
public class EmployeeController {

	@Resource
	EmployeeService employeeService;

	@RequestMapping(value = "/employee/add", method = RequestMethod.GET)
	public ModelAndView addEmployee(@ModelAttribute("command") EmployeeBean employeeBean, BindingResult result) {

		System.out.println("/add");
		// Map<String,Object> model=new HashMap<String,Object>();
		// model.put("employees",
		// prepareListofBean(employeeService.listEmployeess()));
		// return new ModelAndView("addEmployee",model);
		return new ModelAndView("addEmployee");
	}

	@PreAuthorize("permitAll")
	@RequestMapping(value = "/employee/save", method = RequestMethod.POST)
	public ModelAndView saveEmployee(@ModelAttribute("command") EmployeeBean employeeBean, BindingResult result) {

		System.out.println("/save");
		Employee employee = prepareModel(employeeBean);
		employeeService.addEmployee(employee);
		return new ModelAndView("redirect:/employee/employees");
	}

	@RequestMapping(value = "/employee/employees", method = RequestMethod.GET)
	public ModelAndView listEmployees() {

		System.out.println("/employees");
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("employees", prepareListofBean(employeeService.listEmployeess()));
		return new ModelAndView("employeesList", model);
	}

	@RequestMapping(value = "/employee/edit", method = RequestMethod.GET)
	public ModelAndView editEmployee(@ModelAttribute("command") EmployeeBean employeeBean, BindingResult result) {

		System.out.println("/edit");
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("employee", prepareEmployeeBean(employeeService.getEmployee(employeeBean.getId())));
		return new ModelAndView("addEmployee", model);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/employee/delete", method = RequestMethod.GET)
	public ModelAndView deleteEmployee(@ModelAttribute("command") EmployeeBean employeeBean, BindingResult result) {

		System.out.println("/delete");
		employeeService.deleteEmployee(employeeBean.getId());
		return new ModelAndView("redirect:/employee/employees");
	}
	
	
	private List<EmployeeBean> prepareListofBean(List<Employee> employees) {

		List<EmployeeBean> beans = null;
		if (employees != null && !employees.isEmpty()) {
			beans = new ArrayList<EmployeeBean>();
			EmployeeBean bean = null;
			for (Employee employee : employees) {
				bean = new EmployeeBean();
				bean.setName(employee.getEmpName());
				bean.setId(employee.getEmpId());
				bean.setAddress(employee.getEmpAddress());
				bean.setSalary(employee.getSalary());
				bean.setAge(employee.getEmpAge());
				beans.add(bean);
			}
		}
		return beans;
	}

	private Employee prepareModel(EmployeeBean employeeBean) {

		Employee employee = new Employee();
		employee.setEmpAddress(employeeBean.getAddress());
		employee.setEmpAge(employeeBean.getAge().toString());
		employee.setEmpName(employeeBean.getName());
		employee.setSalary(employeeBean.getSalary().toString());
		employee.setEmpId(employeeBean.getId().toString());
		employeeBean.setId(null);
		return employee;
	}

	private EmployeeBean prepareEmployeeBean(Employee employee) {

		EmployeeBean employeeBean = new EmployeeBean();
		employeeBean.setAddress(employee.getEmpAddress());
		employeeBean.setAge(employee.getEmpAge());
		employeeBean.setName(employee.getEmpName());
		employeeBean.setSalary(employee.getSalary());
		employeeBean.setId(employee.getEmpId());
		return employeeBean;

	}

}
