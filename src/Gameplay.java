import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Gameplay extends JPanel implements KeyListener,ActionListener
{
    
    private boolean play =false;
    private int score = 0;
    public int totalBrick=21;
    private Timer timer;
    private int delay=0;
    private int playerx=320;
    private int ballposx=120;
    private int ballposy=350;
    private int ballxdir=-1;
    private int ballydir=-2;

    private Mapgen map;

    public Gameplay()
    {
        map=new Mapgen(3, 7);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer =new Timer(delay,this);
        timer.start();
    }
    public void paint(Graphics g)
    {
        //background
        g.setColor(Color.BLACK);
        g.fillRect(1, 1, 692, 592);
        //drawing map
        map.draw((Graphics2D) g);
        //border
        g.setColor(Color.orange);
        g.fillRect(0,0, 3, 592);
        g.fillRect(0,0, 692, 3);
        g.fillRect(961,0, 3, 592);
        //score
        g.setColor(Color.cyan);
        g.setFont(new Font("serif",Font.BOLD,25));
        g.drawString(""+score,59,30);
        //paddle
        g.setColor(Color.green);
        g.fillRect(playerx,550, 100, 8);
        //the ball
        g.setColor(Color.yellow);
        g.fillOval(ballposx, ballposy, 20, 20);
        if (totalBrick<=0)
        {
            play=false;
            ballxdir=0;
            ballydir=0;
            g.setColor(Color.red);
            g.setFont(new Font("serif",Font.BOLD,35));
            g.drawString("you won", 260, 300);
            g.setFont(new Font("serif",Font.BOLD,20));
            g.drawString("press enter to restart",230,350);
        }
        if (ballposy>570)
        {
            play=false;
            ballxdir=0;
            ballydir=0;
            g.setColor(Color.red);
            g.setFont(new Font("serif",Font.BOLD,35));
            g.drawString("Game Over", 190, 300);
            g.setFont(new Font("serif",Font.BOLD,20));
            g.drawString("press enter to restart",230,350);
        }
        g.dispose();
        
    }
    

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        timer.start();
        if (play)
        {
            if (new Rectangle(ballposx, ballposy, 20, 20).intersects(new Rectangle(playerx,550,100,8)))
            {
                ballydir=-ballydir;
            }
           A: for (int i=0;i<map.map.length;i++)
            {
                for (int j=0;j<map.map[0].length;j++)
                {
                    if (map.map[i][j]>0)
                    {
                        int brickX=j*map.brickwidth+80;
                        int brickY=i*map.brickheight+50;
                        int brickwidth=map.brickwidth;
                        int brickheight=map.brickheight;
                        
                        Rectangle rect=new Rectangle(brickX,brickY,brickwidth,brickheight);
                        Rectangle ballRect=new Rectangle(ballposx,ballposy,20,20);
                        Rectangle brickrect=rect;
                        if (ballRect.intersects(brickrect))
                        {
                            map.setBrickValue(0, i, j);
                            totalBrick--;
                            score+=5;
                            
                            if (ballposx+19<=brickrect.x || ballposx+1>=brickrect.x+brickrect.width)
                            {
                                ballxdir=-ballxdir;

                            }
                            else
                            {
                                ballydir=-ballydir;
                            }
                            break A;
                        }
                    }
                }
            }
            ballposx+=ballxdir;
            ballposy+=ballydir;
            if (ballposx<0)
            {
                ballxdir=-ballxdir;
            }
            if (ballposy<0)
            {
                ballydir=-ballydir;
            }
            if (ballposx>670)
            {
                ballxdir=-ballxdir;
            }
        }
        repaint();

    }

    @Override
    public void keyTyped(KeyEvent e) 
    {
        
    }

    @Override
    public void keyPressed(KeyEvent e) 
    {
        if (e.getKeyCode()==KeyEvent.VK_RIGHT)
       {
            if (playerx>=600)
            {
                playerx=600;
            }
            else{
                moveRight();
            }
       }
       if (e.getKeyCode()==KeyEvent.VK_LEFT)
       {
            if (playerx<10)
            {
                playerx=10;

            }else
            {
                moveLeft();
            }
       }
       if (e.getKeyCode()==KeyEvent.VK_ENTER)
       {
            if(!play)
            {
                play=true;
                ballposx=120;
                ballposy=350;
                ballxdir=-1;
                ballydir=-2;
                playerx=310;
                score=0;
                totalBrick=21;
                map=new Mapgen(3,7);
                repaint();
            }
       }
    }

    @Override
    public void keyReleased(KeyEvent e) {
       
    }
    private void moveRight()
    {
        play =true;
        playerx+=30;
    }
    private void moveLeft()
    {
        play =true;
        playerx-=30;
    }
    
}