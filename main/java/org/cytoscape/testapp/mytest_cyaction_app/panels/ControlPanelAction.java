package org.cytoscape.testapp.mytest_cyaction_app.panels;

import java.awt.event.ActionEvent;
import org.cytoscape.application.swing.AbstractCyAction;
import org.cytoscape.application.swing.CySwingApplication;
import org.cytoscape.application.swing.CytoPanel;
import org.cytoscape.application.swing.CytoPanelName;
import org.cytoscape.application.swing.CytoPanelState;

public class ControlPanelAction extends AbstractCyAction{	
	
	/**  Sets up a Control Panel
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CySwingApplication desktopapp;
	private final CytoPanel cytoPanelWest;
	private ControlPanel controlPanel;
	
	public ControlPanelAction(CySwingApplication desktopapp, ControlPanel controlPanel) {
		
		super("Control Panel");
		setPreferredMenu("Apps");
		
		this.desktopapp = desktopapp;
		this.cytoPanelWest = this.desktopapp.getCytoPanel(CytoPanelName.WEST);
		this.controlPanel = controlPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// If the state of the cytoPanelWest is HIDE, show it
				if (cytoPanelWest.getState() == CytoPanelState.HIDE) {
					cytoPanelWest.setState(CytoPanelState.DOCK);
				}	
				
				// Select my panel
				int index = cytoPanelWest.indexOfComponent(controlPanel);
				if (index == -1) {
					return;
				}
				cytoPanelWest.setSelectedIndex(index);
		
	}
}
