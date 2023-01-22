import java.io.*;
import java.nio.file.*;

public class DataHolder 
{
	private static int winCount = 0;
	private static int loseCount = 0;
	private static int currentWinStreak = 0;
	private static int maxWinStreak = 0;
	private static String[] words;
	
	private DataHolder() {}
	
	public static int getWinCount()
	{
		return winCount;
	}
	
	public static void setWinCount(int value)
	{
		if(value == 1)
			winCount++;
	}
	
	public static int getLoseCount()
	{
		return loseCount;
	}
	
	public static void setLoseCount(int value)
	{
		if(value == 1)
			loseCount++;
	}
	
	public static int getCurrentWinStreak()
	{
		return currentWinStreak;
	}
	
	public static void setCurrentWinStreak(int value)
	{
		if(value == 1) 
		{
			currentWinStreak++;
			if(currentWinStreak > maxWinStreak)
				maxWinStreak = currentWinStreak;
		}
		else
			currentWinStreak = 0;
	}
	
	public static int getMaxWinStreak()
	{
		return maxWinStreak;
	}

	public static String getRandomWord() 
	{
		return words[(int)(Math.random() * words.length)];
	}
	
	public static void write(String fileName, String content) 
	{
		Path dataPath = Path.of(fileName);
		try 
		{
			if(Files.exists(dataPath) == false)
				Files.createFile(dataPath);
			
			Files.writeString(dataPath, content);
		}
		catch(IOException e) 
		{
			throw new RuntimeException(e);
		}
	}
	
	public static void saveData() 
	{
		String content = winCount + " " + loseCount + " " + currentWinStreak + " " + maxWinStreak;
		write("data", content);
	}
	
	public static String readContent(String fileName) 
	{
		String content;
		Path filePath = Path.of(fileName);
		try 
		{
			if(Files.exists(filePath) == false)
				return null;
			content = Files.readString(filePath);
		}
		catch(IOException e) 
		{
			throw new RuntimeException(e);
		}

		return content;
	}
	
	private static void loadWords() 
	{
		String content = readContent("words");
		words = content.split(", ");
	}
	
	private static void loadStatistics() 
	{
		String content = readContent("data");
		
		String[] data = content.split(" ");
		winCount = Integer.parseInt(data[0]);
		loseCount = Integer.parseInt(data[1]);
		currentWinStreak = Integer.parseInt(data[2]);
		maxWinStreak = Integer.parseInt(data[3]);

	}
	
	public static void loadData() 
	{
		loadStatistics();
		loadWords();
	}
}
