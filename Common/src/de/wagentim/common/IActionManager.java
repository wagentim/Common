package de.wagentim.common;

public interface IActionManager
{
	void addActionListener(IActionListener listener);
	void removeActionListener(IActionListener listener);
	void sendAction(final int type, final Object content);
}
