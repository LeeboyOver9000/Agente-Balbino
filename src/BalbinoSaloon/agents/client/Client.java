package BalbinoSaloon.agents.client;

import java.util.Random;
import java.util.Vector;

import BalbinoSaloon.Table;
import BalbinoSaloon.Objects.Brand;
import BalbinoSaloon.agents.client.actions.DrinkingBeer;
import BalbinoSaloon.agents.client.actions.LeaveTheSaloon;
import BalbinoSaloon.agents.client.actions.MakeSomeRiot;
import BalbinoSaloon.agents.client.actions.Ordering;
import jade.core.AID;
import jade.lang.acl.ACLMessage;
import jamder.Environment;
import jamder.agents.ReflexAgent;
import jamder.behavioural.Sensor;
import jamder.roles.AgentRole;
import jamder.structural.Belief;

public class Client extends ReflexAgent
{
	private Table table;
	private Brand brand;
	
	private int drinkedBeer = 0;
	private int beerToGetDrunk;
	private int beersToBackToHome;
	private boolean drunk = false;
	
	private Ordering order;
	private MakeSomeRiot riot;
	private LeaveTheSaloon leave;
	
	private Vector<AID> waiters = new Vector<AID>();
	Random random = new Random( System.currentTimeMillis() );
	private Belief clientState = new Belief();
	
	public Client(String name, Environment ambient, AgentRole role, Brand brand, int beerToGetDrunk, Table table)
	{
		super(name, ambient, role);
		setBrand(brand);
		setTable(table);
		setBeerToGetDrunk(beerToGetDrunk);
		setBeersToBackToHome( random.nextInt(beerToGetDrunk * 2) + 1 );
		
		order = new Ordering(this);
		riot = new MakeSomeRiot(this);
		leave = new LeaveTheSaloon(this);
		
		addAction(order.getName(), order);
		addAction(riot.getName(), riot);
		addAction(leave.getName(), leave);
	}	
	
	public boolean isDrunk() {
		return drunk;
	}

	public void setDrunk(boolean drunk) {
		this.drunk = drunk;
	}
	
	public int getBeersToBackToHome() {
		return beersToBackToHome;
	}

	public void setBeersToBackToHome(int beersToBackToHome) {
		this.beersToBackToHome = beersToBackToHome;
	}

	public int getDrinkedBeer() {
		return drinkedBeer;
	}

	public void setDrinkedBeer(int drinkedBeer) {
		this.drinkedBeer = drinkedBeer;
	}

	public int getBeerToGetDrunk() {
		return beerToGetDrunk;
	}

	public void setBeerToGetDrunk(int beerToGetDrunk) {
		this.beerToGetDrunk = beerToGetDrunk;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
		
		if(table != null)
			table.sit(this);
	}
	
	public void changeState(Belief belief)
	{
		clientState = belief;
	}
	
	public Belief getClienteState()
	{
		return clientState;
	}

	public Vector<AID> getWaiters()
	{
		return waiters;
	}

	@Override
	protected void setup()
	{	
		addBehaviour( new Perception(this, 3000) );
	}
	
	private class Perception extends Sensor
	{	
		private Client agent;
		
		protected Perception(Client agent, int time)
		{
			super(agent, time);
			this.agent = agent;
		}
		
		@Override
		protected void onTick() 
		{
			addBehaviour(order);
			addBehaviour(riot);
			addBehaviour(leave);
		}
	}
}
