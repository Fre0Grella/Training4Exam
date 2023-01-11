package a02c.e2;

import javax.swing.*;
import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    
    private static final long serialVersionUID = -6218820567019985015L;
    private final List<JButton> cells = new ArrayList<>();
    
    public GUI(int size) {
        Logic logic = new LogicImpl(size);
        logic.init();
        List<String> map = logic.returnMap();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(50*size, 50*size);
        
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(panel);
        
        ActionListener al = e -> {
            if(logic.isOver()) {
                System.exit(0);
            }
            cells.get(logic.getCurrPos()).setText(" ");
            cells.get(logic.drop()).setText("*");
        };

                
        for (int i=0; i<size*size; i++){
                final JButton jb = new JButton(map.get(i));
                this.cells.add(jb);
                jb.addActionListener(al);
                panel.add(jb);
        }
        this.setVisible(true);
    }
    
}
