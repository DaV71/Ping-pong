import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.Math.*;
import java.util.Random;
import java.awt.Font;
import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;



public class Pong implements ActionListener, KeyListener {
    public static Pong pong;
    public final int WIDTH = 600, HEIGHT = 400;
    public Renderrer renderrer;
    public Rectangle lPadle, rPadle, puck;
    public int  lMotion, rMotion;
    public double angle;
    public int ySpeed, xSpeed;
    public static Random random;
    public int rScore, lScore;
    public boolean game = false, pauseGame=false;
    public File muzyka;




    public Pong(){
        JFrame jFrame=new JFrame();
        this.renderrer = new Renderrer();
        Timer timer = new Timer(10, this);
        random = new Random();
        //muzyka = new File("pong.m4a");
        //PlaySound(muzyka);
        jFrame.setTitle("Pong");
        jFrame.setDefaultCloseOperation(jFrame.EXIT_ON_CLOSE);
        jFrame.setResizable(false);
        jFrame.add(this.renderrer);
        jFrame.setVisible(true);
        jFrame.setSize(WIDTH, HEIGHT);
        jFrame.addKeyListener(this);
        puck = new Rectangle(WIDTH/2,HEIGHT/2,15,15);
        lPadle = new Rectangle(15,HEIGHT/2-37,10,75);
        rPadle = new Rectangle(WIDTH-30,HEIGHT/2-37,10,75);

        reset();



    timer.start();
    }

 /*   public void PlaySound(File Sound){
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(Sound));
            clip.start();

            Thread.sleep(clip.getMicrosecondLength()/1000);
        }
        catch (Exception e)
        {

        }

    }*/

    public void repaint (Graphics g){
        g.setColor(Color.black);
        g.fillRect(0,0,WIDTH,HEIGHT);

    if (game) {
        g.setColor(Color.WHITE);
        g.fillOval(puck.x, puck.y, puck.width, puck.height);
        g.fillRect(lPadle.x, lPadle.y, lPadle.width, lPadle.height);
        g.fillRect(rPadle.x, rPadle.y, rPadle.width, rPadle.height);


        g.drawString(lScore + " : " + rScore, WIDTH / 2, 10);
    }

        if(!game){
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial",1,40));
            g.drawString("Press Space to start",WIDTH/2-200,HEIGHT/2);

        }

        if (pauseGame){
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial",1,40));
        g.drawString("Paused",WIDTH/2-50,HEIGHT/2-50);
        g.drawString("Press Space to resume", WIDTH/2-200, HEIGHT/2+20);
        }

    }

    public void reset(){

        puck.x=WIDTH/2;
        puck.y=HEIGHT/2-37;
        boolean b;
        double a;
        a=1+random.nextDouble()*8;
        b=(random.nextBoolean());



        if (b)
        {
            xSpeed=2;
        }
        else {
            xSpeed=-2;
        }

        if (a<=4)
        {
            ySpeed=-1*(int)a;
        } else
        {
            ySpeed=((int)a)/2;
        }

    }



    public static void main(String[] args) {
        pong = new Pong();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (!pauseGame){
        puck.x += xSpeed;
        puck.y += ySpeed;}

        if (puck.intersects(lPadle) || puck.intersects(rPadle)) {

            if (Math.abs(xSpeed) < 10) {
                xSpeed *= -1.5;
            } else {
                xSpeed *= -1;
            }
        }


        if (puck.y < 0 || puck.y > HEIGHT - 45) {
            ySpeed *= -1;
        }

        if (puck.x < 0) {
            reset();
            rScore++;
        } else if (puck.x > WIDTH - 20) {
            reset();
            lScore++;
        }

        if (lPadle.y < 0) {
            lPadle.y = 0;


        } else if (lPadle.y > HEIGHT - 110) {
            lPadle.y = HEIGHT - 110;
        }
        if (rPadle.y < 0) {
            rPadle.y = 0;


        } else if (rPadle.y > HEIGHT - 110) {
            rPadle.y = HEIGHT - 110;
        }
        lPadle.y += lMotion;
        rPadle.y += rMotion;


        renderrer.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_W){
            lMotion=0;
        }
        if (e.getKeyCode()==KeyEvent.VK_S){
            lMotion=0;
        }
        if (e.getKeyCode()==KeyEvent.VK_O){
            rMotion=0;
        }
        if (e.getKeyCode()==KeyEvent.VK_L){
            rMotion=0;
        }

        if (e.getKeyCode()==KeyEvent.VK_SPACE)
        {
            pauseGame=false;
            game=true;

        }

        if (e.getKeyCode()==KeyEvent.VK_ESCAPE){

            pauseGame=true;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {


    }

    @Override
    public void keyPressed(KeyEvent e) {

       if(!pauseGame){
        if(lPadle.y>=0&&rPadle.y<=HEIGHT-110){
        if (e.getKeyCode()==KeyEvent.VK_W){
            lMotion=-6;
        }
        if (e.getKeyCode()==KeyEvent.VK_S){
            lMotion=6;
        }}
        if(rPadle.y>=0&&rPadle.y<=HEIGHT-110){
        if (e.getKeyCode()==KeyEvent.VK_O){
            rMotion=-6;
        }
        if (e.getKeyCode()==KeyEvent.VK_L){
            rMotion=6;
        }}
    }}
}
