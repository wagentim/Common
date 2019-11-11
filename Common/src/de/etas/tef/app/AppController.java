package de.etas.tef.app;

import org.eclipse.swt.widgets.Display;

import de.wagentim.common.ColorPicker;
import de.wagentim.common.ImageRegister;

public class AppController implements IController
{
	private final Display display; 
	private final ImageRegister imageRegister;
	private final ColorPicker colorPicker;
	
	public AppController()
	{
		display = new Display();
		imageRegister = new ImageRegister(display);
		colorPicker = new ColorPicker(display);
	}
	
	public Display getDisplay()
	{
		return display;
	}
	
	public ImageRegister getImageRegister()
	{
		return imageRegister;
	}
	
	public ColorPicker getColorPicker()
	{
		return colorPicker;
	}
}
