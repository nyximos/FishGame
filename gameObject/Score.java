package gameObject;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;



public class Score extends JFrame {
	// DB ����
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;

	// ������ ����
	int z = 10;
	int[] spots = new int[z];
	int currentY = 0;
	JLabel[] jl = new JLabel[z];
//   String[] srr = new String[z];

	// ����
	static int pk = 1;

	

	public Score(String name, double score) {
		this.setTitle("������");
		this.setSize(1080, 720);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		// ȭ���߾�
		this.setLocationRelativeTo(null);
//		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
//		this.setLocation(size.width / 2 - getSize().width / 2, size.height / 2 - getSize().height / 2);

		// ����
		for (int i = 0; i < z; i++) {
			spots[i] = currentY;
			currentY += 50;
		}
		makeConnection();
	}

	public void insert(String name, double score) {
		selectNumDB("select * from score");
		insertDB("insert into score values(" + ++pk + ",'" + name + "'," + (int) score + ")");

	}

	public void select() {
		selectDB("select * from (select * from score order by score desc) where rownum <= 10");
		this.setVisible(true);

		try {
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void makeConnection() { // conn�� stmt�� �������ش�.
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "test";
		String password = "test";

		// 1. JDBC ����̹� ����
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("����̹� �ε��� ����!!");

		// 2. �����ͺ��̽� ����
		try {
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.createStatement();
			System.out.println("DB ����");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// DB ����
	public void insertDB(String str) {
		try {
			selectNumDB("select * from score");
			stmt.executeUpdate(str);
			System.out.println(str);
		} catch (SQLException e) {
			System.out.println("SQLException �߻�");
			e.printStackTrace();
		}
	}

	// DB ��ȸ
	public void selectDB(String str) {
		try {
			rs = stmt.executeQuery(str);
			int i = 0;

			while (rs.next()) {
				String name = rs.getString("name");
				int score = rs.getInt("score");
				Font font= new Font("Jalnan", Font.PLAIN, 30);
// 			  �� �����ӿ� �󺧷� �־������
//            srr[i] = "#"+(i+1)+name+score;
				jl[i] = new JLabel((i+1)+ "��   " + name + "       " + score+"��");
				jl[i].setBounds(350, spots[i]+50, 600, 100);
	            jl[i].setFont(font);
				this.add(jl[i]);
				i++;
				System.out.println(name + score);
			}
			pk = i;
			System.out.println("DB ��ȸ �Ϸ�");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

// DB �� �� �ִ��� ��ȸ
	public void selectNumDB(String str) {
		try {
			rs = stmt.executeQuery(str);
			int i = 0;

			while (rs.next()) {
				i++;
			}
			pk = i;
			System.out.println("DB ��ȸ �Ϸ�");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
//	public void paint(Graphics g) {//�׸��� �Լ�
//		g.drawImage(background, 0, 0, null);//background�� �׷���
//	}
//   public void draw(Graphics bg) {
//	   for (int i=0; i<z; i++) {
//		   bg.setColor(new Color(50,50,50,128));
//		   bg.drawRect(0,  spots[i], 125, 30);
//		   bg.fillRect(0, spots[i], 125, 30);
//		   bg.setColor(Color.black);
////		   bg.drawStrig(sr[i], 0, spots[i]);
//	   }
//   }

}