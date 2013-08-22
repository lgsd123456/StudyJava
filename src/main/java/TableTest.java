import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;




import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractCellEditor;

import java.util.ArrayList;
import java.util.EventObject;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;


public class TableTest extends JFrame{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				JFrame frame = new TableTest();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}

	public TableTest(){
		setTitle("TableTest");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		TableModel model = new PlanetTableModel();
		JTable table = new JTable(model);
		table.setRowSelectionAllowed(false);
		
		table.setDefaultRenderer(Color.class, new ColorTableCellRenderer());
		table.setDefaultEditor(Color.class, new ColorTableCellEditor());
		
		JComboBox moonComboBox = new JComboBox();
		for(int i = 0; i <= 20; i++)
			moonComboBox.addItem(i);
		
		TableColumnModel columnModel = table.getColumnModel();
		TableColumn moonColumn = columnModel.getColumn(PlanetTableModel.MOONS_COLUMN);
		moonColumn.setCellEditor(new DefaultCellEditor(moonComboBox));
		moonColumn.setHeaderRenderer(table.getDefaultRenderer(ImageIcon.class));
		moonColumn.setHeaderValue(new ImageIcon("Moons.gif"));
		
		table.setRowHeight(100);
		add(new JScrollPane(table), BorderLayout.CENTER);
	}
	
   public static final int DEFAULT_WIDTH = 600;
   public static final int DEFAULT_HEIGHT = 400;
}

class PlanetTableModel extends AbstractTableModel{
	public String getColumnName(int c){
		return columnNames[c];
	}
	
	public Class<?> getColumnClass(int c){
		return cells[0][c].getClass();
	}
	
	public int getColumnCount(){
		return cells[0].length;
	}
	
	public int getRowCount(){
		return cells.length;
	}
	
	public Object getValueAt(int r, int c){
		return cells[r][c];
	}
	
	public void setValueAt(Object obj, int r, int c){
		cells[r][c] = obj;
	}
	
	public boolean isCellEditable(int r, int c){
		return c == PLANET_COLUMN || c == MOONS_COLUMN || c == GASEOUS_COLUMN || c == COLOR_COLUMN;
	}
	
	public static final int PLANET_COLUMN = 0;
	public static final int MOONS_COLUMN = 2;
	public static final int GASEOUS_COLUMN = 3;
	public static final int COLOR_COLUMN = 4;

	
	private Object[][] cells = {
	         { "Mercury", 2440.0, 0, false, Color.YELLOW, new ImageIcon("Mercury.gif") },
	         { "Venus", 6052.0, 0, false, Color.YELLOW, new ImageIcon("Venus.gif") },
	         { "Earth", 6378.0, 1, false, Color.BLUE, new ImageIcon("Earth.gif") },
	         { "Mars", 3397.0, 2, false, Color.RED, new ImageIcon("Mars.gif") },
	         { "Jupiter", 71492.0, 16, true, Color.ORANGE, new ImageIcon("Jupiter.gif") },
	         { "Saturn", 60268.0, 18, true, Color.ORANGE, new ImageIcon("Saturn.gif") },
	         { "Uranus", 25559.0, 17, true, Color.BLUE, new ImageIcon("Uranus.gif") },
	         { "Neptune", 24766.0, 8, true, Color.BLUE, new ImageIcon("Neptune.gif") },
	         { "Pluto", 1137.0, 1, false, Color.BLACK, new ImageIcon("Pluto.gif") } };

	   private String[] columnNames = { "Planet", "Radius", "Moons", "Gaseous", "Color", "Image" };
}

class ColorTableCellRenderer extends JPanel implements TableCellRenderer{
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		// TODO Auto-generated method stub
		setBackground((Color)value);
		if(hasFocus) setBorder(UIManager.getBorder("Table.focusCellHighlightBorder"));
		else setBorder(null);
		return this;
	}
}

class ColorTableCellEditor extends AbstractCellEditor implements TableCellEditor{
	public ColorTableCellEditor(){
		panel = new JPanel();
		
		colorChooser = new JColorChooser();
		colorDialog = JColorChooser.createDialog(null, "Planet Color", 
				false, colorChooser, new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						stopCellEditing();
					}
				}, new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						cancelCellEditing();
					}
				});
		colorDialog.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event){
				cancelCellEditing();
			}
		});
	}
	
	@Override
	public boolean shouldSelectCell(EventObject anEvent) {
		// TODO Auto-generated method stub
		colorDialog.setVisible(true);
		return true;
	}
	
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		// TODO Auto-generated method stub
		colorChooser.setColor((Color)value);
		return panel;
	}
	
	@Override
	public void cancelCellEditing() {
		// TODO Auto-generated method stub
		colorDialog.setVisible(false);
		super.cancelCellEditing();
	}
	
	@Override
	public boolean stopCellEditing() {
		// TODO Auto-generated method stub
		colorDialog.setVisible(false);
		super.stopCellEditing();
		return true;
	}
	
	@Override
	public Object getCellEditorValue() {
		// TODO Auto-generated method stub
		return colorChooser.getColor();
	}
	
	private JColorChooser colorChooser;
	private JDialog colorDialog;
	private JPanel panel;
}

