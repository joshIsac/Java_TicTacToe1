/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Java_TicTacToe1;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.awt.Color;
import java.sql.CallableStatement;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Joshwin Isac
 */
public class Java_TicTacToe1 extends javax.swing.JFrame {

    /**
     * Creates new form Java_TicTacToe1
     */
    private String startGame="X";
    private String playerX="player X";
    private String playerO="player O";
    private int xCount=0;
    private int oCount=0;
    boolean checker;
    private Java_TicTacToe1 tictactoe;
   private DefaultTableModel tableModel;
    public Java_TicTacToe1() {
        initComponents();
        connect();
        getPlayerNames();
       }
    
     Connection con;
    PreparedStatement pat;
    DefaultTableModel model;
  
    //Setting up connection to database
      public void connect() {
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/tictactoe","root","");
            System.out.println("Connected to the database!");
        } catch (SQLException ex) {
             ex.printStackTrace();
            Logger.getLogger(Java_TicTacToe1.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error connecting to the database. Check your connection settings.");
        }
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Java_TicTacToe1.class.getName()).log(Level.SEVERE, null, ex);
    }
 }
      //inserting players into database
       private void insertPlayerNames(String playerX, String playerO) {
        connect(); // Ensure you are connected to the database
        String query = "INSERT INTO playernames (player_name, player_type) VALUES (?, ?)";
        try {
            String fetch = "SELECT * FROM playernames where player_name like ?";
            PreparedStatement fet = con.prepareStatement(fetch);
            fet.setString(1, playerX);
            ResultSet rs = fet.executeQuery();
            if(!rs.next()){
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setString(1, playerX);
                pst.setString(2, "X");
                pst.executeUpdate();
            }
            catch(Exception e){}}
            
            String fetch2 = "SELECT * FROM playernames where player_name like ?";
            PreparedStatement fet2 = con.prepareStatement(fetch2);
            fet2.setString(1, playerO);
            ResultSet rs2 = fet2.executeQuery();
            if(!rs2.next()){
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setString(1, playerO);
                pst.setString(2, "O");
                pst.executeUpdate();
            } catch(Exception e){}}

            System.out.println("Player names inserted into the database!");
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(Java_TicTacToe1.class.getName()).log(Level.SEVERE, null, ex);
        }
       }
     private void getPlayerNames()
     {
        playerX=JOptionPane.showInputDialog(this,"Enter player X name :","Player Name",JOptionPane.INFORMATION_MESSAGE);
        playerO=JOptionPane.showInputDialog(this,"Enter player O name :","Player Name",JOptionPane.INFORMATION_MESSAGE);
        
        if(playerX.equals("")){
            playerO="Player X";
        }
        if(playerX.equals("")){
           playerO="Player O";
        }
          insertPlayerNames(playerX, playerO);
     }
       
    private void gameScore()
    {
        jlblPlayerX.setText(String.valueOf(xCount));
        jlblPlayerO.setText(String.valueOf(oCount));
    }
     
    
     private void choosePlayer()
    {
        if(startGame.equalsIgnoreCase("X"))
        {
            startGame="O";
              
        }
        else 
        {
            startGame="X";
            
            
        }
    }
   private void winGame()
   {
       String b1=jBtnTic1.getText();
       String b2=jBtnTic2.getText();
       String b3=jBtnTic3.getText();
       String b4=jBtnTic4.getText();
       String b5=jBtnTic5.getText();
       String b6=jBtnTic6.getText();
       String b7=jBtnTic7.getText();
       String b8=jBtnTic8.getText();
       String b9=jBtnTic9.getText();
       
       if(b1==("X")&& b2==("X")&& b3==("X"))
       {
         JOptionPane.showMessageDialog(this,"Player X wins","Tic Tac Toe",
         JOptionPane.INFORMATION_MESSAGE);
        jBtnTic1.setBackground(Color.lightGray);
          jBtnTic2.setBackground(Color.lightGray);
           jBtnTic3.setBackground(Color.lightGray);
           xCount++;
           gameScore();
        }
       
         
       if(b4==("X")&& b5==("X")&& b6==("X"))
       {
         JOptionPane.showMessageDialog(this,"Player X wins","Tic Tac Toe",JOptionPane.INFORMATION_MESSAGE);
        jBtnTic4.setBackground(Color.orange);
        jBtnTic5.setBackground(Color.orange);
        jBtnTic6.setBackground(Color.orange);
        xCount++;
        gameScore();
        }
       
        if(b7==("X")&& b8==("X")&& b9==("X"))
       {
         JOptionPane.showMessageDialog(this,"Player X wins","Tic Tac Toe",JOptionPane.INFORMATION_MESSAGE);
        jBtnTic7.setBackground(Color.BLUE);
        jBtnTic8.setBackground(Color.BLUE);
        jBtnTic9.setBackground(Color.BLUE);
        xCount++;
        gameScore();
        }
        //diagonal wins for X
         if(b1==("X")&& b5==("X")&& b9==("X"))
       {
         JOptionPane.showMessageDialog(this,"Player X wins","Tic Tac Toe",JOptionPane.INFORMATION_MESSAGE);
        jBtnTic1.setBackground(Color.red);
        jBtnTic5.setBackground(Color.red);
        jBtnTic9.setBackground(Color.red);
        xCount++;
        gameScore();
        }
          if(b3==("X")&& b5==("X")&& b7==("X"))
       {
         JOptionPane.showMessageDialog(this,"Player X wins","Tic Tac Toe",JOptionPane.INFORMATION_MESSAGE);
        jBtnTic3.setBackground(Color.magenta);
        jBtnTic5.setBackground(Color.magenta);
        jBtnTic7.setBackground(Color.magenta);
        xCount++;
        gameScore();
        }
        
          //Vertical wins for X
         
          if(b1==("X")&& b4==("X")&& b7==("X"))
       {
         JOptionPane.showMessageDialog(this,"Player X wins","Tic Tac Toe",JOptionPane.INFORMATION_MESSAGE);
        jBtnTic1.setBackground(Color.GREEN);
        jBtnTic4.setBackground(Color.green);
        jBtnTic7.setBackground(Color.green);
        xCount++;
        gameScore();
        }
          
           if(b2==("X")&& b5==("X")&& b8==("X"))
       {
         JOptionPane.showMessageDialog(this,"Player X wins","Tic Tac Toe",JOptionPane.INFORMATION_MESSAGE);
        jBtnTic2.setBackground(Color.yellow);
        jBtnTic5.setBackground(Color.yellow);
        jBtnTic8.setBackground(Color.yellow);
        xCount++;
        gameScore();
        }
            if(b3==("X")&& b6==("X")&& b9==("X"))
       {
         JOptionPane.showMessageDialog(this,"Player X wins","Tic Tac Toe",JOptionPane.INFORMATION_MESSAGE);
        jBtnTic3.setBackground(Color.orange);
        jBtnTic6.setBackground(Color.orange);
        jBtnTic9.setBackground(Color.orange);
        xCount++;
        gameScore();
        }
        
            
            //Wins for O
             if(b1==("O")&& b2==("O")&& b3==("O"))
       {
         JOptionPane.showMessageDialog(this,"Player O Wins","Tic Tac Toe",
         JOptionPane.INFORMATION_MESSAGE);
        jBtnTic1.setBackground(Color.lightGray);
          jBtnTic2.setBackground(Color.lightGray);
           jBtnTic3.setBackground(Color.lightGray);
           oCount++;
           gameScore();
        }
       
         
       if(b4==("O")&& b5==("O")&& b6==("O"))
       {
         JOptionPane.showMessageDialog(this,"Player O wins","Tic Tac Toe",JOptionPane.INFORMATION_MESSAGE);
        jBtnTic4.setBackground(Color.orange);
        jBtnTic5.setBackground(Color.orange);
        jBtnTic6.setBackground(Color.orange);
        oCount++;
        gameScore();
        }
       
        if(b7==("O")&& b8==("O")&& b9==("O"))
       {
         JOptionPane.showMessageDialog(this,"Player O wins","Tic Tac Toe",JOptionPane.INFORMATION_MESSAGE);
        jBtnTic7.setBackground(Color.BLUE);
        jBtnTic8.setBackground(Color.BLUE);
        jBtnTic9.setBackground(Color.BLUE);
        oCount++;
        gameScore();
        }
        //diagonal wins for O
         if(b1==("O")&& b5==("O")&& b9==("O"))
       {
         JOptionPane.showMessageDialog(this,"Player O wins","Tic Tac Toe",JOptionPane.INFORMATION_MESSAGE);
        jBtnTic1.setBackground(Color.orange);
        jBtnTic5.setBackground(Color.orange);
        jBtnTic9.setBackground(Color.orange);
        oCount++;
        gameScore();
        }
          if(b3==("O")&& b5==("O")&& b7==("O"))
       {
         JOptionPane.showMessageDialog(this,"Player O wins","Tic Tac Toe",JOptionPane.INFORMATION_MESSAGE);
        jBtnTic3.setBackground(Color.orange);
        jBtnTic5.setBackground(Color.orange);
        jBtnTic7.setBackground(Color.orange);
        oCount++;
        gameScore();
        }
        
          //Vertical wins for O
         
          if(b1==("O")&& b4==("O")&& b7==("O"))
       {
         JOptionPane.showMessageDialog(this,"Player O wins","Tic Tac Toe",JOptionPane.INFORMATION_MESSAGE);
        jBtnTic1.setBackground(Color.orange);
        jBtnTic4.setBackground(Color.orange);
        jBtnTic7.setBackground(Color.orange);
        oCount++;
        gameScore();
        }
          
           if(b2==("O")&& b5==("O")&& b8==("O"))
       {
         JOptionPane.showMessageDialog(this,"Player O wins","Tic Tac Toe",JOptionPane.INFORMATION_MESSAGE);
        jBtnTic2.setBackground(Color.orange);
        jBtnTic5.setBackground(Color.orange);
        jBtnTic8.setBackground(Color.orange);
        oCount++;
        gameScore();
        }
            if(b3==("O")&& b6==("O")&& b9==("O"))
       {
         JOptionPane.showMessageDialog(this,"Player O wins","Tic Tac Toe",JOptionPane.INFORMATION_MESSAGE);
        jBtnTic3.setBackground(Color.orange);
        jBtnTic6.setBackground(Color.orange);
        jBtnTic9.setBackground(Color.orange);
        oCount++;
        gameScore();
        }
           else 
            {
        // Check for a draw
        if (!b1.isEmpty() && !b2.isEmpty() && !b3.isEmpty() &&
            !b4.isEmpty() && !b5.isEmpty() && !b6.isEmpty() &&
            !b7.isEmpty() && !b8.isEmpty() && !b9.isEmpty()) {
            JOptionPane.showMessageDialog(this, "The game is a draw!", "Tic Tac Toe", JOptionPane.INFORMATION_MESSAGE);
        }
     }
            
   }
   /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jlblPlayerO = new javax.swing.JLabel();
        jlblPlayerX = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jNewGamebtn = new javax.swing.JButton();
        jexitbtn = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jResetbtn1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jBtnTic3 = new javax.swing.JButton();
        jBtnTic1 = new javax.swing.JButton();
        jBtnTic2 = new javax.swing.JButton();
        jBtnTic6 = new javax.swing.JButton();
        jBtnTic4 = new javax.swing.JButton();
        jBtnTic5 = new javax.swing.JButton();
        jBtnTic7 = new javax.swing.JButton();
        jBtnTic8 = new javax.swing.JButton();
        jBtnTic9 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        generateBtn = new javax.swing.JButton();
        searchbtn1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 0, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(95, 158, 160));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(95, 158, 160));
        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 50)); // NOI18N
        jLabel2.setText("Player X:");
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 50)); // NOI18N
        jLabel3.setText("Player X:");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 50)); // NOI18N
        jLabel4.setText("Player O:");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, -1, -1));

        jlblPlayerO.setFont(new java.awt.Font("Segoe UI", 1, 50)); // NOI18N
        jlblPlayerO.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblPlayerO.setText("0");
        jlblPlayerO.setOpaque(true);
        jPanel4.add(jlblPlayerO, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 130, 220, 60));

        jlblPlayerX.setFont(new java.awt.Font("Segoe UI", 1, 50)); // NOI18N
        jlblPlayerX.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblPlayerX.setText("0");
        jlblPlayerX.setOpaque(true);
        jPanel4.add(jlblPlayerX, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 40, 220, 60));

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 610, 260));

        jPanel5.setBackground(new java.awt.Color(95, 158, 160));
        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton10.setFont(new java.awt.Font("Tahoma", 1, 96)); // NOI18N
        jButton10.setMaximumSize(new java.awt.Dimension(150, 125));
        jButton10.setMinimumSize(new java.awt.Dimension(150, 125));
        jButton10.setPreferredSize(new java.awt.Dimension(150, 125));
        jPanel5.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 360, 220, 170));

        jButton11.setFont(new java.awt.Font("Tahoma", 1, 96)); // NOI18N
        jButton11.setMaximumSize(new java.awt.Dimension(150, 125));
        jButton11.setMinimumSize(new java.awt.Dimension(150, 125));
        jButton11.setPreferredSize(new java.awt.Dimension(150, 125));
        jPanel5.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 360, 220, 170));

        jNewGamebtn.setFont(new java.awt.Font("Tahoma", 1, 64)); // NOI18N
        jNewGamebtn.setText("New Game");
        jNewGamebtn.setMaximumSize(new java.awt.Dimension(150, 125));
        jNewGamebtn.setMinimumSize(new java.awt.Dimension(150, 125));
        jNewGamebtn.setPreferredSize(new java.awt.Dimension(150, 125));
        jNewGamebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jNewGamebtnActionPerformed(evt);
            }
        });
        jPanel5.add(jNewGamebtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 570, 110));

        jexitbtn.setFont(new java.awt.Font("Tahoma", 1, 50)); // NOI18N
        jexitbtn.setText("Exit");
        jexitbtn.setMaximumSize(new java.awt.Dimension(150, 125));
        jexitbtn.setMinimumSize(new java.awt.Dimension(150, 125));
        jexitbtn.setPreferredSize(new java.awt.Dimension(150, 125));
        jexitbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jexitbtnActionPerformed(evt);
            }
        });
        jPanel5.add(jexitbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 170, 280, 110));

        jButton1.setText("jButton1");
        jPanel5.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 560, -1, -1));

        jResetbtn1.setFont(new java.awt.Font("Tahoma", 1, 50)); // NOI18N
        jResetbtn1.setText("Reset");
        jResetbtn1.setMaximumSize(new java.awt.Dimension(150, 125));
        jResetbtn1.setMinimumSize(new java.awt.Dimension(150, 125));
        jResetbtn1.setPreferredSize(new java.awt.Dimension(150, 125));
        jResetbtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jResetbtn1ActionPerformed(evt);
            }
        });
        jPanel5.add(jResetbtn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 280, 110));

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 610, 320));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 0, 650, 630));

        jPanel3.setBackground(new java.awt.Color(95, 158, 160));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jBtnTic3.setFont(new java.awt.Font("Tahoma", 1, 96)); // NOI18N
        jBtnTic3.setMaximumSize(new java.awt.Dimension(150, 125));
        jBtnTic3.setMinimumSize(new java.awt.Dimension(150, 125));
        jBtnTic3.setPreferredSize(new java.awt.Dimension(150, 125));
        jBtnTic3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnTic3ActionPerformed(evt);
            }
        });
        jPanel3.add(jBtnTic3, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 10, 210, 160));

        jBtnTic1.setFont(new java.awt.Font("Tahoma", 1, 96)); // NOI18N
        jBtnTic1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jBtnTic1.setMaximumSize(new java.awt.Dimension(150, 125));
        jBtnTic1.setMinimumSize(new java.awt.Dimension(150, 125));
        jBtnTic1.setPreferredSize(new java.awt.Dimension(150, 125));
        jBtnTic1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnTic1ActionPerformed(evt);
            }
        });
        jPanel3.add(jBtnTic1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 200, 160));

        jBtnTic2.setFont(new java.awt.Font("Tahoma", 1, 96)); // NOI18N
        jBtnTic2.setMaximumSize(new java.awt.Dimension(150, 125));
        jBtnTic2.setMinimumSize(new java.awt.Dimension(150, 125));
        jBtnTic2.setPreferredSize(new java.awt.Dimension(150, 125));
        jBtnTic2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnTic2ActionPerformed(evt);
            }
        });
        jPanel3.add(jBtnTic2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, 200, 160));

        jBtnTic6.setFont(new java.awt.Font("Tahoma", 1, 96)); // NOI18N
        jBtnTic6.setMaximumSize(new java.awt.Dimension(150, 125));
        jBtnTic6.setMinimumSize(new java.awt.Dimension(150, 125));
        jBtnTic6.setPreferredSize(new java.awt.Dimension(150, 125));
        jBtnTic6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnTic6ActionPerformed(evt);
            }
        });
        jPanel3.add(jBtnTic6, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 180, 210, 170));

        jBtnTic4.setFont(new java.awt.Font("Tahoma", 1, 96)); // NOI18N
        jBtnTic4.setMaximumSize(new java.awt.Dimension(150, 125));
        jBtnTic4.setMinimumSize(new java.awt.Dimension(150, 125));
        jBtnTic4.setPreferredSize(new java.awt.Dimension(150, 125));
        jBtnTic4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnTic4ActionPerformed(evt);
            }
        });
        jPanel3.add(jBtnTic4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 200, 170));

        jBtnTic5.setFont(new java.awt.Font("Tahoma", 1, 96)); // NOI18N
        jBtnTic5.setMaximumSize(new java.awt.Dimension(150, 125));
        jBtnTic5.setMinimumSize(new java.awt.Dimension(150, 125));
        jBtnTic5.setPreferredSize(new java.awt.Dimension(150, 125));
        jBtnTic5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnTic5ActionPerformed(evt);
            }
        });
        jPanel3.add(jBtnTic5, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 180, 200, 170));

        jBtnTic7.setFont(new java.awt.Font("Tahoma", 1, 96)); // NOI18N
        jBtnTic7.setMaximumSize(new java.awt.Dimension(150, 125));
        jBtnTic7.setMinimumSize(new java.awt.Dimension(150, 125));
        jBtnTic7.setPreferredSize(new java.awt.Dimension(150, 125));
        jBtnTic7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnTic7ActionPerformed(evt);
            }
        });
        jPanel3.add(jBtnTic7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 200, 170));

        jBtnTic8.setFont(new java.awt.Font("Tahoma", 1, 96)); // NOI18N
        jBtnTic8.setMaximumSize(new java.awt.Dimension(150, 125));
        jBtnTic8.setMinimumSize(new java.awt.Dimension(150, 125));
        jBtnTic8.setPreferredSize(new java.awt.Dimension(150, 125));
        jBtnTic8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnTic8ActionPerformed(evt);
            }
        });
        jPanel3.add(jBtnTic8, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 360, 200, 170));

        jBtnTic9.setFont(new java.awt.Font("Tahoma", 1, 96)); // NOI18N
        jBtnTic9.setMaximumSize(new java.awt.Dimension(150, 125));
        jBtnTic9.setMinimumSize(new java.awt.Dimension(150, 125));
        jBtnTic9.setPreferredSize(new java.awt.Dimension(150, 125));
        jBtnTic9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnTic9ActionPerformed(evt);
            }
        });
        jPanel3.add(jBtnTic9, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 360, 210, 170));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 650, 550));

        jPanel6.setBackground(new java.awt.Color(153, 0, 153));
        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 50)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 204, 204));
        jLabel5.setText("        TIC TAC TOE");
        jPanel6.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 480, 60));

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 490, 60));

        jTable1.setBackground(new java.awt.Color(0, 0, 255));
        jTable1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTable1.setForeground(new java.awt.Color(255, 255, 255));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "id", "playername", "playertype", "totalmatches", "wins", "loss", "winpercentage"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 640, 780, 200));

        jPanel7.setBackground(new java.awt.Color(0, 0, 102));
        jPanel7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        generateBtn.setBackground(new java.awt.Color(153, 255, 255));
        generateBtn.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        generateBtn.setText("Generate wins and loss ");
        generateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateBtnActionPerformed(evt);
            }
        });
        jPanel7.add(generateBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 360, 30));

        searchbtn1.setText("Search");
        jPanel7.add(searchbtn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 50, -1, -1));

        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 680, 400, 90));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1410, 860));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnTic2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnTic2ActionPerformed
               jBtnTic2.setText(startGame);
           {
               if(startGame.equalsIgnoreCase("X"))
               {
                   checker=false;
               }
               else
               {
                   checker=true;
               }
               choosePlayer();
               winGame();
           }
    }//GEN-LAST:event_jBtnTic2ActionPerformed

    private void jBtnTic5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnTic5ActionPerformed
              jBtnTic5.setText(startGame);
           {
               if(startGame.equalsIgnoreCase("X"))
               {
                   checker=false;
               }
               else
               {
                   checker=true;
               }
               choosePlayer();
               winGame();
           }
    }//GEN-LAST:event_jBtnTic5ActionPerformed

    private void jBtnTic8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnTic8ActionPerformed
               jBtnTic8.setText(startGame);
           {
               if(startGame.equalsIgnoreCase("X"))
               {
                   checker=false;
               }
               else
               {
                   checker=true;
               }
               choosePlayer();
               winGame();
           }
    }//GEN-LAST:event_jBtnTic8ActionPerformed

