package printserver.action;

public class StartAction extends PrintServerAction {

	public StartAction() {
		super("start");
	}

	@Override
	void doAsPrivileged() {
		PrintServer.start();	
	}
}
