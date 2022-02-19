package printserver.action;

public class QueueAction extends PrintServerAction {
	
	public QueueAction() {
		super("queue");
	}
	
	@Override
	void doAsPrivileged() {
		PrintServer.queue();		
	}
}
