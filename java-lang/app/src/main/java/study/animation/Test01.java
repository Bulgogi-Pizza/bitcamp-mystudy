package study.animation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Test01 extends JPanel implements ActionListener {
  private static final int PANEL_WIDTH = 400;
  private static final int PANEL_HEIGHT = 400;
  private static final int PIXEL_SIZE = 20;
  private int x = 0;
  private int y = 0;
  private int dx = 2;
  private int dy = 2;
  private Timer timer;

  public Test01() {
    this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
    this.setBackground(Color.WHITE);
    timer = new Timer(25, this); // 애니메이션 속도 조절 (25 밀리초 대기)
    timer.start();
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    // 기존 배경 색으로 픽셀 지우기
    g.setColor(Color.WHITE);
    g.fillRect(0, 0, PANEL_WIDTH, PANEL_HEIGHT);

    // 움직이는 픽셀 그리기
    g.setColor(Color.BLACK);
    g.fillOval(x, y, PIXEL_SIZE, PIXEL_SIZE);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // 픽셀 위치 갱신
    x += dx;
    y += dy;

    // 경계 체크 및 방향 전환
    if (x < 0 || x > PANEL_WIDTH - PIXEL_SIZE) {
      dx = -dx;
    }
    if (y < 0 || y > PANEL_HEIGHT - PIXEL_SIZE) {
      dy = -dy;
    }

    repaint(); // 화면 갱신
  }

  public static void main(String[] args) {
    JFrame frame = new JFrame("Pixel Animation");
    Test01 animation = new Test01();
    frame.add(animation);
    frame.pack();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null); // 화면 중앙에 배치
    frame.setVisible(true);
  }
}
