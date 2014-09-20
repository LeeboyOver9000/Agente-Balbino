package BalbinoSaloon;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import BalbinoSaloon.Objects.Beer;
import BalbinoSaloon.Objects.Brand;
import BalbinoSaloon.Objects.Refrigerator;
import jamder.Environment;

public class Saloon extends Environment 
{
	private Refrigerator refrigerator;
	private List<Beer> beers = new Vector<Beer>();
	private List<Table> tables = new ArrayList<Table>();
	
	public Saloon(String name, String host, String port) 
	{
		super(name, host, port);
		
		refrigerator = new Refrigerator("Refrigerator", 4);
		addObject(refrigerator.getName(), refrigerator);
		
		for(int i = 1; i <= refrigerator.getCapacity() ; i += 2)
		{
			Beer beerA = new Beer(Brand.A);
			Beer beerB = new Beer(Brand.B);
			
			beers.add(beerA);
			beers.add(beerB);
		}
		
		for(int i = 1; i <= 6; i++)
		{
			Table table = new Table(i);
			tables.add(table);
		}
	}

	public Refrigerator getRefrigerator() {
		return refrigerator;
	}

	public List<Beer> getBeers() {
		return beers;
	}
	
	public List<Table> getTables() {
		return tables;
	}
}
