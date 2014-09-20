package BalbinoSaloon.agents.waiter.actions;

import BalbinoSaloon.agents.waiter.Waiter;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jamder.behavioural.Action;

public class KickOutClient extends Action 
{
	private Waiter agent;
	private ACLMessage message;
	private MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.PROPAGATE);
	
	public KickOutClient(Waiter agent) 
	{
		super("KickOutClient");
		this.agent = agent;
	}

	@Override
	public void execute() 
	{
		String agentLocalName = message.getSender().getLocalName();
		agent.getEnvironment().removeAgent( message.getSender().getLocalName() );
		System.out.println("The client " + agentLocalName + " was expelled." );
	}

	@Override
	public boolean preConditionIsTrue() 
	{
		message = myAgent.receive(mt);
		if(message != null) {
			return true;
		}
		else {
			block();
		}
		
		return false;
	}

}
