package de.wagentim.common;

import de.etas.tef.app.IActionListener;

public interface IActionManager
{
	void addActionListener(IActionListener listener);
	void removeActionListener(IActionListener listener);
	void sendAction(final int type, final Object content);
}
