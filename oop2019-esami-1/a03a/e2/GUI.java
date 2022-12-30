package a03a.e2;

import javax.swing.*;
import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    
    private List<JButton> list = new ArrayList<>();
    public GUI(int size) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(size*100, size*100);

        int cols = 3; // {1,2,3,4,5}

        Logic logic = new LogicImpl(cols);


        JPanel panel = new JPanel(new GridLayout(cols,cols));
        final JButton actionButton = new JButton(">");
        
        ActionListener al = (e)->{
            final JButton bt = (JButton)e.getSource();
            logic.markTheSpot(list.indexOf(bt));
            if(logic.isOver()) {
                System.exit(0);
            }
            bt.setText("0");
        };

        actionButton.addActionListener(action -> {
            list.get(logic.getLastXPos()).setText("");
            list.get(logic.moveTheX()).setText("X");
            if(logic.isOver()) {
                System.exit(0);
            }
        });


        for (int i=0;i<cols*cols;i++){
            final JButton jb = new JButton("");
            jb.addActionListener(al);
            list.add(jb);
            panel.add(jb);
        }

        list.get(0).setText("X");
        
        
        this.getContentPane().add(BorderLayout.CENTER,panel);
        this.getContentPane().add(BorderLayout.SOUTH, actionButton);
        this.setVisible(true);
    }
    
    
}
