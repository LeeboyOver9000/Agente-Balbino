package BalbinoSaloon.agents.client.actions;

import BalbinoSaloon.Objects.Brand;
import BalbinoSaloon.agents.client.Client;
import jade.core.AID;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jamder.behavioural.Action;

public class Ordering extends Action 
{
	private Client agent;
	private Brand brand;
	private ACLMessage message;
	private MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
	
	public Ordering(Client agent)
	{
		super("Ordering");
		this.agent = agent;
		brand = agent.getBrand();
	}

	@Override
	public void execute() 
	{
		/*
		DFAgentDescription template = new DFAgentDescription();
		ServiceDescription sd = new ServiceDescription();
		sd.setType("Beer-selling");
		template.addServices(sd);
		
		try {
			DFAgentDescription[] result = DFService.search(myAgent, template);
			agent.getWaiters().clear();
			
			for(int i = 0 ; i < result.length ; i++)
			{
				agent.getWaiters().addElement( result[i].getName() );
			}
		}
		catch(FIPAException exception) {
			exception.printStackTrace();
		}
		
		
		ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
		msg.setContent( type.toString() );
		
		for(AID waiter : agent.getWaiters() ) {
			msg.addReceiver(waiter);
		}
		
		myAgent.send(msg);
		*/
		
		ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
		msg.setContent( brand.toString() );
		msg.addReceiver( new AID( agent.getTable().getResponsible().getLocalName(), AID.ISLOCALNAME ) );
		myAgent.send(msg);
		
		try {
			Thread.sleep(10000); // Make this agent waits for 10 seconds
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean preConditionIsTrue()
	{
		message = myAgent.receive(mt);
		if(message != null) {
			agent.addBehaviour( new DrinkingBeer(agent) );
			agent.getTable().changeState( agent, agent.getTable().getClientDrinking() );
		}
		else {
			agent.getTable().changeState( agent, agent.getTable().getClientWaiting() );
			return true;
		}
		
		return false;
	}
}
