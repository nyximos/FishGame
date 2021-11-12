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
	
	// ������
	public aqua() {
		setTitle("����� ����");
		setSize(1000,800);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MyPanel panel = new MyPanel();
		this.add(panel, BorderLayout.CENTER);
		
		//������ ����
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.getImage("./src/helpalien.gif");
		this.setIconImage(img);
		
		// �������� ��ġ ����, ������ ��ũ�� �߾ӿ� ��ġ�ϱ�
		setLocationRelativeTo(null);
		

		
		
        setVisible(true);    // GUI ���̰� (����)

	}
	


}
