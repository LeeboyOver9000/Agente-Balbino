/**
 * 
 */
package jamder.agents;

import jamder.Environment;
import jamder.behavioural.Action;
import jamder.behavioural.Plan;
import jamder.behavioural.Sensor;
import jamder.roles.AgentRole;
import jamder.roles.ProactiveAgentRole;
import jamder.structural.Belief;
import jamder.structural.Goal;

import java.util.Hashtable;
import java.util.List;

/**
 * @author Administrator
 *
 */
public abstract class GoalAgent extends GenericAgent 
{
	private Hashtable<String, Belief> memories = new Hashtable<String, Belief>();
	private Hashtable<String, Goal> goals = new Hashtable<String, Goal>();
	private Hashtable<String, Plan> plans = new Hashtable<String, Plan>();
	
	protected GoalAgent(String name, Environment environment, AgentRole agentRole) 
	{
		super(name, environment, agentRole);
	}
	
	public void addAgentRole(String name, ProactiveAgentRole role) 
	{
		super.addAgentRole(name, role);
		
		// 4.2.3.1
		if (role.getAllBeliefs() != null)
		this.getAllBeliefs().putAll(role.getAllBeliefs());
		
		// 3.1.5
		if (role.getAllGoals() != null)
		this.getAllGoals().putAll(role.getAllGoals());
	}
	
	//Memories
	public Belief getMemory(String key) 
	{
		return memories.get(key);
	}
	
	public void addMemory(String key, Belief belief) 
	{
		memories.put(key, belief);
	}
	
	public Belief removeMemory(String key) 
	{
		return memories.remove(key);
	}
	
	public void removeAllMemories() 
	{
		memories.clear();
	}
	
	public Hashtable<String, Belief> getAllMemories() 
	{
		return memories;
	}

	public boolean containMemory(String key)
	{
		return memories.containsKey(key);
	}
	
	// Goals
	public Goal getGoal(String key) {
		return goals.get(key);
	}
	protected void addGoal(String key, Goal goal) {
		goals.put(key, goal);
	}
	protected Goal removeGoal(String key) {
		return goals.remove(key);
	}
	protected void removeAllGoals() {
		goals.clear();
	}
	protected Hashtable<String, Goal> getAllGoals() {
		return goals;
	}
	
	// Plans
	public Plan getPlan(String key) {
		return plans.get(key);
	}
	public void addPlan(String key, Plan plan) {
		plans.put(key, plan);
	}
	public Plan removePlan(String key) {
		return plans.remove(key);
	}
	public void removeAllPlans() {
		plans.clear();
	}
	public Hashtable<String, Plan> getAllPlans() {
		return plans;
	}
	
	 // recebe o estado (crenca) e retorna os objetivos formulados
	protected abstract List<Goal> formulateGoalFunction(Belief belief);
		  
	// recebe o estado (crenca) e o objetivo, e retorna o problema (Lista de ações que o agente pode executar para alcançar o seu objetivo)
	protected abstract List<Action> formulateProblemFunction(Belief belief, List<Goal> goal);
	  
	// recebe a lista com as possíveis ações que o agente pode executar e retorna um plano
	protected abstract Plan planning(List<Action> actions);
		  
	// Next Function Method
	protected abstract Belief nextFunction(Belief belief);
}
