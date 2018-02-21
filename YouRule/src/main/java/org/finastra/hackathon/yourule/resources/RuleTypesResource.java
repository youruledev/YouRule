package org.finastra.hackathon.yourule.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;



import org.finastra.hackathon.yourule.loader.RuleTypeData;
import org.finastra.hackathon.yourule.model.RuleDetails;
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
		
		return ruleTypeService.getAllRuleTypes(Mid);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("rules/{mid}")
	public List<RuleDetails> getAllRules(@PathParam("mid") String Mid )
	{
		System.out.println("Mid="+Mid);
		RuleTypeService ruleTypeService = new RuleTypeService();
		
		return ruleTypeService.getAllRules(Mid);
	}
	
//	for testing purpose
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/{mid}/{ruleIndex}")
	public String getAllRuleTypes(@PathParam("mid") String Mid,  @PathParam("ruleIndex") int ruleIndex)
	{
		RuleTypeService ruleTypeService = new RuleTypeService();
		
		return ruleTypeService.loadAllRuleTypes(Mid,ruleIndex);
	}
	
	

}
