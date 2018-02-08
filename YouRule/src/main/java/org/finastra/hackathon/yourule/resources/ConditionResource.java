package org.finastra.hackathon.yourule.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.finastra.hackathon.yourule.model.ComplexCondition;
import org.finastra.hackathon.yourule.model.Condition;
import org.finastra.hackathon.yourule.model.Conditions;
import org.finastra.hackathon.yourule.model.FinalCondition;
import org.finastra.hackathon.yourule.model.compCondion;
import org.finastra.hackathon.yourule.service.ConditionService;


@Path("/condition")
@Produces(MediaType.APPLICATION_JSON)
public class ConditionResource 
{
	ConditionService conditionService = new ConditionService();
		
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<compCondion> getAllConditions()
	{
//		List<Emp> empList = myDAO.getAllEmployees();
//	    log.info("size   " + empList.size());
//	    Employees employees = new Employees();
//	    employees.setEmployeeList(empList);
//
//	    return employees;
		//List<Condition> l = conditionService.getAllConditions();
		//Conditions c = new Conditions();
		//c.setCondList(l);
		return conditionService.getAllConditions();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{conditionId}")
	public Condition getCondition(@PathParam("conditionId") int conditionId)
	{	
		return conditionService.getCondition(conditionId);
	}

}
