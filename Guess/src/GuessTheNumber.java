import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GuessTheNumber {
    private JFrame frame;
    private JTextField guessInput;
    private JLabel resultLabel, scoreLabel;
    private JButton submitButton, restartButton;
    private int randomNumber, attempts, score;

    public GuessTheNumber() {
        // Set up the futuristic theme
        FlatLightLaf.setup();

        frame = new JFrame("Guess the Number");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.decode("#1E1E2F"));
        frame.setUndecorated(true); // Remove the default title bar
        frame.setShape(new java.awt.geom.RoundRectangle2D.Double(0, 0, 400, 300, 50, 50)); // Rounded edges

        // Title Label
        JLabel titleLabel = new JLabel("Guess the Number");
        titleLabel.setBounds(100, 20, 200, 30);
        titleLabel.setForeground(Color.decode("#FFD700"));
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(titleLabel);

        // Input field
        guessInput = new JTextField();
        guessInput.setBounds(120, 80, 150, 30);
        guessInput.setFont(new Font("Arial", Font.PLAIN, 16));
        guessInput.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        frame.add(guessInput);

        // Result Label
        resultLabel = new JLabel("Make a guess!");
        resultLabel.setBounds(120, 120, 200, 20);
        resultLabel.setForeground(Color.decode("#FFFFFF"));
        frame.add(resultLabel);

        // Submit Button
        submitButton = new JButton("Submit");
        submitButton.setBounds(140, 150, 120, 30);
        styleButton(submitButton);
        frame.add(submitButton);

        // Restart Button
        restartButton = new JButton("Restart");
        restartButton.setBounds(140, 190, 120, 30);
        styleButton(restartButton);
        restartButton.setVisible(false);
        frame.add(restartButton);

        // Score Label
        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setBounds(10, 10, 100, 20);
        scoreLabel.setForeground(Color.decode("#FFD700"));
        frame.add(scoreLabel);

        // Generate random number
        randomNumber = new Random().nextInt(100) + 1;
        attempts = 0;
        score = 0;

        // Button actions
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processGuess();
            }
        });

        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restartGame();
            }
        });

        frame.setVisible(true);
        centerFrame();
    }

    private void processGuess() {
        try {
            int guess = Integer.parseInt(guessInput.getText());
            attempts++;

            if (guess < randomNumber) {
                resultLabel.setText("Too low! Try again.");
            } else if (guess > randomNumber) {
                resultLabel.setText("Too high! Try again.");
            } else {
                resultLabel.setText("Correct! You've guessed it!");
                score += Math.max(0, 10 - attempts);
                scoreLabel.setText("Score: " + score);
                submitButton.setEnabled(false);
                restartButton.setVisible(true);
            }
        } catch (NumberFormatException ex) {
            resultLabel.setText("Please enter a valid number.");
        }
        guessInput.setText("");
    }

    private void restartGame() {
        randomNumber = new Random().nextInt(100) + 1;
        attempts = 0;
        resultLabel.setText("Make a guess!");
        guessInput.setText("");
        submitButton.setEnabled(true);
        restartButton.setVisible(false);
    }

    private void styleButton(JButton button) {
        button.setFocusPainted(false);
        button.setBackground(Color.decode("#33334D"));
        button.setForeground(Color.decode("#FFD700"));
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBorder(BorderFactory.createLineBorder(Color.decode("#FFD700"), 2));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.decode("#FFD700"));
                button.setForeground(Color.decode("#1E1E2F"));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.decode("#33334D"));
                button.setForeground(Color.decode("#FFD700"));
            }
        });
    }

    private void centerFrame() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(screenSize.width / 2 - frame.getWidth() / 2,
                screenSize.height / 2 - frame.getHeight() / 2);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GuessTheNumber());
    }
}
