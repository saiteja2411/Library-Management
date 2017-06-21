
package lib;
public class LibraryManagement extends database {
 public static void main(String[] args) {
        new lib.database();
         java.awt.EventQueue.invokeLater(new Runnable() {
           public void run() {
               new library().setVisible(true);
            }
      });    
    }
}
