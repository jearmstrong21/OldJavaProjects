package co.megadodo.problem24;

import java.awt.Color;
import java.awt.Component;
import java.io.ObjectInputStream.GetField;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class MyListCellRenderer implements ListCellRenderer<String> {

//	private final JLabel jlblCell = new JLabel(" ", JLabel.LEFT);

	@Override
	public Component getListCellRendererComponent(JList<? extends String> jList, String value, int index, boolean isSelected,
			boolean cellHasFocus) {

		JLabel jlblCell = new JLabel(value, JLabel.LEFT);
		jlblCell.setFont(jList.getFont());
		Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 1);
		Border emptyBorder = BorderFactory.createEmptyBorder(2, 2, 2, 2);

		jlblCell.setOpaque(true);

		if (isSelected) {
			jlblCell.setForeground(jList.getSelectionForeground());
			jlblCell.setBackground(jList.getSelectionBackground());
			jlblCell.setBorder(new LineBorder(Color.BLUE));
		} else {
			jlblCell.setForeground(jList.getForeground());
			jlblCell.setBackground(jList.getBackground());
		}

		jlblCell.setBorder(cellHasFocus ? lineBorder : emptyBorder);

		return jlblCell;
	}
}