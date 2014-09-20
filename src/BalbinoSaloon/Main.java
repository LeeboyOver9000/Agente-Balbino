package BalbinoSaloon;

import BalbinoSaloon.Objects.Brand;
import BalbinoSaloon.agents.client.Client;
import BalbinoSaloon.agents.waiter.Waiter;
import jamder.roles.ProactiveAgentRole;
import jamder.roles.ReflexAgentRole;

public class Main 
{
	public static void main(String[] args)
	{
		Saloon balbinoBar = new Saloon("Saloon", "localhost", "54321");
		
		BalbinoOrg balbinoOrg = new BalbinoOrg("BalbinoOrg", balbinoBar, null);
		balbinoBar.addOrganization("BalbinoOrg", balbinoOrg);
		
		ReflexAgentRole waiterRole = new ReflexAgentRole("WaitersRole", balbinoOrg, null);
		Waiter waiter = new Waiter("Waiter", balbinoBar, waiterRole, balbinoBar.getRefrigerator(), 
				balbinoBar.getBeers(), balbinoBar.getTables());
		
		ReflexAgentRole clientRole1 = new ReflexAgentRole("ClientRole1", balbinoOrg, null);
		ReflexAgentRole clientRole2 = new ReflexAgentRole("ClientRole2", balbinoOrg, null);
		ReflexAgentRole clientRole3 = new ReflexAgentRole("ClientRole3", balbinoOrg, null);
		
		Client client1 = new Client("Client1", balbinoBar, clientRole1, Brand.A, 5, 
				balbinoBar.getTables().get(1));
		Client client2 = new Client("Client2", balbinoBar, clientRole2, Brand.A, 8, 
				balbinoBar.getTables().get(1));
		Client client3 = new Client("Client3", balbinoBar, clientRole3, Brand.B, 6, 
				balbinoBar.getTables().get(2));
		
		balbinoBar.addAgent("Waiter", waiter);
		
		for(Table table : balbinoBar.getTables() )
			table.setResponsible(waiter);
		
		balbinoBar.addAgent("Client1", client1);
		balbinoBar.addAgent("Client2", client2);		
		balbinoBar.addAgent("Client3", client3);
	}
}
