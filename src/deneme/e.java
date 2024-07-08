package deneme;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;

public class e extends JFrame {

    Connection conn;
    Statement stm;
    PreparedStatement prm;
    ResultSet rs;
    

    public void connect() {
        try {
            // JDBC sürücüsünü yükleyin
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Bağlantı URL'sini güncelleyin
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/den?useSSL=false&serverTimezone=UTC", "root", "24321008350Hk");
        } catch (Exception e) {
            e.printStackTrace();  // Hata durumunda hata mesajı basılır
        }
    }
    public void LoadPruduct() {
    	try {
    		prm.getConnection().prepareStatement("SELECT * FROM denee");
    		rs=prm.executeQuery();
    		
    	}
    	catch(Exception e) {
    		
    	}
    }

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTable table;
    private final Action action = new SwingAction();
    private final Action action_1 = new SwingAction_1();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    e frame = new e();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public e() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 944, 550);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("name");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel.setBounds(31, 36, 109, 21);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("name");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_1.setBounds(31, 68, 109, 21);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("name");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_2.setBounds(31, 125, 109, 21);
        contentPane.add(lblNewLabel_2);

        textField = new JTextField();
        textField.setBounds(201, 38, 138, 20);
        contentPane.add(textField);
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(201, 70, 138, 20);
        contentPane.add(textField_1);

        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBounds(201, 127, 138, 20);
        contentPane.add(textField_2);

        JComboBox comboBox = new JComboBox();
        comboBox.setToolTipText("ghjuy");
        comboBox.setBounds(201, 195, 98, 22);
        contentPane.add(comboBox);

        JLabel lblNewLabel_2_1 = new JLabel("name");
        lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_2_1.setBounds(31, 199, 109, 21);
        contentPane.add(lblNewLabel_2_1);

        JButton btnNewButton = new JButton("add");
        btnNewButton.setAction(action);
        btnNewButton.setBounds(31, 277, 89, 23);
        contentPane.add(btnNewButton);

        JButton btnUpdate = new JButton("update");
        btnUpdate.setAction(action_1);
        btnUpdate.setBounds(142, 277, 89, 23);
        contentPane.add(btnUpdate);

        JButton btnDelete = new JButton("delete");
        btnDelete.setBounds(266, 277, 89, 23);
        contentPane.add(btnDelete);

        JButton btnNew = new JButton("new");
        btnNew.setBounds(391, 277, 89, 23);
        contentPane.add(btnNew);

        table = new JTable();
        table.setBounds(562, 41, 337, 388);
        contentPane.add(table);
    }

    private class SwingAction extends AbstractAction {
        public SwingAction() {
            putValue(NAME, "kaydet");
            putValue(SHORT_DESCRIPTION, "Some short description");
        }

        public void actionPerformed(ActionEvent e) {
            connect();
            String name = textField.getText();
            String name2 = textField_1.getText();
            String name3 = textField_2.getText();

            try {
                prm = conn.prepareStatement("INSERT INTO denee (isim, gg, name) VALUES (?, ?, ?)");
                prm.setString(1, name);
                prm.setString(2, name2);
                prm.setString(3, name3);
                int k = prm.executeUpdate();
                if (k == 1) {
                    JOptionPane.showMessageDialog(rootPane, "eklendi");
                    textField.setText("");
                    textField_1.setText("");
                    textField_2.setText("");
                } else {
                    JOptionPane.showMessageDialog(rootPane, "eklenmedi");
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }
	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "güncelle");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			String stok_koduu=stok_kodu.getText();
			
		}
	}
}
