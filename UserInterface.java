package lab8;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Window.Type;
import java.awt.Rectangle;
import javax.swing.border.LineBorder;
import java.awt.Dimension;
import java.awt.ComponentOrientation;

public class UserInterface extends TableWorker {

    private JFrame frmLabProg;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UserInterface window = new UserInterface();
                    window.frmLabProg.setVisible(true);
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public UserInterface() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {

        TableWorker tw = new TableWorker();

        frmLabProg = new JFrame();
        frmLabProg.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        frmLabProg.setBounds(new Rectangle(0, 0, 1092, 779));
        frmLabProg.setBackground(new Color(240, 240, 240));
        frmLabProg.setType(Type.POPUP);
        frmLabProg.setTitle("Lab8 prog");
        frmLabProg.setAlwaysOnTop(true);

        JDesktopPane containerWorker = new JDesktopPane();
        containerWorker.setBorder(new LineBorder(new Color(0, 0, 0)));
        containerWorker.setBackground(Color.WHITE);

        JDesktopPane containerGetter = new JDesktopPane();
        containerGetter.setBorder(new LineBorder(new Color(0, 0, 0)));
        containerGetter.setBackground(Color.WHITE);

        JDesktopPane containerTableWorker = new JDesktopPane();
        containerTableWorker.setBorder(new LineBorder(new Color(0, 0, 0)));
        containerTableWorker.setBackground(Color.WHITE);

        containerGetter.setVisible(false);
        containerTableWorker.setVisible(false);

        JLabel lblNewLabel = new JLabel("\u0412\u0432\u0435\u0434\u0438\u0442\u0435 \u0441\u0432\u043E\u0439 id: ");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));

        JTextArea ta1 = new JTextArea();
        ta1.setFont(new Font("Monospaced", Font.PLAIN, 12));
        ta1.setBorder(new LineBorder(new Color(0, 0, 0)));
        ta1.setBackground(Color.WHITE);

        JTextArea ta1_name_surname = new JTextArea();
        ta1_name_surname.setFont(new Font("Times New Roman", Font.PLAIN, 12));

        /**
         * TODO Проверка сотрудника
         */
        JButton btnCheckWorker = new JButton("\u041F\u0440\u0432\u043E\u0435\u0440\u0438\u0442\u044C");
        btnCheckWorker.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	    String ns = tw.workerCheck(ta1.getText());
        	    ta1_name_surname.setText(ns);
        	    if (ns == "Такого номера нет!" || ns == "") {
                    containerGetter.setVisible(false);
                    containerTableWorker.setVisible(false);
                } else {
        	        containerGetter.setVisible(true);
                    containerTableWorker.setVisible(true);
                }
        	}
        });
        GroupLayout gl_containerWorker = new GroupLayout(containerWorker);
        gl_containerWorker.setHorizontalGroup(
        	gl_containerWorker.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_containerWorker.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(lblNewLabel)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(gl_containerWorker.createParallelGroup(Alignment.LEADING)
        				.addComponent(btnCheckWorker)
        				.addGroup(gl_containerWorker.createSequentialGroup()
        					.addComponent(ta1, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(ta1_name_surname, GroupLayout.PREFERRED_SIZE, 324, GroupLayout.PREFERRED_SIZE)))
        			.addContainerGap(451, Short.MAX_VALUE))
        );
        gl_containerWorker.setVerticalGroup(
        	gl_containerWorker.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_containerWorker.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(gl_containerWorker.createParallelGroup(Alignment.TRAILING)
        				.addGroup(gl_containerWorker.createParallelGroup(Alignment.BASELINE)
        					.addComponent(ta1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
        					.addComponent(ta1_name_surname, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
        				.addComponent(lblNewLabel))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(btnCheckWorker)
        			.addContainerGap(31, Short.MAX_VALUE))
        );
        containerWorker.setLayout(gl_containerWorker);

        JLabel lblNewLabel_1 = new JLabel("\u041F\u043E\u043B\u0443\u0447\u0438\u0442\u044C \u0434\u0430\u043D\u043D\u044B\u0435 \u0438\u0437 \u0442\u0430\u0431\u043B\u0438\u0446\u044B");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));

        JLabel lblNewLabel_2 = new JLabel("\u041D\u0430\u0437\u0432\u0430\u043D\u0438\u0435 \u0442\u0430\u0431\u043B\u0438\u0446\u044B:");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));

        JTextArea ta2_tab_name = new JTextArea();
        ta2_tab_name.setFont(new Font("Monospaced", Font.PLAIN, 12));
        ta2_tab_name.setBorder(new LineBorder(new Color(0, 0, 0)));
        ta2_tab_name.setBackground(Color.WHITE);

        /**
         * TODO select *
         */
        JButton btnGetFull = new JButton("\u041F\u043E\u043B\u0443\u0447\u0438\u0442\u044C \u0434\u0430\u043D\u043D\u044B\u0435");
        
        JScrollPane scrollPane = new JScrollPane();
        GroupLayout gl_containerGetter = new GroupLayout(containerGetter);
        gl_containerGetter.setHorizontalGroup(
        	gl_containerGetter.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_containerGetter.createSequentialGroup()
        			.addGap(10)
        			.addGroup(gl_containerGetter.createParallelGroup(Alignment.LEADING)
        				.addComponent(lblNewLabel_1)
        				.addGroup(gl_containerGetter.createSequentialGroup()
        					.addComponent(lblNewLabel_2)
        					.addGap(10)
        					.addComponent(ta2_tab_name, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE))
        				.addGroup(gl_containerGetter.createSequentialGroup()
        					.addGap(125)
        					.addComponent(btnGetFull, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 507, Short.MAX_VALUE)
        			.addGap(245))
        );
        gl_containerGetter.setVerticalGroup(
        	gl_containerGetter.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_containerGetter.createSequentialGroup()
        			.addGap(11)
        			.addGroup(gl_containerGetter.createParallelGroup(Alignment.LEADING)
        				.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
        				.addGroup(gl_containerGetter.createSequentialGroup()
        					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
        					.addGap(11)
        					.addGroup(gl_containerGetter.createParallelGroup(Alignment.LEADING)
        						.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
        						.addComponent(ta2_tab_name, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
        					.addGap(7)
        					.addComponent(btnGetFull)))
        			.addContainerGap())
        );
        
        JTextArea ta2_out = new JTextArea();
        ta2_out.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        scrollPane.setViewportView(ta2_out);
        containerGetter.setLayout(gl_containerGetter);

		/**
		 * TODO вся информация из таблицы
		 */
		btnGetFull.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String textFromTA = ta2_tab_name.getText();
				if (textFromTA.isEmpty()) {
					ta2_out.setText("Введите название таблицы");
				} else {
					String txt = tw.informationOut(textFromTA);
					ta2_out.setText(txt);
				}
			}
		});

        JLabel lblNewLabel_3 = new JLabel("\u0410\u0440\u0445\u0438\u0432\u0438\u0440\u043E\u0432\u0430\u043D\u0438\u0435");
        lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
        
        JLabel lblNewLabel_5 = new JLabel("\u041D\u0430\u0437\u0432\u0430\u043D\u0438\u0435 \u0442\u0430\u0431\u043B\u0438\u0446\u044B:");
        lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
        
        JTextArea ta3_tab_name = new JTextArea();
        ta3_tab_name.setFont(new Font("Monospaced", Font.PLAIN, 12));
        ta3_tab_name.setBorder(new LineBorder(new Color(0, 0, 0)));
        ta3_tab_name.setBackground(Color.WHITE);
        
        JLabel lblNewLabel_5_1 = new JLabel("\u041D\u043E\u043C\u0435\u0440 \u043F\u043B\u0430\u043D\u0430:");
        lblNewLabel_5_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        
        JTextArea ta3_id_plan = new JTextArea();
        ta3_id_plan.setFont(new Font("Monospaced", Font.PLAIN, 12));
        ta3_id_plan.setBorder(new LineBorder(new Color(0, 0, 0)));
        ta3_id_plan.setBackground(Color.WHITE);
        
        JTextArea ta3_text = new JTextArea();
        ta3_text.setMinimumSize(new Dimension(1, 16));
        ta3_text.setDisabledTextColor(new Color(109, 109, 109));
        ta3_text.setBorder(new LineBorder(new Color(0, 0, 0)));
        ta3_text.setBackground(Color.WHITE);
        ta3_text.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        
        JLabel lblNewLabel_4 = new JLabel("\u0422\u0435\u043A\u0441\u0442 \u043F\u0440\u0438\u043A\u0430\u0437\u0430");
        lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));

        JButton btnArchive = new JButton("\u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C \u0432 \u0430\u0440\u0445\u0438\u0432");
        
        JScrollPane scrollPane_1 = new JScrollPane();
        
        JLabel lblNewLabel_6 = new JLabel("\u0421\u0442\u0430\u0442\u0443\u0441");
        lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_6.setHorizontalTextPosition(SwingConstants.CENTER);
        lblNewLabel_6.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        GroupLayout gl_containerTableWorker = new GroupLayout(containerTableWorker);
        gl_containerTableWorker.setHorizontalGroup(
        	gl_containerTableWorker.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_containerTableWorker.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(gl_containerTableWorker.createParallelGroup(Alignment.LEADING)
        				.addComponent(ta3_text, GroupLayout.DEFAULT_SIZE, 1034, Short.MAX_VALUE)
        				.addGroup(gl_containerTableWorker.createSequentialGroup()
        					.addComponent(lblNewLabel_3)
        					.addPreferredGap(ComponentPlacement.RELATED, 839, Short.MAX_VALUE)
        					.addComponent(lblNewLabel_6, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))
        				.addGroup(gl_containerTableWorker.createSequentialGroup()
        					.addGroup(gl_containerTableWorker.createParallelGroup(Alignment.LEADING)
        						.addGroup(gl_containerTableWorker.createSequentialGroup()
        							.addGroup(gl_containerTableWorker.createParallelGroup(Alignment.LEADING)
        								.addGroup(gl_containerTableWorker.createSequentialGroup()
        									.addComponent(lblNewLabel_5)
        									.addPreferredGap(ComponentPlacement.UNRELATED)
        									.addComponent(ta3_tab_name, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE))
        								.addGroup(gl_containerTableWorker.createSequentialGroup()
        									.addComponent(lblNewLabel_5_1, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
        									.addPreferredGap(ComponentPlacement.UNRELATED)
        									.addComponent(ta3_id_plan, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)))
        							.addGap(54)
        							.addComponent(btnArchive, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE))
        						.addComponent(lblNewLabel_4))
        					.addPreferredGap(ComponentPlacement.RELATED, 181, Short.MAX_VALUE)
        					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 371, GroupLayout.PREFERRED_SIZE)))
        			.addContainerGap())
        );
        gl_containerTableWorker.setVerticalGroup(
        	gl_containerTableWorker.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_containerTableWorker.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(gl_containerTableWorker.createParallelGroup(Alignment.TRAILING, false)
        				.addGroup(gl_containerTableWorker.createSequentialGroup()
        					.addComponent(lblNewLabel_3)
        					.addGap(29)
        					.addGroup(gl_containerTableWorker.createParallelGroup(Alignment.LEADING, false)
        						.addGroup(gl_containerTableWorker.createSequentialGroup()
        							.addGroup(gl_containerTableWorker.createParallelGroup(Alignment.TRAILING, false)
        								.addComponent(ta3_tab_name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        								.addComponent(lblNewLabel_5, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addGroup(gl_containerTableWorker.createParallelGroup(Alignment.LEADING)
        								.addComponent(ta3_id_plan, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
        								.addComponent(lblNewLabel_5_1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)))
        						.addComponent(btnArchive, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        					.addGap(18)
        					.addComponent(lblNewLabel_4)
        					.addGap(3))
        				.addGroup(gl_containerTableWorker.createSequentialGroup()
        					.addComponent(lblNewLabel_6, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)))
        			.addComponent(ta3_text, GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
        			.addContainerGap())
        );
        
        JTextArea errorLog = new JTextArea();
        errorLog.setFont(new Font("Monospaced", Font.PLAIN, 12));
        scrollPane_1.setViewportView(errorLog);
        containerTableWorker.setLayout(gl_containerTableWorker);
        GroupLayout groupLayout = new GroupLayout(frmLabProg.getContentPane());
        groupLayout.setHorizontalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
        			.addGap(10)
        			.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
        				.addComponent(containerWorker, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1056, Short.MAX_VALUE)
        				.addComponent(containerGetter, Alignment.LEADING)
        				.addComponent(containerTableWorker, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1056, Short.MAX_VALUE))
        			.addContainerGap())
        );
        groupLayout.setVerticalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(containerWorker, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
        			.addGap(11)
        			.addComponent(containerGetter, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
        			.addGap(11)
        			.addComponent(containerTableWorker, GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
        			.addContainerGap())
        );
        frmLabProg.getContentPane().setLayout(groupLayout);

		/**
		 * TODO архивирование
		 */
		btnArchive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String planId = ta3_id_plan.getText();
				String text = ta3_text.getText();
				String tableName = ta3_tab_name.getText();
				if (planId.isEmpty() || text.isEmpty() || tableName.isEmpty()) {
					errorLog.setText("Присутствуют незаполненные поля");
				} else if (!tableName.equalsIgnoreCase("objPSklad") && !tableName.equalsIgnoreCase("objPSotrud")) {
					errorLog.append("\nВведена неверная таблица. Ожидалось objPSklad или objPSotrud");
				} else {
					tw.archiveInformation(text, planId, ta1.getText(), tableName);
					errorLog.setText("Успевно архивировано!");
				}
			}
		});
    }
}
