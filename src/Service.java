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
					sql += " and ���� like ?";
					selectFlag = 2;
					if (!publisher.equals("")) {
						sql += " and ������ like ?";
						selectFlag = 3;
					}
				} else {
					if (!publisher.equals("")) {
						sql += " and ������ like ?";
						selectFlag = 4;
					}
				}
			} else {
				if (!author.equals("")) {
					sql += "where ���� like ?";
					selectFlag = 5;
					if (!publisher.equals("")) {
						sql += " and ������ like ?";
						selectFlag = 6;
					}
				} else {
					if (!publisher.equals("")) {
						sql += "where ������ like ?";
						selectFlag = 7;
					}
				}
			}
			if (condition.equals("ģ����ѯ")) {
				name = "%" + name + "%";
				author = "%" + author + "%";
				publisher = "%" + publisher + "%";
			}
		} else if (f == 2) {
			if (name != null && !name.equals("")) {
				sql += "where ���� like ?";
				selectFlag = 1;
				if (!author.equals("")) {
					sql += " and ���� like ?";
					selectFlag = 2;
					if (!publisher.equals("")) {
						sql += " and ������ like ?";
						selectFlag = 3;
					}
				} else {
					if (!publisher.equals("")) {
						sql += " and ������ like ?";
						selectFlag = 4;
					}
				}
			} else {
				if (!author.equals("")) {
					sql += "where ���� like ?";
					selectFlag = 5;
					if (!publisher.equals("")) {
						sql += " and ������ like ?";
						selectFlag = 6;
					}
				} else {
					if (!publisher.equals("")) {
						sql += "where ������ like ?";
						selectFlag = 7;
					}
				}
			}

			if (condition.equals("ģ����ѯ")) {
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
				"��ȷ��Ҫɾ����һ�������Ϣ���£�\n" + "ISBN��" + str[0] + "\n" + "������" + str[1] + "\n" + "���ߣ�" + str[2] + "\n" + "�����磺"
						+ str[3] + "\n" + "�۸�" + str[4] + "\n" + "���ԣ�" + str[5] + "\n" + "ҳ����" + str[6] + "\n"
						+ "����ʱ�䣺" + str[7] + "\n" + "������" + str[9] + "\n" + "���λ�ã�" + str[9] + "\n" + "ȷ����Ҫɾ����");
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
		String details = "��Ҫ�鿴��һ����\n" + "ISBN��" + str[0] + "\n" + "������" + str[1] + "\n" + "���ߣ�" + str[2] + "\n" + "�����磺"
				+ str[3] + "\n" + "�۸�" + str[4] + "\n" + "���ԣ�" + str[5] + "\n" + "ҳ����" + str[6] + "\n" + "����ʱ�䣺"
				+ str[7] + "\n" + "������" + str[8] + "\n" + "���λ�ã�" + book + "\n";
		return details;
	}

}
