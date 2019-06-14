package de.wagentim.common.ui;

public interface ITableController
{
	boolean isEditable();

	void recordItemChanged(CellIndex cell, String newValue);

}
