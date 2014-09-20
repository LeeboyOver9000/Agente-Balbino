package BalbinoSaloon.agents.waiter;

import java.util.ArrayList;
import java.util.List;

import BalbinoSaloon.Table;
import BalbinoSaloon.Objects.Beer;
import BalbinoSaloon.Objects.Refrigerator;
import BalbinoSaloon.agents.waiter.actions.KickOutClient;
import BalbinoSaloon.agents.waiter.actions.SellBeer;
import BalbinoSaloon.agents.waiter.actions.TakeToFreeze;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jamder.Environment;
import jamder.agents.ReflexAgent;
import jamder.behavioural.Sensor;
import jamder.roles.AgentRole;

public class Waiter extends ReflexAgent
{	
	private List<Beer> beers;
	private List<Table> tables;
	private Refrigerator refrigerator;
	private List<Beer> beerToSell = new ArrayList<Beer>();
	
	private SellBeer sellBeer;
	private TakeToFreeze takeToFreeze;
	private KickOutClient kickOutClient;
	
	public Waiter(String name, Environment environment, AgentRole agentRole, 
			Refrigerator refrigerator, List<Beer> beers, List<Table> tables)
	{
		super(name, environment, agentRole);
		this.beers = beers;
		this.tables = tables;
		this.refrigerator = refrigerator;
		
		sellBeer = new SellBeer(this);
		takeToFreeze = new TakeToFreeze(this);
		kickOutClient = new KickOutClient(this);
		
		addAction(sellBeer.getName(), sellBeer);
		addAction(takeToFreeze.getName(), takeToFreeze);
		addAction(kickOutClient.getName(), kickOutClient);
	}
	
	public Refrigerator getRefrigerator()
	{
		return refrigerator;
	}
	
	public List<Beer> getBeers()
	{
		return beers;
	}
	
	public List<Beer> getBeerToSell()
	{
		return beerToSell;
	}
	
	@Override
	protected void setup()
	{
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName( getAID() );
		
		ServiceDescription sd = new ServiceDescription();
		sd.setType("Beer-selling");
		sd.setName(getLocalName() + "-Beer-selling");
		
		dfd.addServices(sd);
		
		try {
			DFService.register(this, dfd);
		}
		catch(FIPAException exception) {
			exception.printStackTrace();
		}
		
		addBehaviour( new Perception(this) );
	}
	
	@Override
	protected void takeDown()
	{
		try {
			DFService.deregister(this);
		}
		catch(FIPAException exception) {
			exception.printStackTrace();
		}
	}
	
	private class Perception extends Sensor
	{	
		private Waiter agent;
		
		protected Perception(Waiter agent)
		{
			super(agent, 1000);
			this.agent = agent;
		}
		
		@Override
		protected void onTick() 
		{
			addBehaviour(sellBeer);
			addBehaviour(takeToFreeze);
			addBehaviour(kickOutClient);
		}
	}
}
