package de.etas.tef.production.shell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GeneralShellExecutor implements IShellExecutor
{
	private static final Logger logger = LoggerFactory.getLogger(GeneralShellExecutor.class);
	private final List<String> content;
	private final List<String> error;
	
	public GeneralShellExecutor()
	{
		content = new ArrayList<String>();
		error = new ArrayList<String>();
	}
	
	public void exec(IShellContentHandler handler)
	{
		String file = getClass().getClassLoader().getResource(handler.getShellFile()).getFile().substring(1);
		
		String command = handler.getShellCommand() + file;
		
		logger.info("Execute Commander: {}", command);
		
		Process shellProcess;
		try
		{
			shellProcess = Runtime.getRuntime().exec(command);
			shellProcess.getOutputStream().close();
			
			String line;

			BufferedReader stdout = new BufferedReader(new InputStreamReader(
					shellProcess.getInputStream(), "utf-8"));
			while ((line = stdout.readLine()) != null)
			{
				
				if(line != null && !line.isEmpty())
					content.add(line);
			}
			stdout.close();

			BufferedReader stderr = new BufferedReader(new InputStreamReader(
					shellProcess.getErrorStream(), "utf-8"));
			
			while ((line = stderr.readLine()) != null)
			{
				if(line != null && !line.isEmpty())
					error.add(line);
			}
			
			stderr.close();
			
			handler.processContent(content);
			handler.processError(error);
			handler.finish();
			
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
