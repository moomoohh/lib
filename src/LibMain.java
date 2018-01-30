import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;
import java.net.*;

public class LibMain extends JFrame implements ActionListener, TreeSelectionListener {
	private static final long serialVersionUID = 1L;
	JTree tree;// 建立JTree菜单
	DefaultMutableTreeNode root;// 图书信息
	DefaultMutableTreeNode node1;// 图书
	DefaultMutableTreeNode node2;// 图书
	DefaultMutableTreeNode node3;//
	DefaultMutableTreeNode node4;
	DefaultMutableTreeNode node5;
	DefaultMutableTreeNode node6;
	DefaultMutableTreeNode node7;
	DefaultMutableTreeNode node8;
	DefaultMutableTreeNode leafnode;
	TreePath treepath;
	public static JSplitPane splitPane;
	JPanel panel1;
	//JPanel panel2;
	JPanel panel3;
	JLabel welcome = new JLabel();
	JScrollPane scrollPane;

	// JPanel backgroundPanel = new JPanel();
	public LibMain() {
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		// this.setSize(faceSize);
		this.setTitle("图书管理系统");
		// icon=getImage("图书馆1.png");
		this.setResizable(false);
		try {
			Init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void Init() throws Exception {
		// 添加JTree菜单
		root = new DefaultMutableTreeNode("图书管理系统");
		node1 = new DefaultMutableTreeNode("图书信息");
		node2 = new DefaultMutableTreeNode("图书");
		node3 = new DefaultMutableTreeNode("退出系统");
		node4 = new DefaultMutableTreeNode("图书");
		node5 = new DefaultMutableTreeNode("图书");
		node6 = new DefaultMutableTreeNode("图书");
		node7 = new DefaultMutableTreeNode("图书");
		node8 = new DefaultMutableTreeNode("图书");
		// 图书基本信息
		root.add(node1);
		leafnode = new DefaultMutableTreeNode("图书的录入");
		node1.add(leafnode);
		leafnode = new DefaultMutableTreeNode("图书的修改");
		node1.add(leafnode);
		leafnode = new DefaultMutableTreeNode("图书的查询");
		node1.add(leafnode);
		leafnode = new DefaultMutableTreeNode("图书的删除");
		node1.add(leafnode);
		root.add(node2);
		root.add(node3);
		// 生成左侧的JTree
		tree = new JTree(root);
		scrollPane = new JScrollPane(tree);
		scrollPane.setPreferredSize(new Dimension(200, 400));
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		panel1 = new JPanel();
		//panel2 = new JPanel();
		panel3 = new JPanel();
		panel1.add(scrollPane);
		welcome.setText("欢迎使用图书管理系统");
		welcome.setFont(new Font("Dialog", 0, 16));
		panel3.add(welcome);
		// 生成splitPane并设置参数
		splitPane = new JSplitPane();
		splitPane.setOneTouchExpandable(false);
		splitPane.setContinuousLayout(true);
		splitPane.setPreferredSize(new Dimension(200, 400));
		splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		splitPane.setLeftComponent(panel1);
		splitPane.setRightComponent(panel3);
		splitPane.setDividerSize(2);
		splitPane.setDividerLocation(161);
		// 生成主界面
		this.setContentPane(splitPane);
		this.setVisible(true);
		// 添加事件侦听
		tree.addTreeSelectionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	// 事件处理
	public void actionPerformed(ActionEvent e) {

	}

	// JTree事件处理
	public void valueChanged(TreeSelectionEvent tse) {
		DefaultMutableTreeNode dnode = (DefaultMutableTreeNode) tse.getPath().getLastPathComponent();
		System.out.println("dnode=" + dnode);
		String node_str = dnode.toString();
		if (node_str == "图书管理系统") {
			splitPane.setRightComponent(panel3);
		} else if (node_str == "图书信息") {
			treepath = new TreePath(node1.getPath());
			if (tree.isExpanded(treepath)) {
				tree.collapsePath(treepath);
			} else
				tree.expandPath(treepath);
		} else if (node_str == "图书的修改") {
			CardPanel cardPanel = new CardPanel();
			splitPane.setRightComponent(cardPanel);
		} else if (node_str == "图书的录入") {
			EnterBooks enterBooksInsert = new EnterBooks(1);
			JPanel rightPanel = new JPanel();
			rightPanel.setLayout(new BorderLayout());
			rightPanel.add(enterBooksInsert);
			splitPane.setRightComponent(rightPanel);
		} else if (node_str == "图书的删除") {
			QueryPanel queryPanel = new QueryPanel(4);
			splitPane.setRightComponent(queryPanel);
		} else if (node_str == "图书的查询") {
			QueryPanel queryPanel = new QueryPanel(3);
			splitPane.setRightComponent(queryPanel);
		} else if (node_str == "退出系统") {
			Service.quit();
			System.exit(0);
		}
	}


}