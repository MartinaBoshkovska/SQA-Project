package b0ardTesting;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.junit.rules.ExpectedException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.junit.Rule;


@RunWith(Enclosed.class)
public class PitTicketTesting {

	
	@RunWith(Parameterized.class)
	public static class ComponentParamTests {
		
		@Parameters
		public static Collection<Object[]> data() {
	        return Arrays.asList(new Object[][] {     
	                 { 1, "Watcher 1" },  
	                 { 2, "Watcher 2" }, 
	                 { 3, "Watcher 3" },
	                 { 4, "Watcher 4" },
	                 { 5, "Watcher 5"}, 
	                 { 6, "Watcher 6" }  
	           });
	    }
		
		@Parameter(value=0)
		public int res;
		@Parameter(value=1)
		public String name;
    	public static Ticket tick = new Ticket();
		
        @Test
        public void addingWatchersUsingParams() throws Exception {
        	assertEquals(res, tick.addWatchers(name));
        }
		
	}
	
	
	public static class ComponentSingleTests {

		Ticket ticket;
		
		@Test
		public void defaultConstructorTicket(){
			ticket = new Ticket();
			assertNull(ticket.getDescription());
			assertNull(ticket.getAssignTo());
			assertNull(ticket.getTitle());
			assertNull(ticket.getWatchedBy());
			assertNull(ticket.getDeadline());
			assertNull(ticket.getTicketType());
		}
		
		@Test
		public void parametersConstructorTicket(){
			ticket = new Ticket("PIT TEST TESTING TICKET", "PIT TEST", new String[]{"Martina Boshkovska"}, new String[]{"SQA WATCHER"}, null, null, "This is a test pit ticket");
			assertEquals("Martina Boshkovska", ticket.getAssignTo()[0]);
		}
		
		@Test
		public void ticketCalculateDeadlineTest() throws Exception{
			
			DateFormat fmt = new SimpleDateFormat("dd/MMM/yyyy");
			Calendar c = Calendar.getInstance();
	    	c.setTime(new Date()); // Now use today date.
	    	c.add(Calendar.DATE, 5); //Add a 5 day deadline
	    	String deadlineDate = fmt.format(c.getTime());
			
			ticket = new Ticket("PIT TEST TESTING TICKET", "PIT TEST", new String[]{"Martina Boshkovska"}, new String[]{"SQA WATCHER"}, null, null, "This is a test pit ticket");
			assertNull(ticket.getDeadline());
			
			assertEquals(ticket.calculateDeadline(), deadlineDate);
			assertNotNull(ticket.calculateDeadline());
			

			
			//SimpleDateFormat formatter = new SimpleDateFormat("dd/MMM/yyyy", Locale.ENGLISH);
			Date date = fmt.parse(ticket.calculateDeadline());
			Calendar tmp = Calendar.getInstance();
			tmp.setTime(date);
			tmp.add(Calendar.DATE, -5);
			c.setTime(new Date());
			assertEquals(fmt.format(tmp.getTime()), fmt.format(c.getTime()));
			
	    	
	    	assertEquals(11, ticket.calculateDeadline().length());
	    	assertTrue(ticket.calculateDeadline() != null);
			
			assertNull(ticket.calculateDeadline(3));
		}
		
	    @Rule
	    public ExpectedException thrown = ExpectedException.none();
		
		@Test
		public void inheritedCalcuateDeadlineTest() throws Exception{
			
			DeadlineTicket dt = new DeadlineTicket();
			dt.setAssignTo(new String[]{"Assigned Test String"});
			dt.setDesc("This is a test description for DeadlineTicket object");
			dt.setTags(new String [] {"myTag"});
			dt.setTicketType("Calc Deadline Test");
			dt.setTitle("Deadline Ticket Title");
			dt.calculateDeadline(10);
			
			DateFormat fmt = new SimpleDateFormat("dd/MMM/yyyy");
			Calendar c = Calendar.getInstance();
	    	c.setTime(new Date()); // Now use today date.
	    	c.add(Calendar.DATE, 10); //Add a 5 day deadline
	    	String deadlineDate = fmt.format(c.getTime());
	    	
	    	assertEquals(dt.getDeadline(), deadlineDate);
	    	

	    	c = Calendar.getInstance();
		    c.setTime(new Date()); // Now use today date.
		    c.add(Calendar.DATE, 0); //Add a 5 day deadline
		    deadlineDate = fmt.format(c.getTime());
	    	
	    	dt.calculateDeadline(0);
	    	assertEquals(deadlineDate, dt.getDeadline());
	    	
	    	c = Calendar.getInstance();
		    c.setTime(new Date()); // Now use today date.
		    c.add(Calendar.DATE, 365); //Add a 5 day deadline
		    deadlineDate = fmt.format(c.getTime());
	    	dt.calculateDeadline(365);
	    	assertEquals(deadlineDate, dt.getDeadline());
	    	
	    	thrown.expect(Exception.class);
	    	dt.calculateDeadline(-5);
	    		
	    	thrown.expect(Exception.class);
	    	dt.calculateDeadline(500);
	    	
			
		}
		
		@Test 
		public void settersTesting(){
			ticket = new Ticket("PIT TEST TESTING TICKET", "PIT TEST", new String[]{"Martina Boshkovska"}, new String[]{"SQA WATCHER"}, null, null, "This is a test pit ticket");
			assertEquals(ticket.getDescription(),"This is a test pit ticket");
			int returnNum = ticket.setDesc("This is a test pit ticket");
			assertEquals(0, returnNum);
			returnNum = ticket.setDesc("This is the new description");
			assertEquals(1, returnNum);
			assertEquals(ticket.getDescription(),"This is the new description");
			
			
			assertTrue(ticket.getTags() == null);
			ticket.setTags(new String[]{"Pit Tag 1", "Pit Tag 2"});
			assertTrue(ticket.getTags().length == 2);
			
			ticket.setDeadline("12/Nov/2018");
			assertTrue(ticket.getDeadline()== "12/Nov/2018");
			
			ticket.setWatchedBy(new String[]{"Watcher for Set method"});
			assertEquals("Watcher for Set method", ticket.getWatchedBy()[0]);
			
			ticket.addWatchers("Second watcher");
			assertEquals("Second watcher", ticket.getWatchedBy()[1]);
			
			
			
		}
		
	}
	

}
