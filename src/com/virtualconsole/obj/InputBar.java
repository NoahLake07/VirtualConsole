package com.virtualconsole.obj;

import com.virtualconsole.app.VConsole;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.security.Key;

public class InputBar implements KeyListener {

    private JTextField entry;
    private JButton push;

    private boolean waiting = false;
    private VConsole parent;

    public InputBar(VConsole parent){
        this.parent = parent;
        entry = new JTextField(40);
        push = new JButton("Push");

        entry.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_ENTER){
                    tryEntryPush();
                }
            }
        });

        push.addActionListener(e -> tryEntryPush());
    }

    public void addAt(int x, int y){
        parent.add(entry,x,y);
        parent.add(push,x+entry.getWidth()+(parent.X_PADDING/2),y);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            tryEntryPush();
        }
    }

    public void keyPressed(KeyEvent e){
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            tryEntryPush();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    private void waitForFinish(){
        waiting = true;
        while(waiting) System.out.print("");
    }

    private void tryEntryPush(){
        if(waiting){
            waiting = false;
        }
    }

    public String getInput(){
        return this.getInput("");
    }

    public String getInput(String prefill){
        entry.setText(prefill);
        waitForFinish();
        return entry.getText();
    }

    public void setButtonText(String s){
        push.setText(s);
    }

    public String getButtonText(){
        return push.getText();
    }

    public void setButtonForeground(Color c){
        push.setForeground(c);
    }

    public Color getButtonForeground(){
        return push.getForeground();
    }

    public void setButtonBackground(Color bg){
        push.setBackground(bg);
    }

    public Color getButtonBackground(){
        return push.getBackground();
    }

    public void setBackground(Color bg){
        entry.setBackground(bg);
    }

    public Color getBackground(){
        return entry.getBackground();
    }

    public void setOpaque(boolean b){
        entry.setOpaque(b);
    }

    public boolean isOpaque(){
        return entry.isOpaque();
    }

    public void setForeground(Color fg){
        entry.setForeground(fg);
    }

    public Color getForeground(){
        return entry.getForeground();
    }

    public void setLocation(int x, int y){
        entry.setLocation(x,y);
        push.setLocation(x+entry.getWidth()+(parent.X_PADDING/2),y);
    }

}
