package de.wagentim.common;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

public final class ColorPicker
{
	public static final int COLOR_WHITE = 0x00;
	public static final int COLOR_BLUE = 0x01;
	private static final Display display = Display.getCurrent();
	
	public static Color pickColor(int colorName)
	{
		switch(colorName)
		{
		
		case COLOR_BLUE:
			return display.getSystemColor(SWT.COLOR_BLUE);
		
		default:
			return display.getSystemColor(SWT.COLOR_WHITE);
		}
	}
}
