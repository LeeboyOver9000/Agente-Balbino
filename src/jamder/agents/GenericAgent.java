package jamder.agents;

import jade.core.Agent;
import jade.core.ContainerID;
import jade.core.Location;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jamder.Environment;
import jamder.Organization;
import jamder.behavioural.Action;
import jamder.monitoring.Monitor;
import jamder.norms.Norm;
import jamder.norms.NormType;
import jamder.roles.AgentRole;
import jamder.roles.AgentRoleStatus;
import jamder.structural.Belief;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class GenericAgent extends Agent
{
	private Environment ambient;
	
	private Hashtable<String, Action> actions = new Hashtable<String, Action>();
	private Hashtable<String, Belief> beliefs = new Hashtable<String, Belief>();
	private Hashtable<String, AgentRole> agentRoles = new Hashtable<String, AgentRole>();
	private Hashtable<String, Organization> organizations = new Hashtable<String, Organization>();
	protected Hashtable<String, Norm> restrictNorms = new Hashtable<String, Norm>();
	
	public GenericAgent(String name, Environment ambient, AgentRole role)
	{
		this.ambient = ambient;
		
		if(role != null)
			addAgentRole(role.getName(), role);
	}
	
	public Environment getEnvironment()
	{
		return ambient;
	}
	
	// Actions
	public Action getAction(String key) 
	{
		return actions.get(key);
	}
	
	public void addAction(String key, Action action) 
	{
		actions.put(key, action);
	}
	
	public Action removeAction(String key) 
	{
		return actions.remove(key);
	}
	
	public void removeAllActions() 
	{
		actions.clear();
	}
	
	public Hashtable<String, Action> getAllActions() 
	{
		return actions;
	}
	
	public boolean containAction(String key)
	{
		return actions.containsKey(key);
	}
	
	// Beliefs
	public Belief getBelief(String key) 
	{
		return beliefs.get(key);
	}
	
	public void addBelief(String key, Belief belief) 
	{
		beliefs.put(key, belief);
	}
	
	public Belief removeBelief(String key) 
	{
		return beliefs.remove(key);
	}
	
	public void removeAllBeliefs() 
	{
		beliefs.clear();
	}
	
	public Hashtable<String, Belief> getAllBeliefs() 
	{
		return beliefs;
	}
	
	public boolean containBelief(String key)
	{
		return beliefs.containsKey(key);
	} 
	
	// AgentRoles
	public AgentRole getAgentRole(String name) 
	{
		return agentRoles.get(name);
	}

	public void addAgentRole(String name, AgentRole role)
	{
		agentRoles.put(name, role);
		role.setPlayer(this);
	}

	public AgentRole removeAgentRole(String name)
	{	
		return agentRoles.remove(name);
	}
	
	public void removeAllAgentRoles() 
	{
		agentRoles.clear();
	}
	
	public Hashtable<String, AgentRole> getAllAgentRoles() 
	{
		return agentRoles;
	}
	
	// Organizations
	public Organization getOrganization(String key) 
	{
		return organizations.get(key);
	}
	
	public void addOrganization(String key, Organization organization) 
	{
		organizations.put(key, organization);
	}
	
	public Organization removeOrganization(String key) 
	{
		return organizations.remove(key);
	}
	
	public void removeAllOrganizations() 
	{
		organizations.clear();
	}
	
	public Hashtable<String, Organization> getAllOrganizations() 
	{
		return organizations;
	}

	//RestrictNorms
	public Norm getRestrictNorm(String key) 
	{
		return restrictNorms.get(key);
	}

	public void addRestrictNorm(String key, Norm restrictNorm) 
	{
		restrictNorms.put(key, restrictNorm);
	}

	public Norm removeRestrictNorm(String key) 
	{
		return restrictNorms.remove(key);
	}

	public void removeAllRestrictNorms() 
	{
		restrictNorms.clear();
	}

	public Hashtable<String, Norm> getAllRestrictNorms() 
	{
		return restrictNorms;
	}
}
