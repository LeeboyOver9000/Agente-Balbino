package jamder.behavioural;

import jade.core.behaviours.SequentialBehaviour;
import jamder.agents.GenericAgent;
import jamder.structural.Goal;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class Plan extends SequentialBehaviour
{
	private String name;
	
	private List<Action> actions = new ArrayList<Action>();
	
	// Name
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	// Actions
	
	public List<Action> getActionList()
	{
		return actions;
	}
	
	public void removeAction(Action action) {
		actions.remove(action);
	}
	
	public void removeAllActions() {
		actions.clear();
	}
		
	public void addAction(Action action) {
		actions.add(action);
	}
	
	public boolean containAction(Action action)
	{
		return actions.contains(action);
	}
}
