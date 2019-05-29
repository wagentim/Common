package de.wagentim.common;

import java.util.ArrayList;
import java.util.List;

import de.wagentim.common.IActionListener;

public abstract class AbstractActionManager implements IActionManager
{
	private List<IActionListener> listenerList = null;
	
	public AbstractActionManager()
	{
		listenerList = new ArrayList<IActionListener>();
	}
	
	public void addActionListener(IActionListener listener)
	{
		if( !listenerList.contains(listener) )
		{
			listenerList.add(listener);
		}
	}
	
	public void removeActionListener(IActionListener listener)
	{
		if( !listenerList.isEmpty() )
		{
			listenerList.remove(listener);
		}
	}
	
	public void sendAction(final int type, final Object content)
	{
		if( !listenerList.isEmpty() )
		{
			for( int i = 0; i < listenerList.size(); i++ )
			{
				listenerList.get(i).receivedAction(type, content);
			}
		}
	}
}
