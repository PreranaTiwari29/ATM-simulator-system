
package atm.simulator.system;
import java.awt.*;
import java.sql.ResultSet;
import javax .swing.*;
import java.awt.event.*;


public class BalanceEnquiry extends JFrame implements ActionListener {
        String pin;
        int balance = 0;
    
    BalanceEnquiry(String pin){
        this.pin = pin;
        
        
       ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("atm/simulator/system/icons/atm.jpg"));
       Image i2 = i1.getImage().getScaledInstance(1000, 1180, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel ll6= new JLabel(i3);
        ll6.setBounds(0, 0, 960, 1080);
        add(ll6);
        
        JLabel l1 = new JLabel();
        l1.setForeground(Color.WHITE);
        l1.setFont(new Font("System",Font.BOLD,16));
        
           try{
               Conn c1 = new Conn();
                           
             ResultSet rs = c1.s.executeQuery("select * from bank where pin ='"+pin+"' ");
             while(rs.next()){
              if (rs.getString("mode").equals("Deposit")){
                  balance += Integer.parseInt(rs.getString("amount"));
              }else{
                   balance -= Integer.parseInt(rs.getString("amount"));
                  
              }
              }
           }catch(Exception e){
               l1.setText("Your current account balance is Rs  " + balance);
           } 
        
        JButton b1 = new JButton("Back");
        b1.addActionListener(this);
        
        setLayout(null);
        
        l1.setBounds(190, 350, 400, 35);
        ll6.add(l1);
        
        b1.setBounds(390, 633, 150, 35);
        ll6.add(b1);
        
        setSize(960,1080);
        setUndecorated(true);
        setLocation(500,0);
        
    }
    
    public void actionPerformed(ActionEvent ae){
        setVisible(false);
        new Transactions(pin).setVisible(true);
    }
    
    public static void main(String[]args){
        new BalanceEnquiry("").setVisible(true);
    }
}
