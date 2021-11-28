//package gameObject;
//
//import java.awt.Color;
//import java.awt.Dimension;
//import java.awt.Graphics;
//import java.awt.Toolkit;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//
//public class Score extends JFrame {
//
//	   
//   // DB 관련
//   Connection conn = null;
//   Statement stmt = null;
//   ResultSet rs = null;
//
//   // 점수판 순위
//   int z = 10;
//   int[] spots = new int[z];
//   int currentY = 0;
//   JLabel[] jl = new JLabel[z];
////   String[] srr = new String[z];
//   
//   // 공용
//   static int pk = 1;
//
//   public Score(String name, double score) {
//      this.setTitle("점수판");
//      this.setSize(500, 500);
//      this.setDefaultCloseOperation(EXIT_ON_CLOSE);
////      this.setLayout(null);
//
//      // 화면중앙
//      Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
//      this.setLocation(size.width / 2 - getSize().width / 2, size.height / 2 - getSize().height / 2);
//
//      // 설정
//      for (int i = 0; i < z; i++) {
//         spots[i] = currentY;
//         currentY += 30;
//      }
//
//      makeConnection();
//      selectDB("select * from score");
//      insertDB("insert into score values("+ ++pk + ",'" + name + "'," + score + ")");
//      selectDB("select * from score where rownum<=10 order by score desc");
//      this.setVisible(true);
//
//      try {
//         rs.close();
//      } catch (SQLException e) {
//         // TODO Auto-generated catch block
//         e.printStackTrace();
//      }
//      try {
//         stmt.close();
//      } catch (SQLException e) {
//         // TODO Auto-generated catch block
//         e.printStackTrace();
//      }
//      try {
//         conn.close();
//      } catch (SQLException e) {
//         // TODO Auto-generated catch block
//         e.printStackTrace();
//      }
//
//   }
//
//
//   public void makeConnection() { // conn과 stmt를 설정해준다.
//      String url = "jdbc:oracle:thin:@localhost:1521:xe";
//      String user = "test";
//      String password = "test";
//
//      // 1. JDBC 드라이버 적재
//      try {
//         Class.forName("oracle.jdbc.driver.OracleDriver");
//      } catch (ClassNotFoundException e) {
//         e.printStackTrace();
//      }
//      System.out.println("드라이버 로딩이 성공!!");
//
//      // 2. 데이터베이스 연결
//      try {
//         conn = DriverManager.getConnection(url, user, password);
//         stmt = conn.createStatement();
//         System.out.println("DB 연결");
//      } catch (SQLException e) {
//         e.printStackTrace();
//      }
//   }
//
//   // DB 삽입
//   public void insertDB(String str) {
//      try {
//         stmt.executeUpdate(str);
//         System.out.println(str);
//      } catch (SQLException e) {
//         System.out.println("SQLException 발생");
//         e.printStackTrace();
//      }
//   }
//
//   // DB 조회
//   public void selectDB(String str) {
//      try {
//         rs = stmt.executeQuery(str);
//         int i = 0;
//
//         while (rs.next()) {
//            String name = rs.getString("name");
//            Double score = rs.getDouble("score");
//            // 이 프레임에 라벨로 넣어줘야함
////            srr[i] = "#"+(i+1)+name+score;
//            jl[i] = new JLabel("#" + name + score);
//            jl[i].setBounds(0, spots[i], 100, 100);
//            this.add(jl[i]);
//            i++;
//            System.out.println(name + score);
//         }
//         pk = i;
//         System.out.println("DB 조회 완료");
//      } catch (SQLException e) {
//         e.printStackTrace();
//      }
//   }
//   
////   public void draw(Graphics bg) {
////	   for (int i=0; i<z; i++) {
////		   bg.setColor(new Color(50,50,50,128));
////		   bg.drawRect(0,  spots[i], 125, 30);
////		   bg.fillRect(0, spots[i], 125, 30);
////		   bg.setColor(Color.black);
//////		   bg.drawStrig(sr[i], 0, spots[i]);
////	   }
////   }
//}