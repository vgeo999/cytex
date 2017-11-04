package org.cytoscape.testapp.mytest_cyaction_app.network;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.work.AbstractTaskFactory;
import org.cytoscape.work.TaskIterator;

public class GetNetworkColumnFactory extends AbstractTaskFactory{
	
	private CyApplicationManager cyAppMan;
	
	public GetNetworkColumnFactory(CyApplicationManager cyAppMan) {
		this.cyAppMan = cyAppMan;
	}

	public TaskIterator createTaskIterator() {
		return new TaskIterator(new GetNetworkColumn(this.cyAppMan.getCurrentNetwork()));
	}
}
