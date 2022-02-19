package printserver.action;

import java.security.AccessControlException;
import java.security.Permission;
import java.security.PrivilegedAction;

import printserver.PrintServerPermission;

abstract class PrintServerAction implements PrivilegedAction {
	
	private String actionId;

    public PrintServerAction(String actionId) {
        this.actionId = actionId;
    }

	@Override
	public Object run() {
		Permission permission = new PrintServerPermission(actionId);
		SecurityManager securityManager = System.getSecurityManager();
		if (securityManager != null) {
			try {
                securityManager.checkPermission(permission);
                printPermissionResult(true);
                doAsPrivileged();
            } catch (AccessControlException ace) {
            	printPermissionResult(false);
            }
		}
		
		return null;
	}
	
	private void printPermissionResult(boolean isAccessGranted) {
		System.out.println(String.format("Permission for %s: %s", actionId, isAccessGranted ? "yes" : "no"));
	}
	
	abstract void doAsPrivileged();
}
