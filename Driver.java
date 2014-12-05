import javax.swing.*;

public class Driver
{
   public static void main(String [] args)
   {
      JFrame frame = new Crazy8sGUI();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
      frame.pack();
      
   }
}