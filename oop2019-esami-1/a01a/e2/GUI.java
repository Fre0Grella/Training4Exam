package a01a.e2;

import javax.swing.*;
import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {

    List<JButton> buttonList = new ArrayList<>();
    
    
    public GUI(int size, int boat) {
        Logic logic = new LogicImpl(size);        
    	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(size*100, size*100);
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(BorderLayout.CENTER,panel);
        logic.placeBoat(boat);

        ActionListener al = (e)->{
            final JButton bt = (JButton)e.getSource();
             if(logic.isHit(buttonList.indexOf(bt))) {
                bt.setText("X");
             } else {
                bt.setText("O");
             }

             if(logic.isOver()) {
                System.exit(1);        
            }
            
        };


        for (int i=0;i<size*size;i++){
            final JButton jb = new JButton("");
            jb.addActionListener(al);
            panel.add(jb);
            buttonList.add(jb);
        } 
        this.setVisible(true);
    }
    
}