private JFrame frame;
    private void jexitbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jexitbtnActionPerformed
        // TODO add your handling code here:
        frame = new JFrame("Exit");
        if(JOptionPane.showConfirmDialog(frame,"Confirm if you want to exit","Tic Tac Toe",
              JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION) 
        {
            Welcome wc=new Welcome();
            wc.show();
            dispose();
              }
    }//GEN-LAST:event_jexitbtnActionPerformed

    private void jNewGamebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jNewGamebtnActionPerformed
        jBtnTic1.setEnabled(true);
        jBtnTic2.setEnabled(true);
        jBtnTic3.setEnabled(true);
        jBtnTic3.setEnabled(true);
        jBtnTic4.setEnabled(true);
        jBtnTic5.setEnabled(true);
        jBtnTic6.setEnabled(true);
        jBtnTic7.setEnabled(true);
        jBtnTic8.setEnabled(true);
        jBtnTic9.setEnabled(true);
        
        jlblPlayerX.setText("0");
        jlblPlayerO.setText("0");
      
        
        jBtnTic1.setText("");
        jBtnTic2.setText("");
        jBtnTic3.setText("");
        jBtnTic4.setText("");
        jBtnTic5.setText("");
        jBtnTic6.setText("");
        jBtnTic7.setText("");
        jBtnTic8.setText("");
        jBtnTic9.setText("");
        
        jBtnTic1.setBackground(Color.LIGHT_GRAY);
        jBtnTic2.setBackground(Color.LIGHT_GRAY);
        jBtnTic3.setBackground(Color.LIGHT_GRAY);
        jBtnTic4.setBackground(Color.LIGHT_GRAY);
        jBtnTic5.setBackground(Color.LIGHT_GRAY);
        jBtnTic6.setBackground(Color.LIGHT_GRAY);
        jBtnTic7.setBackground(Color.LIGHT_GRAY);
        jBtnTic8.setBackground(Color.LIGHT_GRAY);
        jBtnTic9.setBackground(Color.LIGHT_GRAY);
        
    }//GEN-LAST:event_jNewGamebtnActionPerformed

    private void jBtnTic1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnTic1ActionPerformed
           jBtnTic1.setText(startGame);
           {
               if(startGame.equalsIgnoreCase("X"))
               {
                   checker=false;
               }
               else
               {
                   checker=true;
               }
               choosePlayer();
               winGame();
           }
    }//GEN-LAST:event_jBtnTic1ActionPerformed

    private void jBtnTic3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnTic3ActionPerformed
               jBtnTic3.setText(startGame);
           {
               if(startGame.equalsIgnoreCase("X"))
               {
                   checker=false;
               }
               else
               {
                   checker=true;
               }
               choosePlayer();
               winGame();
           }
    }//GEN-LAST:event_jBtnTic3ActionPerformed

    private void jBtnTic4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnTic4ActionPerformed
               jBtnTic4.setText(startGame);
           {
               if(startGame.equalsIgnoreCase("X"))
               {
                   checker=false;
               }
               else
               {
                   checker=true;
               }
               choosePlayer();
               winGame();
           }
    }//GEN-LAST:event_jBtnTic4ActionPerformed

    private void jBtnTic6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnTic6ActionPerformed
               jBtnTic6.setText(startGame);
           {
               if(startGame.equalsIgnoreCase("X"))
               {
                   checker=false;
               }
               else
               {
                   checker=true;
               }
               choosePlayer();
               winGame();
           }
    }//GEN-LAST:event_jBtnTic6ActionPerformed

    private void jBtnTic7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnTic7ActionPerformed
              jBtnTic7.setText(startGame);
           {
               if(startGame.equalsIgnoreCase("X"))
               {
                   checker=false;
               }
               else
               {
                   checker=true;
               }
               choosePlayer();
               winGame();
           }
         
    }//GEN-LAST:event_jBtnTic7ActionPerformed

    private void jBtnTic9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnTic9ActionPerformed
               jBtnTic9.setText(startGame);
           {
               if(startGame.equalsIgnoreCase("X"))
               {
                   checker=false;
               }
               else
               {
                   checker=true;
               }
               choosePlayer();
               winGame();
           }
    }//GEN-LAST:event_jBtnTic9ActionPerformed

    private void jResetbtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jResetbtn1ActionPerformed
        // TODO add your handling code here:
         jBtnTic1.setEnabled(true);
        jBtnTic2.setEnabled(true);
        jBtnTic3.setEnabled(true);
        jBtnTic3.setEnabled(true);
        jBtnTic4.setEnabled(true);
        jBtnTic5.setEnabled(true);
        jBtnTic6.setEnabled(true);
        jBtnTic1.setEnabled(true);
        jBtnTic1.setEnabled(true);
        
        jBtnTic1.setText("");
        jBtnTic2.setText("");
        jBtnTic3.setText("");
        jBtnTic4.setText("");
        jBtnTic5.setText("");
        jBtnTic6.setText("");
        jBtnTic7.setText("");
        jBtnTic8.setText("");
        jBtnTic9.setText("");
        
        jBtnTic1.setBackground(Color.LIGHT_GRAY);
        jBtnTic2.setBackground(Color.LIGHT_GRAY);
        jBtnTic3.setBackground(Color.LIGHT_GRAY);
        jBtnTic4.setBackground(Color.LIGHT_GRAY);
        jBtnTic5.setBackground(Color.LIGHT_GRAY);
        jBtnTic6.setBackground(Color.LIGHT_GRAY);
        jBtnTic7.setBackground(Color.LIGHT_GRAY);
        jBtnTic8.setBackground(Color.LIGHT_GRAY);
        jBtnTic9.setBackground(Color.LIGHT_GRAY);

    }//GEN-LAST:event_jResetbtn1ActionPerformed

    private void generateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateBtnActionPerformed
        // TODO add your handling code here:   
    }//GEN-LAST:event_generateBtnActionPerformed
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Java_TicTacToe1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Java_TicTacToe1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Java_TicTacToe1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Java_TicTacToe1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Java_TicTacToe1().setVisible(true);
            }
               
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton generateBtn;
    private javax.swing.JButton jBtnTic1;
    private javax.swing.JButton jBtnTic2;
    private javax.swing.JButton jBtnTic3;
    private javax.swing.JButton jBtnTic4;
    private javax.swing.JButton jBtnTic5;
    private javax.swing.JButton jBtnTic6;
    private javax.swing.JButton jBtnTic7;
    private javax.swing.JButton jBtnTic8;
    private javax.swing.JButton jBtnTic9;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JButton jNewGamebtn;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JButton jResetbtn1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton jexitbtn;
    private javax.swing.JLabel jlblPlayerO;
    private javax.swing.JLabel jlblPlayerX;
    private javax.swing.JButton searchbtn1;
    // End of variables declaration//GEN-END:variables
}