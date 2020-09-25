package de.wagentim.common.db.sqlite;

import java.sql.ResultSet;

public interface IResultSetExtractor<T>
{
	public abstract T extractData(ResultSet rs);
}
