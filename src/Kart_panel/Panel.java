package Kart_panel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;

import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;   
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JInternalFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JFormattedTextField;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JScrollPane;

public class Panel extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField stok_kodu;
	private JTextField stok_adi;
	private JTextField barkod;
	 
	private JTable table;
    Connection conn;
    Statement stm;
    PreparedStatement prm;
    ResultSet rs;
    public void connect() {
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
        
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/guru_stokkart?useSSL=false&serverTimezone=UTC", "root", "24321008350Hk");
        } catch (Exception e) {
            e.printStackTrace();   
        }
    }
    public void LoadPruduct() {
    	try {
    		prm.getConnection().prepareStatement("SELECT * FROM kart");
    		rs=prm.executeQuery();
    		while(rs.next()) {
    			stok_kodu.setText(rs.getString(1));
    		}
    	}
    	catch(Exception e) {
    		
    	}
    }
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Panel frame = new Panel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Panel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 996, 531);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		stok_kodu = new JTextField();
		stok_kodu.setBounds(140, 72, 161, 23);
		contentPane.add(stok_kodu);
		stok_kodu.setColumns(10);
		
		stok_adi = new JTextField();
		stok_adi.setColumns(10);
		stok_adi.setBounds(140, 38, 161, 23);
		contentPane.add(stok_adi);
		
		barkod = new JTextField();
		barkod.setColumns(10);
		barkod.setBounds(140, 104, 161, 23);
		contentPane.add(barkod);
		
		final JTextArea aciklama = new JTextArea();
		aciklama.setBounds(140, 251, 161, 58);
		contentPane.add(aciklama);
		aciklama.setRows(5);
		aciklama.setColumns(10);
		
		final JFormattedTextField tarih = new JFormattedTextField();
		tarih.setBounds(140, 340, 161, 23);
		contentPane.add(tarih);
		
		Integer[] scopestoktipi= {1,2};
		final JComboBox stok_tipi = new JComboBox(scopestoktipi);
		stok_tipi.setBounds(140, 138, 59, 22);
		contentPane.add(stok_tipi);
		
		String []scopebirimi= {"A","B","C"};
		final JComboBox birimi = new JComboBox(scopebirimi);
		birimi.setBounds(140, 171, 59, 22);
		contentPane.add(birimi);
		
		Double []scopekdv= {20.0,18.0};
		final JComboBox kdv = new JComboBox(scopekdv );
		getContentPane().add(kdv);
		kdv.setBounds(140, 204, 59, 22);
		contentPane.add(kdv);
		//BUTTON SİLL-----------------------------------------------
		JButton btkayit = new JButton("kayıt et");
		btkayit.setBounds(10, 424, 102, 34);
		contentPane.add(btkayit);
		btkayit.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent c) {
		    	connect();
		        
		        String stok_kodus = stok_kodu.getText();
		        String stok_adis = stok_adi.getText();
		        String barkodus = barkod.getText();
		        String aciklamas = aciklama.getText();
		        String tarihs = tarih.getText();
		        Double kdvs=(double) kdv.getSelectedIndex();
		        Integer stok_tipis=stok_tipi.getSelectedIndex();
		        String birimis=(String) birimi.getSelectedItem();
		        
		        Connection baglanma = null;
		        PreparedStatement pstmt = null;
		        
		        try {
	                prm = conn.prepareStatement("INSERT INTO kart (stok_kodu, stok_adi,stok_tipi,birimi, barkodu,kdv_tipi,aciklama,olusturma_zamani) VALUES (?,?,?,?, ?, ?,?,?)");
	                prm.setString(1, stok_kodus);
	                prm.setString(2, stok_adis);
	                prm.setInt(3,  stok_tipis);
	                prm.setString(4, birimis);
	                prm.setString(5, barkodus);
	                prm.setDouble(6, kdvs);
	                prm.setString(7, aciklamas);
	                prm.setString(8, tarihs);
	                int k = prm.executeUpdate();
	                if (k == 1) {
	                    JOptionPane.showMessageDialog(rootPane, "eklendi");
	                    stok_kodu.setText("");
	                    stok_adi.setText("");
	                    barkod.setText("");
	                    aciklama.setText("");
	                    tarih.setText("");
	                    
	                    
	                } else {
	                    JOptionPane.showMessageDialog(rootPane, "eklenmedi");
	                }
	            } catch (SQLException e1) {
	            	JOptionPane.showMessageDialog(rootPane, "hata ");
	                e1.printStackTrace();
	            }
		        }
		    }
		);

	 
		
		JButton btsil = new JButton("sil"); 
		btsil.setBounds(150, 424, 102, 34);
		contentPane.add(btsil);
		btsil.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent c) {
		    	
		    	String stok_kodus=stok_kodu.getText();
		    	try {
					prm=conn.prepareStatement("DELETE FROM kart WHERE stok_kodu=?");
					prm.setString(1, stok_kodus);
					int k=prm.executeUpdate();
					if(k==1) {
						JOptionPane.showMessageDialog(rootPane, "silindi");
					}
					else {
						JOptionPane.showMessageDialog(rootPane, "silinme hatası");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	
		    }});
		//BUTTON GÜNCELLE
		JButton btguncelle = new JButton("güncelle");
		btguncelle.setBounds(283, 424, 102, 34);
		contentPane.add(btguncelle);
		btguncelle.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent c) {
                connect();
 
		         
		        
		        try {
		        	String stok_kodug = stok_kodu.getText();
			        String stok_adig = stok_adi.getText();
			        String barkodug = barkod.getText();
			        String aciklamag = aciklama.getText();
			        String tarihg = tarih.getText();
			        Double kdvg=(double) kdv.getSelectedIndex();
			        Integer stok_tipig=stok_tipi.getSelectedIndex();
			        String birimig=(String) birimi.getSelectedItem();
			        
					prm=conn.prepareStatement("UPDATE kart SET    stok_adi=?,stok_tipi=?,birimi=? ,barkodu=?,kdv_tipi=?,aciklama=?,olusturma_zamani=? WHERE stok_kodu=?  ");
				
					   
		                prm.setString(1, stok_adig);
		                prm.setInt(2,  stok_tipig);
		                prm.setString(3, birimig);
		                prm.setString(4, barkodug);
		                prm.setDouble(5, kdvg);
		                prm.setString(6, aciklamag);
		                prm.setString(7, tarihg);
		                prm.setString(8, stok_kodug);
		                
		                int k = prm.executeUpdate();
		                if(k==1) {
		                	JOptionPane.showMessageDialog(rootPane, "güncellendi");
		                	stok_kodu.setText( "");
							stok_adi.setText("");
							stok_tipi.setSelectedIndex(0);
							birimi.setSelectedItem(0);
							barkod.setText("");
							kdv.setSelectedItem(0);
							aciklama.setText("");
							tarih.setText("");
			                LoadPruduct();
		                }
		        
		        } catch (SQLException e) {
		        	JOptionPane.showMessageDialog(rootPane, "güncelleme hatası");
					 
					e.printStackTrace();
				}
		         
		    	//BUTTON BUL
		    	
		    }});JButton btnBul = new JButton("bul"); 
			btnBul.setBounds(422, 424, 102, 34);
			contentPane.add(btnBul);
			btnBul.addActionListener(new ActionListener() {
				 public void actionPerformed(ActionEvent c) {
					 connect();
					 LoadPruduct();
					 
					 String stok_kodub=stok_kodu.getText();
					try {
						prm=conn.prepareStatement("SELECT*FROM kart WHERE stok_kodu=?");
						prm.setString(1, stok_kodub);
						rs=prm.executeQuery();
						
						if(rs.next()==true) {
							stok_kodu.setText(rs.getString(2));
							stok_adi.setText(rs.getString(3));
							stok_tipi.setSelectedIndex((rs.getInt(4)));
							birimi.setSelectedItem(rs.getString(5));
							barkod.setText(rs.getString(6));
							kdv.setSelectedItem(rs.getInt(7));
							aciklama.setText(rs.getString(8));
							tarih.setText(rs.getString(9));
							
						}else {
							JOptionPane.showMessageDialog(rootPane, "kayıt yok");
						}
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}}
			});
		
		JButton btlist = new JButton("llistele");
		btlist.setBounds(561, 424, 102, 34);
		contentPane.add(btlist);
		btlist.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent c) {
		    	connect();
		    	try {
					prm=conn.prepareStatement("SELECT *FROM kart");
					rs=prm.executeQuery();
					ResultSetMetaData meta=rs.getMetaData();
					
					DefaultTableModel model=(DefaultTableModel) table.getModel();
					int colm=meta.getColumnCount();
					String[] colmnames=new String[colm];
					
					if(table.getRowCount()<=0) {
					for(int i=0;i<colm;i++) {
						colmnames[i]=meta.getColumnName(i+1);
						model.setColumnIdentifiers(colmnames);
						String id,stok_kodu,stok_adi,stok_tipi,birimi,barkodu,kdv,aciklama,tarih;
						while(rs.next()) {
							id=rs.getString(1);
							stok_kodu=rs.getString(2);
							stok_adi=rs.getString(3);
							stok_tipi=rs.getString(4);
							birimi=rs.getString(5);
							barkodu=rs.getString(6);
							kdv=rs.getString(7);
							aciklama=rs.getString(8);
							tarih=rs.getString(9);
							String []row= {id,stok_kodu,stok_adi,stok_tipi,birimi,barkodu,kdv,aciklama,tarih};
							model.addRow(row);
						}
						
					}}
					else if(table.getRowCount()>=1){
						table.setModel(new DefaultTableModel());
						 
					}
					
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	
		    	
		    	
		    }});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(374, 25, 582, 382);
		contentPane.add(scrollPane);
		
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
		//labelss----------------------------------------
		JLabel lblNewLabel = new JLabel("stok adı");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(29, 38, 112, 23);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("stok kodu");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(29, 76, 112, 23);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Barkod");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(29, 108, 112, 23);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("stok tipi");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(29, 142, 112, 23);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("birimi");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4.setBounds(29, 175, 112, 23);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("kdv");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_5.setBounds(29, 204, 112, 23);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("açıklama");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_6.setBounds(29, 251, 112, 23);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("tarih");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_7.setBounds(29, 344, 112, 23);
		contentPane.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("liste");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_8.setBounds(628, 0, 112, 23);
		contentPane.add(lblNewLabel_8);
		 
		 
	}
	 
	 
}
