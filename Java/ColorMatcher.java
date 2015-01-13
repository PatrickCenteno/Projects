/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.util.*;


public class ColorMatcher extends Applet{


    public void init() {
       
        setLayout(new BorderLayout());
        
        ScrollPanel = new Panel();
        ButtonPanel = new Panel();
        ColorPanel = new Panel();
        
        NewColor = new Button("New Color");
        SeeColor = new Button("See Color");
        
        RedPanel = new Panel();
        RedPanel.setLayout(new BorderLayout());
        RedXPanel = new Panel();
        RedXPanel.setLayout(new BorderLayout());
        GreenPanel = new Panel();
        GreenPanel.setLayout(new BorderLayout());
        GreenXPanel = new Panel();
        GreenXPanel.setLayout(new BorderLayout());
        BluePanel = new Panel();
        BluePanel.setLayout(new BorderLayout());
        BlueXPanel = new Panel();
        BlueXPanel.setLayout(new BorderLayout());
        
        Red = new Scrollbar(Scrollbar.VERTICAL, 0, 30, 0, 254);
        Redtext = new TextField();
        Redtext.setEditable(false);
        
        RedX = new Scrollbar(Scrollbar.VERTICAL, 0, 30, 0, 254);
        RedXtext = new TextField();
        RedXtext.setEditable(false);
        
        Green = new Scrollbar(Scrollbar.VERTICAL, 0, 30, 0, 254);
        Greentext = new TextField();
        Greentext.setEditable(false);
        
        GreenX = new Scrollbar(Scrollbar.VERTICAL, 0, 30, 0, 254);
        GreenXtext = new TextField();
        GreenXtext.setEditable(false);
        
        Blue = new Scrollbar(Scrollbar.VERTICAL, 0, 30, 0, 254);
        Bluetext = new TextField();
        Bluetext.setEditable(false);
        
        BlueX = new Scrollbar(Scrollbar.VERTICAL, 0, 30, 0, 254);
        BlueXtext = new TextField();
        BlueXtext.setEditable(false);
        
        ScrollPanel.setLayout(new GridLayout(1,6));
        
        colorCanvas = new ColorCanvas(NewColor, SeeColor, RedX, GreenX, BlueX,
                                        RedXtext, GreenXtext, BlueXtext);
        matcherCanvas = new MatcherCanvas(NewColor, Red, Green, Blue, Redtext, 
                                            Greentext, Bluetext);
        
        RedPanel.add(Redtext, BorderLayout.NORTH);
        RedPanel.add(Red);
        RedXPanel.add(RedXtext, BorderLayout.NORTH);
        RedXPanel.add(RedX);
        GreenPanel.add(Greentext, BorderLayout.NORTH);
        GreenPanel.add(Green);
        GreenXPanel.add(GreenXtext, BorderLayout.NORTH);
        GreenXPanel.add(GreenX);
        BluePanel.add(Bluetext, BorderLayout.NORTH);
        BluePanel.add(Blue);
        BlueXPanel.add(BlueXtext, BorderLayout.NORTH);
        BlueXPanel.add(BlueX);
        
        ScrollPanel.add(RedPanel);
        ScrollPanel.add(RedXPanel);
        ScrollPanel.add(GreenPanel);
        ScrollPanel.add(GreenXPanel);
        ScrollPanel.add(BluePanel);
        ScrollPanel.add(BlueXPanel);
        
        ColorPanel.setLayout(new GridLayout(1,2));
        ColorPanel.add(colorCanvas);
        ColorPanel.add(matcherCanvas);
       
        add(ColorPanel, BorderLayout.WEST);
        add(ScrollPanel);
        ButtonPanel.add(NewColor);
        ButtonPanel.add(SeeColor);
        add(ButtonPanel, BorderLayout.SOUTH);
    }
   
    Panel ScrollPanel, ButtonPanel, RedPanel,
            GreenPanel, BluePanel, RedXPanel,
            GreenXPanel, BlueXPanel, ColorPanel;
    
    Scrollbar Red, Green, Blue,
              RedX, GreenX, BlueX;
   
    TextField Redtext, Greentext, Bluetext,
                RedXtext, GreenXtext, BlueXtext;
    
    Button NewColor, SeeColor;
    
