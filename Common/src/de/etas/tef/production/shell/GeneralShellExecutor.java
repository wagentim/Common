package de.etas.tef.production.shell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GeneralShellExecutor implements IShellExecutor
{
	private final IShellContentHandler handler;
	
	public GeneralShellExecutor(final IShellContentHandler handler)
	{
		this.handler = handler;
	}
	
	public void exec()
	{
		String command = handler.getShellCommand() + handler.getShellFile();
		Process shellProcess;
		try
		{
			shellProcess = Runtime.getRuntime().exec(command);
			shellProcess.getOutputStream().close();
			
			String line;
			StringBuffer sb_content = new StringBuffer();
			StringBuffer sb_error = new StringBuffer();

			BufferedReader stdout = new BufferedReader(new InputStreamReader(
					shellProcess.getInputStream(), "utf-8"));
			while ((line = stdout.readLine()) != null)
			{
				sb_content.append(line);
			}
			stdout.close();

			BufferedReader stderr = new BufferedReader(new InputStreamReader(
					shellProcess.getErrorStream(), "utf-8"));
			
			while ((line = stderr.readLine()) != null)
			{
				sb_error.append(line);
			}
			
			stderr.close();
			
			handler.processContent(sb_content);
			handler.processError(sb_error);
			
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
