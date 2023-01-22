import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MainMenu
{
	private static Achievement[] achievements;
	
	private static void printInfo()
	{
		System.out.println("");
		System.out.println("Введите одну из следующих операций:");
		System.out.println("1. Начать игру.");
		System.out.println("2. Посмотреть статистику.");
		System.out.println("3. Загрузить своё слово в базу.");
		System.out.println("4. Достижения.");
		System.out.println("5. Закончить работу программы.");
	}
	
	private static void loopCycle() 
	{
		System.out.println("Добро пожаловать в Виселицу!");
		System.out.println("Помоги дед инсайду найти загаданное слово, иначе он повесится!");
		
		Scanner scanner = new Scanner(System.in);
		String line = "";
		do
		{
			printInfo();
			line = scanner.nextLine();
			
			switch(line) 
			{
				case "1":
					Game game = new Game();
					game.start();
					break;
				case "2":
					showStatistics();
					break;
				case "3":
					addNewWord();
					break;
				case "4":
					reloadAchievements();
					showAchievements();
					break;
				case "5":
					break;
				default:
					System.out.println("Навалил кринжа 0_0");
					break;
					
			}
		}while(line.equals("5") == false);
	}
	
	private static void showStatistics() 
	{
		System.out.println("Количество убитых дединсайдов: " + DataHolder.getLoseCount());
		System.out.println("Количество спасённых дединсайдов: " + DataHolder.getWinCount());
		System.out.println("Максимальное количество подряд спасённых дединсайдов: " + DataHolder.getMaxWinStreak());
		System.out.println("Текущее количество подряд спасённых дединсайдов: " + DataHolder.getCurrentWinStreak());
		System.out.print("Нажмите Enter для продолжения.");
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
	}
	
	private static boolean checkWordCorrectness(String word) 
	{
		if(word.length() == 0)
			return true;
		
		for(int i = 0; i < word.length(); i++) 
			if(word.charAt(i) < 'а' || word.charAt(i) > 'я')
				return true;
		
		return false;
	}
	
	private static String generateNewContent(String newWord) 
	{
		File file = new File("words");
		String content;
		try 
		{
			if(file.exists() == false)
				return null;
			
			Scanner scanner = new Scanner(file);
			content = scanner.nextLine();
		}
		catch(FileNotFoundException e) 
		{
			throw new RuntimeException(e);
		}
		
		return content + ", " + newWord;
	}
	
	private static void addNewWord() 
	{
		Scanner scanner = new Scanner(System.in);
		String line = scanner.nextLine().toLowerCase();
		
		if(checkWordCorrectness(line)) 
		{
			System.out.println("Введённое слово неккоректно");
			return;
		}
		
		DataHolder.write("words", generateNewContent(line));
	}
	
	private static void showAchievements() 
	{
		for(int i = 0; i < achievements.length; i++) 
		{
			System.out.println(achievements[i].getTitle());
			System.out.println(achievements[i].getCondition());
			if(achievements[i].getIsReceived())
				System.out.println("Достижение получено");
			else
				System.out.println("Достижение не получено");
			System.out.println("");
		}
	}
	
	private static void reloadAchievements() 
	{
		achievements[0].setIsReceived(DataHolder.getWinCount() >= 20);
		achievements[1].setIsReceived(DataHolder.getLoseCount() >= 20);
		achievements[2].setIsReceived(DataHolder.getMaxWinStreak() >= 5);
	}

	private static void loadAchievements() 
	{
		achievements = new Achievement[3];
		
		achievements[0] = new Achievement("Спаситель мёртвых", "Спасите 20 дед инсайдов", false);
		achievements[1] = new Achievement("Палач", "Убейте 20 дед инсайдов", false);
		achievements[2] = new Achievement("Ультимативный спаситель мёртвых", "Спасите 5 дед инсайдов подряд", false);
		
		reloadAchievements();
	}
	
	public static void main(String[] args)
	{
		DataHolder.loadData();
		loadAchievements();
		loopCycle();
		DataHolder.saveData();
	}
}