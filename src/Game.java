import java.util.Arrays;
import java.util.Scanner;

public class Game
{
	enum GameResult
	{
		Win,
		Lose
	}
	
	private int mistakes;
	private int maxMistakes;
	private String word;
	private StringBuilder hiddenWord;
	private String usedSymbols;
	
	public Game() 
	{
		mistakes = 0;
		maxMistakes = 6;
		word = DataHolder.getRandomWord();
		hiddenWord = new StringBuilder();
		for(int i = 0; i < word.length(); i++) 
		{
			hiddenWord.append("_");
		}
		usedSymbols = "";
	}
	
	private void printGallows(int mistakes) 
	{
		if(mistakes == 0) 
		{
			System.out.println("      o");    //▉
			System.out.println("     /|\\"); //┌┼┐
			System.out.println("      |");  //  │
			System.out.println("     / \\");// ┌┴┐
		}
		else if(mistakes == 1) 
		{
			System.out.println("");
			System.out.println("");
			System.out.println("      o");
			System.out.println("     /|\\");
			System.out.println("      |");
			System.out.println("     / \\");
			System.out.println(Colors.brown + "     ╦═╦");
			System.out.println("     ║ ║" + Colors.defaultColor);
		}
		else if(mistakes == 2) 
		{
			System.out.println("");
			System.out.println("");
			System.out.println("      o");
			System.out.println("     /|\\");
			System.out.println("      |");
			System.out.println(Colors.brown + " █  " + Colors.defaultColor + " / \\");
			System.out.println(Colors.brown + " █   ╦═╦");
			System.out.println(" █   ║ ║" + Colors.defaultColor);
		}
		else if(mistakes == 3) 
		{
			System.out.println("");
			System.out.println(Colors.brown + " █   ");
			System.out.println(Colors.brown + " █  " + Colors.defaultColor + "  o");
			System.out.println(Colors.brown + " █  " + Colors.defaultColor + " /|\\");
			System.out.println(Colors.brown + " █  " + Colors.defaultColor + "  |");
			System.out.println(Colors.brown + " █ " + Colors.defaultColor + "  / \\");
			System.out.println(Colors.brown + " █   ╦═╦");
			System.out.println(" █   ║ ║" + Colors.defaultColor);
		}
		else if(mistakes == 4) 
		{
			System.out.println(Colors.brown + "▄▄▄▄▄▄▄▄▄▄▄▄");
			System.out.println(" █   ");
			System.out.println(" █  " + Colors.defaultColor + "  o");
			System.out.println(Colors.brown + " █  " + Colors.defaultColor + " /|\\");
			System.out.println(Colors.brown + " █  " + Colors.defaultColor + "  |");
			System.out.println(Colors.brown + " █  " + Colors.defaultColor + " / \\");
			System.out.println(Colors.brown + " █   ╦═╦");
			System.out.println(" █   ║ ║" + Colors.defaultColor);
		}
		else if(mistakes == 5) 
		{
			System.out.println(Colors.brown + "▄▄▄▄▄▄▄▄▄▄▄▄");
			System.out.println(" █    │");
			System.out.println(" █  " + Colors.defaultColor + "  o");
			System.out.println(Colors.brown + " █  " + Colors.defaultColor + " /|\\");
			System.out.println(Colors.brown + " █  " + Colors.defaultColor + "  |");
			System.out.println(Colors.brown + " █  " + Colors.defaultColor + " / \\");
			System.out.println(Colors.brown + " █   ╦═╦");
			System.out.println(" █   ║ ║" + Colors.defaultColor);
		}
		else if(mistakes == 6) 
		{
			System.out.println(Colors.brown + "▄▄▄▄▄▄▄▄▄▄▄▄");
			System.out.println(" █    │");
			System.out.println(" █  " + Colors.defaultColor + "  o");
			System.out.println(Colors.brown + " █  " + Colors.defaultColor + " /|\\");
			System.out.println(Colors.brown + " █  " + Colors.defaultColor + "  |");
			System.out.println(Colors.brown + " █  " + Colors.defaultColor + " / \\");
			System.out.println(Colors.brown + " █   ");
			System.out.println(" █  " + Colors.boldRed + " R.I.P." + Colors.defaultColor);
		}
		
		System.out.println(Colors.boldGreen + "██████████████" + Colors.defaultColor);
	}
	
	private void decryptWord(char symbol) 
	{
		for(int i = 0; i < word.length(); i++) 
		{
			if(word.charAt(i) == symbol) 
			{
				hiddenWord.setCharAt(i, symbol);
			}
		}
	}
	
	private void end(GameResult result) 
	{
		Scanner scanner = new Scanner(System.in);
		if(result == GameResult.Win) 
		{
			printGallows(0);
			DataHolder.setCurrentWinStreak(1);
			DataHolder.setWinCount(1);
			System.out.println(Colors.boldRed + "♥♥♥" + Colors.defaultColor + "Поздравляю! Ты спас своего дед инсайдика!" + Colors.boldRed + "♥♥♥" + Colors.defaultColor );
			
			scanner.nextLine();
		}
		else 
		{
			DataHolder.setCurrentWinStreak(0);
			DataHolder.setLoseCount(1);
			System.out.println("О НЕТ! БЛЯТ, ОН ПОВЕСИЛСЯ ПИЗДЕЦ, ТЫ ЧТ СДЕЛАЛ ДОЛБАЁБ");
			System.out.println("ТЫ УБИЛ ДЕДИНСАЙДИКА ЯВАХУИ");
			System.out.println("КАК ТЫ МОГ НЕ ОТГОДАТ СЛОВО " + word + " ЕБАНАТ");
			scanner.nextLine();
		}
	}
	
	private void loop() 
	{
		Scanner scanner = new Scanner(System.in);
		String line = "";
		while(mistakes < maxMistakes) 
		{
			printGallows(mistakes);
			System.out.println("Слово: " + hiddenWord);
			System.out.println("Использованные символы: " + usedSymbols);
			line = scanner.nextLine();
			if(line.length() < 1)
				continue;
			
			char firstChar = line.toLowerCase().charAt(0); 
			
			if(word.indexOf(firstChar) == -1) 
			{
				if(usedSymbols.indexOf(firstChar) == -1)
					mistakes += 1;
			} 
			else 
				decryptWord(line.toLowerCase().charAt(0));
			
			if(usedSymbols.indexOf(firstChar) == -1)
			{
				usedSymbols += firstChar;
				char[] array = usedSymbols.toCharArray();
				Arrays.sort(array);
				usedSymbols = new String(array);
			}
			
			if(hiddenWord.toString().equals(word)) 
			{
				end(GameResult.Win);
				return;
			}
		}
		
		printGallows(mistakes);
		end(GameResult.Lose);
	}
	
	public void start() 
	{
		loop();
	}
}