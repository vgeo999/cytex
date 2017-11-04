package org.cytoscape.testapp.mytest_cyaction_app.internal;


import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.swing.AbstractCyAction;
import org.cytoscape.model.CyTable;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.presentation.property.BasicVisualLexicon;
import org.cytoscape.view.vizmap.VisualMappingFunctionFactory;
import org.cytoscape.view.vizmap.VisualMappingManager;
import org.cytoscape.view.vizmap.VisualStyle;
import org.cytoscape.view.vizmap.VisualStyleFactory;
import org.cytoscape.view.vizmap.mappings.ContinuousMapping;


/**
 * Creates a new menu item under Apps menu section.
 *
 */
public class MenuAction extends AbstractCyAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CyApplicationManager cyApplicationManagerServiceRef;
	private VisualStyleFactory visualStyleFactoryServiceRef;
	private VisualMappingManager vmmServiceRef;
	private VisualMappingFunctionFactory vmfFactoryC;
	private CyNetworkView netView;

	public MenuAction(final String menuTitle, CyApplicationManager cyApplicationManagerServiceRef,VisualMappingManager vmmServiceRef, 
			VisualStyleFactory visualStyleFactoryServiceRef, VisualMappingFunctionFactory vmfFactoryC, CyNetworkView netView) {
		
		
		super(menuTitle, cyApplicationManagerServiceRef, null, null);
		setPreferredMenu("Apps");
		
		this.cyApplicationManagerServiceRef = cyApplicationManagerServiceRef;
		this.visualStyleFactoryServiceRef = visualStyleFactoryServiceRef;
		this.vmmServiceRef =  vmmServiceRef;
		this.vmfFactoryC	= vmfFactoryC;
		this.netView = netView;
		
	}

	/**
	 *  Some Documentation
	 *
	 * @param e uuuuund Action
	 */
	
	public void actionPerformed(ActionEvent e) {

		
		VisualStyle vs = this.visualStyleFactoryServiceRef.createVisualStyle("Lisas Test Visual Style");
		this.vmmServiceRef.addVisualStyle(vs);
		
 		CyTable attrForTest = cyApplicationManagerServiceRef.getCurrentNetwork().getDefaultNodeTable();
		
		// continuous mapping for expressions		
		ContinuousMapping cMapping = (ContinuousMapping) this.vmfFactoryC.createVisualMappingFunction("GTEX.1117F", float.class, BasicVisualLexicon.NODE_FILL_COLOR);

				
		vs.addVisualMappingFunction(cMapping);
		vs.apply(netView);
		netView.updateView();
		
	}
}
