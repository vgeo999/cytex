package org.cytoscape.testapp.mytest_cyaction_app.panels;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.List;

import javax.swing.Icon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.swing.CytoPanelComponent;
import org.cytoscape.application.swing.CytoPanelName;
import org.cytoscape.model.CyRow;
import org.cytoscape.model.CyTable;

public class ControlPanel extends JPanel implements CytoPanelComponent{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public ControlPanel(CyApplicationManager cyAppMan) {
		
		// label and drop-down menu for choosing the tissue
		JLabel chooseLabel = new JLabel("Choose tissue of interest: ");
		JLabel chooseExp = new JLabel("Choose threshold for gene-expression: ");
		// tissueList from names_uniqe.txt 
		String[] tissues = new String[] {"Lung", "Heart_Atrial_Appenda", 
				"Brain_Hypothalamus", "Cells_Transformed_fi", "Adipose_Subcutaneous", 
				"Minor_Salivary_Gland", "Cells_EBV-transforme", "Thyroid"};
		JComboBox<String> tissueList = new JComboBox<>(tissues);
		
		// set up expression-threshold-slider
		JSlider slider = new JSlider(JSlider.HORIZONTAL, 0 , 50, 25);
		slider.setMinorTickSpacing(2);
		slider.setMajorTickSpacing(10);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setLabelTable(slider.createStandardLabels(10));
		
		
		this.setLayout(new GridLayout(10,0,10,10));
		this.add(chooseLabel);
		this.add(tissueList);
		this.add(chooseExp);
		this.add(slider);
		this.setVisible(true);
		
		tissueList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Get input from tissueList
				String input = (String) tissueList.getSelectedItem();
				CyTable table = cyAppMan.getCurrentNetwork().getDefaultNodeTable();
				// CyColumn col = table.getColumn("label");
//				Collection<CyRow> match = table.getMatchingRows("label", input);
				// String pkc = table.getPrimaryKey().getName();
				Collection<CyRow> def = table.getAllRows();
		
				for (CyRow row : def) {
					List<String> tissues = row.getList("label", String.class);
					boolean matched = false;
					for (String tissue : tissues) {
						if (tissue.equals(input)) {
							row.set("selected", true);
							matched = true;
						}
					}
					if (!matched) row.set("selected", false);
				}
//				for (CyRow row : match) {
//					row.set("selected", true);
//				}
				
				cyAppMan.getCurrentNetworkView().updateView();
				//CyNetwork actualNetwork = new CyApplicationManager.getCurrentNetwork();
				// here I need something like: GetNetworkColumn dropdown = new NetworkColumn; -> select Nodes from input
				//JOptionPane.showMessageDialog(null, "Selected: " + input);				
			}
		});
	}
	
	public Component getComponent() {
		return this;
	}
	
	public CytoPanelName getCytoPanelName() {
		return CytoPanelName.WEST;
	}
	
	public String getTitle() {
		return "GTEx App Panel";
	}
	
	public Icon getIcon() {
		return null;
	}
	
	
}
