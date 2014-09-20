package BalbinoSaloon.agents.waiter.actions;

import BalbinoSaloon.Objects.Beer;
import BalbinoSaloon.Objects.BeerState;
import BalbinoSaloon.Objects.Brand;
import BalbinoSaloon.Objects.Local;
import BalbinoSaloon.Objects.Refrigerator;
import BalbinoSaloon.agents.waiter.Waiter;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jamder.behavioural.Action;

public class SellBeer extends Action 
{
	private Beer beer;
	private Brand brand;
	private Waiter agent;
	private ACLMessage message;
	private MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
	
	public SellBeer(Waiter agent)
	{
		super("SellBeer");
		this.agent = agent;
	}

	@Override
	public void execute() 
	{
		ACLMessage reply = message.createReply();
		reply.setPerformative(ACLMessage.INFORM);
		reply.setContent("Ok, take your beer.");
		myAgent.send(reply);
		
		agent.getBeerToSell().remove(beer);
		System.out.println("A beer of brand " + beer.getType() + " was sold.");
	}

	@Override
	public boolean preConditionIsTrue() 
	{
		message = myAgent.receive(mt);
		if(message != null)
		{
			if(message.getContent().equals("A")) {
				brand = Brand.A;
			}
			else {
				brand = Brand.B;
			}
			
			beer = agent.getRefrigerator().getBeer( brand );
			if(beer != null && beer.getState() == BeerState.ICED)
			{
				System.out.println("There is beer ICED in refrigerator to sell.");
				return true;
			}
		}
		
		return false;
	}

}
