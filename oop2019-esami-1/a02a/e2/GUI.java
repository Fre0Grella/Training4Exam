package a02a.e2;

import javax.swing.*;
import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    
    private static final long serialVersionUID = -6218820567019985015L;
    
    public GUI(int size) {
        Logic logic = new LogicImpl(size);
        List<JButton> buttonList = new ArrayList<>(size*size);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(size*100, size*100);
        JPanel panel = new JPanel(new GridLayout(size,size));
        JButton next = new JButton(">");
        
        for (int i=0;i<size*size;i++){
            final JButton jb = new JButton("");
            panel.add(jb);
            buttonList.add(jb);
        }

        ActionListener al = (e)->{
            System.exit(1);
        };

        next.addActionListener(e -> {
            for (JButton jb : buttonList) {
                jb.setText("");
                jb.removeActionListener(al);
            }
            for (int index : logic.setXPosition()) {
                buttonList.get(index).setText("X");
                buttonList.get(index).addActionListener(al);
                
            }
            
        });

        for (int index : logic.setXPosition()) {
            buttonList.get(index).setText("X");
            buttonList.get(index).addActionListener(al);
            
        }

        this.getContentPane().add(BorderLayout.CENTER,panel);
        this.getContentPane().add(BorderLayout.SOUTH,next);
        this.setVisible(true);
    }
    
    
}
