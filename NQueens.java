import javax.swing.*;
import javax.swing.event.*;
import javax.imageio.*;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.awt.event.*;
import java.io.*;


public class NQueens extends JPanel implements MouseListener {

  private static JPanel canvas;
  private static int[][] board;
  private static int placed = 0;
  private static boolean solved = false;
  private static Image image;
  private static JButton reset = new JButton("Reset");
  private static JButton runAlg = new JButton("Run AI");
  private static JSlider nSlider = new JSlider(2,4,3);
  private static int n = (int) Math.pow(2,nSlider.getValue());
  private static int CUBE_S = (int) 800/n;

  public NQueens() {
    addMouseListener(this);
    setSize(801,801);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    for(int i = 0; i < board.length; i++) {
      for(int j = 0; j < board[0].length; j++) {
        g.setColor(Color.BLACK);
        g.drawRect(i*CUBE_S,j*CUBE_S,CUBE_S,CUBE_S);
        switch(board[i][j]) {
          case 1:
            int size = (int)CUBE_S/5;
            g.fillOval((i*CUBE_S)+(CUBE_S/2)-(size/2),(j*CUBE_S)+(CUBE_S/2)-(size/2),size,size);
            break;
          case 2:

            g.drawImage(image, (i*CUBE_S)+(CUBE_S/4),(j*CUBE_S)+(CUBE_S/4), null);
            break;
        }
      }
    }
  }

  @Override
	public void mousePressed(MouseEvent e) {
    int x = e.getX()/CUBE_S;
		int y = e.getY()/CUBE_S;
    placeQueen(x,y);
    repaint();
    if(placed == n) {
      JOptionPane.showMessageDialog(null, "Good Job, you did it!");
    }
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    //System.out.println("Released");
  }

  @Override
  public void mouseExited(MouseEvent e) {
    //System.out.println("Exited");
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    //System.out.println("Entered");
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    //System.out.println("Clicked");
  }

  public static boolean placeQueen(int x, int y) {
    if(board[x][y] == 0) {
      board[x][y] = 2;
      placed++;
      int startx = x;
      int starty = y;
      while(startx>0) {
        startx--;
        board[startx][starty] = 1;
      }
      startx = x;
      starty = y;
      while(startx<board.length-1) {
        startx++;
        board[startx][starty] = 1;
      }
      startx = x;
      starty = y;
      while(starty>0) {
        starty--;
        board[startx][starty] = 1;
      }
      startx = x;
      starty = y;
      while(starty<board.length-1) {
        starty++;
        board[startx][starty] = 1;
      }
      startx = x;
      starty = y;
      while(startx>0 && starty<board.length-1) {
        startx--;
        starty++;
        board[startx][starty] = 1;
      }
      startx = x;
      starty = y;
      while(startx<board.length-1 && starty>0) {
        startx++;
        starty--;
        board[startx][starty] = 1;
      }
      startx = x;
      starty = y;
      while(startx<board.length-1 && starty<board.length-1) {
        startx++;
        starty++;
        board[startx][starty] = 1;
      }
      startx = x;
      starty = y;
      while(startx>0 && starty>0) {
        startx--;
        starty--;
        board[startx][starty] = 1;
      }
      return true;
    }
    return false;
  }

  public static void loadAndScaleImage() {
    try {
      int SCALE = (int) CUBE_S/2;

      Image img = ImageIO.read(NQueens.class.getResource("Queen.png"));

      BufferedImage bi = new BufferedImage(SCALE, SCALE, BufferedImage.TRANSLUCENT);

      Graphics2D g2 = bi.createGraphics();

      g2.drawImage(img, 0, 0, SCALE, SCALE, null);
      g2.dispose();

      image = bi;
    } catch(IOException ex) {}
  }

  public static void AI() {
    int index = 0;
    int[] testIndexes = new int[n];
    while(index < n) {
      while(!placeQueen(index,testIndexes[index])) {
        testIndexes[index]++;
        if(testIndexes[index] == n) {
          testIndexes[index] = 0;
          index--;
          while(testIndexes[index] == n-1) {
            index--;
          }
          testIndexes[index]++;
          board = new int[n][n];
          index = 0;
        }
      }
      index++;
    }
    canvas.repaint();
  }

  public static void main(String[] args) {
    loadAndScaleImage();
    board = new int[n][n];
    JFrame frame = new JFrame();
    canvas = new NQueens();
    runAlg.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        AI();
      }
    });
    reset.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        board = new int[n][n];
        placed = 0;
        canvas.repaint();
      }
    });
    nSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
        n = (int)Math.pow(2,nSlider.getValue());
        board = new int[n][n];
        placed = 0;
        CUBE_S = 800 / n;
        loadAndScaleImage();
        canvas.repaint();
			}
		});
    frame.setTitle("N Queens");
    frame.pack();
    frame.setSize(815,880);
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(canvas);
    frame.getContentPane().setLayout(null);
    runAlg.setBounds(200,800,100,40);
    frame.getContentPane().add(runAlg);
    reset.setBounds(310,800,100,40);
    frame.getContentPane().add(reset);
    nSlider.setBounds(415,810,100,30);
    frame.getContentPane().add(nSlider);
    frame.setVisible(true);
  }

}
