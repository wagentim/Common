package de.etas.tef.app;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertyHandler
{
	public Properties loadProperties()
	{
		Properties properties = new Properties();
		try
		{
			String propertyFile = getCurrentPath() + File.separator + IGlobalConstants.PROPERTIES_FILE;
			checkFile(propertyFile, true);
			properties.load(new FileReader(new File(propertyFile)));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return properties;
	}
	
	public String getCurrentPath()
	{
		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		return s;
	}
	
	/**
	 * Validating if the expected file already existed or as required to create one
	 * 
	 * @param file	The file to check
	 * @param createNewFile	@TRUE if the file do not exist, then create one. @FALSE only check the exist status
	 * @return	@FLASE if the give {@link String} is invalid or Error by creating the file or file do not exist @TRUE create file successfully or checking file status running successfully
	 */
	public final boolean checkFile(final String file, final boolean createNewFile)
	{
		if( null == file || file.isEmpty() )
		{
			return false;
		}
		
		Path path = Paths.get(file);
		
		if(!Files.exists(path, LinkOption.NOFOLLOW_LINKS))
		{
			if( createNewFile )
			{
				try
				{
					Files.createFile(path);
					
					return true;
				} 
				catch (IOException e)
				{
					return false;
				}
			}
			
			return true;
		}
		
		return false;
	}
}
