package a04.e2;

import javax.swing.*;

import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    
    private static final long serialVersionUID = -6218820567019985015L;
    private final List<JButton> cells = new ArrayList<>();
    private final JButton quit = new JButton("QUIT");
        
    public GUI(int size) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100*size, 100*size);
        Logic logic = new LogicImpl(size);

        
        JPanel grid = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(BorderLayout.CENTER,grid);
        this.getContentPane().add(BorderLayout.SOUTH,quit);
        
        quit.addActionListener(e -> {
            System.out.println(logic.calcResult());
        	System.exit(0);
       	});
        
        ActionListener al = e -> {
        	var jb = (JButton)e.getSource();
            if(logic.isPossible(cells.indexOf(jb), jb.getText())) {
                logic.addValue(jb.getText());
                jb.setEnabled(false);
            }	
        };
                
        for (int i=0; i<size*size; i++){
                List<String> valueList = logic.init();
                final JButton jb = new JButton(valueList.get(i));
                this.cells.add(jb);
                grid.add(jb);
                jb.addActionListener(al);
            
        }
        this.setVisible(true);
    }
}