import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;


public class TreeTest extends JFrame{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				JFrame frame = new TreeTest();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}

	public TreeTest(){
		setTitle("TreeTest");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		root = new DefaultMutableTreeNode(Object.class);
		model = new DefaultTreeModel(root);
		tree = new JTree(model);
		MiddleTree();
		
		ClassNameTreeCellRenderer renderer = new ClassNameTreeCellRenderer();
		renderer.setClosedIcon(new ImageIcon("red-ball.gif"));
		renderer.setOpenIcon(new ImageIcon("yellow-ball.gif"));
		renderer.setLeafIcon(new ImageIcon("blue-ball.gif"));
	    tree.setCellRenderer(renderer);
	    
		int mode = TreeSelectionModel.SINGLE_TREE_SELECTION;
		tree.getSelectionModel().setSelectionMode(mode);
		tree.addTreeSelectionListener(new TreeSelectionListener() {

	
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				// TODO Auto-generated method stub
				textArea.setText("");
				DefaultMutableTreeNode selecTreeNode = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
				Class class1 = (Class)selecTreeNode.getUserObject();
				for(Method method : class1.getDeclaredMethods())
					textArea.append(method + "\n");
			}
		});
		//SimpleTree();
		
	}
	
	public void MiddleTree(){
		JPanel center = new JPanel();
		center.setLayout(new GridLayout(1, 2));
		westPanel = new JPanel();
		westPanel.setLayout(new BorderLayout());
		westPanel.add(new JScrollPane(tree));
		center.add(westPanel);
		
		textArea = new JTextArea(30,1);
		center.add(new JScrollPane(textArea));
		add(center);
		
		JPanel panel = new JPanel();
		textField = new JTextField(30);
		JButton button = new JButton("Add");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				addClass();
				textField.setText("");
			}
		});
		panel.add(textField);
		panel.add(button);
		add(panel, BorderLayout.SOUTH);
	}
	
	public void addClass(){
		String text = textField.getText().trim();
		ArrayDeque<Class> classList = new ArrayDeque();
		try {
			Class class1 = Class.forName(text);
			DefaultMutableTreeNode node = findUserObject(class1);
			if(node != null) return;
			classList.addFirst(class1);
			Class superClass = class1.getSuperclass();
			while( superClass != Object.class){
				classList.addFirst(superClass);
				superClass = superClass.getSuperclass();
			}
			Class class2 = classList.pollFirst();
			DefaultMutableTreeNode ccNode = root;
			while(class2 != null){
				DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode(class2);
				model.insertNodeInto(treeNode, ccNode, ccNode.getChildCount());
				ccNode = treeNode;
				class2 = classList.pollFirst();
			}
			TreeNode[] nodes = model.getPathToRoot(ccNode);
			TreePath path = new TreePath(nodes);
			tree.scrollPathToVisible(path);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public DefaultMutableTreeNode findUserObject(Object obj){
		Enumeration<TreeNode> e = (Enumeration<TreeNode>)root.breadthFirstEnumeration();
		while(e.hasMoreElements()){
			DefaultMutableTreeNode node = (DefaultMutableTreeNode)e.nextElement();
			if(node.getUserObject().equals(obj)) return node;
		}
		return null;
	}
	
	
	public void SimpleTree(){
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("World");
		DefaultMutableTreeNode country = new DefaultMutableTreeNode("China");
		root.add(country);
		
		
		DefaultMutableTreeNode state = new DefaultMutableTreeNode("GuangDong");
		country.add(state);
		DefaultMutableTreeNode city = new DefaultMutableTreeNode("Guang Zhou");
		city.setAllowsChildren(false);
		state.add(city);
		city = new DefaultMutableTreeNode("Shen Zhen");
		city.setAllowsChildren(false);
		state.add(city);
		state = new DefaultMutableTreeNode("HeNan");
		country.add(state);
		city = new DefaultMutableTreeNode("Zhu ma Dian");
		city.setAllowsChildren(false);
		state.add(city);
		city = new DefaultMutableTreeNode("Zheng Zhou");
		city.setAllowsChildren(false);
		state.add(city);
		state = new DefaultMutableTreeNode("HuBei");
		//state.setAllowsChildren(true);
		country.add(state);
		
		model = new DefaultTreeModel(root);
		model.setAsksAllowsChildren(true);
		tree = new JTree(model);
		//tree.putClientProperty("JTree.lineStyle", "None");
		tree.putClientProperty("JTree.lineStyle", "Angled");
		tree.setShowsRootHandles(true);
		tree.setEditable(true);
		//tree.setRootVisible(false);
		add(new JScrollPane(tree));
		
		JPanel panel = new JPanel();
		JButton button = new JButton("Add State Node");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
				if(selectedNode == null) return;
				DefaultMutableTreeNode newNode = new DefaultMutableTreeNode("HuNan");
				model.insertNodeInto(newNode, selectedNode, selectedNode.getChildCount());
				TreeNode[] nodes = model.getPathToRoot(newNode);
				TreePath path = new TreePath(nodes);
				//tree.makeVisible(path);
				tree.scrollPathToVisible(path);
			}
		});
		JButton remove = new JButton("Remove Node");
		remove.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
				if(selectedNode != null && selectedNode.getParent() != null)
					model.removeNodeFromParent(selectedNode);
			}
		});
		
		panel.add(button);
		panel.add(remove);
		add(panel, BorderLayout.SOUTH);
	}
	
	private DefaultMutableTreeNode root;
	private JTextField textField;
	private JPanel westPanel;
	private JTextArea textArea;
	private JTree tree;
	private DefaultTreeModel model;
	public static final int DEFAULT_WIDTH = 600;
	public static final int DEFAULT_HEIGHT = 400;
}

class ClassNameTreeCellRenderer extends DefaultTreeCellRenderer{
	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean sel, boolean expanded, boolean leaf, int row,
			boolean hasFocus) {
		// TODO Auto-generated method stub
		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf,
				row, hasFocus);
		
		DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;
		Class<?> c = (Class<?>)node.getUserObject();
		
		if(plainFont == null){
			plainFont = getFont();
			if(plainFont != null) italicFont = plainFont.deriveFont(Font.ITALIC);
		}
		
		if((c.getModifiers() & Modifier.ABSTRACT) == 0) setFont(plainFont);
		else setFont(italicFont);
		return this;
	}
	
	private Font plainFont = null;
	private Font italicFont = null;
}
