package org.cytoscape.testapp.mytest_cyaction_app.network;

import org.cytoscape.model.CyNetworkFactory;
import org.cytoscape.model.CyNetworkManager;
import org.cytoscape.session.CyNetworkNaming;
import org.cytoscape.work.AbstractTaskFactory;
import org.cytoscape.work.TaskIterator;

public class NetworkNeedsFactory extends AbstractTaskFactory {
	private final CyNetworkManager netMan;
	private final CyNetworkFactory netFac;
	private final CyNetworkNaming netName;
	
	public NetworkNeedsFactory(final CyNetworkManager netMan, final CyNetworkFactory netFac, final CyNetworkNaming netName) {
		this.netFac = netFac;
		this.netMan = netMan; 
		this.netName = netName;
	}
	
	@Override
	public TaskIterator createTaskIterator() {
		// returned a taskIterator - obviously, but I have no clue what that might be
		return new TaskIterator(new NetworkBuild(netMan, netFac, netName));
	}
	
	
}
