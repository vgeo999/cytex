package org.cytoscape.testapp.mytest_cyaction_app.network;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyTable;
import org.cytoscape.work.AbstractTask;
import org.cytoscape.work.TaskMonitor;
import org.cytoscape.work.Tunable;

public class GetNetworkColumn extends AbstractTask {

	
	@Tunable(description  = "Get Tissue Choice")
	public String tiss = "Tissue Choice Name is: ";
	private CyNetwork cyNet;
	
	public GetNetworkColumn(CyNetwork cyNet) {
		this.cyNet = cyNet;
	}
	
	
	@Override
	public void run(TaskMonitor monitor) throws Exception {
		// check network
		if(cyNet == null) {
			System.out.println("There is no network loaded?");
		return;
		}
		CyTable nodeTable = cyNet.getDefaultNetworkTable();
		
		if(nodeTable.getColumn("label")!= null) {
			nodeTable.countMatchingRows(tiss, null);
			System.out.println(tiss + "is chosen by " + nodeTable.countMatchingRows(tiss, null));
		} else {
			System.out.println("Nothing to print here...");
		}
	}

	
	@Override
	public void cancel() {
		cancelled = true;

	}

}
