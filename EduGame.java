

/** Project: Group Lab Project Assignment
* Purpose Details: a shape and number educational Game
* Course:IST 242
* Author:Andrew Shafer, Christopher Carlos
 * all classes and methods as well as structr were jointly written by both Andrew And Chris
* Date Developed: 6/21/2024
* Last Date Changed:6/21/2024
* Rev: 1
*/

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.awt.Graphics;



public class EduGame extends JFrame implements KeyListener {


    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;
    private static final int Imagex = WIDTH / 3;
    private static final int Imagey = HEIGHT / 4;
    private static final int QAX = 83;
    private static final int QAY = WIDTH - 166;
    private static final int QBX = 83;
    private static final int QBY = WIDTH - 83;
    private static final int QCX = HEIGHT - 188;
    private static final int QCY = WIDTH - 166;
    private static final int QDX = HEIGHT - 188;
    private static final int QDY = WIDTH - 83;
    private static final int ImageHeight = 150;
    private static final int ImageWidth = 150;


    public int Q;
    //Image Variable for the Shapes
    private BufferedImage hex;
    private BufferedImage oct;
    private BufferedImage squ;
    private BufferedImage pnt;
    private BufferedImage tri;
    private BufferedImage hep;

    //labels the game panel and string place-holders
    private JPanel gamePanel;
    public String answer;
    public String qAnswer;
    private JLabel scoreLabel;
    private JLabel questionNumLabel;
    private int questionNum;
    private int Q0Counter;
    public static int score;
    ;
    private boolean isGameOver;



