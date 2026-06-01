import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class gamecopa {
    int boardWidth = 600;
    int boardHeight = 650;

    JFrame frame = new JFrame("Copa: Ganhe a copa para o Neymar");
    JLabel textLabel  = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();

    JButton[] board = new JButton[9];
    ImageIcon neymar;
    ImageIcon taca;

    JButton currNeymar;
    JButton currTaca;

    Random random = new Random();
    Timer tempoNeymar;
    Timer tempoTaca;

    int score;


    gamecopa() {

        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textLabel.setFont(new Font("Arial", Font.PLAIN, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Pontos: 0");
        textPanel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(3, 3));
        frame.add(boardPanel);

        //neymar = new ImageIcon(getClass().getResource("./neymar.png"));

        Image neymarImage = new ImageIcon(getClass().getResource("neymar.png")).getImage();
        neymar = new ImageIcon(neymarImage.getScaledInstance(150, 150, Image.SCALE_SMOOTH));

        Image tacaImage = new ImageIcon(getClass().getResource("tacacopa26.png")).getImage();
        taca = new ImageIcon(tacaImage.getScaledInstance(150, 150, Image.SCALE_SMOOTH));

        score = 0;
        for (int i = 0; i < 9; i++) {
            JButton button = new JButton();
            board[i] = button;
            boardPanel.add(button);
            button.setFocusable(false); //tirar retangulo do quadrado
            //button.setIcon(taca);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JButton button = (JButton) e.getSource();
                    if (button == currNeymar) {
                        score += 10;
                        textLabel.setText("Pontos: " + Integer.toString(score));

                    }
                    else if (button == currTaca) {
                        textLabel.setText("Perdeu o Hexa: " + Integer.toString(score));
                        tempoTaca.stop();
                        tempoNeymar.stop();
                        for (int i = 0; i < 9; i++) {
                            board[i].setEnabled(false);
                        }
                    }

                }
            });
        }

        tempoNeymar = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(currNeymar != null) {
                    currNeymar.setIcon(null);
                    currNeymar = null;
                }
                int num = random.nextInt(9);
                JButton button = board[num];

                if (currTaca == button) return;

                currNeymar = button;
                button.setIcon(neymar);
            }
        });

        tempoTaca = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(currTaca != null) {
                    currTaca.setIcon(null);
                    currTaca = null;
                }
                int num = random.nextInt(9);
                JButton button = board[num];

                if (currNeymar == button) return;

                currTaca = button;
                button.setIcon(taca);
            }
        });
        frame.setVisible(true);
        tempoNeymar.start();
        tempoTaca.start();

    }
}
