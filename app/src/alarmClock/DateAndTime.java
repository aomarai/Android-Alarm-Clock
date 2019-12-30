package alarmClock;

import javax.swing.JComboBox;

public class DateAndTime
{

	public static JComboBox<Integer> monthTime()
	{
		JComboBox<Integer> monthTime = new JComboBox<Integer>();
		for(int i = 1; i < 13; i++)
		{
			monthTime.addItem(i);
		}

		return monthTime;
	}

	public static JComboBox<Integer> day(int month)
	{
		JComboBox<Integer> day = new JComboBox<Integer>();

		switch(month)
		{

		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			for (int i = 1; i < 32; i++)
			{
				day.addItem(i);
			}
			break;
			
		case 2:
			for (int i = 1; i < 29; i++)
			{
				day.addItem(i);
			}
			break;
		case 4:
		case 6:
		case 9: 
		case 11:
			for (int i = 1; i < 31; i++)
			{
				day.addItem(i);
			}
			break;
		}
		return day;
		
	}
	
	public static JComboBox<Integer> minute()
	{
		JComboBox<Integer> minute = new JComboBox<Integer>();
		for(int i = 0; i < 61; i++)
		{
			minute.addItem(i);
		}

		return minute;
	}
	
	public static JComboBox<Integer> time()
	{
		JComboBox<Integer> time = new JComboBox<Integer>();
		for(int i = 1; i < 25; i++)
		{
			time.addItem(i);
		}
		
		return time; 
	}
	



}