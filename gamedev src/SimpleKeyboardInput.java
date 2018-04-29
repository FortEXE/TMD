import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.*;
import javax.swing.JFrame;

public class SimpleKeyboardInput extends JFrame {

  static final int WIDTH = 640;
  static final int HEIGHT = 480;
  class Bob { int x, y, w, h, dx, dy; }
  KeyboardInput keyboard = new KeyboardInput(); // Keyboard polling
  Canvas canvas; // Our drawing component
  Vector< Point > circles = new Vector< Point >(); // Circles
  Bob bob = new Bob(); // Our rectangle
  Random rand = new Random(); // Used for random circle locations

  public SimpleKeyboardInput() {
  
    setIgnoreRepaint( true );
    setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    canvas = new Canvas();
    canvas.setIgnoreRepaint( true );
    canvas.setSize( WIDTH, HEIGHT );
    add( canvas );
    pack();
    
    // Hookup keyboard polling
    addKeyListener( keyboard );
    canvas.addKeyListener( keyboard );
    
    bob.x = bob.y = 0;
    bob.dx = bob.dy = 5;
    bob.w = bob.h = 25;
  }
        
  public void run() {
  
    canvas.createBufferStrategy( 2 );
    BufferStrategy buffer = canvas.getBufferStrategy();
    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    GraphicsDevice gd = ge.getDefaultScreenDevice();
    GraphicsConfiguration gc = gd.getDefaultConfiguration();
    BufferedImage bi = gc.createCompatibleImage( WIDTH, HEIGHT );
    
    Graphics graphics = null;
    Graphics2D g2d = null;
    Color background = Color.BLACK;
    
    while( true ) {
      try {
      
        // Poll the keyboard
        keyboard.poll();
        // Should we exit?
        if( keyboard.keyDownOnce( KeyEvent.VK_ESCAPE ) )
          break;
        
        // Clear the back buffer          
        g2d = bi.createGraphics();
        g2d.setColor( background );
        g2d.fillRect( 0, 0, WIDTH, HEIGHT );
        
        // Draw help
        g2d.setColor(  Color.GREEN );
        g2d.drawString( "Use arrow keys to move rect", 20, 20 );
        g2d.drawString( "Press SPACE to add circles", 20, 32 );
        g2d.drawString( "Press C to clear circles", 20, 44 );
        g2d.drawString( "Press ESC to exit", 20, 56 );
        
        // Move bob and add circles
        processInput();
        
        // Draw random circles
        g2d.setColor( Color.MAGENTA );
        for( Point p : circles ) {
          g2d.drawOval( p.x, p.y, 25, 25 );
        }
        
        // Draw bob
        g2d.setColor(  Color.GREEN );
        g2d.drawRect( bob.x, bob.y, bob.w, bob.h );
        
        // Blit image and flip...
        graphics = buffer.getDrawGraphics();
        graphics.drawImage( bi, 0, 0, null );
        if( !buffer.contentsLost() )
          buffer.show();
          
        // Let the OS have a little time...
        try {
          Thread.sleep(10);
        } catch (InterruptedException e) {
        }
        
      } finally {
        // Release resources
        if( graphics != null ) 
          graphics.dispose();
        if( g2d != null ) 
          g2d.dispose();
      }
    }
  }
        
  protected void processInput() {
    // If moving down
    if( keyboard.keyDown( KeyEvent.VK_DOWN ) ) {
      bob.y += bob.dy;
      // Check collision with botton
      if( bob.y + bob.h > HEIGHT - 1 )
        bob.y = HEIGHT - bob.h - 1;
    }
    // If moving up
    if( keyboard.keyDown( KeyEvent.VK_UP ) ) {
      bob.y -= bob.dy;
      // Check collision with top
      if( bob.y < 0 )
        bob.y = 0;
    }
    // If moving left
    if( keyboard.keyDown( KeyEvent.VK_LEFT ) ) {
      bob.x -= bob.dx;
      // Check collision with left
      if( bob.x < 0 )
        bob.x = 0;
    }
    // If moving right
    if( keyboard.keyDown( KeyEvent.VK_RIGHT ) ) {
      bob.x += bob.dx;
      // Check collision with right
      if( bob.x + bob.w > WIDTH - 1 )
        bob.x = WIDTH - bob.w - 1;
    }
    // Add random circle if space bar is pressed
    if( keyboard.keyDownOnce( KeyEvent.VK_SPACE ) ) {
      int x = rand.nextInt( WIDTH );
      int y = rand.nextInt( HEIGHT );
      circles.add( new Point( x, y ) );
    }
    // Clear circles if they press C
    if( keyboard.keyDownOnce( KeyEvent.VK_C ) ) {
      circles.clear();
    }
  }
        
  public static void main( String[] args ) {
    SimpleKeyboardInput app = new SimpleKeyboardInput();
    app.setTitle( "Simple Keyboard Input" );
    app.setVisible( true );
    app.run();
    System.exit( 0 );
  }
}