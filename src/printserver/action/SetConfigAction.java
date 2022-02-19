package printserver.action;

public class SetConfigAction extends PrintServerAction {

	private String parameter;
	private String value;

	public SetConfigAction(String parameter, String value) {
		super("setConfig");
		this.parameter = parameter;
		this.value = value;
	}
	
	@Override
	void doAsPrivileged() {
		PrintServer.setConfig(parameter, value);		
	}
}
