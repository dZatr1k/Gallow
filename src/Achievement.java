
public class Achievement
{
	private String title;
	private String condition;
	private boolean isReceived;
	
	public Achievement(String title1, String condition1, boolean isReceived1) 
	{
		title = title1;
		condition = condition1;
		isReceived = isReceived1;
	}
	
	public String getTitle() 
	{
		return title;
	}
	
	public String getCondition() 
	{
		return condition;
	}
	
	public boolean getIsReceived() 
	{
		return isReceived;
	}
	
	public void setIsReceived(boolean value) 
	{
		isReceived = value;
	}
}