class MiddleTablePanel extends JPanel{
	public MiddleTablePanel(JFrame frame){
		setLayout(new BorderLayout());
		model = new DefaultTableModel(10, 10);
		
		for(int i = 0; i < model.getRowCount(); i++)
			for(int j = 0; j < model.getColumnCount(); j++)
				model.setValueAt((i + 1) * (j + 1), i, j);
		
		table = new JTable(model);
		add(new JScrollPane(table), BorderLayout.CENTER);
		
		removedColumns = new ArrayList();
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu selectionMenu = new JMenu("Selection");
		menuBar.add(selectionMenu);
		
		final JCheckBoxMenuItem rowsItem = new JCheckBoxMenuItem("Rows");
		final JCheckBoxMenuItem columnsItem = new JCheckBoxMenuItem("Columns");
		final JCheckBoxMenuItem cellsItem = new JCheckBoxMenuItem("Cells");
		
		rowsItem.setSelected(table.getRowSelectionAllowed());
		columnsItem.setSelected(table.getColumnSelectionAllowed());
		cellsItem.setSelected(table.getCellSelectionEnabled());
		
		rowsItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				table.clearSelection();
				table.setRowSelectionAllowed(rowsItem.isSelected());
				cellsItem.setSelected(table.getCellSelectionEnabled());
			}
		});
		selectionMenu.add(rowsItem);
		
		columnsItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				table.clearSelection();
				table.setColumnSelectionAllowed(columnsItem.isSelected());
				cellsItem.setSelected(table.getCellSelectionEnabled());
			}
		});
		selectionMenu.add(columnsItem);
		
		cellsItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				table.clearSelection();
				table.setCellSelectionEnabled(cellsItem.isSelected());
				rowsItem.setSelected(table.getRowSelectionAllowed());
				columnsItem.setSelected(table.getColumnSelectionAllowed());
			}
		});
		selectionMenu.add(cellsItem);
		
		JMenu tableMenu = new JMenu("Edit");
		menuBar.add(tableMenu);
		
		JMenuItem hideColumnsItem = new JMenuItem("Hide Columns");
		hideColumnsItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int[] selected = table.getSelectedColumns();
				TableColumnModel columnModel = table.getColumnModel();
				
				for(int i = selected.length - 1; i >= 0; i--){
					TableColumn column = columnModel.getColumn(selected[i]);
					table.removeColumn(column);
					
					removedColumns.add(column);
				}
			}
		});
		tableMenu.add(hideColumnsItem);
		
		JMenuItem showColumnsItem = new JMenuItem("Show Columns");
		showColumnsItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				for(TableColumn tc : removedColumns)
					table.addColumn(tc);
				removedColumns.clear();
			}
		});
		tableMenu.add(showColumnsItem);
		
		JMenuItem addRowItem = new JMenuItem("Add Row");
		addRowItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Integer[] newCells = new Integer[model.getColumnCount()];
				for(int i = 0; i < newCells.length; i++)
					newCells[i] = (i + 1) * (model.getRowCount() + 1);
				model.addRow(newCells);
			}
		});
		tableMenu.add(addRowItem);
		
		JMenuItem removeRowsItem = new JMenuItem("Remove Rows");
		removeRowsItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int[] selected = table.getSelectedRows();
				for(int i = selected.length - 1; i >= 0; i--)
					model.removeRow(selected[i]);
			}
		});
		tableMenu.add(removeRowsItem);
		
		JMenuItem clearCellsItem = new JMenuItem("Clear Cells");
		clearCellsItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				for(int i = 0; i < table.getRowCount(); i++)
					for(int j = 0; j < table.getColumnCount(); j++)
						if(table.isCellSelected(i, j)) table.setValueAt(0, i, j);
			}
		});
		tableMenu.add(clearCellsItem);
		
	}
	
	private DefaultTableModel model;
	private JTable table;
	private ArrayList<TableColumn> removedColumns;
}

class SimpleTablePanel extends JPanel{
	public SimpleTablePanel(){
		setLayout(new BorderLayout());
		final JTable table = new JTable(cells, columnNames);
		table.setAutoCreateRowSorter(true);
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);
		JButton button = new JButton("Print");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					Class class1 = table.getColumnClass(0);
					Class class2 = table.getColumnClass(1);
					System.out.println(class1);
					//table.print();
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
				
			}
		});
		add(button, BorderLayout.SOUTH);
	}
	
   private String[] columnNames = { "Planet", "Radius", "Moons", "Gaseous", "Color" };
   private Object[][] cells = { { "Mercury", 2440.0, 0, false, Color.yellow },
					{ "Venus", 6052.0, 0, false, Color.yellow }, 
					{ "Earth", 6378.0, 1, false, Color.blue },
					{ "Mars", 3397.0, 2, false, Color.red }, 
					{ "Jupiter", 71492.0, 16, true, Color.orange },
					{ "Saturn", 60268.0, 18, true, Color.orange },
					{ "Uranus", 25559.0, 17, true, Color.blue }, 
					{ "Neptune", 24766.0, 8, true, Color.blue },
					{ "Pluto", 1137.0, 1, false, Color.black } };
}
