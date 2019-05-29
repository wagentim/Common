package de.wagentim.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IDManager
{
	private static final IDManager generator = new IDManager();
	private final Random random;
	private final List<Integer> idList;
	private final Logger logger = LoggerFactory.getLogger(IDManager.class);
	
	public static final IDManager INSTANCE()
	{
		return generator;
	}
	
	private IDManager()
	{
		random = new Random();
		idList = new ArrayList<Integer>();
	}
	
	public int getRandomInteger()
	{
		int result;
		
		do
		{
			result = random.nextInt((999999 - 100000) + 1) + 100000;
		}
		while(idList.contains(result));
		
		addID(result);
		
		return result;
	}

	public void addID(int id)
	{
		if(!idList.contains(id))
		{
			idList.add(id);
		}
		else
		{
			logger.error("The ID: {} has already exised.", id);
		}
	}

	public void clear()
	{
		idList.clear();
	}
}
