package de.etas.tef.production.shell;

public interface IShellContentHandler
{
	String getShellCommand();

	void processContent(StringBuffer sb_content);

	void processError(StringBuffer sb_error);

	String getShellFile();
}
