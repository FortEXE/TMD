import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Vector;
import javax.swing.JFrame;

public class SimpleMouseInput extends JFrame {
        
  static final int WIDTH = 640;
  static final int HEIGHT = 480;
        
  // The new mouse input class
  MouseInput1 mouse;
  // Keyboard polling
  KeyboardInput keyboard;
  // Adding a null into this list will start a new line
  Vector< Point > lines = new Vector< Point >();
  // Are we currently drawing a line?
  boolean drawingLine;
  // Our drawing component
  Canvas canvas;

  public SimpleMouseInput() {
                
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
    mouse = new MouseInput1();
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
        g2d.drawString( "Use mouse to draw lines", 20, 20 );
        g2d.drawString( "Press C to clear lines", 20, 32 );
        g2d.drawString( "Press ESC to exit", 20, 44 );
        g2d.drawString( mouse.getPosition().toString(), 20, 56 );
                                
        // Process mouse input
        processInput();

        // Set line color
        g2d.setColor(  Color.WHITE );

        // If just one line, draw a point
        if( lines.size() == 1 ) {
          Point p = lines.get( 0 );
          if( p != null )
            g2d.drawLine( p.x, p.y, p.x, p.y );
        } else {
          // Draw all the lines
          for( int i = 0; i < lines.size()-1; ++i ) {
            Point p1 = lines.get( i );
            Point p2 = lines.get( i+1 );
            // Adding a null into the list is used
            // for breaking up the lines when
            // there are two or more lines
            // that are not connected
            if( !(p1 == null || p2 == null) )
              g2d.drawLine( p1.x, p1.y, p2.x, p2.y );
          }
        }
                                
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
    // if button pressed for first time,
    // start drawing lines
    if( mouse.buttonDownOnce( 1 ) ) {
      drawingLine = true;
    }
    // if the button is down, add line point
    if( mouse.buttonDown( 1 ) ) {
      lines.add( mouse.getPosition() );
    // if the button is not down but we were drawing,
    // add a null to break up the lines
    } else if( drawingLine ){
      lines.add( null );
      drawingLine = false;
    }
    // if 'C' is down, clear the lines
    if( keyboard.keyDownOnce( KeyEvent.VK_C ) ) {
        lines.clear();
    }
  }
        
  public static void main( String[] args ) {
    SimpleMouseInput app = new SimpleMouseInput();
    app.setTitle( "Simple Mouse Example" );
    app.setVisible( true );
    app.run();
    System.exit( 0 );
  }
}