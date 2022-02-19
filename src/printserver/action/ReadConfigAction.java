package printserver.action;

public class ReadConfigAction extends PrintServerAction {

	private String parameter;

	public ReadConfigAction(String parameter) {
		super("readConfig");
		this.parameter = parameter;
	}

	@Override
	void doAsPrivileged() {
		PrintServer.readConfig(parameter);		
	}
}
