import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public class RelativeMouseInput extends JFrame {
        
  static final int WIDTH = 640;
  static final int HEIGHT = 480;

  // Used for drawing rectangle
  Point point = new Point(0,0);
  // Used to toggle relative/absolute
  boolean relative = false;
  // Used to toggle the cursor
  boolean disableCursor = false;
  // Relative mouse input class
  MouseInput2 mouse;
  // Keyboard polling
  KeyboardInput keyboard;
  // Our drawing component
  Canvas canvas;

  public RelativeMouseInput() {
                
    // Setup specific JFrame properties
    setIgnoreRepaint( true );
    setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

    // Create canvas to force the drawing
    // surface to the correct size...
    canvas = new Canvas();
    canvas.setIgnoreRepaint( true );
    canvas.setSize( WIDTH, HEIGHT );
    add( canvas );
    pack();
    
    // Add key listeners
    keyboard = new KeyboardInput();
    addKeyListener( keyboard );
    canvas.addKeyListener( keyboard );
                
    // Add mouse listeners
    // For full screen : mouse = new MouseInput( this );
    mouse = new MouseInput2( canvas );
    addMouseListener( mouse );
    addMouseMotionListener( mouse );
    canvas.addMouseListener( mouse );
    canvas.addMouseMotionListener( mouse );
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
        // Poll the mouse
        mouse.poll();
                                
        // Exit the program on ESC key
        if( keyboard.keyDownOnce( KeyEvent.VK_ESCAPE ) )
          break;
                
        // Clear back buffer...
        g2d = bi.createGraphics();
        g2d.setColor( background );
        g2d.fillRect( 0, 0, WIDTH, HEIGHT );
                                
        // Display help
        g2d.setColor(  Color.GREEN );
        g2d.drawString( "Position: " + mouse.getPosition().toString(), 20, 20 );
        g2d.drawString( "Press Space to switch mouse modes", 20, 32 );
        g2d.drawString( "Press C to toggle cursor", 20, 44 );
        g2d.drawString( "Press ESC to exit", 20, 56 );

        // Process mouse input
        processInput();

        // Draw the rectangle
        g2d.setColor( Color.WHITE );
        g2d.drawRect( point.x, point.y, 25, 25 );
        
        // Blit image and flip...
        graphics = buffer.getDrawGraphics();
        graphics.drawImage( bi, 0, 0, null );
        if( !buffer.contentsLost() ) 
          buffer.show();
                                
        // Let the OS have a little time...
        try {
          Thread.sleep(10);
        } catch( InterruptedException ex ) {
                                        
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
    // If relative, move the rectangle
    if( mouse.isRelative() ) {
      Point p = mouse.getPosition();
      point.translate( p.x, p.y );
      // Wrap rectangle around the screen
      if( point.x + 25 < 0 ) 
        point.x = WIDTH - 1;
      else if( point.x > WIDTH - 1 ) 
        point.x = -25;
      if( point.y + 25 < 0 ) 
        point.y = HEIGHT - 1;
      else if( point.y > HEIGHT - 1 ) 
        point.y = -25;
    } 
    // Toggle relative
    if( keyboard.keyDownOnce( KeyEvent.VK_SPACE ) ) {
      relative = !relative;
      mouse.setRelative( relative );
      setTitle( "Relative: " + relative );
    }
    // Toggle cursor
    if( keyboard.keyDownOnce( KeyEvent.VK_C ) ) {
        disableCursor = !disableCursor;
      if( disableCursor ) {
          disableCursor(); 
      } else {
          // setCoursor( Cursor.DEFAULT_CURSOR ) is deprecated
          setCursor( new Cursor( Cursor.DEFAULT_CURSOR ) );
      }
    }
  }
  
  private void disableCursor() {
    Toolkit tk = Toolkit.getDefaultToolkit();
    Image image = tk.createImage( "" );
    Point point = new Point( 0, 0 );
    String name = "CanBeAnything";
    Cursor cursor = tk.createCustomCursor( image, point, name ); 
    setCursor( cursor );
  }
        
  public static void main( String[] args ) {
    RelativeMouseInput app = new RelativeMouseInput();
    app.setTitle( "Simple Mouse Example" );
    app.setVisible( true );
    app.run();
    System.exit( 0 );
  }
}