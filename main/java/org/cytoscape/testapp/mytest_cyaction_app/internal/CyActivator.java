package org.cytoscape.testapp.mytest_cyaction_app.internal;

import java.util.Properties;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.swing.CyAction;
import org.cytoscape.application.swing.CySwingApplication;
import org.cytoscape.application.swing.CytoPanelComponent;
import org.cytoscape.model.CyNetworkManager;
import org.cytoscape.model.CyNetworkFactory;
import org.cytoscape.session.CyNetworkNaming;
import org.cytoscape.testapp.mytest_cyaction_app.network.GetNetworkColumnFactory;
import org.cytoscape.testapp.mytest_cyaction_app.network.NetworkNeedsFactory;
import org.cytoscape.testapp.mytest_cyaction_app.panels.ControlPanel;
import org.cytoscape.testapp.mytest_cyaction_app.panels.ControlPanelAction;
import org.cytoscape.service.util.AbstractCyActivator;
import org.cytoscape.view.vizmap.VisualMappingFunctionFactory;
import org.cytoscape.view.vizmap.VisualMappingManager;
import org.cytoscape.view.vizmap.VisualStyleFactory;
import org.cytoscape.work.TaskFactory;
import org.osgi.framework.BundleContext;




public class CyActivator extends AbstractCyActivator {
	
		public CyActivator() {
			super();
		};

		
		public void start(BundleContext bc) {
			
			// Menu-Action Stuff:
			CyApplicationManager cyApplicationManagerServiceRef = getService(bc,CyApplicationManager.class);
			VisualMappingManager vmmServiceRef = getService(bc,VisualMappingManager.class);			
			VisualStyleFactory visualStyleFactoryServiceRef = getService(bc,VisualStyleFactory.class);
			VisualMappingFunctionFactory vmfFactoryC = getService(bc,VisualMappingFunctionFactory.class, "(mapping.type=continuous)");

			MenuAction createVisualStyleAction = new MenuAction( "LisasLayout", cyApplicationManagerServiceRef, vmmServiceRef, visualStyleFactoryServiceRef, 
					vmfFactoryC, cyApplicationManagerServiceRef.getCurrentNetworkView());
			
			
			// ControlPanel Stuff:
			CySwingApplication cytodeskService = getService(bc, CySwingApplication.class);
			ControlPanel cp = new ControlPanel(cyApplicationManagerServiceRef);
			ControlPanelAction cpa = new ControlPanelAction(cytodeskService, cp);
//			
//			Properties panelProp = new Properties();
//			panelProp.setProperty("prefferedMenu", "Apps.LisasLayout");
//			panelProp.setProperty("title", "Volle_Kontrolle");
//						
			
			// stuff I need for Getting NetworkColumns
			GetNetworkColumnFactory getnetcol = new GetNetworkColumnFactory(cyApplicationManagerServiceRef);
			Properties getnetcolprops = new Properties();
			getnetcolprops.setProperty("preferredMenu", "Apps.LisasLayout");
			getnetcolprops.setProperty("title", "GetNetworkColumn_eventually");
			
			// stuff I need for NetworkBuilding:
			CyNetworkManager cyNetService = getService(bc, CyNetworkManager.class);
			CyNetworkFactory cyNetFacService = getService(bc, CyNetworkFactory.class);
			CyNetworkNaming cyNetNameService = getService(bc, CyNetworkNaming.class);
			
			NetworkNeedsFactory nnf = new NetworkNeedsFactory(cyNetService, cyNetFacService, cyNetNameService);
			
			Properties netProps = new Properties();
			netProps.setProperty("preferredMenu", "Apps.LisasLayout");
			netProps.setProperty("title", "BauDeinNetzHiermit");
			
			
			
			
			// Register all Services
			
			registerService(bc,createVisualStyleAction,CyAction.class, new Properties());
			registerService(bc, nnf, TaskFactory.class, netProps);
			registerService(bc, cp, CytoPanelComponent.class, new Properties());
			registerService(bc, cpa, CyAction.class, new Properties());
			registerService(bc, getnetcol, TaskFactory.class, getnetcolprops);
		}
	
		/*@Override
	public void start(BundleContext context) throws Exception {
		
		CyApplicationManager cyApplicationManager = getService(context, CyApplicationManager.class);
		
		MenuAction action = new MenuAction(cyApplicationManager, "LisasLayout");
		
		Properties properties = new Properties();
		properties.put(ServiceProperties.PREFERRED_MENU, ServiceProperties.APPS_MENU);
		properties.put(ServiceProperties.TITLE, "SomeTitle");
		
		registerAllServices(context, action, properties);
	}
*/
}
