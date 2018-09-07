package b0ardTesting;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	CreatingUser.class,
	LoggingIn.class,
	CreatingNewBoard.class,
	CreatingNewPanel.class,
	ChangingPrivileges.class,
	CreatingNewTicket.class
	})
public class AllTests {
	// Vo proektot se opfateni: Unit Testovi so jUnit 4 
	//(asserts, parametrizirani testovi, testiranje na iskluchoci)
	// koristen e TestSuite
	// koristena e klasa na koja e izvedeno Mutacisko testiranje so Pitclipse
	// 
	

}
