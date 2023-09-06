import javax.swing.*;

import java.awt.*;
import java.io.BufferedWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Calculator {
    JFrame frame = new JFrame("Bill Calculator");
    data[] people = new data[3];
    JLabel total_billL = new JLabel("Total bill amount : ");
    JTextField total_billF = new JTextField(7);
    JLabel total_UnitsL = new JLabel("Total Units");
    JTextField total_UnitsF = new JTextField(7);
    JButton calc;

    float PUC;
    int errorUnits;
    float errorCharge = 0f;
    float total_amount;
    int total_units;
    int TPU;
    int i = 0;
    String extra_money;

    public static void main(String[] args) throws Exception {
        new Calculator().go();
    }

    void go() {
        frame.setLayout(null);
        frame.setIconImage(new ImageIcon("icon.png").getImage());
            
        
        JLabel title = new JLabel("Electricity Bill Calculator");
        title.setFont(new Font("Times new Roman", Font.ITALIC, 50));
        title.setForeground(Color.ORANGE);
        title.setBounds(440, 30, 550, 50);
        frame.add(title);

        JPanel main_panel_total = new JPanel();
        main_panel_total.setLayout(new BoxLayout(main_panel_total, 1));
        main_panel_total.setBorder(BorderFactory.createLineBorder(Color.green, 5));

        JPanel total_billP = new JPanel();
        total_billP.add(total_billL);
        total_billP.add(total_billF);

        JPanel total_UnitP = new JPanel();
        total_UnitP.add(total_UnitsL);
        total_UnitP.add(total_UnitsF);

        main_panel_total.add(total_billP);
        main_panel_total.add(total_UnitP);

        main_panel_total.setBounds(550, 170, 250, 150);
        frame.add(main_panel_total);

        people[0] = new data("Kalicharan", 20, 370, 300, 200);
        people[1] = new data("Devendra", 500, 370, 300, 200);
        people[2] = new data("Jitendra", 950, 370, 300, 200);

        frame.add(people[0].mainPanel);
        frame.add(people[1].mainPanel);
        frame.add(people[2].mainPanel);

         calc = new JButton("Calculate");
        calc.addActionListener(e -> calculate());

        calc.setBounds(600, 590, 200, 50);
        calc.setBackground(Color.CYAN);
        calc.setFont(new Font("Times New Roman", Font.ITALIC, 25));
        frame.add(calc);

        frame.setVisible(true);
        frame.setSize(1200, 1200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    void calculate() {

        total_amount = Float.parseFloat(total_billF.getText());
        total_units = Integer.parseInt(total_UnitsF.getText());
        PUC = total_amount/total_units;
        TPU = 0;

        for (i = 0; i < 3; i++) {
            people[i].prev_units = Integer.parseInt(people[i].prev.getText());
            people[i].present_units = Integer.parseInt(people[i].present.getText());
            people[i].units = people[i].present_units - people[i].prev_units;
            people[i].amount = people[i].units * PUC;
            
            TPU += people[i].units;

            
        }

       
       

        

        save();

    }

    void save() {
        errorUnits = Math.abs(TPU - total_units);
        errorCharge = (errorUnits * PUC) / 3;

        Date today = new Date();
        String t = String.format("%td %<tB %<tY", today);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("Recipts.txt",true));

            writer.append("__________________________________________________________________________________________\n\n");
            for (int i = 0; i < 3; i++) {
                writer.write("\n\n                         Bill Recipt[Billing Date : " + t + "] "                      + "                   \n");
                writer.write("\n                          "+people[i].name + " "  + "Uncle\n");
                writer.write("Meter Units => " + people[i].present_units + "\n");
                writer.write("Units = " + people[i].present_units + " - " + people[i].prev_units + " = "
                        + people[i].units + "\n");
                writer.write("Per Unit Charge => " + PUC);
                writer.write("\nBill => " + people[i].units*PUC);


                if(TPU!=total_units){
                    if(TPU>total_units){
                        writer.write( String.format("\nAmount should be deducted => %f  [%d units less in total bill ]\nTotal Bill => %f - %f = %f\n",errorCharge,errorUnits,people[i].amount,errorCharge,people[i].amount-errorCharge));
                         people[i].amount=people[i].amount-errorCharge;
                    }else{
                        writer.write( String.format("\nExtra Charge => %f  [%d units extra in total bill ]\nTotal Bill => %f + %f = %f\n",errorCharge,errorUnits,people[i].amount,errorCharge,people[i].amount+errorCharge));
                        people[i].amount=people[i].amount+errorCharge;
                    }
                }

               

 


            }

            writer.append("\n\n____________________________________________________________________________________________\n\n");
            writer.append("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");


            writer.close();
            calc.setText("Success");
            calc.setBackground(Color.green);

        } catch (IOException e) {
            calc.setText("Error");
            calc.setBackground(Color.red);
        }

    }

}
