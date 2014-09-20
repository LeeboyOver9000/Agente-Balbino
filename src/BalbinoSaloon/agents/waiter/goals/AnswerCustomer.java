package BalbinoSaloon.agents.waiter.goals;

import java.util.List;

import jamder.structural.Belief;
import jamder.structural.Goal;
import BalbinoSaloon.Table;
import BalbinoSaloon.agents.client.Client;

public class AnswerCustomer extends Goal
{
	private List<Table> tables;
	
	public AnswerCustomer()
	{
		this(null);
	}
	
	public AnswerCustomer(List<Table> tables)
	{
		setTables(tables);
		setAchieved(false);
	}
	
	public void setTables(List<Table> tables)
	{
		this.tables = tables;
	}
	
	@Override
	public boolean isAchieved() 
	{
		Belief clientStateGoal = new Belief("Drinking");
		
		for(Table table : tables)
		{
			if( !table.isFree() )
			{
				for( Client client : table.getClients() )
				{
					if( !table.getClientState(client).getName().equalsIgnoreCase( clientStateGoal.getName() ))
						return false;
				}
			}
		}
		
		return true; 
	}
}
