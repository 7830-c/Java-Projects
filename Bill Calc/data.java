import java.awt.Color;
import java.awt.Font;

import javax.swing.*;

public class data {

    JPanel mainPanel = new JPanel();
    JPanel prevPanel = new JPanel();
    JPanel presentpanel = new JPanel();

    JLabel nameLabel =new JLabel();
    String name;
    JLabel prevL = new JLabel("Enter previous month units : ");
    JTextField prev = new JTextField(7);
    JLabel presentL = new JLabel("Enter present month units : ");
    JTextField present = new JTextField(7);
    int prev_units;
    int present_units;
    int units;
    float amount;


    data(String name,int x,int y,int width,int height){
        this.name=name;
        nameLabel.setText(name);
        nameLabel.setForeground(Color.red);
        nameLabel.setFont(new Font("Times new Roman",Font.ITALIC,20));
        nameLabel.setAlignmentX(12);


        mainPanel.setLayout(new BoxLayout(mainPanel,1));

        prevPanel.add(prevL);
        prevPanel.add(prev);

        presentpanel.add(presentL);
        presentpanel.add(present);

        mainPanel.add(nameLabel);
        mainPanel.add(prevPanel);
        mainPanel.add(presentpanel);

        mainPanel.setBounds(x,y,width,height);

        mainPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE,4));

    }
}
