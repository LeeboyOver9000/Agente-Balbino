package jamder.behavioural;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jamder.agents.ReflexAgent;
import jamder.norms.Norm;
import jamder.norms.NormType;

public abstract class Action extends Behaviour
{
	private String name;
	private NormType normType = null;
	protected boolean done = false;
	private boolean isCyclic = false;
	private int executionPrice = 0;
	
	public Action(String name)
	{
		setName(name);
	}
	
	public Action(String name, int price)
	{
		setName(name);
		setExecutionPrice(price);
	}

	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}

	public NormType getNormType()
	{
		return normType;
	}

	public void setNormType(NormType normType)
	{
		this.normType = normType;
	}

	@Override
	public void action()
	{
		Agent ag = getAgent();
		
		if( ag instanceof ReflexAgent )
		{
			ReflexAgent ra = (ReflexAgent)ag;

			Norm norm = ra.containsNorm(this, NormType.OBLIGATION);

			if( norm != null )
			{
				setNormType(NormType.OBLIGATION);
				execute();
				if( !isCyclic )
					done = true;
			}
			else 
			{
				norm = ra.containsNormDiferent(this, NormType.OBLIGATION);

				if( norm == null )
				{
					norm = ra.containsNorm(this, NormType.PROHIBITION);
					
					if( norm == null )
					{
						if( preConditionIsTrue() )
						{
							setNormType(NormType.PERMISSION);
							execute();
							if( !isCyclic )
								done = true;
						}
						else
						{
							done = true;
						}
					}
					else
					{
						setNormType(NormType.PROHIBITION);
						done = true;
					}
				}
				else
				{
					done = true;
				}
			}
		}
		else
		{
			if( preConditionIsTrue() )
			{
				execute();
				if( !isCyclic )
					done = true;
			}
			else
			{
				done = true;
			}
		}
	}

	@Override
	public boolean done()
	{
		return done;
	}

	public boolean isCyclic()
	{
		return isCyclic;
	}

	public void setCyclic(boolean cyclic)
	{
		isCyclic = cyclic;
	}

	public int getExecutionPrice()
	{
		return executionPrice;
	}
	
	public void setExecutionPrice(int price)
	{
		executionPrice = price;
	}
	
	// Implements what the agent is gonna do.
	public abstract void execute();

	// Pre-Condition for Agent do this Action
	public abstract boolean preConditionIsTrue();
}
