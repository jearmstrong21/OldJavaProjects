package co.megadodo.lolcodeide1;

import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class LOLCodeProjectManager extends JScrollPane {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6687884945785619030L;
	JScrollPane scrlPane;
	JTree tree;
	Font font;

	public LOLCodeProjectManager(int WIDTH, int HEIGHT) {
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
//		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		setFontSize();
//		tree = new JTree();
//		tree.setModel(new DefaultTreeModel(node));
//		
//		scrlPane = new JScrollPane(tree);
//		this.add(scrlPane);
	}
	
	public void setFontSize() {
		font = new Font(Font.MONOSPACED, Font.PLAIN, LOLCodePrefs.TEXTSIZE_PROJ_MAN);
		setFont(font);
		updateTree();
		tree.setFont(font);
	}
	
	public void updateTree() {
		File root = new File(LOLCodePrefs.ROOT_FILE);
		DefaultMutableTreeNode node = nodeForFile(root);
		ArrayList<DefaultMutableTreeNode> nodes = getChildNodes(root);
		for(DefaultMutableTreeNode n : nodes) node.add(n);
		tree = new JTree();
		tree.setModel(new DefaultTreeModel(node));
		this.setViewportView(tree);
	}
	
	FileFilter filter = new FileFilter() {
		
		@Override
		public boolean accept(File f) {
			String fileName = f.getName().split("\\.")[0];
			if(fileName.equals("")) return false;
			return true;
		}
	};
	
	public ArrayList<DefaultMutableTreeNode> getChildNodes(File root) {
		ArrayList<DefaultMutableTreeNode> nodes = new ArrayList<>();
		if(root.isFile()) {
			return nodes; // empty list
		} else {
			// non-empty list
			File[] subFiles = root.listFiles(filter);
			for(int i = 0; i < subFiles.length; i++) {
				File f = subFiles[i];
				System.out.println("FILE " + f);
				DefaultMutableTreeNode node = nodeForFile(f);
				ArrayList<DefaultMutableTreeNode> childNodes = getChildNodes(f);
				for(DefaultMutableTreeNode curNode : childNodes) {
					node.add(curNode);
				}
				nodes.add(node);
			}
		}
		return nodes;
	}
	
	public DefaultMutableTreeNode nodeForFile(File f) {
		return new DefaultMutableTreeNode(f.getName());
	}
}
