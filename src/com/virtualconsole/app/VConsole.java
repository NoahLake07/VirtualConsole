package com.virtualconsole.app;

import com.virtualconsole.obj.InputBar;
import freshui.gui.FTextArea;
import freshui.gui.Header;
import freshui.program.FreshProgram;

import java.awt.*;

public class VConsole extends FreshProgram {

    private final static String VERSION = "v.1.0.1";
    public final static int DARK = 10;
    public final static int LIGHT = -10;

    private final static int VCONSOLE_HEIGHT = 400;
    private final static int VCONSOLE_WIDTH = 775;
    public final static int Y_PADDING = VCONSOLE_HEIGHT/10;
    public final static int X_PADDING = VCONSOLE_WIDTH/25;

    public final static Color LIGHT_MODE_BACKGROUND = new Color(184, 197, 206);
    public final static Color DARK_MODE_BACKGROUND = new Color(114, 129, 134);

    private Header header;
    private FTextArea textArea;
    private InputBar inputBar;

    public VConsole(){
        this.start();
        this.setSize(VCONSOLE_WIDTH,VCONSOLE_HEIGHT);
        this.setTitle("VConsole Window");

        header = new Header(this.getWidth(),"VirtualConsole " + VERSION,CENTER,this);
        this.add(header,0,0);
        header.setColor(new Color(20, 154, 222));

        // scrollable non-editable text area (where the console is)
        textArea = new FTextArea(this.getWidth()-(X_PADDING*2),this.getHeight()/2);
        add(textArea,X_PADDING,header.getHeight()+Y_PADDING);
        textArea.setEditable(false);
        textArea.append("VIRTUAL CONSOLE " + getVersion().toUpperCase() + " IS RUNNING...",new Color(137, 0, 169));

        inputBar = new InputBar(this);
        inputBar.addAt(X_PADDING,this.getHeight()-Y_PADDING*2);
       // inputBar.addAt(0,(int) 0);

        setColorMode(LIGHT);
    }

    public void setColorMode(int mode){
        if(mode == DARK){
            textArea.setBackground(new Color(45, 45, 45));
            textArea.setForeground(new Color(234, 234, 234));
            textArea.setCaretColor(new Color(190, 190, 190));
            inputBar.setBackground(new Color(45, 45, 45));
            this.setBackground(DARK_MODE_BACKGROUND);
        } else {
            textArea.setBackground(new Color(234, 234, 234));
            textArea.setForeground(new Color(0, 0, 0));
            textArea.setCaretColor(new Color(37, 37, 37));
            inputBar.setBackground(new Color(234, 234, 234));
            inputBar.setForeground(textArea.getForeground());
            this.setBackground(LIGHT_MODE_BACKGROUND);
        }
    }

    public InputBar getInputBar(){
        return this.inputBar;
    }

    public void setInputBar(InputBar newInputBar){
        this.inputBar = newInputBar;
    }

    public void resize(){
        textArea.setSize(this.getWidth()-(X_PADDING*2),this.getHeight()/2);
        header.setWidth(this.getWidth());
        inputBar.setLocation(X_PADDING,this.getHeight()-Y_PADDING*2);
    }

    public String getInputFromConsole(){
        return inputBar.getInput();
    }

    public String getInputFromConsole(String prefill){
        textArea.append(inputBar.getInput());
        return inputBar.getInput(prefill);
    }

    public void print(String s){
        textArea.append(s);
    }

    public void println(String s){
        textArea.append("\n"+s);
    }

    public void println(String s, Color c, Font f){
        textArea.append("\n"+s,c,f);
        textArea.scrollToBottom();
    }

    public void print(String s, Color c, Font f){
        textArea.append(s,c,f);
    }

    public void print(String s, Color c){
        textArea.append(s,c);
    }

    public void println(String s, Color c){
        textArea.append("\n"+s,c);
        textArea.scrollToBottom();
    }

    public void print(){
        this.print("");
    }

    public void println(){
        this.println("");
    }

    public String getVersion(){
        return VERSION;
    }

    public void scrollToBottom(){
        textArea.scrollToBottom();
    }

}
