package co.megadodo.worldgenide1;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.json.JSONObject;

public class OctaveComponent extends JPanel {
	
	public float zoom,scale,offset;
	
	public JLabel lblZoom,lblScale,lblOffset;
	public JTextField txtZoom,txtScale,txtOffset;
	public GridLayout layout;
	
	public OctaveComponent() {
		lblZoom = new JLabel("Zoom:");
		lblScale = new JLabel("Scale:");
		lblOffset = new JLabel("Offset:");
		txtZoom = new JTextField();
		txtScale = new JTextField();
		txtOffset = new JTextField();
		zoom=0;
		scale=0;
		offset=0;
		layout = new GridLayout(3, 2);
		this.setSize(200,100);
		this.setPreferredSize(new Dimension(200, 100));
		this.setLayout(layout);
		this.add(lblZoom);
		this.add(txtZoom);
		this.add(lblScale);
		this.add(txtScale);
		this.add(lblOffset);
		this.add(txtOffset);
		txtZoom.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				zoom = Float.parseFloat(txtZoom.getText());
			}
		});
		txtScale.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				scale = Float.parseFloat(txtScale.getText());
			}
		});
		txtOffset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				offset = Float.parseFloat(txtOffset.getText());
			}
		});
		txtZoom.setText("0");
		txtScale.setText("0");
		txtOffset.setText("0");
		zoom=scale=offset=0;
	}
	
	public void loadJSON(JSONObject object) {
		txtZoom.setText("" + object.getFloat("zoom"));
		txtScale.setText("" + object.getFloat("scale"));
		txtOffset.setText("" + object.getFloat("offset"));
	}
	
	public JSONObject toJSON() {
		JSONObject obj = new JSONObject();
		obj.put("zoom", zoom);
		obj.put("scale", scale);
		obj.put("offset", offset);
		return obj;
	}

}
