package org.cytoscape.testapp.mytest_cyaction_app.network;

import org.cytoscape.work.AbstractTask;
import org.cytoscape.work.TaskMonitor;
import org.cytoscape.model.CyNetworkManager;
import org.cytoscape.model.CyNetworkFactory;

//import java.util.ArrayList;
import java.util.List;
//import java.util.function.Consumer;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.session.CyNetworkNaming;

public class NetworkBuild extends AbstractTask{

	private final CyNetworkManager netMan;
	private final CyNetworkFactory netFac;
	private final CyNetworkNaming netName;
	
	
	public NetworkBuild(final CyNetworkManager netMan, final CyNetworkFactory netFac, final CyNetworkNaming netName) {
		this.netMan = netMan;
		this.netFac = netFac;
		this.netName = netName;
	}
	
	
	// THAT DOES NOT WORK PROPERLY!
	@Override
	public void run(TaskMonitor arg0) throws Exception {
		// create empty network
		CyNetwork emptyNet = netFac.createNetwork();
		emptyNet.getRow(emptyNet).set(CyNetwork.NAME, netName.getSuggestedNetworkTitle("Dies ist ein Titel."));
		
		// returns default Network information from pre-processed StringDB as List of String -> protein1, protein2, score
		// later?  score[i-1] = Integer.parseInt(Score.get(i));
		List<List<String>> defNet = NetworkDefault.createNetwork("/home/liz/eclipse-workspace/Spielerei/src/TestsFuerCytosApp/InteractionNetwork.txt");
		
		for(int i=0; i<defNet.get(0).size(); i++) {
			CyNode n1 = emptyNet.addNode();
			CyNode n2 = emptyNet.addNode();
			emptyNet.getDefaultNodeTable().getRow(n1.getSUID()).set("name", defNet.get(0).get(i));
			emptyNet.getDefaultNodeTable().getRow(n2.getSUID()).set("name", defNet.get(1).get(i));
			emptyNet.addEdge(n1, n2, true);
		}
		
		
		// Adding Nodes to the Network
//		CyNode node1 = emptyNet.addNode();
//		CyNode node2 = emptyNet.addNode();
//		
//		
//		// naming the new nodes
//		emptyNet.getDefaultNodeTable().getRow(node1.getSUID()).set("name", "erster Knoten");
//		emptyNet.getDefaultNodeTable().getRow(node2.getSUID()).set("name", "zweiter Knoten");
//		
//		// tying the nodes together with an edge
//		emptyNet.addEdge(node1, node2, true);
//		
		// add the network
		netMan.addNetwork(emptyNet);

	}

}
