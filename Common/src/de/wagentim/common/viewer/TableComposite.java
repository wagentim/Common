package de.wagentim.common.viewer;


import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MenuAdapter;
import org.eclipse.swt.events.MenuEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.ToolItem;

import de.etas.tef.app.IGlobalConstants;
import de.wagentim.common.IImageConstants;
import de.wagentim.common.ImageRegister;

public class TableComposite extends CommonAbstractComposite
{
	private final ITableController controller;
	private Table table;
	protected Color tableBackgroudColor;
	private Menu rightClickMenu;
	private TableListener tableListener;
	private ToolItem addToolItem;
	
	public TableComposite(Composite parent, int style, ITableController controller, ImageRegister imageRegister)
	{
		super(parent, style, imageRegister);

		this.setLayout(new GridLayout(1, false));
		this.setLayoutData(new GridData(GridData.FILL_BOTH));
		this.controller = controller;
		
		initMainComposite(this);
		tableBackgroudColor = parent.getDisplay().getSystemColor(SWT.COLOR_INFO_BACKGROUND);
	}
	
	protected void initMainComposite(Composite parent)
	{
		Composite tableComposite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(1, false);
		layout.marginTop = layout.marginBottom = layout.marginLeft = layout.marginRight = 0; 
		tableComposite.setLayout(layout);
		tableComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
		
//		ToolBar bar = new ToolBar(tableComposite, SWT.FLAT);
//		bar.setLayoutData(new GridData(SWT.RIGHT, SWT.FILL, true, false));
//		addToolItem = new ToolItem(bar, SWT.PUSH);
//		addToolItem.setImage(imageRegister.getImage(IImageConstants.IMAGE_ADD));
//		addToolItem.addSelectionListener(new SelectionAdapter()
//		{
//			@Override
//			public void widgetSelected(SelectionEvent arg0)
//			{
//				RecordItem recordItem = controller.getNewRecordItem();
//				
//				if( recordItem == null )
//				{
//					return;
//				}
//				
//				ProtectorActionManager.INSTANCE().sendAction(IProtectorActionType.ACTION_ADD_NEW_RECORD_ITEM, recordItem);
//			}
//		});
//		addToolItem.setEnabled(false);
		
		
		table = new Table(tableComposite, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = IGlobalConstants.HEIGHT_HINT;
		table.setLayoutData(gd);
		
		tableListener = new TableListener(table, controller);
	    
//		for (int i = 0; i < IProtectorConstants.TABLE_TITLES.length; i++) 
//		{
//			TableColumn column = new TableColumn(table, SWT.LEFT);
//			column.setText(IProtectorConstants.TABLE_TITLES[i]);
//			column.setResizable(true);
//			column.setWidth(150);
//		}
		
		table.addMouseListener(tableListener);
		table.addKeyListener(tableListener);
		table.addSelectionListener(tableListener);
	}
	
	private void createRightMenu(Control control, SelectionListener listener)
	{
		rightClickMenu = new Menu(table);
		table.setMenu(rightClickMenu);
		
		rightClickMenu.addMenuListener(new MenuAdapter()
	    {
	        public void menuShown(MenuEvent e)
	        {
	            MenuItem[] items = rightClickMenu.getItems();
	            
	            for (int i = 0; i < items.length; i++)
	            {
	                items[i].dispose();
	            }
	            
	            if(table.getSelectionCount() <= 0)
	            {
	            	return;
	            }
	            
	            MenuItem copyItem = new MenuItem(rightClickMenu, SWT.NONE);
	            copyItem.setText(IGlobalConstants.TXT_COPY);
	            copyItem.setImage(imageRegister.getImage(IImageConstants.IMAGE_COPY));
	            copyItem.addSelectionListener(listener);
	            
	            MenuItem pasteItem = new MenuItem(rightClickMenu, SWT.NONE);
	            pasteItem.setText(IGlobalConstants.TXT_PASTE);
	            pasteItem.setImage(imageRegister.getImage(IImageConstants.IMAGE_PASTE));
	            pasteItem.addSelectionListener(listener);
	            
	            new MenuItem(rightClickMenu, SWT.SEPARATOR);
	            
	            MenuItem newItem = new MenuItem(rightClickMenu, SWT.NONE);
	            newItem.setText(IGlobalConstants.TXT_ADD);
	            newItem.setImage(imageRegister.getImage(IImageConstants.IMAGE_ADD));
	            newItem.addSelectionListener(listener);
	            
	            MenuItem deleteItem = new MenuItem(rightClickMenu, SWT.NONE);
	            deleteItem.setText(IGlobalConstants.TXT_DELETE);
	            deleteItem.setImage(imageRegister.getImage(IImageConstants.IMAGE_REMOVE));
	            deleteItem.addSelectionListener(listener);
	        }
	    });
	}

	protected void deleteSelectedItems()
	{
		int[] selectedItems = table.getSelectionIndices();
		
		if( null != selectedItems && selectedItems.length > 0 )
		{
			
			MessageBox mb = new MessageBox(this.getShell(), SWT.ICON_WARNING | SWT.YES | SWT.NO);

			mb.setMessage("Do you really want to delete?");

			boolean done = mb.open() == SWT.YES;
			
			if( done )
			{
//				controller.deleteParameters(selectedItems, searchTree.getSelectedTreeItem().getText());
			}
		}
	}


	public void createRow(List<String> items)
	{
		clearTable();

		if( null == table || null == items || items.isEmpty() )
		{
			return;
		}
		
		Iterator<String> it = items.iterator();
		
		while(it.hasNext())
		{
			addTableItem(it.next());
		}
		
		resize();
	}
	
	private void addTableItem(String item)
	{
		StringTokenizer st = new StringTokenizer(item, " ");
		String key = st.nextToken();
		
		if (!st.hasMoreTokens())
		{
			return;
		}
		
		String value = st.nextToken();
		
		TableItem ti = new TableItem(table, SWT.NONE);
		ti.setText(0, key);
		ti.setText(1, value);
		ti.setData(item);
		ti.setBackground(tableBackgroudColor);
	}
	
	public void resize()
	{
		for (TableColumn tc : table.getColumns())
		{
	        resizeColumn(tc);
		}
	}
	
	private static void resizeColumn(TableColumn tableColumn_)
	{
	    tableColumn_.pack();

	}
	
	protected void clearTable()
	{
		if( null == table )
		{
			return;
		}
			
		TableItem[] items = table.getItems();
		
		if( items.length <= 0 )
		{
			return;
		}
		
		for(int i = items.length - 1; i >= 0; i--)
		{
			TableItem item = items[i];
			table.remove(i);
			item.dispose();
		}
	}

	@Override
	public void receivedAction(int type, Object content)
	{
//		if( type == IProtectorActionType.ACTION_RECORD_SELECTED )
//		{
//			updateParameters(controller.getItems((Record)content));
//		}
//
//		else if( type == IProtectorActionType.ACTION_DATA_LOADED )
//		{
//			updateParameters(null);
//		}
//		
//		else if( type == IProtectorActionType.ACTION_EDITING_STATUS_CHANGED)
//		{
//			boolean isEditable = (boolean)content;
//			
//			if(isEditable)
//			{
//				createRightMenu(table, tableListener);
//			}
//			else
//			{
//				table.setMenu(null);
//				
//				if( null != rightClickMenu )
//				{
//					rightClickMenu.dispose();
//				}
//			}
//
//			addToolItem.setEnabled(isEditable);
//		}
//		else if( type == IProtectorActionType.ACTION_ADD_NEW_RECORD_ITEM )
//		{
//			RecordItem ri = (RecordItem)content;
//			
//			addTableItem(ri);
//		}
//		else if( type == IProtectorActionType.ACTION_FOCUS_TABLE )
//		{
//			table.setFocus();
//		}
	}
}
