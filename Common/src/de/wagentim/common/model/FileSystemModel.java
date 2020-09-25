package de.wagentim.common.model;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

public class FileSystemModel
{
	private Path root;
	
	public void setRoot(Path root)
	{
		if(root == null)
		{
			return;
		}
		
		this.root = root;
	}
	
	public Set<Path> listFilesUsingDirectoryStream(Path dir) throws IOException
	{
		Set<Path> fileList = new HashSet<>();
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir))
		{
			for (Path path : stream)
			{
				if (!Files.isDirectory(path))
				{
					fileList.add(path);
				}
			}
		}
		return fileList;
	}

	
	public static void main(String[] args)
	{
		FileSystemModel model = new FileSystemModel();
		Path path = Paths.get("d:");
	}
}
