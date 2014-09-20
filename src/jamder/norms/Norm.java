package jamder.norms;
import jamder.Environment;
import jamder.Organization;
import jamder.agents.GenericAgent;
import jamder.roles.AgentRole;

import java.util.Hashtable;

public class Norm
{
	private String name;
	private NormType normType;
	private NormResource normResource;

	//protected Organization contextOrganization;
	//protected Environment contextEnvironment;

	private Hashtable <String, NormConstraint> normConstraint = new Hashtable <String, NormConstraint>();

	//private Hashtable <String, Norm> sanctionReward = new Hashtable <String, Norm>();
	//private Hashtable <String, Norm> sanctionPunishment = new Hashtable <String, Norm>();

	public Norm(String name, NormType normType, NormResource normResource, Hashtable<String, NormConstraint> normConstraint)
	{
		setName( name );
		setNormType( normType );
		setNormResource( normResource );
		setNormConstraint( normConstraint );
	}

	/******************gets and sets*****************/

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public NormType getNormType() 
	{
		return normType;
	}

	public void setNormType(NormType normType) 
	{
		this.normType = normType;
	}

	public NormResource getNormResource()
	{
		return normResource;
	}

	public void setNormResource( NormResource normResource )
	{
		this.normResource = normResource;
	}

	public Hashtable<String, NormConstraint> getNormConstraint() 
	{
		return normConstraint;
	}

	public void setNormConstraint(Hashtable<String, NormConstraint> normConstraint) 
	{
		this.normConstraint = normConstraint;
	}

	/*
	public Hashtable<String, Norm> getSanctionReward() 
	{
		return sanctionReward;
	}

	public void setSanctionReward(Hashtable<String, Norm> sanctionReward) 
	{
		this.sanctionReward = sanctionReward;
	}

	public Hashtable<String, Norm> getSanctionPunishment() 
	{
		return sanctionPunishment;
	}

	public void setSanctionPunishment(Hashtable<String, Norm> sanctionPunishment) 
	{
		this.sanctionPunishment = sanctionPunishment;
	}

	public Object getContext()
	{
		if ( this.contextOrganization != null )
			return this.contextOrganization;

		if ( this.contextEnvironment != null)
			return this.contextEnvironment;

		return null;
	}
	
	public void setContext(Object context)
	{
		this.contextOrganization = null;
		this.contextEnvironment = null;

		if (context instanceof Organization)
			this.contextOrganization = (Organization)context;

		if (context instanceof Environment)
			this.contextEnvironment = (Environment)context;
	}
	*/

	public boolean isActive()
	{
		for( NormConstraint constraint : normConstraint.values() )
			if ( !constraint.isTrue() )
				return false;
			
		return true;
	}
}