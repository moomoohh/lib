import java.util.Vector;
import javax.swing.JOptionPane;
import java.awt.Component;

public class Service {
	private static DataOperator dataOperate = new DataOperator();

	public static int addBook(Vector<String> bookInfo) {
		if (dataOperate.insert(bookInfo) == -1)
			return -1;
		return 0;
	}

	public static int modifyBook(Vector<String> bookInfo) {
		if (dataOperate.update(bookInfo) == -1)
			return -1;
		return 0;
	}

	public static void connect() {
		dataOperate.loadDatabaseDriver();
		dataOperate.connect();
	}

	public static void quit() {
		dataOperate.disConnect();
	}

	public static Vector<String> publishers() {
		return dataOperate.publishersQuery();
	}

	public static Vector<String> language() {
		return dataOperate.languageQuery();
	}

	public static Vector<String> author() {
		return dataOperate.authorQuery();
	}

	public static Vector<String> seek(int f, int operateFlag, String name, String author, String publisher,
			String condition) {
		String sql = "select *from BOOKINFO ";
		int selectFlag = 0;
		if (f == 1) {
			if (name != null && !name.equals("")) {
				sql += "where ISBN like ?";
				selectFlag = 1;
				if (!author.equals("")) {
					sql += " and 作者 like ?";
					selectFlag = 2;
					if (!publisher.equals("")) {
						sql += " and 出版社 like ?";
						selectFlag = 3;
					}
				} else {
					if (!publisher.equals("")) {
						sql += " and 出版社 like ?";
						selectFlag = 4;
					}
				}
			} else {
				if (!author.equals("")) {
					sql += "where 作者 like ?";
					selectFlag = 5;
					if (!publisher.equals("")) {
						sql += " and 出版社 like ?";
						selectFlag = 6;
					}
				} else {
					if (!publisher.equals("")) {
						sql += "where 出版社 like ?";
						selectFlag = 7;
					}
				}
			}
			if (condition.equals("模糊查询")) {
				name = "%" + name + "%";
				author = "%" + author + "%";
				publisher = "%" + publisher + "%";
			}
		} else if (f == 2) {
			if (name != null && !name.equals("")) {
				sql += "where 书名 like ?";
				selectFlag = 1;
				if (!author.equals("")) {
					sql += " and 作者 like ?";
					selectFlag = 2;
					if (!publisher.equals("")) {
						sql += " and 出版社 like ?";
						selectFlag = 3;
					}
				} else {
					if (!publisher.equals("")) {
						sql += " and 出版社 like ?";
						selectFlag = 4;
					}
				}
			} else {
				if (!author.equals("")) {
					sql += "where 作者 like ?";
					selectFlag = 5;
					if (!publisher.equals("")) {
						sql += " and 出版社 like ?";
						selectFlag = 6;
					}
				} else {
					if (!publisher.equals("")) {
						sql += "where 出版社 like ?";
						selectFlag = 7;
					}
				}
			}

			if (condition.equals("模糊查询")) {
				name = "%" + name + "%";
				author = "%" + author + "%";
				publisher = "%" + publisher + "%";
			}
		}

		Vector<String> infoStringCollection = dataOperate.generaQuery(operateFlag, sql, selectFlag, name, author,
				publisher);
		return infoStringCollection;
	}

	public static int deleteBook(Component c, String book) {
		String str[] = new String[10];
		int index = -1;
		for (int i = 0; i < 9; i++) {
			index = book.indexOf(',');
			str[i] = book.substring(0, index);
			book = book.substring(index + 1);
		}
		int confirm = JOptionPane.showConfirmDialog(c,
				"您确定要删除的一本书的信息如下：\n" + "ISBN：" + str[0] + "\n" + "书名：" + str[1] + "\n" + "作者：" + str[2] + "\n" + "出版社："
						+ str[3] + "\n" + "价格：" + str[4] + "\n" + "语言：" + str[5] + "\n" + "页数：" + str[6] + "\n"
						+ "出版时间：" + str[7] + "\n" + "数量：" + str[9] + "\n" + "存放位置：" + str[9] + "\n" + "确定需要删除吗？");
		// if(confirm=='Y')
		if (dataOperate.delete(str[0]) == -1)
			return -1;
		else
			return 0;
		// else
		// return -1;
	}

	public static String detailsBook(int operateFlag, String book) {
		String str[] = new String[10];
		int index = -1;
		for (int i = 0; i < 9; i++) {
			index = book.indexOf(',');
			str[i] = book.substring(0, index);
			book = book.substring(index + 1);
		}
		String details = "您要查看的一条：\n" + "ISBN：" + str[0] + "\n" + "书名：" + str[1] + "\n" + "作者：" + str[2] + "\n" + "出版社："
				+ str[3] + "\n" + "价格：" + str[4] + "\n" + "语言：" + str[5] + "\n" + "页数：" + str[6] + "\n" + "出版时间："
				+ str[7] + "\n" + "数量：" + str[8] + "\n" + "存放位置：" + book + "\n";
		return details;
	}

}
