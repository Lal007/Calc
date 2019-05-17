import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class CalculatorX extends JFrame implements ActionListener{

    JTextField enter1, enter2, result;
    JButton sum, diff, div, multi, exp;
    boolean alert = true;
    boolean err = true;

    public CalculatorX(){
        super("КалькуляторX");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(250, 300);

        // Это для того, чтобы ограничить количество цифр после запятой в дробных числах
        DecimalFormat df = new DecimalFormat("#.###");
        df.setRoundingMode(RoundingMode.CEILING);

        // Создаю текстовые поля
        enter1 = new JTextField("Введите первое число", 20);
        enter1.setToolTipText("Первое число");
        enter2 = new JTextField("Введите второе число",20);
        enter2.setToolTipText("Второе число");
        result = new JTextField("Результат подсчета", 20);

        // При нажатии мышью текстовые поля сами очищаются от текста
        enter1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                enter1.setText("");
            }
        });

        // Запрет на ввод букв
        enter1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (!Character.isDigit(e.getKeyChar())){
                    if (err){
                        JOptionPane.showMessageDialog(CalculatorX.this, "Допустимы только цифры");
                        err = false;
                    }
                    e.consume();
                }
            }
        });

        enter2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                enter2.setText("");
            }
        });

        enter2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (!Character.isDigit(e.getKeyChar())) {
                    if (err){
                        JOptionPane.showMessageDialog(CalculatorX.this, "Допустимы только цифры");
                        err = false;
                    }
                    e.consume();
                }

            }
        });

        // Запрет на редактирование c клавиатуры
        result.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                e.consume();
            }
        });

        // Создаю кнопки
        sum = new JButton("+");
        sum.setActionCommand("+");
        sum.addActionListener(this);

        diff = new JButton("-");
        diff.setActionCommand("-");
        diff.addActionListener(this);

        div = new JButton("/");
        div.setActionCommand("/");
        div.addActionListener(this);

        multi = new JButton("*");
        multi.setActionCommand("*");
        multi.addActionListener(this);

        exp = new JButton("^");
        exp.setActionCommand("^");
        exp.addActionListener(this);


        JPanel text = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        text.add(enter1);
        text.add(enter2);
        text.add(result);
        buttons.add(sum);
        buttons.add(diff);
        buttons.add(div);
        buttons.add(multi);
        buttons.add(exp);
        text.add(buttons);

        getContentPane().add(text);

        setVisible(true);


    }

    public static void main(String[] args) {
        //JFrame.setDefaultLookAndFeelDecorated(true);
        new CalculatorX();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton)e.getSource();
        String command = button.getActionCommand(); // Считываю кая кнопка создала событие
        //System.out.println(command);
        double firstNumber = 0;
        double secondNumber = 0;
        try {
            firstNumber = Double.parseDouble(enter1.getText()); //Получение цифры из текстового поля
            secondNumber = Double.parseDouble(enter2.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(CalculatorX.this, "Введите числа в соответсвующие поля");
        }
        result.setText(calculation(firstNumber, secondNumber, command)); // При нажатии кнопки результат записывается в текстовое поле "Результат подсчета"
    }

    public String calculation(double firstNumber, double secondNumber, String operator){

        // Это для того, чтобы ограничить количество цифр после запятой в дробных числах
        DecimalFormat df = new DecimalFormat("#.###");
        df.setRoundingMode(RoundingMode.CEILING);

        String result = "";
        switch (operator) {

            case ("+"):
                result = String.valueOf(df.format(firstNumber + secondNumber));
                break;
            case ("-"):
                result = String.valueOf(df.format(firstNumber - secondNumber));
                break;
            case("/"):
                if (secondNumber == 0){
                    result = "Деление на ноль невозможно!";
                    break;
                }else {
                    result = String.valueOf(df.format(firstNumber / secondNumber));
                    break;
                }
            case("*"):
                result = String.valueOf(df.format(firstNumber * secondNumber));
                break;
            case ("^"):
                if (alert){ // Вывод предупреждения только один раз.
                    JOptionPane.showMessageDialog(CalculatorX.this, "Первое число возводится в степень второго");
                    alert = false;
                }
                result = String.valueOf(df.format(Math.pow(firstNumber, secondNumber)));
        }
        return result;
    }
}
