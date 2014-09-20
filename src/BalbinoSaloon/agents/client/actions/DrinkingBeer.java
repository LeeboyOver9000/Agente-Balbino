package BalbinoSaloon.agents.client.actions;

import BalbinoSaloon.agents.client.Client;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jamder.behavioural.Action;

public class DrinkingBeer extends Action
{
	private Client agent;
	//private ACLMessage message;
	//private MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
	
	public DrinkingBeer(Client agent)
	{
		super("DrinkingBeer");
		this.agent = agent;
	}

	@Override
	public void execute() 
	{
		int beersDrinked = agent.getDrinkedBeer();
		agent.setDrinkedBeer(++beersDrinked);
		
		try {
			System.out.println("The client " + agent.getLocalName() + " is drinkig a beer now.");
			Thread.sleep(10000); // 10 seconds break to drinking a beer
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if( agent.getDrinkedBeer() >= agent.getBeerToGetDrunk() )
		{
			agent.setDrunk(true);
			System.out.println("The client " + agent.getLocalName() + " is drunk!");
		}
	}

	@Override
	public boolean preConditionIsTrue() 
	{
		/*
		message = myAgent.receive(mt);
		if(message != null) {
			agent.getTable().changeState( agent, agent.getTable().getClientDrinking() );
			return true;
		}
		*/
		
		return true;
	}
}
