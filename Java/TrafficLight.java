/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.applet.Applet;
import java.awt.*;
import java.awt.image.*;
import java.net.URL;
import java.io.*;
import javax.imageio.*;

import java.awt.event.*;

public class TrafficLight extends Applet {

    public void init() {
       
       setLayout(new BorderLayout());
       
       ButtonPanel = new Panel();
       ButtonPanel.setLayout(new GridLayout(5,1));
       
       CanvasPanel = new Panel();
       
       Button Normal = new Button("Normal");
       ButtonPanel.add(Normal);
       
       Button Stop = new Button("Stop");
       ButtonPanel.add(Stop);
       
       Button Caution = new Button("Caution");
       ButtonPanel.add(Caution);
       
       Button RightTurn = new Button("Right Turn");
       ButtonPanel.add(RightTurn);
       
       Button Disengage = new Button("Disengage");
       ButtonPanel.add(Disengage);
       
       //Creating Canvas
       Light light = new Light(Normal, Stop, Caution, 
                                RightTurn, Disengage);
      
       new LightThread(light).start();
       
       CanvasPanel.add(light);
       add(ButtonPanel, BorderLayout.WEST);
       add(CanvasPanel);
       
    }

    Panel ButtonPanel, CanvasPanel;
}


/*************
 * 
 * Canvas subclass
 */

class Light extends Canvas implements ActionListener{
    Light(Button Normal, Button Stop, Button Caution,
            Button RightTurn, Button Disengage){
        
        this.Normal = Normal;
        Normal.addActionListener(this);
        
        this.Stop = Stop;
        Stop.addActionListener(this);
        
        this.Caution = Caution;
        Caution.addActionListener(this);
        
        this.RightTurn = RightTurn;
        RightTurn.addActionListener(this);
        
        this.Disengage = Disengage;
        Disengage.addActionListener(this);
		
        /*
        try {
			URL url = new URL(getCodeBase(), "06-Animation/rightArrow.jpg");
			img = ImageIO.read(url);
		} catch (IOException e) {}
		*/
        
        setSize(WIDTH, HEIGHT);
        
    }
    
    public void paint(Graphics g){
        //-----Set a background color simply for testing purposes
        setBackground(Color.blue);
        g.setColor(Color.ORANGE);
        g.fillRect(80,40, 160, 330);
        g.setColor(RedLight);
        g.fillOval(X_VALUE, Y_VALUE, CIRCLE_DIAMETER, CIRCLE_DIAMETER);
        g.setColor(YellowLight);
        g.fillOval(X_VALUE, Y_VALUE + 107, CIRCLE_DIAMETER, CIRCLE_DIAMETER);
        g.setColor(GreenLight);
        g.fillOval(X_VALUE, Y_VALUE + (107*2), CIRCLE_DIAMETER, CIRCLE_DIAMETER);
        
		/*Doesnt Work
        if(isRightTurn){
            g.drawImage(img, X_VALUE + 20, Y_VALUE + (115 * 2), 45, 60, Normal);
        }
		*/
      
       
    }
    
    public void actionPerformed(ActionEvent e){
        Reset();
        if (e.getSource() == Normal)        isNormal = !isNormal;
        else if(e.getSource() == Caution)   isCaution = !isCaution;
        else if(e.getSource() == Stop)      isStop = !isStop;
        else if(e.getSource() == RightTurn) isRightTurn = !isRightTurn;
        else if(e.getSource() == Disengage) isDisengage = !isDisengage;
    }
    
    private void Reset()    {
        isNormal = false;
        isCaution = false;
        isStop = false;
        isRightTurn = false;
        isDisengage = false;
    }
    
    Button
            Normal, Stop, Caution,
             RightTurn, Disengage;
    
    final int
                HEIGHT = 400,
                WIDTH = 325,
                CIRCLE_DIAMETER = 90,
                X_VALUE = 115,
                Y_VALUE = 60;
                
    
    Color
            RedLight = Color.BLACK,
            YellowLight = Color.BLACK,
            GreenLight = Color.BLACK;
    
    boolean
            isNormal = false,
            isStop = false,
            isCaution = false,
            isRightTurn = false,
            isDisengage = false;
    
            
    
    BufferedImage img;
            
}

class LightThread extends Thread{
    LightThread(Light light){
        this.light = light;
    }
    
    public void run(){
        while(true){
            
            try{
                
            if(light.isNormal){
                while(light.isNormal){
                    Thread.sleep(500);
                    light.RedLight = Color.RED;
                        light.repaint();
                    Thread.sleep(500);
                    light.RedLight = Color.BLACK;
                        light.repaint();
                    Thread.sleep(500);
                    light.YellowLight = Color.YELLOW;
                        light.repaint();
                    Thread.sleep(500);
                    light.YellowLight = Color.BLACK;
                        light.repaint();
                    Thread.sleep(500);
                    light.GreenLight = Color.GREEN;
                        light.repaint();
                    Thread.sleep(500);
                    light.GreenLight = Color.BLACK; 
                        light.repaint();
                }
            }
            else if(light.isStop){  
               Thread.sleep(1000);
               light.RedLight = Color.RED;
               light.YellowLight = Color.BLACK;
               light.GreenLight = Color.BLACK;
                   light.repaint(); 
            }
            else if (light.isCaution){
                while(light.isCaution){
                    Thread.sleep(500);
                    light.YellowLight = Color.YELLOW;
                    light.RedLight = Color.BLACK;
                    light.GreenLight = Color.BLACK;
                        light.repaint();
                }
            }
            else if (light.isRightTurn){
                Thread.sleep(1000);
                light.RedLight = Color.BLACK;
                light.YellowLight = Color.BLACK;
                light.GreenLight = Color.GREEN;
                    light.repaint();
            }
            else if (light.isDisengage){
                Thread.sleep(1000);
                light.YellowLight = Color.BLACK;
                light.RedLight = Color.BLACK;
                light.GreenLight = Color.BLACK;
                    light.repaint();
            }
            }catch (InterruptedException e) {}
        }
    }
    Light light;
}

