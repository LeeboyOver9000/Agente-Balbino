/**
 * 
 */
package jamder.agents;

import jamder.Environment;
import jamder.Organization;
import jamder.behavioural.Action;
import jamder.roles.AgentRole;
import jamder.roles.ProactiveAgentRole;

/**
 * @author Administrator
 *
 */
public abstract class UtilityAgent extends GoalAgent {
	
	protected UtilityAgent(String name, Environment environment, AgentRole agentRole) {
		super(name, environment, agentRole);
	}
	
	// Integer � o grau de prioridade da a��o
	protected abstract Integer utilityFunction(Action action);
}
