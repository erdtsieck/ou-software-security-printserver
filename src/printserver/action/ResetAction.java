package printserver.action;

public class ResetAction extends PrintServerAction {

	
	public ResetAction() {
		super("reset");
	}

	@Override
	void doAsPrivileged() {
		PrintServer.reset();		
	}
}
