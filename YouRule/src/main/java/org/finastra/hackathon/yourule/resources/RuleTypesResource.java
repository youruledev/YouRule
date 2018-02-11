package org.finastra.hackathon.yourule.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import org.finastra.hackathon.yourule.model.Condition;
import org.finastra.hackathon.yourule.model.RuleType;
import org.finastra.hackathon.yourule.service.RuleTypeService;


@Path("/ruletypes")
@Produces(MediaType.APPLICATION_JSON)
public class RuleTypesResource 
{
	
		
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{mid}")
	public List<RuleType> getAllRuleTypes(@PathParam("mid") String Mid )
	{
		System.out.println("Mid="+Mid);
		RuleTypeService ruleTypeService = new RuleTypeService();
//		List<Emp> empList = myDAO.getAllEmployees();
//	    log.info("size   " + empList.size());
//	    Employees employees = new Employees();
//	    employees.setEmployeeList(empList);
//
//	    return employees;
		//List<Condition> l = conditionService.getAllConditions();
		//Conditions c = new Conditions();
		//c.setCondList(l);
		return ruleTypeService.getAllRuleTypes();
	}
	
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	@Path("/{conditionId}")
//	public Condition getCondition(@PathParam("conditionId") int conditionId)
//	{	
//		return conditionService.getCondition(conditionId);
//	}

}
