package printserver;

import java.util.*;
import java.security.Principal;
import java.security.PrivilegedAction;
import javax.security.auth.*;
import javax.security.auth.login.*;

import printserver.action.*;
import printserver.module.PrintServerLoginModule;

public class Main {

    public static void main(String[] args) {
    	
        for (String username : PrintServerLoginModule.GetUserNames()) {
        	
        	LoginCallbackHandler loginCallbackHandler = new LoginCallbackHandler();
            LoginContext loginContext = null;
            try {
                loginContext = new LoginContext("PrintServer", loginCallbackHandler);
            } catch (Exception le) {
                System.err.println("Cannot create LoginContext. "
                        + le.getMessage());
                System.exit(-1);
            }
            loginCallbackHandler.setUsername(username);

            // the user has 3 attempts to authenticate successfully
            int i;
            for (i = 0; i < 3; i++) {
                try {

                    // attempt authentication
                    loginContext.login();

                    // if we return with no exception, authentication succeeded
                    break;

                } catch (LoginException le) {
                    System.err.println("Authentication failed:");
                    System.err.println("  " + le.getMessage());
                    try {
                        Thread.currentThread().sleep(3000);
                    } catch (Exception e) {
                        // ignore
                    }
                }
            }

            // did they fail three times?
            if (i == 3) {
                System.out.println("Sorry");
                System.exit(-1);
            }

            Subject subject = loginContext.getSubject();
            System.out.println(String.format("Username: %s", username));
            System.out.println(subject.getPrincipals().toArray()[0]);

            // now try to execute the all actions as the authenticated Subject
            for (PrivilegedAction action : actionsToPerform()) {
                Subject.doAsPrivileged(subject, action, null);
            }
            
            System.out.println();
        }

        System.exit(0);

    }

    private static ArrayList<PrivilegedAction> actionsToPerform() {
        ArrayList<PrivilegedAction> actions = new ArrayList<PrivilegedAction>();
        actions.add(new PrintAction("foo.txt"));
        actions.add(new QueueAction());
        actions.add(new TopQueueAction(100));
        actions.add(new StartAction());
        actions.add(new StopAction());
        actions.add(new ResetAction());
        actions.add(new StatusAction());
        actions.add(new ReadConfigAction("foo"));
        actions.add(new SetConfigAction("foo", "foo"));   
        
        return actions;
    }

}
