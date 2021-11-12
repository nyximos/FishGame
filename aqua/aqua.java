package aqua;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class aqua extends JFrame {
	
    class MyPanel extends JPanel{
        ImageIcon icon = new ImageIcon("./src/aquarium1.jpg");
        Image img = icon.getImage();
        
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }
	
	// 생성자
	public aqua() {
		setTitle("물고기 게임");
		setSize(1000,800);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MyPanel panel = new MyPanel();
		this.add(panel, BorderLayout.CENTER);
		
		//아이콘 설정
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.getImage("./src/helpalien.gif");
		this.setIconImage(img);
		
		// 프레임의 위치 선정, 프레임 스크린 중앙에 배치하기
		setLocationRelativeTo(null);
		

		
		
        setVisible(true);    // GUI 보이게 (띄우기)

	}
	


}
