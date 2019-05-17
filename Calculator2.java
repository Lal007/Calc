import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Calculator2 extends JFrame {

    JTextField enter1, enter2, result;
    JButton sum, diff, div, multi, exp;
    boolean alert = true;

    public Calculator2(){
        super("Калькулятор");
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

        enter2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                enter2.setText("");
            }
        });

        // Создаю кнопки
        sum = new JButton("+");
        sum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double firstNumber = Double.parseDouble(enter1.getText());  //Получение цифры из текстового поля
                double secondNumber = Double.parseDouble(enter2.getText());
                result.setText(String.valueOf(df.format(firstNumber + secondNumber))); // При нажатии кнопки результат записывается в текстовое поле "Результат подсчета"
            }
        });

        diff = new JButton("-");
        diff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double firstNumber = Double.parseDouble(enter1.getText());
                double secondNumber = Double.parseDouble(enter2.getText());
                result.setText(String.valueOf(df.format(firstNumber - secondNumber)));
            }
        });

        div = new JButton("/");
        div.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double firstNumber = Double.parseDouble(enter1.getText());
                double secondNumber = Double.parseDouble(enter2.getText());
                result.setText(String.valueOf(df.format(firstNumber / secondNumber)));
            }
        });

        multi = new JButton("*");
        multi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double firstNumber = Double.parseDouble(enter1.getText());
                double secondNumber = Double.parseDouble(enter2.getText());
                result.setText(String.valueOf(df.format(firstNumber * secondNumber)));
            }
        });

        exp = new JButton("^");
        exp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (alert){ // Вывод предупреждения только один раз.
                    JOptionPane.showMessageDialog(Calculator2.this, "Первое число возводится в степень второго");
                    alert = false;
                }
                double firstNumber = Double.parseDouble(enter1.getText());
                double secondNumber = Double.parseDouble(enter2.getText());
                result.setText(String.valueOf(df.format(Math.pow(firstNumber, secondNumber))));
            }
        });

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
        new Calculator2();
    }
}