    //the main game function
    public EduGame() {
        setTitle("Education Game");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        questionNum = 0;
        Q0Counter = 0;
        gamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                draw(g);
            }
        };
        ///Sets the score label on start

        scoreLabel = new JLabel("Score:");
        scoreLabel.setForeground(Color.BLUE);
        scoreLabel.setBounds(10, 10, 100, 20);
        gamePanel.add(scoreLabel);
        ///Sets the Question label on start
        questionNumLabel = new JLabel("welcome to the game");
        questionNumLabel.setForeground(Color.BLUE);
        questionNumLabel.setBounds(20, 10, 100, 20);
        gamePanel.add(questionNumLabel);
        add(gamePanel);
        gamePanel.setFocusable(true);
        gamePanel.addKeyListener(this);
        isGameOver = false;
        qAnswer = "";
        //Imports all the image files into memory amd assigns them to a variable
        try {
            hex = ImageIO.read(new File("hexagon.png"));
            oct = ImageIO.read(new File("octagon.png"));
            pnt = ImageIO.read(new File("pentagon.png"));
            squ = ImageIO.read(new File("square.png"));
            tri = ImageIO.read(new File("triangle.png"));
            hep = ImageIO.read(new File("heptagon.png"));


        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // handles the procurement of a new question upon the keystroke answer
    //also handles game end and initial start
    public void update() {
        iscorrect();
        scoreLabel.setText("Score: " + score);
        if (!isGameOver) {
            Random random = new Random();
            Q = random.nextInt(16);
            questionNum += 1;
            gamePanel.repaint();
            questionNumLabel.setText("Question #:" + questionNum);
        }
        if (questionNum > 10) {
            isGameOver = true; //final score label
            repaint();
            questionNumLabel.setText("Your Grade is: " + score + " Out Of Ten");
        }


    }

    public void draw(Graphics g) {
        //BackGround
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        //Game end Splash Screen
        if (isGameOver) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 24));
            g.drawString("Game Over!", WIDTH / 2 - 80, HEIGHT / 2);
        }
        //ALL questions, their images and multiple choice answers
        //Determined by the Random Number generator in the UPDATE() method
        if (Q == 0) {
            //Unique question used as the star splash scree, then if the RNG produces 0 it will freely add 1 to the over all score as a bonus point
            if (Q0Counter <= 1) {

                qAnswer = "";
                g.setColor(Color.WHITE);
                g.setFont(new Font("Arial", Font.BOLD, 24));
                g.drawString("Please press a key, ABCD to start", 60, 250);
                g.drawString("Answer A", QAX, QAY);
                g.drawString("Answer B", QBX, QBY);
                g.drawString("Answer C", QCX, QCY);
                g.drawString("Answer D", QDX, QDY);
                Q0Counter += 1;
            } else {
                qAnswer = "";
                g.setColor(Color.WHITE);
                g.setFont(new Font("Arial", Font.BOLD, 24));
                g.drawString("FREEEBIE POINT", 60, 250);
                score += 1;
            }

        } else if (Q == 1) {
            qAnswer = "B";
            g.drawImage(squ, Imagex, Imagey, ImageWidth, ImageHeight, null);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 24));
            g.drawString("A.Triangle", QAX, QAY);
            g.drawString("B.Square", QBX, QBY);
            g.drawString("C.Octagon", QCX, QCY);
            g.drawString("D.Hexagon", QDX, QDY);

        } else if (Q == 2) {
            qAnswer = "B";
            g.drawImage(tri, Imagex, Imagey, ImageWidth, ImageHeight, null);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 24));
            g.drawString("A.Purple", QAX, QAY);
            g.drawString("B.Triangle", QBX, QBY);
            g.drawString("C.Square", QCX, QCY);
            g.drawString("D.Seven", QDX, QDY);

        } else if (Q == 3) {
            qAnswer = "C";
            g.setColor(Color.WHITE);
            g.drawImage(hex, Imagex, Imagey, ImageWidth, ImageHeight, null);
            g.setFont(new Font("Arial", Font.BOLD, 24));
            g.drawString("A.Yellow", QAX, QAY);
            g.drawString("B.Square", QBX, QBY);
            g.drawString("C.Hexagon", QCX, QCY);
            g.drawString("D.Triangle", QDX, QDY);

        } else if (Q == 4) {
            qAnswer = "D";
            g.setColor(Color.WHITE);
            g.drawImage(oct, Imagex, Imagey, ImageWidth, ImageHeight, null);
            g.setFont(new Font("Arial", Font.BOLD, 24));
            g.drawString("A.Porygon", QAX, QAY);
            g.drawString("B.Three", QBX, QBY);
            g.drawString("C.Green", QCX, QCY);
            g.drawString("D.Octagon", QDX, QDY);

        } else if (Q == 5) {
            qAnswer = "B";
            g.setColor(Color.WHITE);
            g.drawImage(pnt, Imagex, Imagey, ImageWidth, ImageHeight, null);
            g.setFont(new Font("Arial", Font.BOLD, 24));
            g.drawString("A.Quadrilateral", QAX, QAY);
            g.drawString("B.Pentagon", QBX, QBY);
            g.drawString("C.Circle", QCX, QCY);
            g.drawString("D.Digimon", QDX, QDY);

        } else if (Q == 6) {
            qAnswer = "D";
            g.drawImage(hep, Imagex, Imagey, ImageWidth, ImageHeight, null);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 24));
            g.drawString("A.ellipse", QAX, QAY);
            g.drawString("B.Hypersphere", QBX, QBY);
            g.drawString("C.Circle", QCX, QCY);
            g.drawString("D.Heptagon", QDX, QDY);

        } else if (Q == 7) {
            qAnswer = "B";
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 100));
            g.drawString("3", 225, 200);
            g.setFont(new Font("Arial", Font.BOLD, 24));
            g.drawString("A.Six", QAX, QAY);
            g.drawString("B.Three", QBX, QBY);
            g.drawString("C.Tree", QCX, QCY);
            g.drawString("D.Thirteen", QDX, QDY);

        } else if (Q == 8) {
            qAnswer = "D";
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 100));
            g.drawString("4", 225, 200);
            g.setFont(new Font("Arial", Font.BOLD, 24));
            g.drawString("A.Cuatro", QAX, QAY);
            g.drawString("B.Four", QBX, QBY);
            g.drawString("C.Two X Two", QCX, QCY);
            g.drawString("D.All Of The Above", QDX, QDY);

        } else if (Q == 10) {
            qAnswer = "A";
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 100));
            g.drawString("5", 225, 200);
            g.setFont(new Font("Arial", Font.BOLD, 24));
            g.drawString("A.Five", QAX, QAY);
            g.drawString("B.Seven", QBX, QBY);
            g.drawString("C.Two", QCX, QCY);
            g.drawString("D.Nine", QDX, QDY);

        } else if (Q == 11) {
            qAnswer = "C";
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 100));
            g.drawString("6", 225, 200);
            g.setFont(new Font("Arial", Font.BOLD, 24));
            g.drawString("A.Tree", QAX, QAY);
            g.drawString("B.Five", QBX, QBY);
            g.drawString("C.Six", QCX, QCY);
            g.drawString("D.Pink", QDX, QDY);

        } else if (Q == 12) {
            qAnswer = "A";
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 100));
            g.drawString("7", 225, 200);
            g.setFont(new Font("Arial", Font.BOLD, 24));
            g.drawString("A.Seven", QAX, QAY);
            g.drawString("B.Seventy", QBX, QBY);
            g.drawString("C.Twelve", QCX, QCY);
            g.drawString("D.Two", QDX, QDY);

        } else if (Q == 13) {
            qAnswer = "D";
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 100));
            g.drawString("8", 225, 200);
            g.setFont(new Font("Arial", Font.BOLD, 24));
            g.drawString("A.Two Zeros's", QAX, QAY);
            g.drawString("B.Quack", QBX, QBY);
            g.drawString("C.Thirty-Seven", QCX, QCY);
            g.drawString("D.Eight", QDX, QDY);

        } else if (Q == 14) {
            qAnswer = "B";
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 100));
            g.drawString("9", 225, 200);
            g.setFont(new Font("Arial", Font.BOLD, 24));
            g.drawString("A.Mine", QAX, QAY);
            g.drawString("B.Nine", QBX, QBY);
            g.drawString("C.Three", QCX, QCY);
            g.drawString("D.Eight", QDX, QDY);

        } else if (Q == 15) {
            qAnswer = "C";
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 100));
            g.drawString("0", 225, 200);
            g.setFont(new Font("Arial", Font.BOLD, 24));
            g.drawString("A.Ten", QAX, QAY);
            g.drawString("B.Twenty", QBX, QBY);
            g.drawString("C.Zero", QCX, QCY);
            g.drawString("D.Five", QDX, QDY);


        } else if (Q == 9) {
            qAnswer = "A";
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 100));
            g.drawString("1", 225, 200);
            g.setFont(new Font("Arial", Font.BOLD, 24));
            g.drawString("A.One", QAX, QAY);
            g.drawString("B.Seven", QBX, QBY);
            g.drawString("C.Sixteen", QCX, QCY);
            g.drawString("D.Two", QDX, QDY);

        }
    };
    // increments the score for update
 public void iscorrect() {
     if (qAnswer == answer) {
         score += 1;
     }
 }


// key listening methods, searches for a key press and key release. sets the value of answer for correct answer comparison
    @Override
    // event handler for keystrokes.
    public void keyPressed(KeyEvent e){
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_A) {
            answer = "A";
            update();
        } else if (keyCode == KeyEvent.VK_B) {
            answer = "B";
            update();
        } else if (keyCode == KeyEvent.VK_C) {
            answer = "C";
            update();
        } else if (keyCode == KeyEvent.VK_D) {
            answer = "D";
            update();
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
// Main
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EduGame().setVisible(true);
            }
        });
    }



}