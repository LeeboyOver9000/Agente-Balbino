/**
 * 
 */
package jamder.agents;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentContainer;
import jamder.Environment;
import jamder.behavioural.Plan;
import jamder.norms.Norm;
import jamder.roles.AgentRole;
import jamder.roles.ObjectRole;
import jamder.roles.ProactiveAgentRole;
import jamder.structural.Belief;
import jamder.structural.Goal;

import java.util.Hashtable;


public class MASMLAgent extends GenericAgent
{
	private Hashtable<String, Goal> goals = new Hashtable<String, Goal>();
	private Hashtable<String, Plan> plans = new Hashtable<String, Plan>();
	
	public MASMLAgent(String name, Environment ambient, AgentRole role)
	{
		super(name, ambient, role);
	}
	
	// Goals
	protected Goal getGoal(String key) 
	{
		return goals.get(key);
	}
	
	protected void addGoal(String key, Goal goal) 
	{
		goals.put(key, goal);
	}
	
	protected Goal removeGoal(String key) 
	{
		return goals.remove(key);
	}
	
	protected void removeAllGoals() 
	{
		goals.clear();
	}
	
	protected Hashtable<String, Goal> getAllGoals() 
	{
		return goals;
	}
	
	// Plans
	public Plan getPlan(String key) 
	{
		return plans.get(key);
	}
	
	public void addPlan(String key, Plan plan) 
	{
		plans.put(key, plan);
	}
	
	public Plan removePlan(String key) 
	{
		return plans.remove(key);
	}
	
	public void removeAllPlans() 
	{
		plans.clear();
	}
	
	public Hashtable<String, Plan> getAllPlans() 
	{
		return plans;
	}
}