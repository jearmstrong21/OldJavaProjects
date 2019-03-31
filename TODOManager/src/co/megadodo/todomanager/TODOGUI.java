package co.megadodo.todomanager;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeSelectionModel;

public class TODOGUI implements TreeSelectionListener {
	
	public static void main(String[] args) {
		System.err.println("Running from TODOGUI");
		TODOManager.main(args);
	}
	
	JFrame frame;
	JTree tree;
	TODODataManager manager;
	
	JMenuBar menuBar;
	JMenu menuTodos;
	JMenu menuTree;
	JMenu menuTools;
	JMenuItem menuAOPSWeek;
	JMenuItem menuExpandTree;
	JMenuItem menuCollapseTree;
	JMenuItem menuAddTodo;
	JMenuItem menuRemoveTodo;
	JScrollPane scrollPane;
	private void expandAllNodes(JTree tree, int startingIndex, int rowCount){
	    for(int i=startingIndex;i<rowCount;++i){
	        tree.expandRow(i);
	    }

	    if(tree.getRowCount()!=rowCount){
	        expandAllNodes(tree, rowCount, tree.getRowCount());
	    }
	}
	private void collapseAllNodes(JTree tree, int startingIndex, int rowCount){
	    for(int i=startingIndex;i<rowCount;++i){
	        tree.collapseRow(i);
	    }

	    if(tree.getRowCount()!=rowCount){
	        expandAllNodes(tree, rowCount, tree.getRowCount());
	    }
	}
	public TODOGUI() {
		manager = TODODataManager.read();
		frame = new JFrame("TODO Manager");
		
		int mask = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();
		
		menuBar = new JMenuBar();
		menuTodos = new JMenu("Todos");
		menuAddTodo = new JMenuItem("Add Todo");
		menuAddTodo.setAccelerator(KeyStroke.getKeyStroke('N',mask));
		menuAddTodo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				addTODO();
			}
		});
		menuRemoveTodo = new JMenuItem("Remove Todo");
		menuRemoveTodo.setAccelerator(KeyStroke.getKeyStroke('R',mask));
		menuRemoveTodo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				removeTODO();
			}
		});
		menuTodos.add(menuAddTodo);
		menuTodos.add(menuRemoveTodo);
		
		menuTree = new JMenu("Tree");
		menuExpandTree = new JMenuItem("Expand tree");
		menuExpandTree.setAccelerator(KeyStroke.getKeyStroke('1', mask));
		menuExpandTree.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				expandAllNodes(tree, 0, tree.getRowCount());
			}
		});
		menuCollapseTree = new JMenuItem("Collapse tree");
		menuCollapseTree.setAccelerator(KeyStroke.getKeyStroke('2',mask));
		menuCollapseTree.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				collapseAllNodes(tree,0,tree.getRowCount());
			}
		});
		menuTree.add(menuExpandTree);
		menuTree.add(menuCollapseTree);
		
		menuTools = new JMenu("Tools");
		menuAOPSWeek = new JMenuItem("Add AOPS Homework week");
		menuAOPSWeek.setAccelerator(KeyStroke.getKeyStroke('3',mask));
		menuAOPSWeek.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				addAOPSWeek();
			}
		});
		menuTools.add(menuAOPSWeek);
		
		menuBar.add(menuTodos);
		menuBar.add(menuTree);
		menuBar.add(menuTools);
		frame.setJMenuBar(menuBar);
		
		
		tree = new JTree();
		
		updateTree();
		scrollPane = new JScrollPane(tree);
		frame.add(scrollPane);
		frame.setSize(500, 500);
		frame.setVisible(true);
	}
	
	public void addAOPSWeek() {
		if(selected == null) {
			JOptionPane.showMessageDialog(frame, "No class selected", "No class selected", JOptionPane.ERROR_MESSAGE);
		} else {
			String todoName = (String)selected.getUserObject();
			String todoParentName = (String)((DefaultMutableTreeNode)selected.getParent()).getUserObject();
			int numChallengeProblems = Integer.parseInt(JOptionPane.showInputDialog(frame, "How many challenge problems?", "How many challenge problems?", JOptionPane.QUESTION_MESSAGE));
			TODO parentTodo = null;
			for(TODO t : manager.todos) {
				if(parentTodo != null) continue;
				if(t.descr.equals(todoName) && manager.todos.get(t.parentNumber).descr.equals(todoParentName)) {
					parentTodo = t;
				}
			}
			for(int i = 1; i <= numChallengeProblems; i++) {
				manager.addTODO(new TODO("Challenge Problem #" + i),parentTodo);
			}
			manager.addTODO(new TODO("Writing problem"),parentTodo);
			manager.write();
			updateTree();
		}
	}
	
	public void updateTree() {
		System.out.println("TODOGUI.updateTree()");
		DefaultMutableTreeNode root = new DefaultMutableTreeNode();
		ArrayList<DefaultMutableTreeNode> nodes = new ArrayList<DefaultMutableTreeNode>();
		for(TODO todo : manager.todos) {
			DefaultMutableTreeNode node = new DefaultMutableTreeNode();
			node.setUserObject(todo.descr);
			nodes.add(node);
			System.out.println("Added todo " + todo.descr);
		}
		for(int i = 0; i < manager.todos.size(); i++) {
			TODO todo = manager.todos.get(i);
			if(i == todo.parentNumber) {
				System.out.println("TODO " + todo.descr + " is linked to itself.");
			} else {
//				System.out.println("Linked todo #" + i + " to parent todo #" + todo.parentNumber);
				if(todo.parentNumber == -1) {
					root.add(nodes.get(i));
				} else {
					nodes.get(todo.parentNumber).add(nodes.get(i));
				}
			}
		}
		TreeModel model = new DefaultTreeModel(root);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.addTreeSelectionListener(this);
		tree.setModel(model);
		tree.setRootVisible(false);
		tree.validate();
	}
	
	DefaultMutableTreeNode selected = null;
	//TODO: cannot add same name twice
	
	public void removeTODO() {
		boolean verify = JOptionPane.showConfirmDialog(frame, "Remove todos?", "Remove todos?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
		if(verify) {
			if(selected == null) {
				JOptionPane.showMessageDialog(frame, "No TODO selected", "No TODO selected", JOptionPane.OK_OPTION);
			} else {
				String todoName = (String)selected.getUserObject();
				int ind = -1;
				for(int i = 0; i < manager.todos.size(); i++) {
					if(ind != -1) continue;
					if(manager.todos.get(i).descr.equals(todoName)) {
						ind = i;
					}
				}
				if(ind==-1) {
					System.out.println("Ooops! Cannot find TODO " + todoName);
				} else {
					manager.removeTODO(ind);
					manager.write();
					updateTree();
				}
			}
		}
	}
	
	public void addTODO() {
		TODO todo = promptForTODO();
		if(todo.descr.trim().equals(""))return;
		if(selected == null) {
			manager.addTODO(todo);
			manager.write();
			updateTree();
		} else {
			String todoName = (String)selected.getUserObject();
			String parentName = (String)((DefaultMutableTreeNode)selected.getParent()).getUserObject();
			int ind = -1;
			for(int i = 0; i < manager.todos.size(); i++) {
				if(ind != -1) continue;
				boolean ok = manager.todos.get(i).descr.equals(todoName);
				if(manager.todos.get(i).parentNumber != -1) {
					ok = ok && manager.todos.get(manager.todos.get(i).parentNumber).descr.equals(parentName);
				}
				if(ok) {
					ind = i;
				}
			}
			if(ind==-1) {
				manager.addTODO(todo);
			} else {
				TODO parent = manager.todos.get(ind);
				manager.addTODO(todo,parent);
			}
			manager.write();
			updateTree();
		}
	}
	
	public TODO promptForTODO() {
		TODO todo = new TODO();
		todo.descr = JOptionPane.showInputDialog(frame, "Description:", "TODO Creator", JOptionPane.QUESTION_MESSAGE);
		return todo;
	}

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		
		selected = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
		
	}

}
