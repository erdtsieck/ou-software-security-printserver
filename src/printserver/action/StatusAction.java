package printserver.action;

public class StatusAction extends PrintServerAction {

	
	public StatusAction() {
		super("status");
	}

	@Override
	void doAsPrivileged() {
		PrintServer.status();	
	}
}
