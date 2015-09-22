/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho01;

import java.awt.Color;
import java.awt.image.BufferedImage;
import static java.awt.image.ImageObserver.ALLBITS;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.Random;

/**
 *
 * @author lennon
 */
public class ImagemPBM extends javax.swing.JFrame {

    /**
     * Creates new form ImagemPBM
     */
    public ImagemPBM() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnBinaria = new javax.swing.JButton();
        lblDescricao = new javax.swing.JLabel();
        painelImagem = new javax.swing.JPanel();
        lblImagem = new javax.swing.JLabel();
        btnExportar = new javax.swing.JButton();
        btnComum = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Processamento de Imagens | Trabalho 01");
        setMinimumSize(new java.awt.Dimension(800, 550));
        setName("framePrincipal"); // NOI18N
        getContentPane().setLayout(null);

        btnBinaria.setFont(new java.awt.Font("Canaro Light DEMO", 0, 18)); // NOI18N
        btnBinaria.setText("ABRIR IMAGEM (BINARIA)");
        btnBinaria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBinariaActionPerformed(evt);
            }
        });
        getContentPane().add(btnBinaria);
        btnBinaria.setBounds(10, 10, 370, 40);

        lblDescricao.setFont(new java.awt.Font("Canaro Light DEMO", 0, 14)); // NOI18N
        lblDescricao.setText("Dados da imagem:");
        getContentPane().add(lblDescricao);
        lblDescricao.setBounds(10, 470, 480, 30);

        painelImagem.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        painelImagem.add(lblImagem);

        getContentPane().add(painelImagem);
        painelImagem.setBounds(10, 60, 760, 400);

        btnExportar.setFont(new java.awt.Font("Canaro Light DEMO", 0, 18)); // NOI18N
        btnExportar.setText("EXPORTAR IMAGEM");
        btnExportar.setEnabled(false);
        btnExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarActionPerformed(evt);
            }
        });
        getContentPane().add(btnExportar);
        btnExportar.setBounds(500, 470, 270, 30);

        btnComum.setFont(new java.awt.Font("Canaro Light DEMO", 0, 18)); // NOI18N
        btnComum.setText("ABRIR IMAGEM (JPEG / PNG)");
        btnComum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComumActionPerformed(evt);
            }
        });
        getContentPane().add(btnComum);
        btnComum.setBounds(400, 10, 370, 40);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /* variaveis globais */
    BufferedImage imagemOriginal, imagemAuxiliar;
    private static final Random rand = new Random();
    /* vetor de letras */
    private static final char[] letras = "abcdefghijlmnopqrstuvxz".toCharArray();
    
    private void btnBinariaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBinariaActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PBM", "pbm");
        chooser.setFileFilter(filter);
        chooser.setDialogTitle("Abrir Imagem Binária");
        int op = chooser.showOpenDialog(this);
        if(op == JFileChooser.APPROVE_OPTION) {  
            File arq = chooser.getSelectedFile();  
            String path = arq.toString();
            try {
                Scanner scan = new Scanner(new File(path)).useDelimiter("\\n");
                String autenticacao = null, descricao = null, dimensao = null, linha = null;
                int linhas = 0, colunas = 0, j = 0;
                
                if (scan.hasNext()) {
                    autenticacao = scan.next();
                    //System.out.println(aut);
                    if (autenticacao.trim().equals("P1")) {
                        if (scan.hasNext()) {
                            descricao = scan.next().substring(2);
                            //System.out.println(desc);
                            JOptionPane.showMessageDialog(null, "Descrição da imagem: " + descricao);
                        }
                        if (scan.hasNext()) {
                            dimensao = scan.next();
                            //System.out.println(dimensao);
                            String[] corte = dimensao.split(" ");
                            colunas = Integer.parseInt(corte[0].trim());
                            linhas = Integer.parseInt(corte[1].trim());
                        }
                        
                        BufferedImage buffer = new BufferedImage(colunas, linhas, BufferedImage.TYPE_INT_RGB);
                        
                        while (scan.hasNext()) {
                            linha = scan.next();
                            //System.out.println(linha);
                            String[] corte = linha.split(" ");
                            Color preto = new Color(0, 0, 0);
                            Color branco = new Color(255, 255, 255);
                            for (int i = 0; i < colunas; i++) {
                                if (Integer.parseInt(corte[i].trim()) == 1) {
                                    buffer.setRGB(i, j, preto.getRGB());
                                }
                                else {
                                    buffer.setRGB(i, j, branco.getRGB());
                                }
                            }
                            j++;
                        }
                        imagemAuxiliar = buffer;
                        ImageIcon icon = new ImageIcon(imagemAuxiliar.getScaledInstance(painelImagem.getWidth(), 
                                painelImagem.getHeight(), java.awt.Image.SCALE_SMOOTH));
                        lblImagem.setIcon(icon);
                        
                        lblDescricao.setText("Dados da imagem:      Altura: " + colunas + 
                                " pixels      Largura: " + linhas + " pixels.");
                    } else { 
                        JOptionPane.showMessageDialog(null, "Formato de imagem inválido.");
                        lblImagem.setIcon(null);
                        lblDescricao.setText("Dados da imagem:");
                    }
                }
            }
	    catch(IOException e) {
		System.out.println("Erro IO Exception! Verifique se o arquivo especificado existe e tente novamente.");
	    }             
        }
        
        btnExportar.setEnabled(false);
    }//GEN-LAST:event_btnBinariaActionPerformed

    private void btnExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarActionPerformed
        // TODO add your handling code here:
        if (imagemOriginal != null) {
            /* passo 01 :: transformar em imagem branco e preto */            
            imagemAuxiliar = imagemOriginal;

            int width = imagemAuxiliar.getWidth();
            int height = imagemAuxiliar.getHeight();
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) { 				
                    int rgb = imagemAuxiliar.getRGB(i, j);				
                    int r = (int) ((rgb&0x00FF0000)>>>16);
                    int g = (int) ((rgb&0x0000FF00)>>>8);
                    int b = (int) (rgb&0x000000FF);
                    int y = (int) ((0.299 * r) + (0.587 * g) + (0.114 * b));
                    y = (y / 128) * 128;
                    if (y <= 127) y = 0; /* preto 100% */
                    if (y >= 128) y = 255; /* branco 100% */
                    Color color = new Color(y, y, y);
                    imagemAuxiliar.setRGB(i, j, color.getRGB());
                }
            }
            
            this.imageUpdate(imagemOriginal, ALLBITS, 0, 0, width, height);
            
            /* gerar nome aleatorio */
            StringBuilder nome = new StringBuilder();
            for (int i = 0; i < 6; i++) {
                int ch = rand.nextInt (letras.length);
                nome.append (letras [ch]);
            }
            
            /* passo 02 :: salvar arquivo pbm */
            File file = new File("C:/Users/lennon/Documents/NetBeansProjects/ProcessamentoDeImagens/images/" + 
                    nome.toString() + ".pbm");
            try {
                FileWriter fw = new FileWriter(file, false);
                /* grava nova linha */
                try (PrintWriter pw = new PrintWriter(fw)) {
                    /* grava nova linha */
                    pw.println("P1");
                    pw.println("# Trabalho 01 :: Processamento de Imagens :: Lennon - Weverton - Daniel");
                    pw.println(width + " " + height);
                    for (int i = 0; i < width; i++) {
                        for (int j = 0; j < height; j ++) {
                            //System.out.println(imagemAuxiliar.getRGB(i, j));
                            if (imagemAuxiliar.getRGB(j, i) == -1) pw.print("0 ");
                            else pw.print("1 ");
                        }
                        pw.println();
                    }

                    /* limpa escrita */
                    pw.flush();
                    /* fecha escrita */
                }
                
                JOptionPane.showMessageDialog(null, "Arquivo salvo com sucesso!");
                btnExportar.setEnabled(false);
            } catch (IOException ex) {
                Logger.getLogger(ImagemPBM.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnExportarActionPerformed

    private void btnComumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComumActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG ou PNG", "jpg", "png");
        chooser.setFileFilter(filter);
        chooser.setDialogTitle("Abrir Imagem");
        int op = chooser.showOpenDialog(this);
        if(op == JFileChooser.APPROVE_OPTION){  
            File arq = chooser.getSelectedFile();  
            String path = arq.toString();  
            try {
                imagemOriginal = ImageIO.read(new File(path));
                System.out.println("Arquivo aberto com sucesso.");
                ImageIcon icon = new ImageIcon(imagemOriginal.getScaledInstance(painelImagem.getWidth(), painelImagem.getHeight(), 
                        java.awt.Image.SCALE_SMOOTH));
                lblImagem.setIcon(icon);
                
                lblDescricao.setText("Dados da imagem:      Altura: " + imagemOriginal.getHeight() + 
                        " pixels      Largura: " + imagemOriginal.getWidth() + " pixels.");
                
                btnExportar.setEnabled(true);
	    }
	    catch(IOException e){
		System.out.println("Erro IO Exception! Verifique se o arquivo especificado existe e tente novamente.");
	    }
	    catch(Exception e){
		System.out.println("Erro Exception! " + e.getMessage());
	    }                   
        }
    }//GEN-LAST:event_btnComumActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ImagemPBM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(ImagemPBM.class.getName()).log(Level.SEVERE, null, ex);
                }
                new ImagemPBM().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBinaria;
    private javax.swing.JButton btnComum;
    private javax.swing.JButton btnExportar;
    private javax.swing.JLabel lblDescricao;
    private javax.swing.JLabel lblImagem;
    private javax.swing.JPanel painelImagem;
    // End of variables declaration//GEN-END:variables
}
