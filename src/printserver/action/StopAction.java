package printserver.action;

public class StopAction extends PrintServerAction {

	
	public StopAction() {
		super("stop");
	}

	@Override
	void doAsPrivileged() {
		PrintServer.stop();	
	}
}
