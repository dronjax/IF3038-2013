package com.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import com.custom.CategoryButton;
import com.model.Category;
import com.model.Task;

public class DashboardPanel extends JPanel implements ActionListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Category> list_category;
	private List<CategoryButton> list_category_buttons;
	private List<Task> active_list_task;
	private int active_category_id;
	
	private JPanel panel_category;
	private JPanel panel_task;
	
	private JButton button_refresh;
	
	private boolean first;
	
	public DashboardPanel(String username)
	{
		super(new BorderLayout());
		
		panel_category = new JPanel(new FlowLayout());
		panel_category.setPreferredSize(new Dimension(220, 600));
		panel_category.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredSoftBevelBorder(), 
								"Kategori", TitledBorder.CENTER, TitledBorder.TOP, new Font("Serif", Font.BOLD, 16)));  
		
		panel_task = new JPanel(new FlowLayout());
		panel_task.setPreferredSize(new Dimension(570, 600));
		panel_task.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredSoftBevelBorder(), 
				"Tugas", TitledBorder.CENTER, TitledBorder.TOP, new Font("Serif", Font.BOLD, 16)));  
		
		JLabel label_app_name = new JLabel("Multiuser Online Agenda");
		label_app_name.setFont(new Font("Serif", Font.BOLD, 22));
		JLabel label_hello = new JLabel("Halo, ");
		label_hello.setFont(new Font("Serif", Font.PLAIN, 14));
		JLabel label_username = new JLabel(username);
		label_username.setFont(new Font("Serif", Font.BOLD, 14));

		ImageIcon refresh_icon = new ImageIcon(ClassLoader.getSystemResource("images/refresh.png"));
		button_refresh = new JButton(refresh_icon);
		button_refresh.setPreferredSize(new Dimension(30, 30));
		button_refresh.addActionListener(this);
		
		JPanel panel_top = new JPanel(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.CENTER;
		c.anchor = GridBagConstraints.NORTHEAST;
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.weightx = 1;
		c.insets = new Insets(10, 0, 10, 120);
		panel_top.add(label_app_name, c);
		c.weightx = 0;
		
		c.fill = GridBagConstraints.EAST;
		c.anchor = GridBagConstraints.NORTHEAST;
		c.gridx = 4;
		c.gridy = 2;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(0, 0, 0, 0);
		panel_top.add(label_hello, c);
		
		c.fill = GridBagConstraints.EAST;
		c.anchor = GridBagConstraints.NORTHEAST;
		c.gridx = 5;
		c.gridy = 2;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(0, 0, 0, 25);
		panel_top.add(label_username, c);
		
		c.fill = GridBagConstraints.EAST;
		c.anchor = GridBagConstraints.NORTHEAST;
		c.gridx = 4;
		c.gridy = 3;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weighty = 1;
		c.insets = new Insets(0, 0, 0, 5);
		panel_top.add(button_refresh, c);
		
		add(panel_top, BorderLayout.PAGE_START);
		
		add(panel_category, BorderLayout.WEST);
		add(panel_task, BorderLayout.CENTER);
		
		list_category = new ArrayList<Category>();
		list_category.add(new Category(1, "kategori 1"));
		list_category.add(new Category(2, "kategori 2"));
		list_category.add(new Category(3, "kategori 3"));
		list_category_buttons = new ArrayList<CategoryButton>();
		active_list_task = new ArrayList<Task>();
		active_list_task.add(new Task(1, "tes", true, new Date()));
		active_category_id = 0;
		
		refresh();
	}
	
	public void refresh()
	{
		revalidate();
	}
	
	@Override
	public void revalidate() 
	{
		if (panel_category!=null)
		{
			panel_category.removeAll();
			list_category_buttons.removeAll(list_category_buttons);
			
			CategoryButton button_all = new CategoryButton("Semua", 0);
			button_all.setPreferredSize(new Dimension(200, 40));
			button_all.setFont(new Font("Serif", Font.BOLD, 14));
			button_all.addActionListener(this);
			if (first)
			{
				button_all.setActive(true);
				first = false;
			}
			list_category_buttons.add(button_all);
			panel_category.add(button_all);
			
			for (int i=0;i<list_category.size();++i)
			{
				CategoryButton button = new CategoryButton(list_category.get(i).getNama_kategori(), list_category.get(i).getId_kategori());
				button.setPreferredSize(new Dimension(200, 40));
				button.setFont(new Font("Serif", Font.BOLD, 14));
				button.addActionListener(this);
				list_category_buttons.add(button);
				panel_category.add(button);
			}
			
			int i = 0;
			boolean check = true;
			while ((check) && (i<list_category_buttons.size()))
			{
				if (list_category_buttons.get(i).getId()==active_category_id)
				{
					list_category_buttons.get(i).setActive(true);
					list_category_buttons.get(i).repaint();
				}
				++i;
			}
			
			panel_task.removeAll();
			for (int j=0;j<active_list_task.size();++j)
			{
				JPanel panel = new JPanel();
				
				JLabel judul_task = new JLabel(active_list_task.get(j).getNama_task());
				judul_task.setFont(new Font("Serif", Font.ITALIC, 18));
				panel.add(judul_task);
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
				JLabel deadline_task = new JLabel(sdf.format(active_list_task.get(j).getDeadline()));
				panel.add(deadline_task);
				
				JLabel status = new JLabel((active_list_task.get(j).getStatus())? "selesai" : "belum selesai");
				if (active_list_task.get(j).getStatus())
				{
					status.setForeground(Color.GREEN);
					status.setFont(new Font("Serif", Font.BOLD, 14));
				}
				else
				{
					status.setForeground(Color.RED);
					status.setFont(new Font("Serif", Font.BOLD, 14));
				}
				panel.add(status);
				
				panel_task.add(panel);
			}
		}
		super.revalidate();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		if (button_refresh.equals(arg0.getSource()))
		{
			refresh();
		}
		else
		{
			if (list_category_buttons.get(0).equals(arg0.getSource()))
			{
				active_category_id = list_category_buttons.get(0).getId();
				list_category_buttons.get(0).setActive(true);
				list_category_buttons.get(0).repaint();
				for (int i=1;i<list_category_buttons.size();++i)
				{
					list_category_buttons.get(i).setActive(false);
					list_category_buttons.get(i).repaint();
				}
			}
			else
			{
				list_category_buttons.get(0).setActive(false);
				list_category_buttons.get(0).repaint();
				for (int i=1;i<list_category_buttons.size();++i)
				{
					if (list_category_buttons.get(i).equals(arg0.getSource()))
					{
						list_category_buttons.get(i).setActive(true);
						active_category_id = list_category_buttons.get(i).getId();
					}
					else
					{
						list_category_buttons.get(i).setActive(false);
					}
					list_category_buttons.get(i).repaint();
				}
			}
		}
	}
}