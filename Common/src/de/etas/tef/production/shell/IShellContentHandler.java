package de.etas.tef.production.shell;

import java.util.List;

public interface IShellContentHandler
{
	String getShellCommand();

	void processContent(List<String> sb_content);

	void processError(List<String> sb_error);

	String getShellFile();

	void finish();
}
