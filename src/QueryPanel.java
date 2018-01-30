import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;
import java.util.Vector;
import javax.management.Query;
import javax.swing.*;

import org.omg.CORBA.PUBLIC_MEMBER;
public class QueryPanel extends JPanel implements ActionListener{
	private JLabel authorLabel,publisherLabel,languageLabel;
	private JLabel hintLabel1;
	private JLabel hintLabel2;
	private JTextField nameField,publishField,authorField;
	private JComboBox publisherChoice,comboBox,languageChoice,authorChoice;
	private JRadioButton condition1,condition2;
	private ButtonGroup group;
	private JList<String> list;
	private JButton submit,showDetails,update,delete,lend,returnB;
	private int operateFlag,f;
	private Container container;
	private CardLayout card;
	private EnterBooks enterPanel;
	//private int userId;
	@SuppressWarnings("unlikely-arg-type")
	public QueryPanel(int flag)//,int userId
	{
		//this.userId=userId;
		comboBox=new JComboBox<String>();
		comboBox.addItem("ISBN");
		comboBox.addItem("书名");
		operateFlag=flag;
		Vector<String>languageInfo = Service.language();
		Vector<String>publisherInfo = Service.publishers();
		Vector<String>authorInfo = Service.author();
		nameField=new JTextField(20);
		authorLabel=new JLabel("作者");
		authorField=new JTextField(15);
		publisherLabel=new JLabel("出版社");
		//languageLabel=new JLabel("语言");
		//publisherField=new JTextField(15);
		publisherChoice=new JComboBox(publisherInfo);
		//languageChoice=new JComboBox(languageInfo);
		authorChoice=new JComboBox(authorInfo);
		hintLabel1=new JLabel("查询条件");
		condition1=new JRadioButton("完全一致",true);
		condition2=new JRadioButton("模糊查询");
		group =new ButtonGroup();
		group.add(condition1);
		group.add(condition2);
		String s=null;
		/*if(operateFlag==3||operateFlag==4)
			s="查询结果（借书记录）:";
		else if(operateFlag==5)
			s="查询结果（还书书记录）:";
		else*/
			s="查询结果（图书信息）:";
		hintLabel2=new JLabel(s,JLabel.LEFT);
		list=new JList<String>();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scroll=new JScrollPane();
		scroll.getViewport().setView(list);
		submit=new JButton("查询");
		submit.addActionListener(this);
		Box box1=new Box(BoxLayout.X_AXIS);
		Box box2=new Box(BoxLayout.X_AXIS);
		Box box3=new Box(BoxLayout.X_AXIS);
		Box box4=new Box(BoxLayout.X_AXIS);
		Box box5=new Box(BoxLayout.X_AXIS);
		Box box6=new Box(BoxLayout.X_AXIS);
		Box box=new Box(BoxLayout.Y_AXIS);
		box1.add(comboBox);
		box1.add(nameField);		
		box2.add(authorLabel);
		box2.add(authorChoice);
		box3.add(publisherLabel);
		box3.add(publisherChoice);
		//box3.add(languageLabel);
		//box3.add(languageChoice);
		box4.add(hintLabel1);
		box4.add(condition1);
		box4.add(condition2);
		box4.add(submit);
		box5.add(hintLabel2);
		if(operateFlag==3)
		{
			showDetails=new JButton("查看详细");
			showDetails.addActionListener(this);
			box6.add(showDetails);
		}
		/*else
		{
			showImg=new JButton("查看图片");
			showImg.addActionListener(this);
			box6.add(showImg);
		}*/
		if(operateFlag==2)
		{
			update=new JButton("修改");
			update.addActionListener(this);
			box6.add(update);
		}
		if(operateFlag==4)
		{
			delete=new JButton("删除");
			delete.addActionListener(this);
			box6.add(delete);
		}	
		box.add(box1);
		box.add(box2);
		box.add(box3);
		box.add(box4);
		box.add(box5);
		box.add(scroll);
		box.add(box6);
		//setLayout(new BorderLayout());
		add(box);	//,BorderLayout.CENTER	
	}
	public QueryPanel(int flag,Container c,CardLayout card,EnterBooks enterPanel)/*,int userId,Container c,*/
	{
		this(flag);//
		container=c;
		this.card=card;
		this.enterPanel= enterPanel;
	}
	@SuppressWarnings("deprecation")
	private void clear()
	{
		nameField.setText("");     
		authorField.setText("");
		publisherChoice.setSelectedIndex(0);	
		list.removeAll();
	}
	public void actionPerformed(ActionEvent e)
	{
		Object source=e.getSource();
		if(source==submit)
		{
			Vector<String>infoStringCollection=new Vector<String>();
			list.setListData(infoStringCollection);
			String name=nameField.getText().trim();
			String author=authorChoice.getSelectedItem().toString();
			String publisher=publisherChoice.getSelectedItem().toString();
			//String  language=languageChoice.getSelectedItem().toString();
			String condition=null;
			if(condition1.isSelected()) 
				condition=condition1.getText().trim();				
			if(condition2.isSelected()) 
				condition=condition2.getText().trim();
			if (comboBox.equals("ISBN")) 
			{
				String ISBN=nameField.getText().trim();
				String 作者=authorChoice.getSelectedItem().toString();
				String 出版社=publisherChoice.getSelectedItem().toString();
				infoStringCollection=Service.seek(f,operateFlag,ISBN,作者,出版社,condition);

			}
			else
				{
				f=2;
				String 书名=nameField.getText().trim();
				String 作者=authorChoice.getSelectedItem().toString();
				String 出版社=publisherChoice.getSelectedItem().toString();
				infoStringCollection=Service.seek(f,operateFlag,书名,作者,出版社,condition);
				}
			if (infoStringCollection==null) {
			String str=null;
			str="抱歉，图书馆里没有次书";
			JOptionPane.showMessageDialog(this,str);		
			}
			else
			list.setListData(infoStringCollection);//将查询结果显示与列表
			//清除界面			
			return ;
		}
		String book=list.getSelectedValue().toString();
		System.out.println(book);
		/*if (source==showImg) {
			String imgFile=Service.getImgFile(operateFlag,book)；
					showImg(imgFile);
		}*/
		if (source==showDetails) {
			String details= Service.detailsBook(operateFlag,book);
			JOptionPane.showMessageDialog(this, details);
			
		}
		if (source==update) {
		    enterPanel.setUpdatedBookInfo(book);
			card.next(container);
			
		}
		if (source==delete) {
			if (Service.deleteBook(this,book)==0) 
				{
				  clear();
				  JOptionPane.showMessageDialog(this, "删除成功");
				
				}
			
			
		}
		/*if (source==lend) {
			StringBuffer hintMessage=new StringBuffer("");
			int lendFlag=Service.lendBook(this,userId,book,hintMessage); 
			if(lendFlag==0)
			    JOptionPane.showMessageDialog(this, "借书成功");
			if(lendFlag==0)
				JOptionPane.showMessageDialog(this, hintMessage+"该书无法借阅，对不起");		
		}
		
		if (source==returnB) {
			if (Service.returnBook(this,userId,book)==0)
			JOptionPane.showMessageDialog(this, "还书成功");	
		}	*/
	}

}
