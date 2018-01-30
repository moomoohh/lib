import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;
import javax.swing.*;
public class EnterBooks extends JPanel implements ActionListener{
	private int operateFlag;
	private JLabel noLabel,nameLabel,authorLabel,publisherLabel;
	private JLabel priceLabel,pubDateLabel,depositLabel,quantityLabel;
	private JLabel languageLabel,pageLabel;
	private JTextField noField,nameField,authorField,publisherField;
	private JTextField priceField,pubDateField,depositField,quantityField;
	private JTextField languageField,pageField;
	private JButton submit,cancel;
	private Container container;
	private CardLayout card;
	private int updatedBookID;
	public EnterBooks(int flag) {
		operateFlag=flag;
		String buttonString=null;
		if(operateFlag==1)
		{
			buttonString="提交";
		}
		if(operateFlag==2)
		{
			buttonString="修改";
		}
	
		noLabel=new JLabel(" I S B N ");
		noField=new JTextField(20);
		nameLabel=new JLabel("书       名");
		nameField=new JTextField(20);
		authorLabel=new JLabel("作       者");
		languageLabel=new JLabel("语       言");
		languageField=new JTextField(20);
		pageLabel=new JLabel("页        数");
		pageField=new JTextField(20);
		authorLabel=new JLabel("作      者");
		authorField=new JTextField(20);
		publisherLabel=new JLabel("出  版 社 ");
		publisherField=new JTextField(20);
		priceLabel=new JLabel("价        格");
		priceField=new JTextField(20);
		pubDateLabel=new JLabel("出版时间");
		pubDateField=new JTextField(20);
		depositLabel=new JLabel("存放位置");
		depositField=new JTextField(20);
		quantityLabel=new JLabel("数       量");
		quantityField=new JTextField(20);
		submit=new JButton(buttonString);
		cancel =new JButton("取消");
		submit.addActionListener(this);
		cancel.addActionListener(this);
		Box box1=new Box(BoxLayout.X_AXIS);
		Box box2=new Box(BoxLayout.X_AXIS);
		Box box3=new Box(BoxLayout.X_AXIS);
		Box box4=new Box(BoxLayout.X_AXIS);
		Box box5=new Box(BoxLayout.X_AXIS);
		Box box6=new Box(BoxLayout.X_AXIS);
		box1.add(noLabel);
		box1.add(noField);
		box1.add(nameLabel);
		box1.add(nameField);
		box2.add(authorLabel);
		box2.add(authorField);
		box2.add(publisherLabel);
		box2.add(publisherField);
		box3.add(priceLabel);
		box3.add(priceField);
		box3.add(pubDateLabel);
		box3.add(pubDateField);
		box4.add(depositLabel);
		box4.add(depositField);
		box4.add(quantityLabel);
		box4.add(quantityField);
		box5.add(languageLabel);
		box5.add(languageField);
		box5.add(pageLabel);
		box5.add(pageField);
		box6.add(submit);
		box6.add(cancel);
		Box box=new Box(BoxLayout.Y_AXIS);
		box.add(box1);
		box.add(box2);
		box.add(box3);
		box.add(box4);
		box.add(box5);
		box.add(box6);
		//setLayout(new BorderLayout());
		add(box);
	}
	public EnterBooks(int flag,Container c,CardLayout card)
	{
		this(flag);
		container=c;
		this.card=card;
	}
	public void setUpdatedBookInfo(String updatedBookInfo)
	{
		if(updatedBookInfo==null)
			return;
		String str[]=new String[10];
		int index=-1;
		for(int i=0;i<9;i++)
		{
			index=updatedBookInfo.indexOf(',');
			str[i]=updatedBookInfo.substring(0,index);
			updatedBookInfo=updatedBookInfo.substring(index+1);
		}
		System.out.println("index" + index);
		System.out.print("updb" + updatedBookInfo.length());
		str[9]=updatedBookInfo.substring(index);
	   
		noField.setText(str[0]);
		
		nameField.setText(str[1]);
		authorField.setText(str[2]);
		publisherField.setText(str[3]);
		priceField.setText(str[4]);
		languageField.setText(str[5]);
		pageField.setText(str[6]);
		pubDateField.setText(str[7] );
		quantityField.setText(str[8]);
		depositField.setText(str[9]);
	}
	public void clearField() {
		noField.setText("");
		nameField.setText("");
		authorField.setText("");
		publisherField.setText("");
		priceField.setText("");
		languageField.setText("");
		pageField.setText("");
		pubDateField.setText("   年      月      日 ");
		depositField.setText("");
		quantityField.setText("");
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==submit)
		{
			String ISBN=noField.getText().trim();
			String 书名=nameField.getText().trim();
			String 作者=authorField.getText().trim();
			String 出版社=publisherField.getText().trim();
			String 价格=priceField.getText().trim();
			String 语言=languageField.getText().trim();
			String 页数=pageField.getText().trim();
			String 出版时间=pubDateField.getText().trim();
			String 数量=quantityField.getText().trim();
			String 存放位置=depositField.getText().trim();
			Vector<String>bookInfo=new Vector<String>();
			bookInfo.add(ISBN);
			bookInfo.add(书名);
			bookInfo.add(作者);
			bookInfo.add(出版社);
			bookInfo.add(价格);
			bookInfo.add(语言);
			bookInfo.add(页数);
			bookInfo.add(出版时间);
			bookInfo.add(数量);
			bookInfo.add(存放位置);
			if(operateFlag==1&&Service.addBook(bookInfo)==0)
			{
				JOptionPane.showMessageDialog(null, "添加成功");
				clearField();
			}
			if(operateFlag==2&&Service.modifyBook(bookInfo)==0)
			{
				card.next(container);
				JOptionPane.showMessageDialog(null, "修改成功！");
			}
			
			
			
			
		}
	}
}
