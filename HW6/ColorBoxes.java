/*-------------------------------------------------- 
 Author: Srizan Gangol Course:CSCE4430 Homework#6
 ColorBoxes.java ©CSCE4430
 USUAGE:
	$ javac ColorBoxes.java
	$ java ColorBoxes
-----------------------------------------------------*/

// Using the Runnable interface
import java.awt.*;
import java.awt.event.*;

class CBox extends Canvas implements Runnable {
  private Thread t;
  private int pause;
  
  private long startTimeMillis;
  
  private static final Color[] colors = { 
    Color.black, Color.blue, Color.cyan, 
    Color.darkGray, Color.gray, Color.green,
    Color.lightGray, Color.magenta, 
    Color.orange, Color.pink, Color.red, 
    Color.white, Color.yellow 
  };
  private Color cColor = newColor();
  private static final Color newColor() {
    return colors[
      (int)(Math.random() * colors.length)
    ];
  }
  public void paint(Graphics  g) {
    g.setColor(cColor);
    Dimension s = getSize();
    g.fillRect(0, 0, s.width, s.height);
  }
  public CBox(int pause) {
    this.pause = pause;
    t = new Thread(this);
    t.start();
	
	
	//System.out.println("id of the thread is " + t.getId()); 
  }
  public void run() {
    while(true) {
	 
      cColor = newColor();
      repaint();
	// System.out.println("id of the thread is " + t.getId()); 
	
      try {
		   
		startTimeMillis = System.currentTimeMillis();	
				   
        t.sleep(pause);
		System.out.println("Thread ID: " + t.getId()+ " Time Elapsed: " + (System.currentTimeMillis() - startTimeMillis));
		//System.out.println(System.currentTimeMillis() - startTimeMillis);
      } catch(InterruptedException e) {}
    } 
  }
} 

public class ColorBoxes extends Frame {
  public ColorBoxes(int pause, int grid) {
    setTitle("ColorBoxes");
    setLayout(new GridLayout(grid, grid));
    for (int i = 0; i < grid * grid; i++)
      add(new CBox(pause));
	  addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });
  }   
  public static void main(String[] args) {
    int pause = 500;
    int grid = 8;
	
    if(args.length > 0) 
      pause = Integer.parseInt(args[0]);
    if(args.length > 1)
      grid = Integer.parseInt(args[1]);
    Frame f = new ColorBoxes(pause, grid);
    f.setSize(5000000, 4000000);
	
    f.setVisible(true);  
  }
} ///:
