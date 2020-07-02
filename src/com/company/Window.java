package com.company;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Window extends JPanel {

    Connection connection;

    public Window(Connection connection) {
        this.connection = connection;
        setLayout(null);
//////////////////////////////////////////////////////////////////////////////////
        JLabel zakonJediLabel = new JLabel("Zakony Jedi");
        zakonJediLabel.setBounds(180, 10, 100, 20);
        add(zakonJediLabel);

        JLabel jediLabel = new JLabel("Jedi");
        jediLabel.setBounds(570, 10, 100, 20);
        add(jediLabel);
///////////////////////////////////////////////////////////////////////////////////

        List<String> zakonJedi = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT nazwa_zakonu FROM zakon");

            while (result.next()) {
                zakonJedi.add(result.getString("nazwa_zakonu"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        JList<String> zakonJediList = new JList(zakonJedi.toArray());
        zakonJediList.setBounds(30, 35, 350, 250);
        add(zakonJediList);
///////////////////////////////////////////////////////////////////////////////////////////////
        List<String> jedi = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT nazwa FROM rycerze_jedi");

            while (result.next()) {
                jedi.add(result.getString("nazwa"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        JList<String> jediList = new JList(jedi.toArray());
        jediList.setBounds(410, 35, 350, 250);
        add(jediList);
///////////////////////////////////////////////////////////////////////////////////////////////////
        JLabel rejestracjaZakonuLabel = new JLabel("Rejestracja zakonu Jedi");
        rejestracjaZakonuLabel.setBounds(165, 300, 200, 30);
        add(rejestracjaZakonuLabel);

        JLabel rejestracjaJediLabel = new JLabel("Rejestracja Jedi");
        rejestracjaJediLabel.setBounds(565, 300, 200, 30);
        add(rejestracjaJediLabel);

        JLabel nazwaZakonuJedi = new JLabel("nazwa:");
        nazwaZakonuJedi.setFont(new Font("Arial UI", Font.PLAIN, 15));
        nazwaZakonuJedi.setBounds(435, 330, 100, 20);
        add(nazwaZakonuJedi);

        JTextField nazwaZakonuJediTextField = new JTextField();
        nazwaZakonuJediTextField.setBounds(114, 330, 265, 20);
        add(nazwaZakonuJediTextField);

        JLabel nazwaJediLabel = new JLabel("nazwa:");
        nazwaJediLabel.setFont(new Font("Arial UI", Font.PLAIN, 15));
        nazwaJediLabel.setBounds(50, 330, 100, 20);
        add(nazwaJediLabel);

        JTextField nazwaJediTextField = new JTextField();
        nazwaJediTextField.setBounds(490, 330, 265, 20);
        add(nazwaJediTextField);

        JButton wybierzJediButton = new JButton("wybierz");
        wybierzJediButton.setBounds(15, 395, 85, 30);
        add(wybierzJediButton);

        JList wybierz2 = new JList();
        wybierz2.setBounds(120, 380, 257, 100);
        add(wybierz2);

        JButton buttonImpLeft = new JButton("import");
        buttonImpLeft.setBounds(15, 490, 85, 30);
        add(buttonImpLeft);

        JButton buttonEksLeft = new JButton("eksport");
        buttonEksLeft.setBounds(15, 530, 85, 30);
        add(buttonEksLeft);
/////////////////////////////////////////////////////////////////////////////////////////////////////
        JTextField zakonJediPath = new JTextField("C:/Users/Dom/Desktop/zakony.txt");
        zakonJediPath.setBounds(120, 510, 250, 30);
        zakonJediPath.setEditable(false);

        buttonEksLeft.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    FileWriter writer = new FileWriter(zakonJediPath.getText());
                    for (String s : zakonJedi) {
                        writer.write(s + "\n");
                    }
                    writer.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                    System.out.println("Nie udało się zapisać do pliku");
                }
            }
        });

        zakonJediPath.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                int returnValue = jfc.showOpenDialog(null);

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = jfc.getSelectedFile();
                    System.out.println(selectedFile.getAbsolutePath());
                    zakonJediPath.setText(selectedFile.getAbsolutePath());
                }
            }
        });
        add(zakonJediPath);
//////////////////////////////////////////////////////////////////////////////////////////////////////
        JButton buttonImpRight = new JButton("import");
        buttonImpRight.setBounds(400, 490, 85, 30);
        add(buttonImpRight);

        JButton buttonEksRight = new JButton("eksport");
        buttonEksRight.setBounds(400, 530, 85, 30);
        add(buttonEksRight);

////////////////////////////////////////////////////////////////////////////////////////////////////
        JTextField jediPath = new JTextField("C:/Users/Dom/Desktop/jedi.txt");
        jediPath.setBounds(510, 510, 250, 30);
        jediPath.setEditable(false);

        buttonEksRight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    FileWriter writer = new FileWriter(jediPath.getText());
                    for (String s : jedi) {
                        writer.write(s + "\n");
                    }
                    writer.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                    System.out.println("Nie udało się zapisać do pliku");
                }
            }
        });

        jediPath.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

                int returnValue = jfc.showOpenDialog(null);

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = jfc.getSelectedFile();
                    System.out.println(selectedFile.getAbsolutePath());
                    jediPath.setText(selectedFile.getAbsolutePath());
                }
            }
        });
        add(jediPath);
//////////////////////////////////////////////////////////////////////////////////////////////////////////
        JButton zarejestrujLeft = new JButton("zarejestruj");
        zarejestrujLeft.setBounds(130, 550, 100, 30);
        add(zarejestrujLeft);

        JButton wyczyscLeft = new JButton("wyczysc");
        wyczyscLeft.setBounds(270, 550, 100, 30);
        add(wyczyscLeft);

        JButton zarejestrujRight = new JButton("zarejestruj");
        zarejestrujRight.setBounds(510, 550, 100, 30);
        add(zarejestrujRight);


        JButton wyczyscRigt = new JButton("wyczysc");
        wyczyscRigt.setBounds(660, 550, 100, 30);
        add(wyczyscRigt);

        JLabel kolorMiecza = new JLabel("kolor miecza:");
        kolorMiecza.setFont(new Font("Arial UI", Font.PLAIN, 15));
        kolorMiecza.setBounds(413, 380, 100, 20);
        add(kolorMiecza);

        String kolory[] = {"niebieski", "czerwony", "zielony", "fioletowy"};
        JComboBox kolorMieczaCombo = new JComboBox(kolory);
        kolorMieczaCombo.setBounds(520, 380, 237, 20);
        add(kolorMieczaCombo);

        JLabel moc = new JLabel("moc:");
        moc.setFont(new Font("Arial UI", Font.PLAIN, 15));
        moc.setBounds(470, 410, 100, 20);
        add(moc);

        JSlider mocSlider = new JSlider(JSlider.HORIZONTAL, 0, 1000, 500);
        mocSlider.setBounds(515, 420, 247, 15);
        add(mocSlider);

        JLabel zeroValueOfSlider = new JLabel("0");
        zeroValueOfSlider.setBounds(520, 440, 50, 15);
        add(zeroValueOfSlider);

        JLabel maxValueOfSlider = new JLabel("1000");
        maxValueOfSlider.setBounds(727, 440, 50, 15);
        add(maxValueOfSlider);

        JLabel stronaMocy = new JLabel("strona mocy:");
        stronaMocy.setFont(new Font("Arial UI", Font.PLAIN, 15));
        stronaMocy.setBounds(512, 460, 100, 20);
        add(stronaMocy);

        JRadioButton r1 = new JRadioButton("ciemna");
        JRadioButton r2 = new JRadioButton("jasna");
        r1.setActionCommand("ciemna");
        r2.setActionCommand("jasna");
        r1.setBounds(610, 457, 100, 30);
        r2.setBounds(705, 457, 100, 30);
        ButtonGroup stronyMocyGroup = new ButtonGroup();
        stronyMocyGroup.add(r1);
        stronyMocyGroup.add(r2);
        add(r1);
        add(r2);

        zarejestrujRight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(nazwaJediTextField.getText());
                System.out.println(kolorMieczaCombo.getSelectedItem());
                System.out.println(mocSlider.getValue());
                System.out.println(stronyMocyGroup.getSelection().getActionCommand());

                try {
                    Statement statement = connection.createStatement();
                    ResultSet result = statement.executeQuery("INSERT INTO jedi VALUES()");

                    while (result.next()) {
                        jedi.add(result.getString("nazwa"));
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        });
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }
}
