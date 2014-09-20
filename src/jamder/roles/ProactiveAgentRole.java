package jamder.roles;

import jamder.Organization;
import jamder.agents.GenericAgent;
import jamder.agents.ReflexAgent;
import jamder.structural.Goal;

import java.util.Hashtable;

public class ProactiveAgentRole extends AgentRole 
{
	private Hashtable<String, Goal> goals = new Hashtable<String, Goal>();
	
	public ProactiveAgentRole(String name, Organization owner, GenericAgent player) {
		super(name, owner, player);
	}
	
	// Goals
	public Goal getGoal(String key) 
	{
		// Reflex and Model agents do not have goals
		if (getPlayer() instanceof ReflexAgent) {
			return null;
		}
		
		Goal goal = goals.get(key);
		// As AgentRole's Goal will never have plans, it is clean.
		goal.removeAllPlans();
		return goal;
	}
	public void addGoal(String key, Goal goal) {
		// Reflex and Model agents do not have goals
		if (getPlayer() instanceof ReflexAgent) {
			return ;
		}
		
		// As AgentRole's Goal will never have plans, it is clean.
		goal.removeAllPlans();
		goals.put(key, goal);
	}
	public Goal removeGoal(String key) {
		return goals.remove(key);
	}
	public void removeAllGoals() {
		goals.clear();
	}
	
	public Hashtable<String, Goal> getAllGoals() {
		// Reflex and Model agents do not have goals
		if (getPlayer() instanceof ReflexAgent) {
			return null;
		}
		
		// As AgentRole's Goal will never have plans, it is clean.
		for (Goal goal : goals.values()) {
			goal.removeAllPlans();
		}
		return goals;
	}
	
	@Override
	protected void checkingNorms() {
		// TODO Auto-generated method stub	
	}
	@Override
	public void initializeNorm() {
		// TODO Auto-generated method stub		
	}
}
