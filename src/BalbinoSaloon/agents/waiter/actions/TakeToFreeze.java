package BalbinoSaloon.agents.waiter.actions;

import BalbinoSaloon.Objects.Beer;
import BalbinoSaloon.Objects.BeerState;
import BalbinoSaloon.Objects.Local;
import BalbinoSaloon.Objects.Refrigerator;
import BalbinoSaloon.agents.waiter.Waiter;
import jamder.behavioural.Action;

public class TakeToFreeze extends Action
{
	private Beer beer;
	private Waiter agent;
	
	public TakeToFreeze(Waiter agent)
	{
		super("TakeToFreeze");
		this.agent = agent;
	}
	
	public Waiter getAgent() {
		return agent;
	}

	public void setAgent(Waiter agent) {
		this.agent = agent;
	}

	@Override
	public void execute()
	{
		agent.getRefrigerator().putInside(beer);
		System.out.println("The beer is inside of refrigerator now.");
	}

	@Override
	public boolean preConditionIsTrue() 
	{
		for( Beer beer : agent.getBeers() )
		{
			if(beer.getLocal() == Local.OUTSIDE && beer.getState() == BeerState.WARM)
			{
				System.out.println("There is a beer WARM and OUTSIDE the refrigerator.");
				this.beer = beer;
				return true;
			}
		}
		
		return false;
	}
	
}
