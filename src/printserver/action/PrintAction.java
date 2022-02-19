package printserver.action;

public class PrintAction extends PrintServerAction {
	
	private String filename;

    public PrintAction(String filename) {
    	super("print");
    	this.filename = filename;        
    }

	@Override
	void doAsPrivileged() {
		PrintServer.print(filename);		
	}
}
