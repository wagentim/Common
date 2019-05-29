package de.wagentim.common.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.TaskBar;
import org.eclipse.swt.widgets.TaskItem;

public class Example {
	static Display display;
	static Shell shell;
	static int pageNum = -1;

static TaskItem getTaskBarItem () {
	TaskBar bar = display.getSystemTaskBar();
	if (bar == null) return null;
	TaskItem item = bar.getItem(shell);
	if (item == null) item = bar.getItem(null);
	return item;
}

public static void main(String[] args) {
//	display = new Display();
//	shell = new Shell(display);
//	shell.setLayout(new GridLayout());
//	TabFolder folder = new TabFolder(shell, SWT.NONE);
//	folder.setLayoutData(new GridData(GridData.FILL_BOTH));
//
//	//Progress tab
//	TabItem item = new TabItem(folder, SWT.NONE);
//	item.setText("Progress");
//	Composite composite = new Composite(folder, SWT.NONE);
//	composite.setLayout(new GridLayout());
//	item.setControl(composite);
//	Listener listener = event -> {
//		Button button = (Button)event.widget;
//		if (!button.getSelection()) return;
//		TaskItem item1 = getTaskBarItem();
//		if (item1 != null) {
//			int state = ((Integer)button.getData()).intValue();
//			item1.setProgressState(state);
//		}
//	};
//	Group group = new Group (composite, SWT.NONE);
//	group.setText("State");
//	group.setLayout(new GridLayout());
//	group.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
//	Button button;
//	String[] stateLabels = {"SWT.DEFAULT", "SWT.INDETERMINATE", "SWT.NORMAL", "SWT.ERROR", "SWT.PAUSED"};
//	int[] states = {SWT.DEFAULT, SWT.INDETERMINATE, SWT.NORMAL, SWT.ERROR, SWT.PAUSED };
//	for (int i = 0; i < states.length; i++) {
//		button = new Button(group, SWT.RADIO);
//		button.setText(stateLabels[i]);
//		button.setData(Integer.valueOf(states[i]));
//		button.addListener(SWT.Selection, listener);
//		if (i==0) button.setSelection(true);
//	}
//	group = new Group (composite, SWT.NONE);
//	group.setText("Value");
//	group.setLayout(new GridLayout(2, false));
//	group.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
//	Label label = new Label(group, SWT.NONE);
//	label.setText("Progress");
//	final Scale scale = new Scale (group, SWT.NONE);
//	scale.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
//	scale.addListener(SWT.Selection, event -> {
//		TaskItem item1 = getTaskBarItem();
//		if (item1 != null) item1.setProgress(scale.getSelection());
//	});
//
//	//Overlay text tab
//	item = new TabItem(folder, SWT.NONE);
//	item.setText("Text");
//	composite = new Composite(folder, SWT.NONE);
//	composite.setLayout(new GridLayout());
//	item.setControl(composite);
//	group = new Group (composite, SWT.NONE);
//	group.setText("Enter a short text:");
//	group.setLayout(new GridLayout(2, false));
//	group.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
//	final Text text = new Text(group, SWT.BORDER | SWT.SINGLE);
//	GridData data = new GridData(GridData.FILL_HORIZONTAL);
//	data.horizontalSpan = 2;
//	text.setLayoutData(data);
//	button = new Button(group, SWT.PUSH);
//	button.setText("Set");
//	button.addListener(SWT.Selection, event -> {
//		TaskItem item1 = getTaskBarItem();
//		if (item1 != null) item1.setOverlayText(text.getText());
//	});
//	button = new Button(group, SWT.PUSH);
//	button.setText("Clear");
//	button.addListener(SWT.Selection, event -> {
//		text.setText("");
//		TaskItem item1 = getTaskBarItem();
//		if (item1 != null) item1.setOverlayText("");
//	});
//
//	//Overlay image tab
//	item = new TabItem(folder, SWT.NONE);
//	item.setText("Image");
//	composite = new Composite(folder, SWT.NONE);
//	composite.setLayout(new GridLayout());
//	item.setControl(composite);
//	Listener listener3 = event -> {
//		Button button1 = (Button)event.widget;
//		if (!button1.getSelection()) return;
//		TaskItem item1 = getTaskBarItem();
//		if (item1 != null) {
//			String text1 = button1.getText();
//			Image image = null;
//			if (!text1.equals("NONE")) image = new Image (display, Example.class.getResourceAsStream(text1));
//			Image oldImage = item1.getOverlayImage();
//			item1.setOverlayImage (image);
//			if (oldImage != null) oldImage.dispose();
//		}
//	};
//	group = new Group (composite, SWT.NONE);
//	group.setText("Images");
//	group.setLayout(new GridLayout());
//	group.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
//	button = new Button(group, SWT.RADIO);
//	button.setText("NONE");
//	button.addListener(SWT.Selection, listener3);
//	button.setSelection(true);
//	String[] images = {"eclipse.png", "pause.gif", "run.gif", "warning.gif"};
//	for (int i = 0; i < images.length; i++) {
//		button = new Button(group, SWT.RADIO);
//		button.setText(images[i]);
//		button.addListener(SWT.Selection, listener3);
//	}
//    shell.pack();
//	shell.open();
//	while (!shell.isDisposed()) {
//		if (!display.readAndDispatch()) display.sleep();
//	}
//	display.dispose();
	
//	Display display = new Display ();
//	Shell shell = new Shell (display);
//	Caret caret = new Caret (shell, SWT.NONE);
//	caret.setBounds (10, 10, 2, 32);
//	shell.open ();
//	while (!shell.isDisposed ()) {
//		if (!display.readAndDispatch ()) display.sleep ();
//	}
//	display.dispose ();
	
//	Display display = new Display ();
//	Shell shell = new Shell (display);
//	Rectangle clientArea = shell.getClientArea();
//	shell.setBounds (clientArea.x + 10, clientArea.y + 10, 300, 200);
//	// create the composite that the pages will share
//	final Composite contentPanel = new Composite (shell, SWT.BORDER);
//	contentPanel.setBounds (clientArea.x + 100, clientArea.y + 10, 190, 90);
//	final StackLayout layout = new StackLayout ();
//	contentPanel.setLayout (layout);
//
//	// create the first page's content
//	final Composite page0 = new Composite (contentPanel, SWT.NONE);
//	page0.setLayout (new RowLayout ());
//	Label label = new Label (page0, SWT.NONE);
//	label.setText ("Label on page 1");
//	label.pack ();
//
//	// create the second page's content
//	final Composite page1 = new Composite (contentPanel, SWT.NONE);
//	page1.setLayout (new RowLayout ());
//	Button button = new Button (page1, SWT.NONE);
//	button.setText ("Button on page 2");
//	button.pack ();
//
//	// create the button that will switch between the pages
//	Button pageButton = new Button (shell, SWT.PUSH);
//	pageButton.setText ("Push");
//	pageButton.setBounds (clientArea.x + 10, clientArea.y + 10, 80, 25);
//	pageButton.addListener (SWT.Selection, event -> {
//		pageNum = ++pageNum % 2;
//		layout.topControl = pageNum == 0 ? page0 : page1;
//		contentPanel.layout ();
//	});
//
//	shell.open ();
//	while (!shell.isDisposed ()) {
//		if (!display.readAndDispatch ()) display.sleep ();
//	}
//	display.dispose ();
	
	Display display = new Display ();
	final Shell shell = new Shell (display);
	final TabFolder tabFolder = new TabFolder (shell, SWT.BORDER);
	Rectangle clientArea = shell.getClientArea ();
	tabFolder.setLocation (clientArea.x, clientArea.y);
	for (int i=0; i<6; i++) {
		TabItem item = new TabItem (tabFolder, SWT.NONE);
		item.setText ("TabItem " + i);
		Button button = new Button (tabFolder, SWT.PUSH);
		button.setText ("Page " + i);
		item.setControl (button);
	}
	tabFolder.pack ();
	shell.pack ();
	shell.open ();
	while (!shell.isDisposed ()) {
		if (!display.readAndDispatch ()) display.sleep ();
	}
	display.dispose ();
}

}
