package alarmClock;

import java.io.*;

import java.util.*;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;



public class CSV {

	static List<Alarm> alarmInfoList = new ArrayList<>();
	static int listCounter = 0;



	//TODO Alarm Time is 24 hr time for simplicity, date format is  MM-DD
	public static void writeCSV(Alarm a) {

		String fileName = "AlarmInfo.csv";
		// String name = null, message = null;
		//int month = 0, day = 0, hour = 0, minute = 0, seconds = 0, listCounter = 0;

		listCounter = alarmInfoList.size();
		try{
			FileWriter fw = new FileWriter(fileName, true);

			alarmInfoList.add(a);
			System.out.println("listCounter = " + listCounter);
			System.out.println("Am I crasging here?" + alarmInfoList.get(listCounter).getName());
			System.out.println(alarmInfoList.size());
			//Writing the argument inputs
			fw.write(alarmInfoList.get(listCounter).getName() + "," +  alarmInfoList.get(listCounter).getMessage() + "," + alarmInfoList.get(listCounter).getTarget()+"\n");
			fw.close();
			listCounter++;
			System.out.println("Listcounter incrememnted");

			//	}catch (FileNotFoundException e){
			//		e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void writeAfterRemoveCSV()
	{
		String fileName = "AlarmInfo.csv";
		// String name = null, message = null;
		//int month = 0, day = 0, hour = 0, minute = 0, seconds = 0, listCounter = 0;
		listCounter = alarmInfoList.size();

		try{
			FileWriter fw = new FileWriter(fileName, false);

			//Writing the argument inputs
			if(alarmInfoList.size() > 0)
			{
				fw.write(alarmInfoList.get(listCounter-1).getName() + "," +  alarmInfoList.get(listCounter-1).getMessage() + "," + alarmInfoList.get(listCounter-1).getTarget()+"\n");
				fw.close();
			}

		}catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public static void readCSV() 
	{
		String fileName = "AlarmInfo.csv";


		try 
		{
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			String line;
			while ((line = reader.readLine()) != null) 
			{
				String[] values = line.split(",\\s*");
				Alarm al = new Alarm(values[0], values[1], values[2]);
				alarmInfoList.add(al); 

				// If you want to fetch a certain group of information, use alarmInfoList.get() to fetch the whole group
				// From the 2d array

				//output array to console for quick testing
				// System.out.println(alarmInfoList);

			}

			listCounter = alarmInfoList.size();
		}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static JComboBox<Alarm> getList()
	{
		readCSV();
		JComboBox<Alarm> jcbA = new JComboBox<Alarm>();
		for(int i = 0; i < alarmInfoList.size(); i++)
		{
			jcbA.addItem(alarmInfoList.get(i));
		}
		return jcbA;
	}

	public static void remove(Alarm al)
	{
		for(int i = 0; i < alarmInfoList.size(); i++)
		{

			if (alarmInfoList.get(i).equals(al))
			{
				String name = alarmInfoList.get(i).getName();
				alarmInfoList.remove(i);				
				JOptionPane.showMessageDialog(null, "Alarm " + name + " removed.");
				listCounter--;
				writeAfterRemoveCSV();
				break;


			}
		}
	}



}