    ColorCanvas colorCanvas;
    MatcherCanvas matcherCanvas;
}

/************************************
 * 
 * Color Canvas Class
 * 
 */


class ColorCanvas extends Canvas implements ActionListener {
    
    public ColorCanvas(Button canvasNewColor, Button canvasSeeColor,
                        Scrollbar cRed, Scrollbar cGreen, Scrollbar cBlue,
                            TextField tRed, TextField tGreen, TextField tBlue){
        this.canvasNewColor = canvasNewColor;
        canvasNewColor.addActionListener(this);
        
        this.canvasSeeColor = canvasSeeColor;
        canvasSeeColor.addActionListener(this);
        
        this.cRed = cRed;
        this.cGreen = cGreen;
        this.cBlue = cBlue;
        
        this.tRed = tRed;
        this.tGreen = tGreen;
        this.tBlue = tBlue;
       
        setSize(WIDTH, HEIGHT);
        red = random.nextInt(254);
        green = random.nextInt(254);
        blue = random.nextInt(254);
        
    }
    
    public void paint (Graphics g){
        color = new Color(red, green, blue);
        setBackground(color);
    }
    
    public void actionPerformed (ActionEvent e){
        if(e.getSource() == canvasNewColor){
            cRed.setValue(0);
            cGreen.setValue(0);
            cBlue.setValue(0);
            
            tRed.setText("");
            tGreen.setText("");
            tBlue.setText("");
            
            red = random.nextInt(254);
            green = random.nextInt(254);
            blue = random.nextInt(254);
            repaint();
        }
        else{
            cRed.setValue(red);
            tRed.setText(java.lang.Integer.toString(red));
            cGreen.setValue(green);
            tGreen.setText(java.lang.Integer.toString(green));
            cBlue.setValue(blue);
            tBlue.setText(java.lang.Integer.toString(blue));            
        }
    }
    
    Button canvasNewColor, canvasSeeColor;
    Scrollbar
              cRed,
              cGreen,
              cBlue;
    TextField
              tRed,
              tGreen,
              tBlue;
    int
            red,
            blue,
            green;
    final int
                WIDTH = 100,
                HEIGHT = 300;
    Random random = new Random();
    Color color;
}

/******************************
 * 
 * MatcherCanvas Class
 * 
 */



class MatcherCanvas extends Canvas implements ActionListener, AdjustmentListener{
    
    public MatcherCanvas(Button mNewColor, Scrollbar mRed, Scrollbar mGreen, Scrollbar mBlue,
                            TextField tRed, TextField tGreen, TextField tBlue){
       
        this.mRed = mRed;
        mRed.addAdjustmentListener(this);
        
        this.mGreen = mGreen;
        mGreen.addAdjustmentListener(this);
        
        this.mBlue = mBlue;
        mBlue.addAdjustmentListener(this);
        
        this.mNewColor = mNewColor;
        mNewColor.addActionListener(this);
        
        this.tRed = tRed;
        this.tGreen = tGreen;
        this.tBlue = tBlue;
        
        setSize(WIDTH, HEIGHT);
    }
    
    public void paint(Graphics g){
        color = new Color(red, green, blue);
        setBackground(color);
    }
    
    public void actionPerformed (ActionEvent e){
        mRed.setValue(0);
        tRed.setText("0");
        
        mGreen.setValue(0);
        tGreen.setText("0");
        
        mBlue.setValue(0);
        tBlue.setText("0");
        repaint();
    }
    
    public void adjustmentValueChanged(AdjustmentEvent e){
        if (e.getSource() == mRed){
            tRed.setText(java.lang.Integer.toString(mRed.getValue()));
            red = mRed.getValue();
        }
        else if(e.getSource() == mGreen){
            tGreen.setText(java.lang.Integer.toString(mGreen.getValue()));
            green = mGreen.getValue();
        }
        else if(e.getSource() == mBlue){
            tBlue.setText(java.lang.Integer.toString(mBlue.getValue()));
            blue = mBlue.getValue();
        }
        repaint();
    }
    
    final int
                WIDTH = 100,
                HEIGHT = 300;
    int
            red,
            green,
            blue;
    Color color;
    
    Scrollbar 
            mRed,
            mGreen,
            mBlue;
    TextField
            tRed,
            tGreen,
            tBlue;
    Button mNewColor;
}