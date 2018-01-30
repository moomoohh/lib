import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;
import java.net.*;

public class LibMain extends JFrame implements ActionListener, TreeSelectionListener {
	private static final long serialVersionUID = 1L;
	JTree tree;// ����JTree�˵�
	DefaultMutableTreeNode root;// ͼ����Ϣ
	DefaultMutableTreeNode node1;// ͼ��
	DefaultMutableTreeNode node2;// ͼ��
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
		this.setTitle("ͼ�����ϵͳ");
		// icon=getImage("ͼ���1.png");
		this.setResizable(false);
		try {
			Init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void Init() throws Exception {
		// ���JTree�˵�
		root = new DefaultMutableTreeNode("ͼ�����ϵͳ");
		node1 = new DefaultMutableTreeNode("ͼ����Ϣ");
		node2 = new DefaultMutableTreeNode("ͼ��");
		node3 = new DefaultMutableTreeNode("�˳�ϵͳ");
		node4 = new DefaultMutableTreeNode("ͼ��");
		node5 = new DefaultMutableTreeNode("ͼ��");
		node6 = new DefaultMutableTreeNode("ͼ��");
		node7 = new DefaultMutableTreeNode("ͼ��");
		node8 = new DefaultMutableTreeNode("ͼ��");
		// ͼ�������Ϣ
		root.add(node1);
		leafnode = new DefaultMutableTreeNode("ͼ���¼��");
		node1.add(leafnode);
		leafnode = new DefaultMutableTreeNode("ͼ����޸�");
		node1.add(leafnode);
		leafnode = new DefaultMutableTreeNode("ͼ��Ĳ�ѯ");
		node1.add(leafnode);
		leafnode = new DefaultMutableTreeNode("ͼ���ɾ��");
		node1.add(leafnode);
		root.add(node2);
		root.add(node3);
		// ��������JTree
		tree = new JTree(root);
		scrollPane = new JScrollPane(tree);
		scrollPane.setPreferredSize(new Dimension(200, 400));
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		panel1 = new JPanel();
		//panel2 = new JPanel();
		panel3 = new JPanel();
		panel1.add(scrollPane);
		welcome.setText("��ӭʹ��ͼ�����ϵͳ");
		welcome.setFont(new Font("Dialog", 0, 16));
		panel3.add(welcome);
		// ����splitPane�����ò���
		splitPane = new JSplitPane();
		splitPane.setOneTouchExpandable(false);
		splitPane.setContinuousLayout(true);
		splitPane.setPreferredSize(new Dimension(200, 400));
		splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		splitPane.setLeftComponent(panel1);
		splitPane.setRightComponent(panel3);
		splitPane.setDividerSize(2);
		splitPane.setDividerLocation(161);
		// ����������
		this.setContentPane(splitPane);
		this.setVisible(true);
		// ����¼�����
		tree.addTreeSelectionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	// �¼�����
	public void actionPerformed(ActionEvent e) {

	}

	// JTree�¼�����
	public void valueChanged(TreeSelectionEvent tse) {
		DefaultMutableTreeNode dnode = (DefaultMutableTreeNode) tse.getPath().getLastPathComponent();
		System.out.println("dnode=" + dnode);
		String node_str = dnode.toString();
		if (node_str == "ͼ�����ϵͳ") {
			splitPane.setRightComponent(panel3);
		} else if (node_str == "ͼ����Ϣ") {
			treepath = new TreePath(node1.getPath());
			if (tree.isExpanded(treepath)) {
				tree.collapsePath(treepath);
			} else
				tree.expandPath(treepath);
		} else if (node_str == "ͼ����޸�") {
			CardPanel cardPanel = new CardPanel();
			splitPane.setRightComponent(cardPanel);
		} else if (node_str == "ͼ���¼��") {
			EnterBooks enterBooksInsert = new EnterBooks(1);
			JPanel rightPanel = new JPanel();
			rightPanel.setLayout(new BorderLayout());
			rightPanel.add(enterBooksInsert);
			splitPane.setRightComponent(rightPanel);
		} else if (node_str == "ͼ���ɾ��") {
			QueryPanel queryPanel = new QueryPanel(4);
			splitPane.setRightComponent(queryPanel);
		} else if (node_str == "ͼ��Ĳ�ѯ") {
			QueryPanel queryPanel = new QueryPanel(3);
			splitPane.setRightComponent(queryPanel);
		} else if (node_str == "�˳�ϵͳ") {
			Service.quit();
			System.exit(0);
		}
	}


}