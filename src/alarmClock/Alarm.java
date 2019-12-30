package alarmClock;
import java.time.*;
import javax.swing.JOptionPane;

public class Alarm {
	private Instant targetTime;
	private LocalDateTime target;
	private String name;
	private String message;
	// constructor for an exact non-relative time (this is the main constructor that should be used)
	public Alarm(String n, String m, int month, int day, int hour, int minute) {
		name = n;
		message = m;
		target = LocalDateTime.of(2019, month, day, hour, minute);
		
	}
	// constructor for an alarm object with a number of hours, minutes and seconds until it goes off.
	public Alarm(String n,String m, long hours, long minutes, long seconds) {
		targetTime = Clock.systemDefaultZone().instant();
		targetTime = targetTime.plusSeconds((hours * 60 * 60) + (minutes * 60) + (seconds));
		target = LocalDateTime.ofInstant(targetTime, Clock.systemDefaultZone().getZone());
		name = n;
		message = m;
		
	}
	// constructor for an alarm object with a number of minutes and seconds until it goes off.
	public Alarm(String n,String m, long minutes, long seconds) {
		targetTime = Clock.systemDefaultZone().instant();
		targetTime = targetTime.plusSeconds((minutes * 60) + (seconds));
		target = LocalDateTime.ofInstant(targetTime, Clock.systemDefaultZone().getZone());
		name = n;
		message = m;
		
	}
	// constructor for an alarm object with a number of seconds until it goes off.
	public Alarm(String n,String m, long seconds) {
		targetTime = Clock.systemDefaultZone().instant();
		targetTime = targetTime.plusSeconds(seconds);
		
		target = LocalDateTime.ofInstant(targetTime, Clock.systemDefaultZone().getZone());
		name = n;
		message = m;
		
	}
	// constructor for a LocalDateTime object
	public Alarm(String n,String m, String time) {
		target = LocalDateTime.parse(time);
		name = n;
		message = m;
		
	}
	
	public boolean expired() {
		//checks if the current time is a later time than the target time and returns the result in boolean format.
		if(target.compareTo(LocalDateTime.ofInstant(Clock.systemDefaultZone().instant(), Clock.systemDefaultZone().getZone())) == -1) {
			return true;
		}
		return false;
	}
	public String getName() {
		return name;
	}
	public String getTarget()
	{
		return target.toString();
	}
	public String getMessage() {
		return message;
	}
	
	public static Alarm copy(Alarm a) {
		return new Alarm(a.getName(),a.getMessage(),a.getTarget());
	}
	public String toString() {
		return name;
	}
	
	public boolean isEqual(Alarm al)
	{
		if(al.getName().equals(this.getName()))
			
		{
			return true;
		}
		return false;
	}
	
	public static void snooze(Alarm a)
	{
		String [] options = new String [] {"Snooze" , "Dismiss"};
		int response = JOptionPane.showOptionDialog(null, "Snooze", "Snooze Alarm", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
		if(response == 0)
			{

			// set new alarm number of minutes/seconds in the future
			Alarm b = new Alarm(a.getName(),a.getMessage(), 0, 10);
			for(int i = 0; i < CSV.alarmInfoList.size(); i++)
			{
				if (CSV.alarmInfoList.get(i).equals(a))
				{
					CSV.alarmInfoList.set(i, b);
					break;
				}
			}
			
			System.out.println("Added 1 mintue to alarm");

			}
		else if(response == 1)
		{
			CSV.remove(a);
		}
	}
}
