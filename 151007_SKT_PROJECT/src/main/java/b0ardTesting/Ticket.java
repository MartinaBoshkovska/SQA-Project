package b0ardTesting;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Ticket{

	String title;
	String ticketType;
	String [] assignTo;
	String [] watchedBy;
	String deadline;
	String [] tags;
	String desc;
	
	
	public Ticket(){
		this.title = null;
		this.ticketType = null;
		this.assignTo = null;
		this.watchedBy = null;
		this.deadline= null;
		this.tags = null;
		this.desc = null;
	}
	

	public String calculateDeadline(int days) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	public Ticket(String title, String ticketType, String[] assignTo,
			String[] watchedBy, String deadline, String[] tags, String desc) {
		super();
		this.title = title;
		this.ticketType = ticketType;
		this.assignTo = assignTo;
		this.watchedBy = watchedBy;
		this.deadline = deadline;
		this.tags = tags;
		this.desc = desc;
	}
	
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTicketType() {
		return ticketType;
	}

	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	}

	public String[] getAssignTo() {
		return assignTo;
	}

	public void setAssignTo(String[] assignTo) {
		this.assignTo = assignTo;
	}

	public String[] getWatchedBy() {
		return watchedBy;
	}

	public void setWatchedBy(String[] watchedBy) {
		this.watchedBy = watchedBy;
	}

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}
	
	public int setDesc(String m){
		if(m.equals(desc))
			return 0;
		this.desc = m;
		return 1;
	}
	
	public String getDescription(){
		return desc;
	}
	
	public String calculateDeadline(){
		
		DateFormat fmt = new SimpleDateFormat("dd/MMM/yyyy");
		Calendar c = Calendar.getInstance();
    	c.setTime(new Date()); // Now use today date.
    	c.add(Calendar.DATE, 5); //Add a 5 day deadline
    	String deadlineDate = fmt.format(c.getTime());
    	this.deadline = deadlineDate;
		return deadline;
	}
	
	public int addWatchers(String m){
		String [] tmp;
		
		if(watchedBy == null)
		{
			watchedBy = new String[1];
			watchedBy[0] = m;
			return watchedBy.length ;
		}
		
		tmp = new String[watchedBy.length + 1];
		for(int i = 0; i < watchedBy.length; i++){
			tmp[i] = watchedBy[i];
		}
		tmp[watchedBy.length] = m;
		watchedBy = new String[tmp.length];
		for(int i = 0; i < tmp.length; i++){
			watchedBy[i] = tmp[i];
		}
		
		return watchedBy.length;
		
	}
}

class DeadlineTicket extends Ticket{
	
	@Override
	public String calculateDeadline(int days) throws Exception {
		
//		try{
			if(days<0 || days>365){
				throw new Exception();
			}
//		}
//		catch(Exception e){
//			super.calculateDeadline();
//			System.out.print("Incorrect number of days for deadline");
//		}
		
		
		DateFormat fmt = new SimpleDateFormat("dd/MMM/yyyy");
		Calendar c = Calendar.getInstance();
    	c.setTime(new Date()); // Now use today date.
    	c.add(Calendar.DATE, days); //Add a 5 day deadline
    	String deadlineDate = fmt.format(c.getTime());
    	this.deadline = deadlineDate;
    	
		return deadline;
	}
	
}

