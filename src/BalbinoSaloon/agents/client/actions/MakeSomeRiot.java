package BalbinoSaloon.agents.client.actions;

import BalbinoSaloon.agents.client.Client;
import jade.core.AID;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jamder.behavioural.Action;

public class MakeSomeRiot extends Action 
{
	private Client agent;
	
	public MakeSomeRiot(Client agent)
	{
		super("MakeSomeRiot");
		this.agent = agent;
	}

	@Override
	public void execute() 
	{
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
		
		ACLMessage msg = new ACLMessage(ACLMessage.PROPAGATE);
		msg.setContent("FUCK YEAH BITCH!!!");
		
		for(AID waiter : agent.getWaiters() ) {
			msg.addReceiver(waiter);
		}
		
		myAgent.send(msg);
		System.out.println( msg.getContent() );
	}

	@Override
	public boolean preConditionIsTrue() 
	{
		if( agent.isDrunk() ) {
			agent.getTable().changeState( agent, agent.getTable().getClientDrunk() );
			return true;
		}
		
		return false;
	}
}
