package de.wagentim.common.viewer;

public interface ITableController
{
	boolean isEditable();

	void recordItemChanged(CellIndex cell, String newValue);

}
