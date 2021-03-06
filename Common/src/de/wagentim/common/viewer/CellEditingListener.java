package de.wagentim.common.viewer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ControlEditor;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.TypedEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.TreeItem;

import de.etas.tef.app.IGlobalConstants;

public abstract class CellEditingListener implements MouseListener, KeyListener, SelectionListener
{
	
	private final Composite composite;
	protected final ITableController controller;

	protected ControlEditor editor = null;
	protected String newValue = IGlobalConstants.EMPTY_STRING;
	protected String oldValue = IGlobalConstants.EMPTY_STRING;
	
	public CellEditingListener(Composite composite, ITableController controller)
	{
		this.composite = composite;
		this.controller = controller;
		
		editor = getNewEditor();
	}

	protected Composite getComposite()
	{
		return composite;
	}
	
	protected abstract ControlEditor getNewEditor();
	

	protected void disposeEditor()
	{
		if( null == editor )
		{
			return;
		}
		
		Text oldEditor = (Text)editor.getEditor();
		
		if( null == oldEditor )
		{
			return;
		}

		if (newValue.isEmpty())
		{
			oldEditor.setText(oldValue);
		}

		oldEditor.dispose();

		if (!newValue.equals(oldValue))
		{
			updateWithNewValue();
		}
		
		editor.setEditor(null);
	}
	
	protected abstract void updateWithNewValue();
	
	protected abstract Item getSelectedItem(TypedEvent event);

	@Override
	public void mouseDoubleClick(MouseEvent event)
	{
		boolean isEditable = controller.isEditable();
		
		if(!isEditable)
		{
			final Item item = getSelectedItem(event);
			
			if( item instanceof TableItem )
			{
				Clipboard clipboard = new Clipboard(composite.getDisplay());
				String textData = ((TableItem)item).getText(1);
		        TextTransfer textTransfer = TextTransfer.getInstance();
		        Transfer[] transfers = new Transfer[]{textTransfer};
		        Object[] data = new Object[]{textData};
		        clipboard.setContents(data, transfers);
		        clipboard.dispose();

			}
			
			return;
		}
		
		handleModification(event);
	}
	
	protected void handleModification(TypedEvent event)
	{
		disposeEditor();

		final Item item = getSelectedItem(event);
        
        if (item == null)
        {
          return;
        }
        Text newEditor = new Text(getComposite(), SWT.NONE);
        
        if( item instanceof TreeItem)
        {
        	newValue = oldValue = item.getText();
        }
        else if (item instanceof TableItem)
        {
        	newValue = oldValue = ((TableItem)item).getText(getCell().getColumn());
        }
        
        newEditor.setText(oldValue);
        
        newEditor.addKeyListener(new KeyListener()
		{
			
			@Override
			public void keyReleased(KeyEvent event)
			{
				if(event.keyCode == SWT.CR)
				{
					disposeEditor();
				}
				else if(event.keyCode == SWT.ESC)
				{
					newValue = IGlobalConstants.EMPTY_STRING;
					disposeEditor();
				}
			}
			
			@Override
			public void keyPressed(KeyEvent arg0)
			{
				
			}
		});
        
        newEditor.addModifyListener(new ModifyListener()
		{
			
			@Override
			public void modifyText(ModifyEvent event)
			{
				Text text = (Text) editor.getEditor();
				
				if(null == text)
				{
					return;
				}
				
				newValue = text.getText();
				setNewValueByModify();
			}

		});
        
        newEditor.selectAll();
        newEditor.setFocus();
        setNewEditor(newEditor, item);
	}
	
	protected abstract CellIndex getCell();

	protected abstract void setNewValueByModify();
	
	protected abstract void setNewEditor(Text newEditor, Item item);

	@Override
	public void mouseDown(MouseEvent event)
	{
		disposeEditor();
	}

	@Override
	public void mouseUp(MouseEvent event)
	{
	}


	@Override
	public void keyPressed(KeyEvent keyEvent)
	{
		if( keyEvent.keyCode == SWT.SPACE )
		{
			boolean isEditingLocked = controller.isEditable();
			
			if(isEditingLocked)
			{
				return;
			}
			
			handleModification(keyEvent);
		}
		
		if(((keyEvent.stateMask & SWT.CTRL) == SWT.CTRL) && ((keyEvent.keyCode == 'c') || (keyEvent.keyCode == 'C')) )
		{
			final Item item = getSelectedItem(keyEvent);
			
			if( item instanceof TableItem )
			{
				Clipboard clipboard = new Clipboard(composite.getDisplay());
				String textData = ((TableItem)item).getText(1);
		        TextTransfer textTransfer = TextTransfer.getInstance();
		        Transfer[] transfers = new Transfer[]{textTransfer};
		        Object[] data = new Object[]{textData};
		        clipboard.setContents(data, transfers);
		        clipboard.dispose();
			}
		}
		
		if(((keyEvent.stateMask & SWT.CTRL) == SWT.CTRL) && ((keyEvent.keyCode == 'v') || (keyEvent.keyCode == 'V')) )
		{
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0)
	{
		
	}
}
