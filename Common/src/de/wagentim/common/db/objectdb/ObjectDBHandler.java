package de.wagentim.common.db.objectdb;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import de.wagentim.common.utils.IConstants;

public class ObjectDBHandler implements IConstants
{
	private String dbFile = EMPTY_STRING;
	private EntityManagerFactory emf = null;
	
	public ObjectDBHandler() {}
	
	public ObjectDBHandler(String dbFile) 
	{
		setDBFile(dbFile);
	}
	
	public void setDBFile(String dbFile)
	{
		this.dbFile = dbFile;
	}
	
	public boolean connect()
	{
		if(null == dbFile || dbFile.isEmpty())
		{
			return false;
		}
		
		emf = Persistence.createEntityManagerFactory(dbFile);
		
		return true;
	}
	
	public void close()
	{
		if(emf != null)
		{
			emf.close();
			emf = null;
		}
	}
	
	private EntityManager createNewEntityManager()
	{
		if(emf == null || !emf.isOpen())
		{
			return null;
		}
		
		return emf.createEntityManager();
	}
}
