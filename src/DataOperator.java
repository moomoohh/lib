import  java.sql.*;
import java.util.Vector;
import java.util.jar.Attributes.Name;
public class DataOperator {
	Connection con;
	private PreparedStatement pstmt;
	private String sql;
	public void loadDatabaseDriver()
	{
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		}
		catch(ClassNotFoundException e)
		{
			System.err.println("加载数据库驱动失败");
			System.err.println(e);
		}
	}
	public void connect()
	{
		try {
			String connectString="jdbc:sqlserver://localhost:1433;DatabaseName=library";
			con=DriverManager.getConnection(connectString,"sa","123");
			
		} catch (Exception e) {
			System.err.println("数据库连接出错！");
			
			System.err.println(e);
			// TODO: handle exception
		}
		
	}
	public int insert(Vector<String>bookInfo)
	{
		try {
			sql="insert into BOOKINFO values(?,?,?,?,?,?,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			for(int i=0;i<bookInfo.size();i++)
			{
					pstmt.setString(i+1, bookInfo.elementAt(i));
				
			}
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			System.err.println("数据库增加记录出错！");
			System.err.println(se);
			return -1;
			// TODO: handle exception
		}
		return 0;
	}
	public int update(Vector<String>bookInfo)
	{
		try {
			sql="update  BOOKINFO set 书名 = ?,作者 = ? ,出版社 = ?,价格= ?,语言 = ?,页数 = ?, [出 版 时 间]= ?,数量 = ?,存放位置 = ? where ISBN=?";
			pstmt=con.prepareStatement(sql);
			for(int i=1;i<bookInfo.size();i++)
			{
					pstmt.setString(i, bookInfo.elementAt(i));
				
			}
			pstmt.setString(bookInfo.size(),bookInfo.elementAt(0));
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			System.err.println("数据库修改记录出错！");
			System.err.println(se);
			return -1;
			// TODO: handle exception
		}                      
		return 0;
	}
	public void addSuperUser()
	{
		
	}
	public int userQuery(String userName,String password)
	{
		return -1;
	}
	public void disConnect() {
		try {
			if(con!=null)
				con.close();
			
		} catch (Exception e) {
			System.err.println("关闭数据库连接出错");
			System.err.println(e);
			// TODO: handle exception
		}
		
	}
	public Vector<String>publishersQuery()
	{
		Vector<String>publisherInfo=new Vector<String>();
		try {
			sql="select distinct(出版社) from BOOKINFO " ;
			pstmt=con.prepareStatement(sql);
			ResultSet rs=pstmt.executeQuery();
			publisherInfo.add("");
			while(rs.next()) {
				publisherInfo.add(rs.getString(1));
			}
			
		} catch (Exception e) {
			System.err.println("数据库查询出错！");
			System.err.println(e);
			// TODO: handle exception
		}
		return publisherInfo;
	}
	public Vector<String>languageQuery()
	{
		Vector<String>languageInfo=new Vector<String>();
		try {
			sql="select 语言 from BOOKINFO union select 语言  from BOOKINFO" ;
			pstmt=con.prepareStatement(sql);
			ResultSet rs=pstmt.executeQuery();
			languageInfo.add("");
			while(rs.next()) {
				languageInfo.add(rs.getString(1));
			}
			
		} catch (Exception e) {
			System.err.println("数据库查询出错！");
			System.err.println(e);
			// TODO: handle exception
		}
		return languageInfo;
	}
	public Vector<String>authorQuery()
	{
		Vector<String>authorInfo=new Vector<String>();
		try {
			sql="select distinct(作者) from BOOKINFO " ;
			pstmt=con.prepareStatement(sql);
			ResultSet rs=pstmt.executeQuery();
			authorInfo.add("");
			while(rs.next()) {
				authorInfo.add(rs.getString(1));
			}
			
		} catch (Exception e) {
			System.err.println("数据库查询出错！");
			System.err.println(e);
			// TODO: handle exception
		}
		return authorInfo;
	}
	public Vector<String>generaQuery(int operateFlag,String sql,int selectFlag,String name,String author,String publisher)
	{
		System.out.print(sql);
		Vector<String>infoStringCollection=new Vector<String>();
		try {			
			pstmt=con.prepareStatement(sql);

			switch (selectFlag) {
			case 0:
				break;
			case 1:
				pstmt.setString(1, name);
				break;
			case 2:
				pstmt.setString(1, name);
				pstmt.setString(2, author);
				break;
			case 3:
				pstmt.setString(1, name);
				pstmt.setString(2, author);
				pstmt.setString(3, publisher);
				break;
			case 4:
				pstmt.setString(1, name);
				pstmt.setString(2, publisher);
				break;
			case 5:
				pstmt.setString(1, author);
				break;
			case 6:
				pstmt.setString(1, author);
				pstmt.setString(2, publisher);
				break;
			case 7:
				pstmt.setString(1, publisher);
				break;
				
			default:
				break;
			}
			ResultSet rs=pstmt.executeQuery();
			
			//System.out.println(rs);
			String infoString=null;
			while(rs.next()) {
			infoString=new String();
			infoString+=rs.getString(1).trim()+",";
			int count=11;
			for(int i=2;i<count;i++)
				infoString+=rs.getString(i).trim()+",";
			infoString=infoString.substring( 0,infoString.length()-1);
			infoStringCollection.add(infoString);
		}
		}
		
		catch (SQLException se) {
			System.err.println("查询数据库出错");
			System.err.println(se);
			se.printStackTrace(System.err);
			// TODO: handle exception
		}
		
		return infoStringCollection;
		
	}
public int delete(String deletedBookID)
{
	try {
		sql="delete from BOOKINFO where ISBN=?";
		pstmt=con.prepareStatement(sql);
		pstmt.setString(1, deletedBookID);
		pstmt.executeUpdate();
		
	} catch (SQLException se) {
		System.err.println("数据库删除记录出错");
		System.err.println(se);
		return -1;
		// TODO: handle exception
	}
	return 0;
}
}
