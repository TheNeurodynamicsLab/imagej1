/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins;

import ij.ImagePlus;
import ij.plugin.PlugIn;
import ij.process.FloatProcessor;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/**
 *
 * @author Balaji
 */
public class Image_to_XYI extends javax.swing.JFrame implements PlugIn{

    /**
     * Creates new form Image_to_XYI
     */
    public Image_to_XYI() {
         ij.WindowManager.addWindow(this);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Start Convert");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(jButton1)
                .addContainerGap(221, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(jButton1)
                .addContainerGap(195, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
       JFileChooser Fc = new JFileChooser();
       Fc.showOpenDialog(this);
      
       JFileChooser Fo = new JFileChooser();
       Fo.showSaveDialog(this);
       
       File Output;
       FileWriter stringWriter = null;
       
       if(Fo.getSelectedFile() == null)
           return;
       else{
           Output = Fo.getSelectedFile();
           try{
               stringWriter = new FileWriter(Output);
           }catch (java.io.IOException e){
               
           }
           
       }
       
       if(Fc.getSelectedFile()!= null){
           ImagePlus imp = new ImagePlus(Fc.getSelectedFile().getAbsolutePath());
          /* if(imp == null) 
               return;*/
           ij.process.FloatProcessor ip;
           int imageType = imp.getBitDepth();
           
           if(imageType != ImagePlus.GRAY32)
              ip =  imp.getProcessor().convertToFloatProcessor();
           else
              ip = (FloatProcessor) imp.getProcessor();
           
           int nRows = ip.getHeight();
           int nCols = ip.getWidth();
           
                     
           //float [] pixelValue = new float[nRows*nCols];
           //float [] valueout = new float [nRows*nCols];
           
           //pixelValue = (float [])ip.getPixels();
           String stringToWrite = "";
           
           
           for(int cRow = 0 ; cRow < nRows ; cRow++)
               for(int cCol = 0 ; cCol < nCols ; cCol++){
                   
                   stringToWrite = "\t" + cRow + "\t" + cCol + "\t" + ip.getPixelValue(cRow, cCol) + "\n";
                   try{
                       stringWriter.append(stringToWrite);
                   }catch(IOException e){
                       Logger.getLogger(Image_to_XYI.class.getName()).log(Level.SEVERE, null, e);
                   }
                   
               }
           try {
               stringWriter.close();
           } catch (IOException ex) {
               Logger.getLogger(Image_to_XYI.class.getName()).log(Level.SEVERE, null, ex);
           }
           
           
           
       }
    }//GEN-LAST:event_jButton1ActionPerformed

    @Override
    public void run(String arg) {
        this.setVisible(true);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @param args the command line arguments
     */
  /*  public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        /*try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Image_to_XYI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Image_to_XYI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Image_to_XYI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Image_to_XYI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
       /* java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Image_to_XYI().setVisible(true);
            }
        });
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    // End of variables declaration//GEN-END:variables
}
