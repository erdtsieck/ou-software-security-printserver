package printserver.action;

public class TopQueueAction extends PrintServerAction {

	private int job;

	public TopQueueAction(int job) {
		super("topQueue");
		this.job = job;
	}

	@Override
	void doAsPrivileged() {
		PrintServer.topQueue(job);
	}
}
