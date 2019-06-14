package de.wagentim.common.ui;

import org.eclipse.swt.widgets.Composite;

import de.wagentim.common.IActionListener;
import de.wagentim.common.ImageRegister;

public abstract class CommonAbstractComposite extends Composite implements IActionListener
{
	
	protected final ImageRegister imageRegister;

	public CommonAbstractComposite(Composite parent, int style, ImageRegister imageRegister)
	{
		super(parent, style);
		this.imageRegister = imageRegister;
	}
	
	public void receivedAction(int type, Object content){}

}
