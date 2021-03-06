package com.panels;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.crypto.Cipher;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.account.LoginHistory;
import com.main.MOA_client;

public class LoginPanel extends JPanel implements ActionListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTextField text_username;
	private JPasswordField text_password;
	
	private JButton button_login;
	
	public LoginPanel()
	{
		super(new GridBagLayout());
		
		JLabel label_app_name = new JLabel("Multiuser Online Agenda");
		label_app_name.setFont(new Font("Serif", Font.BOLD, 22));
		JLabel label_username = new JLabel("Username", SwingConstants.LEFT);
		label_username.setFont(new Font("Serif", Font.BOLD, 14));
		JLabel label_password = new JLabel("Sandi", SwingConstants.LEFT);
		label_password.setFont(new Font("Serif", Font.BOLD, 14));
		
		text_username = new JTextField(20);
		text_username.addActionListener(this);
		text_password = new JPasswordField(20);
		text_password.addActionListener(this);
		
		button_login = new JButton("Masuk");
		button_login.setFont(new Font("Serif", Font.BOLD, 14));
		button_login.addActionListener(this);

		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 2;
		c.gridheight = 2;
		c.insets = new Insets(0, 0, 30, 0);
		add(label_app_name, c);
		c.insets = new Insets(5, 3, 5, 3);
		
		c.fill = GridBagConstraints.WEST;
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 1;
		c.gridy = 3;
		c.gridwidth = 1;
		c.gridheight = 1;
		add(label_username, c);
		
		c.fill = GridBagConstraints.WEST;
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 2;
		c.gridy = 3;
		c.gridwidth = 1;
		c.gridheight = 1;
		add(text_username, c);
		
		c.fill = GridBagConstraints.WEST;
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 1;
		c.gridy = 4;
		c.gridwidth = 1;
		c.gridheight = 1;
		add(label_password, c);
		
		c.fill = GridBagConstraints.WEST;
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 2;
		c.gridy = 4;
		c.gridwidth = 1;
		c.gridheight = 1;
		add(text_password, c);
		
		c.fill = GridBagConstraints.BELOW_BASELINE;
		c.anchor = GridBagConstraints.CENTER;
		c.gridx = 1;
		c.gridy = 6;
		c.gridwidth = 2;
		c.gridheight = 2;
		c.insets = new Insets(15, 0, 0, 0);
		add(button_login, c);
		
		revalidate();
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if ((button_login.equals(e.getSource())) || (text_username.equals(e.getSource())) || (text_password.equals(e.getSource())))
		{
			boolean login_success = false;
			
			byte[] username = new byte[0];
			byte[] password = new byte[0];
			try
			{
				Cipher cipher = Cipher.getInstance("RSA");
	            cipher.init(Cipher.ENCRYPT_MODE, MOA_client.public_key);
	            byte[] temp = text_username.getText().getBytes();
        		username = cipher.doFinal(temp);
        		temp = new String(text_password.getPassword()).getBytes();
	            password = cipher.doFinal(temp);
			}
			catch (Exception exc)
			{
				exc.printStackTrace();
			}
			
			login_success = MOA_client.sc.doLogin(username, password);
			
			if (login_success)
			{
				try
				{
					LoginHistory history = new LoginHistory(username, password);
					history.produce_xml();
					
					Container parent = this.getParent();
					parent.removeAll();
					parent.add(new DashboardPanel(history));
					parent.revalidate();
					MOA_client.logged_in = true;
				}
				catch (Exception exc)
				{
					exc.printStackTrace();
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Login gagal", "Gagal", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponents(g);

		Graphics2D g2d = (Graphics2D) g;
		Color color1 = this.getBackground();
		Color color2 = color1.darker();
		
		int width = getWidth();
		int height = getHeight();
		GradientPaint gp = new GradientPaint(0, 0, color1, 0, height, color2);
		g2d.setPaint(gp);
		g2d.fillRect(0, 0, width, height);
	}
}
