package databaseProject;

import java.io.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import javax.swing.table.*;

class Title extends JFrame {
	SQL sql = new SQL();
	JButton button[] = new JButton[2];
	JTextField memberid = new JTextField(2);

	public Title() {
		Container c1 = getContentPane();
		c1.setLayout(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(2000, 2000);
		setTitle("18011560/���μ�, 18011480/������");

		button[0] = new JButton("������");
		button[0].setSize(300, 300);
		button[0].setLocation(700, 300);
		c1.add(button[0]);

		button[1] = new JButton("ȸ��");
		button[1].setSize(300, 300);
		button[1].setLocation(1000, 300);
		c1.add(button[1]);

		JLabel memberidlabel = new JLabel("ȸ�����̵�");
		memberidlabel.setHorizontalAlignment(JLabel.CENTER);
		memberidlabel.setLocation(1000, 200);
		memberidlabel.setSize(100, 50);
		c1.add(memberidlabel);

		memberid.setText("1");
		memberid.setLocation(1100, 200);
		memberid.setSize(50, 50);
		c1.add(memberid);

		buttonFunction();

		setVisible(true);
	}

	public void buttonFunction() {
		button[0].addActionListener(event -> {
			new Manager(sql);
			setVisible(false);
		});

		button[1].addActionListener(event -> {
			new Member(sql, memberid.getText());
			setVisible(false);
		});
	}
}

class Manager extends JFrame {
	JButton button[] = new JButton[3];
	SQL sql;
	JTextField jt2 = new JTextField(1000);
	JTextField jt3 = new JTextField(1000);
	JLabel error = new JLabel();
	JTextArea result = new JTextArea();
	JScrollPane scrollPane = new JScrollPane(result);
	String modelist[] = { "�Է�", "����", "����" };
	JComboBox<String> mode = new JComboBox<String>(modelist);
	String tablelist[] = { "movies", "movie_schedule", "screens", "tickets", "seats", "members", "booking" };
	JComboBox<String> table = new JComboBox<String>(tablelist);
	JComboBox<String> attribute = new JComboBox<String>();
	Container c1 = getContentPane();

	public Manager(SQL _sql) {
		sql = _sql;
		c1.setLayout(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(2000, 2000);
		setTitle("18011560/���μ�");

		button[0] = new JButton("�ʱ�ȭ");
		button[0].setSize(200, 100);
		button[0].setLocation(0, 0);
		c1.add(button[0]);

		button[1] = new JButton("�Է�/����/����");
		button[1].setSize(200, 100);
		button[1].setLocation(750, 120);
		c1.add(button[1]);

		button[2] = new JButton("��ü ���̺� ����");
		button[2].setSize(200, 100);
		button[2].setLocation(200, 0);
		c1.add(button[2]);

		JLabel label = new JLabel("���ǽ�");
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setVerticalAlignment(JLabel.CENTER);
		label.setSize(100, 10);
		label.setLocation(250, 180);
		c1.add(label);
		
		JLabel label1 = new JLabel("������ ��");
		label1.setHorizontalAlignment(JLabel.CENTER);
		label1.setVerticalAlignment(JLabel.CENTER);
		label1.setSize(100, 10);
		label1.setLocation(250, 130);
		c1.add(label1);

		jt2.setLocation(350, 180);
		jt2.setSize(400, 50);
		c1.add(jt2);
		
		jt3.setLocation(350, 130);
		jt3.setSize(400, 50);
		c1.add(jt3);

		error.setSize(300, 50);
		error.setLocation(0, 250);
		c1.add(error);

		scrollPane.setSize(1700, 500);
		scrollPane.setLocation(0, 400);
		c1.add(scrollPane);

		mode.setSize(150, 50);
		mode.setLocation(100, 100);
		c1.add(mode);

		table.setSize(150, 50);
		table.setLocation(100, 150);
		AttributeShow();
		c1.add(table);

		String attributelist[] = new String[] { "movie_id", "movie_title", "running_time", "movie_rating", "director",
				"actor", "genre", "movie_introduction", "release_date" };
		attribute = new JComboBox<String>(attributelist);
		attribute.setSize(150, 50);
		attribute.setLocation(100, 200);
		c1.add(attribute);

		buttonFunction();

		setVisible(true);
	}

	public void buttonFunction() {
		button[0].addActionListener(event -> {
			result.setText(null);
			error.setText(null);

			sql.Initialize();
		});

		button[1].addActionListener(event -> {
			result.setText(null);
			error.setText(null);

			String query = "";

			switch (mode.getSelectedItem().toString()) {
			case "�Է�":
				query = jt2.getText();
				break;
			case "����":
				query = "update " + table.getSelectedItem().toString() + " set " + attribute.getSelectedItem().toString() + " = " + jt3.getText() + " where " + jt2.getText() + ";";
				System.out.println(query);
				break;
			case "����":
				query = "delete from " + table.getSelectedItem().toString() + " where " + jt2.getText() + ";";
				break;
			default:
				break;
			}
			
			sql.Controll(error, query);
			
		});

		button[2].addActionListener(event -> {
			result.setText(null);
			error.setText(null);

			sql.AllSearch(result);
		});
	}

	public void AttributeShow() {
		table.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String temp = table.getSelectedItem().toString();
				String attributelist[] = null;

				switch (temp) {
				case "movies":
					attributelist = new String[] { "movie_id", "movie_title", "running_time", "movie_rating",
							"director", "actor", "genre", "movie_introduction", "release_date" };
					break;
				case "movie_schedule":
					attributelist = new String[] { "movie_schedule_id", "movie_id", "screen_id", "screening_start_date",
							"screening_day", "screening_round", "screening_start_time" };
					break;
				case "screens":
					attributelist = new String[] { "screen_id", "seats", "is_available" };
					break;
				case "seats":
					attributelist = new String[] { "seat_id", "screen_id", "is_available" };
					break;
				case "tickets":
					attributelist = new String[] { "ticket_id", "movie_schedule_id", "screen_id", "seat_id",
							"booking_id", "is_ticket_printed", "standard_price", "selling_price" };
					break;
				case "booking":
					attributelist = new String[] { "booking_id", "pay_method", "pay_statement", "price", "member_id" };
					break;
				case "members":
					attributelist = new String[] { "member_id", "name", "phone", "email" };
					break;
				default:
					break;
				}

				c1.remove(attribute);
				attribute = new JComboBox<String>(attributelist);
				attribute.setSize(150, 50);
				attribute.setLocation(100, 200);
				c1.add(attribute);
				c1.revalidate();
				c1.repaint();
			}
		});
	}
}

class Member extends JFrame {
	JButton button[] = new JButton[9];
	SQL sql;
	JTable total = new JTable();
	boolean bookingtableopen = false;
	JScrollPane scrollPane = new JScrollPane(total);
	JTextField movieName = new JTextField(20);
	JTextField directorName = new JTextField(20);
	JTextField actorName = new JTextField(20);
	JTextField genre = new JTextField(20);
	Container c1 = getContentPane();
	JLabel error = new JLabel();
	int tableSelectRow, extratableSelectRow;
	String memberid;
	DefaultTableModel extramodel = new DefaultTableModel();
	JTable extra = new JTable();
	JScrollPane extrascrollpane = new JScrollPane(extra);

	public Member(SQL _sql, String _memberid) {
		sql = _sql;
		memberid = _memberid;
		c1.setLayout(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(2000, 2000);
		setTitle("18011560/���μ�");

		button[0] = new JButton("��ȭ ��ȸ");
		button[0].setSize(200, 100);
		button[0].setLocation(0, 0);
		c1.add(button[0]);

		button[1] = new JButton("����");
		button[1].setSize(200, 100);
		button[1].setLocation(200, 0);
		c1.add(button[1]);

		button[2] = new JButton("���� ���� ��ȸ1");
		button[2].setSize(200, 100);
		button[2].setLocation(400, 0);
		c1.add(button[2]);

		button[3] = new JButton("���� ���� ��ȸ2");
		button[3].setSize(200, 100);
		button[3].setLocation(600, 0);
		c1.add(button[3]);

		button[4] = new JButton("���� ����");
		button[4].setSize(200, 100);
		button[4].setLocation(800, 0);
		c1.add(button[4]);

		button[5] = new JButton("�ٸ� ��ȭ ��ȸ");
		button[5].setSize(200, 100);
		button[5].setLocation(1000, 0);
		c1.add(button[5]);

		button[6] = new JButton("�ٸ� �� ���� ��ȸ");
		button[6].setSize(200, 100);
		button[6].setLocation(1200, 0);
		c1.add(button[6]);

		button[7] = new JButton("��ȭ ����");
		button[7].setSize(200, 100);
		button[7].setLocation(1400, 0);
		c1.add(button[7]);
		
		button[8] = new JButton("�� ���� ����");
		button[8].setSize(200, 100);
		button[8].setLocation(1600, 0);
		c1.add(button[8]);

		JLabel label1 = new JLabel("��ȭ��");
		label1.setHorizontalAlignment(JLabel.CENTER);
		label1.setSize(50, 50);
		label1.setLocation(0, 100);
		c1.add(label1);
		movieName.setSize(150, 30);
		movieName.setLocation(50, 110);
		c1.add(movieName);

		JLabel label2 = new JLabel("������");
		label2.setHorizontalAlignment(JLabel.CENTER);
		label2.setSize(50, 50);
		label2.setLocation(200, 100);
		c1.add(label2);
		directorName.setSize(150, 30);
		directorName.setLocation(250, 110);
		c1.add(directorName);

		JLabel label3 = new JLabel("����");
		label3.setHorizontalAlignment(JLabel.CENTER);
		label3.setSize(50, 50);
		label3.setLocation(400, 100);
		c1.add(label3);
		actorName.setSize(150, 30);
		actorName.setLocation(450, 110);
		c1.add(actorName);

		JLabel label4 = new JLabel("�帣");
		label4.setHorizontalAlignment(JLabel.CENTER);
		label4.setSize(50, 50);
		label4.setLocation(600, 100);
		c1.add(label4);
		genre.setSize(150, 30);
		genre.setLocation(650, 110);
		c1.add(genre);

		error.setHorizontalAlignment(JLabel.CENTER);
		error.setSize(300, 50);
		error.setLocation(800, 100);
		c1.add(error);

		scrollPane.setSize(1700, 400);
		scrollPane.setLocation(0, 150);
		c1.add(scrollPane);

		extrascrollpane.setSize(1000, 200);
		extrascrollpane.setLocation(0, 600);
		c1.add(extrascrollpane);

		buttonFunction();
		TableListener();

		setVisible(true);
	}

	public void buttonFunction() {
		button[0].addActionListener(event -> {
			error.setText(null);
			bookingtableopen = false;

			MovieInquery(total);
		});

		button[1].addActionListener(event -> {
			error.setText("");
			if (total.getValueAt(tableSelectRow, 10).equals("1")) {
				String query;

				query = "insert into booking (pay_method, pay_statement, price, member_id, pay_date) values (0, 1, '13,000��', "
						+ memberid + ", '2021-01-01');";
				sql.ExcecuteUpdateQuery(query, error);

				int lastinsertid = sql.GetSQLInt("select last_insert_id() as booking_id;");

				int moviescheduleid = sql.GetSQLInt("select movie_schedule_id\r\n" + "from movie_schedule \r\n"
						+ "where screen_id = " + total.getValueAt(tableSelectRow, 8) + " and screening_start_date = '"
						+ total.getValueAt(tableSelectRow, 6) + "' and screening_start_time =  '"
						+ total.getValueAt(tableSelectRow, 7) + "';");

				query = "insert into tickets (movie_schedule_id, screen_id, seat_id, booking_id, is_ticket_printed, standard_price, selling_price)\r\n"
						+ "values (" + moviescheduleid + ", " + total.getValueAt(tableSelectRow, 8) + ", "
						+ total.getValueAt(tableSelectRow, 9) + ", " + lastinsertid + ", 0, '15,000��', '13,000��');";
				sql.ExcecuteUpdateQuery(query, error);

				query = "update seats set is_available = 0 where seat_id = " + total.getValueAt(tableSelectRow, 9)
						+ ";";
				if (error.getText().equals(""))
					sql.ExcecuteUpdateQuery(query, error);

				query = "update seats set is_available = 0 where seat_id = " + total.getValueAt(tableSelectRow, 9)
						+ ";";
				if (error.getText().equals(""))
					sql.ExcecuteUpdateQuery(query, error);

				MovieInquery(total);
			}
			else
				error.setText("�߸� �Է��߽��ϴ�.");
		});

		button[2].addActionListener(event -> {
			error.setText("");

			BookingShow();
		});
		
		button[3].addActionListener(event -> {
			error.setText("");

			BookingShow2();
		});

		button[4].addActionListener(event -> {
			error.setText("");

			String query = "delete from booking where booking_id in (select booking_id from tickets where seat_id = "
					+ total.getValueAt(tableSelectRow, 12) + ");";
			sql.ExcecuteUpdateQuery(query, error);

			query = "update seats set is_available = 1 where seat_id = " + total.getValueAt(tableSelectRow, 12);
			if(error.getText().equals(""))
				sql.ExcecuteUpdateQuery(query, error);

			BookingShow2();
		});

		button[5].addActionListener(event -> {
			error.setText("");
			
			String query = "select m.movie_title, m.running_time, m.movie_rating, m.director, m.actor, \r\n"
					+ "		m.genre, ms.screening_start_date, ms.screening_start_time, sc.screen_id, s.seat_id\r\n"
					+ "from movies as m\r\n" + "left join movie_schedule as ms\r\n" + "on m.movie_id = ms.movie_id\r\n"
					+ "left join screens as sc\r\n" + "on ms.screen_id = sc.screen_id and sc.is_available = 1\r\n"
					+ "left join seats as s\r\n" + "on sc.screen_id = s.screen_id and s.is_available = 1\r\n"
					+ "where ms.movie_id not in (select movie_id from movie_schedule where movie_schedule_id in "
					+ "(select movie_schedule_id from tickets where seat_id = " + total.getValueAt(tableSelectRow, 12) + ")) and s.is_available = 1 and sc.is_available = 1;";
			sql.MovieSearch(extra, error, query);
		});

		button[6].addActionListener(event -> {
			error.setText("");

			String query = "select ms.screening_start_date, ms.screening_day, ms.screening_round, ms.screening_start_time, ms.screen_id, se.seat_id\r\n"
					+ "from movie_schedule as ms\r\n" + "left join screens as s\r\n"
					+ "on s.is_available = 1 and ms.screen_id = s.screen_id\r\n" + "left join seats as se\r\n"
					+ "on s.screen_id = se.screen_id\r\n"
					+ "where ms.movie_schedule_id not in (select movie_schedule_id \r\n"
					+ "									from tickets \r\n"
					+ "                                    where seat_id = 1) \r\n"
					+ "		and movie_id in (select movie_id \r\n"
					+ "						from movie_schedule \r\n"
					+ "                        where movie_schedule_id in "
					+ "(select movie_schedule_id from tickets where seat_id = " + total.getValueAt(tableSelectRow, 12) + ")) and s.is_available = 1 and se.is_available = 1; ";
			sql.ExtraMoviescheduleShow(extra, query);

		});

		button[7].addActionListener(event -> {
			error.setText("");
			
			int moviescheduleid = sql.GetSQLInt("select movie_schedule_id\r\n" + "from movie_schedule \r\n"
					+ "where screen_id = " + extra.getValueAt(extratableSelectRow, 8) + " and screening_start_date = '"
					+ extra.getValueAt(extratableSelectRow, 6) + "' and screening_start_time =  '"
					+ extra.getValueAt(extratableSelectRow, 7) + "';");

			String query = "update tickets set movie_schedule_id = " + moviescheduleid + ", screen_id = "
					+ extra.getValueAt(extratableSelectRow, 8) + ", seat_id = "
					+ extra.getValueAt(extratableSelectRow, 9)
					+ " where booking_id in (select booking_id from (select a.booking_id \r\n"
					+ "									from tickets a where seat_id = "
					+ total.getValueAt(tableSelectRow, 12) + ") tmp);";
			sql.ExcecuteUpdateQuery(query, error);
			
			query = "update booking set pay_date = '2021-01-01' \r\n"
					+ " where booking_id in (select booking_id from (select a.booking_id \r\n"
					+ "									from tickets a where seat_id = "
					+ extra.getValueAt(extratableSelectRow, 9) + ") tmp);";
			if(error.getText().equals(""))
				sql.ExcecuteUpdateQuery(query, error);

			query = "update seats set is_available = 0 where seat_id = " + extra.getValueAt(extratableSelectRow, 9)
					+ ";";
			if(error.getText().equals(""))
				sql.ExcecuteUpdateQuery(query, error);

			query = "update seats set is_available = 1 where seat_id = " + total.getValueAt(tableSelectRow, 12) + ";";
			if(error.getText().equals(""))
				sql.ExcecuteUpdateQuery(query, error);

			BookingShow2();
		});

		button[8].addActionListener(event -> {
			error.setText("");

			int moviescheduleid = sql.GetSQLInt("select movie_schedule_id\r\n" + "from movie_schedule \r\n"
					+ "where screen_id = " + extra.getValueAt(extratableSelectRow, 4) + " and screening_start_date = '"
					+ extra.getValueAt(extratableSelectRow, 0) + "' and screening_start_time =  '"
					+ extra.getValueAt(extratableSelectRow, 3) + "';");

			String query = "update tickets set movie_schedule_id = " + moviescheduleid + ", screen_id = "
					+ extra.getValueAt(extratableSelectRow, 4) + ", seat_id = "
					+ extra.getValueAt(extratableSelectRow, 5)
					+ " where booking_id in (select booking_id from (select a.booking_id \r\n"
					+ "									from tickets a where seat_id = "
					+ total.getValueAt(tableSelectRow, 12) + ") tmp);";
			sql.ExcecuteUpdateQuery(query, error);
			
			query = "update booking set pay_date = '2021-01-01' \r\n"
					+ " where booking_id in (select booking_id from (select a.booking_id \r\n"
					+ "									from tickets a where seat_id = "
					+ extra.getValueAt(extratableSelectRow, 5) + ") tmp);";
			if(error.getText().equals(""))
				sql.ExcecuteUpdateQuery(query, error);

			query = "update seats set is_available = 0 where seat_id = " + extra.getValueAt(extratableSelectRow, 5) + ";";
			if(error.getText().equals(""))
				sql.ExcecuteUpdateQuery(query, error);

			query = "update seats set is_available = 1 where seat_id = " + total.getValueAt(tableSelectRow, 12) + ";";
			if(error.getText().equals(""))
				sql.ExcecuteUpdateQuery(query, error);

			BookingShow2();
		});
	}

	public void TableListener() {
		total.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				tableSelectRow = total.getSelectedRow();

				String query = "select ms.screening_start_date, ms.screening_day, ms.screening_round, ms.screening_start_time,\r\n"
						+ "t.screen_id, t.seat_id, sc.seats, t.is_ticket_printed, t.standard_price, t.selling_price\r\n"
						+ "from tickets as t \r\n" + "left join movie_schedule as ms\r\n"
						+ "on t.movie_schedule_id = ms.movie_schedule_id \r\n" + "left join screens as sc\r\n"
						+ "on t.screen_id = sc.screen_id\r\n"
						+ "where t.booking_id in (select booking_id from tickets\r\n"
						+ "									where screen_id = " + total.getValueAt(tableSelectRow, 2)
						+ " 								and seat_id = " + total.getValueAt(tableSelectRow, 3) + ");";
				if (bookingtableopen)
					sql.BookingClickShow(total, query);

				bookingtableopen = false;
			}
		});

		extra.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				extratableSelectRow = extra.getSelectedRow();
			}
		});
	}
	
	public int GetMoviescheduleid() {
		return sql.GetSQLInt("select movie_schedule_id\r\n" + "from movie_schedule \r\n"
				+ "where screen_id = " + total.getValueAt(tableSelectRow, 11) + " and screening_start_date = '"
				+ total.getValueAt(tableSelectRow, 9) + "' and screening_start_time =  '"
				+ total.getValueAt(tableSelectRow, 10) + "';");
	}

	public void MovieInquery(JTable table) {
		String where[] = { "", "", "", "" };
		String query = "select m.movie_title, m.running_time, m.movie_rating, m.director, m.actor, m.genre,\r\n"
				+ "		ms.screening_start_date, ms.screening_start_time, sc.screen_id, s.seat_id, s.is_available as ��밡��\r\n"
				+ "from movies as m\r\n" + "left join movie_schedule as ms\r\n" + "on m.movie_id = ms.movie_id\r\n"
				+ "left join screens as sc\r\n" + "on ms.screen_id = sc.screen_id \r\n" + "left join seats as s\r\n"
				+ "on ms.screen_id = s.screen_id\r\n";
		int f = 0;

		where[0] = movieName.getText();
		where[1] = directorName.getText();
		where[2] = actorName.getText();
		where[3] = genre.getText();

		if (!where[0].equals("")) {
			query = query + " and " + "movie_title = '" + where[0] + "'";
			f = 1;
		}
		if (!where[1].equals("")) {
			if (f == 1)
				query = query + " and ";
			query = query + "director = '" + where[1] + "'";
			f = 1;
		}
		if (!where[2].equals("")) {
			if (f == 1)
				query = query + " and ";
			query = query + "actor = '" + where[2] + "'";
			f = 1;
		}
		if (!where[3].equals("")) {
			if (f == 1)
				query = query + " and ";
			query = query + "genre = '" + where[3] + "'";
		}

		sql.MovieSearch2(table, error, query);
	}

	public void BookingShow() {
		bookingtableopen = true;

		String query = "select m.movie_title, ms.screening_start_date, t.screen_id, t.seat_id, b.price\r\n"
				+ "from booking as b\r\n" + "left join tickets as t\r\n" + "on b.booking_id = t.booking_id\r\n"
				+ "left join movie_schedule as ms\r\n" + "on ms.movie_schedule_id = t.movie_schedule_id\r\n"
				+ "left join movies as m\r\n" + "on m.movie_id = ms.movie_id\r\n" + "where member_id = " + memberid
				+ ";";

		sql.BookingSearch(total, query);
	}
	
	public void BookingShow2() {
		bookingtableopen = false;

		String query = "select b.*,\r\n"
				+ "		t.is_ticket_printed, t.standard_price, t.selling_price,\r\n"
				+ "		ms.screening_start_date, ms.screening_start_time, t.screen_id, t.seat_id,	\r\n"
				+ "		m.movie_title\r\n"
				+ "from booking as b\r\n" + "left join tickets as t\r\n" + "on b.booking_id = t.booking_id\r\n"
				+ "left join movie_schedule as ms\r\n" + "on ms.movie_schedule_id = t.movie_schedule_id\r\n"
				+ "left join movies as m\r\n" + "on m.movie_id = ms.movie_id\r\n" + "where member_id = " + memberid
				+ ";";
		sql.BookingSearch2(total, query);
	}
}

class SQL {
	Connection con;

	public SQL() {
		String Driver = "";
		String url = "jdbc:mysql://localhost:3306/hospital?&serverTimezone=Asia/Seoul";
		String userid = "madang";
		String pwd = "madang";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("����̹� �ε� ����");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			System.out.println("�����ͺ��̽� ���� �غ�...");
			con = DriverManager.getConnection(url, userid, pwd);
			System.out.println("�����ͺ��̽� ���� ����");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void Initialize() {
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("drop table if exists tickets;");
			stmt.executeUpdate("drop table if exists movie_schedule;");
			stmt.executeUpdate("drop table if exists seats;");
			stmt.executeUpdate("drop table if exists booking;");
			stmt.executeUpdate("drop table if exists screens;");
			stmt.executeUpdate("drop table if exists members;");
			stmt.executeUpdate("drop table if exists movies;");

			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS movies (\r\n"
					+ "  `movie_id` INT NOT NULL AUTO_INCREMENT,\r\n" + "  `movie_title` VARCHAR(45) NOT NULL,\r\n"
					+ "  `running_time` VARCHAR(45) NOT NULL,\r\n" + "  `movie_rating` VARCHAR(45) NOT NULL,\r\n"
					+ "  `director` VARCHAR(45) NOT NULL,\r\n" + "  `actor` VARCHAR(45) NOT NULL,\r\n"
					+ "  `genre` VARCHAR(45) NOT NULL,\r\n" + "  `movie_introduction` TEXT NOT NULL,\r\n"
					+ "  `release_date` DATE NOT NULL,\r\n" + "  PRIMARY KEY (`movie_id`))\r\n" + "ENGINE = InnoDB;");
			stmt.executeUpdate(
					"CREATE TABLE IF NOT EXISTS screens (\r\n" + "  `screen_id` INT NOT NULL AUTO_INCREMENT,\r\n"
							+ "  `seats` INT NOT NULL,\r\n" + "  `is_available` TINYINT NOT NULL DEFAULT 0,\r\n"
							+ "  PRIMARY KEY (`screen_id`))\r\n" + "ENGINE = InnoDB;");
			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS movie_schedule (\r\n"
					+ "  `movie_schedule_id` INT NOT NULL AUTO_INCREMENT,\r\n" + "  `movie_id` INT NOT NULL,\r\n"
					+ "  `screen_id` INT NOT NULL,\r\n" + "  `screening_start_date` DATE NOT NULL,\r\n"
					+ "  `screening_day` VARCHAR(45) NOT NULL,\r\n" + "  `screening_round` VARCHAR(45) NOT NULL,\r\n"
					+ "  `screening_start_time` VARCHAR(45) NOT NULL,\r\n"
					+ "  PRIMARY KEY (`movie_schedule_id`, `screen_id`, `movie_id`),\r\n"
					+ "  INDEX `fk_movie_schedule_movies1_idx` (`movie_id` ASC) VISIBLE,\r\n"
					+ "  INDEX `fk_movie_schedule_screens1_idx` (`screen_id` ASC) VISIBLE,\r\n"
					+ "  CONSTRAINT `fk_movie_schedule_movies1`\r\n" + "    FOREIGN KEY (`movie_id`)\r\n"
					+ "    REFERENCES `movies` (`movie_id`)\r\n" + "    ON DELETE CASCADE\r\n"
					+ "    ON UPDATE CASCADE,\r\n" + "  CONSTRAINT `fk_movie_schedule_screens1`\r\n"
					+ "    FOREIGN KEY (`screen_id`)\r\n" + "    REFERENCES `screens` (`screen_id`)\r\n"
					+ "    ON DELETE CASCADE\r\n" + "    ON UPDATE CASCADE)\r\n" + "ENGINE = InnoDB;");
			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS seats (\r\n" + "  `seat_id` INT NOT NULL AUTO_INCREMENT,\r\n"
					+ "  `screen_id` INT NOT NULL,\r\n" + "  `is_available` TINYINT NOT NULL DEFAULT 0,\r\n"
					+ "  PRIMARY KEY (`seat_id`, `screen_id`),\r\n"
					+ "  INDEX `fk_seats_screens1_idx` (`screen_id` ASC) VISIBLE,\r\n"
					+ "  CONSTRAINT `fk_seats_screens1`\r\n" + "    FOREIGN KEY (`screen_id`)\r\n"
					+ "    REFERENCES `screens` (`screen_id`)\r\n" + "    ON DELETE CASCADE\r\n"
					+ "    ON UPDATE CASCADE)\r\n" + "ENGINE = InnoDB;");
			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS members (\r\n"
					+ "  `member_id` INT NOT NULL AUTO_INCREMENT,\r\n" + "  `name` VARCHAR(45) NOT NULL,\r\n"
					+ "  `phone` VARCHAR(45) NULL,\r\n" + "  `email` VARCHAR(45) NULL,\r\n"
					+ "  PRIMARY KEY (`member_id`))\r\n" + "ENGINE = InnoDB;");
			stmt.executeUpdate(
					"CREATE TABLE IF NOT EXISTS booking (\r\n" + "  `booking_id` INT NOT NULL AUTO_INCREMENT,\r\n"
							+ "  `pay_method` VARCHAR(45) NOT NULL,\r\n" + "  `pay_statement` TINYINT NOT NULL,\r\n"
							+ "  `price` VARCHAR(45) NOT NULL,\r\n" + "  `member_id` INT NOT NULL,\r\n"
							+ "  `pay_date` DATE NOT NULL,\r\n" + "  PRIMARY KEY (`booking_id`, `member_id`),\r\n"
							+ "  INDEX `fk_booking_members1_idx` (`member_id` ASC) VISIBLE,\r\n"
							+ "  CONSTRAINT `fk_booking_members1`\r\n" + "    FOREIGN KEY (`member_id`)\r\n"
							+ "    REFERENCES `members` (`member_id`)\r\n" + "    ON DELETE CASCADE\r\n"
							+ "    ON UPDATE CASCADE)\r\n" + "ENGINE = InnoDB;");
			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS tickets (\r\n"
					+ "  `ticket_id` INT NOT NULL AUTO_INCREMENT,\r\n" + "  `movie_schedule_id` INT NOT NULL,\r\n"
					+ "  `screen_id` INT NOT NULL,\r\n" + "  `seat_id` INT NOT NULL,\r\n"
					+ "  `booking_id` INT NOT NULL,\r\n" + "  `is_ticket_printed` TINYINT NOT NULL DEFAULT 0,\r\n"
					+ "  `standard_price` VARCHAR(45) NOT NULL,\r\n" + "  `selling_price` VARCHAR(45) NOT NULL,\r\n"
					+ "  PRIMARY KEY (`ticket_id`, `movie_schedule_id`, `screen_id`, `seat_id`, `booking_id`),\r\n"
					+ "  INDEX `fk_tickets_movie_schedule1_idx` (`movie_schedule_id` ASC) VISIBLE,\r\n"
					+ "  INDEX `fk_tickets_seats1_idx` (`seat_id` ASC) VISIBLE,\r\n"
					+ "  INDEX `fk_tickets_booking1_idx` (`booking_id` ASC) VISIBLE,\r\n"
					+ "  INDEX `fk_tickets_screens1_idx` (`screen_id` ASC) VISIBLE,\r\n"
					+ "  CONSTRAINT `fk_tickets_movie_schedule1`\r\n" + "    FOREIGN KEY (`movie_schedule_id`)\r\n"
					+ "    REFERENCES `movie_schedule` (`movie_schedule_id`)\r\n" + "    ON DELETE CASCADE\r\n"
					+ "    ON UPDATE CASCADE,\r\n" + "  CONSTRAINT `fk_tickets_seats1`\r\n"
					+ "    FOREIGN KEY (`seat_id`)\r\n" + "    REFERENCES `seats` (`seat_id`)\r\n"
					+ "    ON DELETE CASCADE\r\n" + "    ON UPDATE CASCADE,\r\n"
					+ "  CONSTRAINT `fk_tickets_booking1`\r\n" + "    FOREIGN KEY (`booking_id`)\r\n"
					+ "    REFERENCES `booking` (`booking_id`)\r\n" + "    ON DELETE CASCADE\r\n"
					+ "    ON UPDATE CASCADE,\r\n" + "  CONSTRAINT `fk_tickets_screens1`\r\n"
					+ "    FOREIGN KEY (`screen_id`)\r\n" + "    REFERENCES `screens` (`screen_id`)\r\n"
					+ "    ON DELETE CASCADE\r\n" + "    ON UPDATE CASCADE)\r\n" + "ENGINE = InnoDB;");

			stmt.executeUpdate(
					"INSERT INTO movies VALUES(1, '���� ��Ʈ������', '2�ð� 20��', '15��', '���μ�', '������', '��Ÿ��', '��¿Ƽ�� �ȱ�Ƽ�� �ȹ�Ƽ��', STR_TO_DATE('2021-01-09','%Y-%m-%d'));");
			stmt.executeUpdate(
					"INSERT INTO movies VALUES(2, '���˵���1', '2�ð� 21��', '18��', '���μ�', '������', '������', '��¿Ƽ�� �ȱ�Ƽ�� �ȹ�Ƽ��', STR_TO_DATE('2021-02-09','%Y-%m-%d'));");
			stmt.executeUpdate(
					"INSERT INTO movies VALUES(3, '���˵���3', '2�ð� 22��', '12��', '�ֹμ�', '������', 'ȣ��', '��¿Ƽ�� �ȱ�Ƽ�� �ȹ�Ƽ��', STR_TO_DATE('2021-03-09','%Y-%m-%d'));");
			stmt.executeUpdate(
					"INSERT INTO movies VALUES(4, '���˵���4', '2�ð� 23��', '18��', '�ڹμ�', '������', '�ڹ̵�', '��¿Ƽ�� �ȱ�Ƽ�� �ȹ�Ƽ��', STR_TO_DATE('2021-04-09','%Y-%m-%d'));");
			stmt.executeUpdate(
					"INSERT INTO movies VALUES(5, '���˵���5', '2�ð� 24��', '15��', '��μ�','������', 'ȣ��', '��¿Ƽ�� �ȱ�Ƽ�� �ȹ�Ƽ��', STR_TO_DATE('2021-05-09','%Y-%m-%d'));");
			stmt.executeUpdate(
					"INSERT INTO movies VALUES(6, '���˵���6', '2�ð� 25��', '12��', '�ӹμ�', '������', '�θǽ�', '��¿Ƽ�� �ȱ�Ƽ�� �ȹ�Ƽ��', STR_TO_DATE('2021-06-09','%Y-%m-%d'));");
			stmt.executeUpdate(
					"INSERT INTO movies VALUES(7, '���˵���6', '2�ð� 26��', '��ü�̿밡', '���μ�', '������', '��Ÿ��', '��¿Ƽ�� �ȱ�Ƽ�� �ȹ�Ƽ��', STR_TO_DATE('2021-07-09','%Y-%m-%d'));");
			stmt.executeUpdate(
					"INSERT INTO movies VALUES(8, '���˵���7', '2�ð� 27��', '15��', '���μ�', '������', '�׼�', '��¿Ƽ�� �ȱ�Ƽ�� �ȹ�Ƽ��', STR_TO_DATE('2021-08-09','%Y-%m-%d'));");
			stmt.executeUpdate(
					"INSERT INTO movies VALUES(9, '���˵���8', '2�ð� 28��', '18��', '�̹μ�', '������', '������', '��¿Ƽ�� �ȱ�Ƽ�� �ȹ�Ƽ��', STR_TO_DATE('2021-09-09','%Y-%m-%d'));");
			stmt.executeUpdate(
					"INSERT INTO movies VALUES(10, '���˵���2', '2�ð� 29��', '15��', '���μ�', '������', '�׼�', '��¿Ƽ�� �ȱ�Ƽ�� �ȹ�Ƽ��', STR_TO_DATE('2021-10-09','%Y-%m-%d'));");

			stmt.executeUpdate(
					"insert into members values (1, 'Brooke', '735-214-8098', 'bmarlon0@constantcontact.com');");
			stmt.executeUpdate(
					"insert into members values (2, 'Martita', '964-716-5566', 'mbusk1@printfriendly.com');");
			stmt.executeUpdate(
					"insert into members values (3, 'Eziechiele', '768-705-1335', 'evan2@miitbeian.gov.cn');");
			stmt.executeUpdate("insert into members values (4, 'Pacorro', '212-772-9242', 'phovard3@oracle.com');");
			stmt.executeUpdate("insert into members values (5, 'Cori', '400-461-8374', 'caburrow4@163.com');");
			stmt.executeUpdate("insert into members values (6, 'Ahmad', '555-584-2230', 'aparram5@yandex.ru');");
			stmt.executeUpdate("insert into members values (7, 'Elliot', '550-149-1193', 'egiacubo6@elpais.com');");
			stmt.executeUpdate("insert into members values (8, 'Barret', '432-886-2071', 'bleathwood7@cisco.com');");
			stmt.executeUpdate(
					"insert into members values (9, 'Ranique', '445-800-0015', 'rculter8@scientificamerican.com');");
			stmt.executeUpdate("insert into members values (10, 'Jaclyn', '585-410-7043', 'jbouchier9@unesco.org');");

			stmt.executeUpdate("insert into screens values (1, 2, 1);");
			stmt.executeUpdate("insert into screens values (2, 3, 1);");
			stmt.executeUpdate("insert into screens values (3, 2, 1);");
			stmt.executeUpdate("insert into screens values (4, 3, 1);");
			stmt.executeUpdate("insert into screens values (5, 2, 0);");
			stmt.executeUpdate("insert into screens values (6, 2, 1);");
			stmt.executeUpdate("insert into screens values (7, 2, 1);");
			stmt.executeUpdate("insert into screens values (8, 2, 1);");
			stmt.executeUpdate("insert into screens values (9, 1, 1);");
			stmt.executeUpdate("insert into screens values (10, 1, 1);");

			stmt.executeUpdate("insert into seats values (1, 1, 0);");
			stmt.executeUpdate("insert into seats values (2, 2, 0);");
			stmt.executeUpdate("insert into seats values (3, 3, 0);");
			stmt.executeUpdate("insert into seats values (4, 4, 0);");
			stmt.executeUpdate("insert into seats values (5, 5, 0);");
			stmt.executeUpdate("insert into seats values (6, 6, 0);");
			stmt.executeUpdate("insert into seats values (7, 7, 0);");
			stmt.executeUpdate("insert into seats values (8, 8, 0);");
			stmt.executeUpdate("insert into seats values (9, 9, 0);");
			stmt.executeUpdate("insert into seats values (10, 10, 0);");
			stmt.executeUpdate("insert into seats values (11, 1, 1);");
			stmt.executeUpdate("insert into seats values (12, 2, 1);");
			stmt.executeUpdate("insert into seats values (13, 3, 1);");
			stmt.executeUpdate("insert into seats values (14, 4, 1);");
			stmt.executeUpdate("insert into seats values (15, 5, 1);");
			stmt.executeUpdate("insert into seats values (16, 6, 1);");
			stmt.executeUpdate("insert into seats values (17, 7, 1);");
			stmt.executeUpdate("insert into seats values (18, 8, 1);");
			stmt.executeUpdate("insert into seats values (19, 2, 1);");
			stmt.executeUpdate("insert into seats values (20, 4, 1);");

			stmt.executeUpdate(
					"insert into movie_schedule values(1, 1, 1, STR_TO_DATE('2021-01-09','%Y-%m-%d'), '������', '1ȸ��', '09�� 15��');");
			stmt.executeUpdate(
					"insert into movie_schedule values(2, 2, 2, STR_TO_DATE('2021-02-09','%Y-%m-%d'), 'ȭ����', '2ȸ��', '10�� 15��');");
			stmt.executeUpdate(
					"insert into movie_schedule values(3, 3, 3, STR_TO_DATE('2021-03-09','%Y-%m-%d'), '������', '2ȸ��', '11�� 15��');");
			stmt.executeUpdate(
					"insert into movie_schedule values(4, 4, 4, STR_TO_DATE('2021-04-09','%Y-%m-%d'), '�����', '3ȸ��', '10�� 15��');");
			stmt.executeUpdate(
					"insert into movie_schedule values(5, 5, 5, STR_TO_DATE('2021-05-09','%Y-%m-%d'), '�ݿ���', '5ȸ��', '18�� 15��');");
			stmt.executeUpdate(
					"insert into movie_schedule values(6, 6, 6, STR_TO_DATE('2021-06-09','%Y-%m-%d'), '������', '2ȸ��', '10�� 15��');");
			stmt.executeUpdate(
					"insert into movie_schedule values(7, 7, 7, STR_TO_DATE('2021-07-09','%Y-%m-%d'), '������', '7ȸ��', '22�� 15��');");
			stmt.executeUpdate(
					"insert into movie_schedule values(8, 8, 8, STR_TO_DATE('2021-08-09','%Y-%m-%d'), '������', '2ȸ��', '11�� 15��');");
			stmt.executeUpdate(
					"insert into movie_schedule values(9, 9, 9, STR_TO_DATE('2021-09-09','%Y-%m-%d'), '�ݿ���', '4ȸ��', '13�� 15��');");
			stmt.executeUpdate(
					"insert into movie_schedule values(10, 10, 10, STR_TO_DATE('2021-10-09','%Y-%m-%d'), '�Ͽ���', '1ȸ��', '09�� 15��');");
			stmt.executeUpdate(
					"insert into movie_schedule values(11, 1, 8, STR_TO_DATE('2021-01-10','%Y-%m-%d'), 'ȭ����', '2ȸ��', '09�� 20��');");

			stmt.executeUpdate(
					"insert into booking values(1, 'ī��', 1, '13,000��', 1, STR_TO_DATE('2021-01-08','%Y-%m-%d'));");
			stmt.executeUpdate(
					"insert into booking values(2, 'ī��', 1, '13,000��', 2, STR_TO_DATE('2021-02-09','%Y-%m-%d'));");
			stmt.executeUpdate(
					"insert into booking values(3, '����', 1, '13,000��', 3, STR_TO_DATE('2021-03-08','%Y-%m-%d'));");
			stmt.executeUpdate(
					"insert into booking values(4, 'ī��', 1, '13,000��', 4, STR_TO_DATE('2021-04-08','%Y-%m-%d'));");
			stmt.executeUpdate(
					"insert into booking values(5, '����', 1, '13,000��', 5, STR_TO_DATE('2021-05-08','%Y-%m-%d'));");
			stmt.executeUpdate(
					"insert into booking values(6, 'ī��', 1, '13,000��', 6, STR_TO_DATE('2021-06-08','%Y-%m-%d'));");
			stmt.executeUpdate(
					"insert into booking values(7, '������', 0, '13,000��', 7, STR_TO_DATE('2021-07-08','%Y-%m-%d'));");
			stmt.executeUpdate(
					"insert into booking values(8, '����', 1, '13,000��', 8, STR_TO_DATE('2021-08-08','%Y-%m-%d'));");
			stmt.executeUpdate(
					"insert into booking values(9, '����', 1, '13,000��', 9, STR_TO_DATE('2021-09-08','%Y-%m-%d'));");
			stmt.executeUpdate(
					"insert into booking values(10, 'ī��', 1, '13,000��', 10, STR_TO_DATE('2021-10-08','%Y-%m-%d'));");

			stmt.executeUpdate("insert into tickets values(1, 1, 1, 1, 1, 1, '15,000��', '13,000��');");
			stmt.executeUpdate("insert into tickets values(2, 2, 2, 2, 2, 2, '15,000��', '13,000��');");
			stmt.executeUpdate("insert into tickets values(3, 3, 3, 3, 3, 3, '15,000��', '13,000��');");
			stmt.executeUpdate("insert into tickets values(4, 4, 4, 4, 4, 4, '15,000��', '13,000��');");
			stmt.executeUpdate("insert into tickets values(5, 5, 5, 5, 5, 5, '15,000��', '13,000��');");
			stmt.executeUpdate("insert into tickets values(6, 6, 6, 6, 6, 6, '15,000��', '13,000��');");
			stmt.executeUpdate("insert into tickets values(7, 7, 7, 7, 7, 7, '15,000��', '13,000��');");
			stmt.executeUpdate("insert into tickets values(8, 8, 8, 8, 8, 8, '15,000��', '13,000��');");
			stmt.executeUpdate("insert into tickets values(9, 9, 9, 9, 9, 9, '15,000��', '13,000��');");
			stmt.executeUpdate("insert into tickets values(10, 10, 10, 10, 10, 10, '15,000��', '13,000��');");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void Controll(JLabel error, String sql) {
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(sql);
			error.setText("�Ϸ��߽��ϴ�");
		} catch (SQLException e) {
			error.setText("SQL���� �ٽ� �Է����ּ���");
		}
	}

	public void AllSearch(JTextArea resultLabel) {

		resultLabel.append(
				"movies \t\tmovie_id \tmovie_title \t\trunning_time \t\tmovie_rating \t\tdirector \t\tactor\t\tgenre \t movie_introduction \t release_date\n");

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from movies");
			int i = 1;

			while (rs.next()) {
				resultLabel.append(i++ + "\t");
				resultLabel.append("\t" + rs.getInt(1));
				resultLabel.append("\t" + rs.getString(2));
				resultLabel.append("\t\t" + rs.getString(3));
				resultLabel.append("\t\t" + rs.getString(4));
				resultLabel.append("\t\t" + rs.getString(5));
				resultLabel.append("\t\t" + rs.getString(6));
				resultLabel.append("\t\t" + rs.getString(7));
				resultLabel.append("\t" + rs.getString(8));
				resultLabel.append("\t" + rs.getString(9) + "\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		resultLabel.append("\n");

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from movie_schedule");
			int i = 1;

			resultLabel.append(
					"movie_schedule \tmovie_schedule_id \tmovie_id \tscreen_id \tscreening_start_date \tscreening_day \t\tscreening_round \tscreening_start_time\n");
			while (rs.next()) {
				resultLabel.append(i++ + "\t");
				resultLabel.append("\t" + rs.getObject(1));
				resultLabel.append("\t\t" + rs.getObject(2));
				resultLabel.append("\t" + rs.getObject(3));
				resultLabel.append("\t" + rs.getObject(4));
				resultLabel.append("\t\t" + rs.getObject(5));
				resultLabel.append("\t\t" + rs.getObject(6));
				resultLabel.append("\t\t" + rs.getObject(7) + "\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		resultLabel.append("\n");

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from screens");
			int i = 1;

			resultLabel.append("screens \t\tscreen_id \tseats \t\tis_available\n");
			while (rs.next()) {
				resultLabel.append(i++ + "\t");
				resultLabel.append("\t" + String.format("%06d", rs.getInt(1)));
				resultLabel.append("\t" + rs.getInt(2));
				resultLabel.append("\t\t" + rs.getInt(3) + "\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		resultLabel.append("\n");

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from tickets");
			int i = 1;

			resultLabel.append(
					"tickets \t\tticket_id \tmovie_schedule_id \tscreen_id \tseat_id \tbooking_id \t is_ticket_printed \t standard_price \t selling_price\n");
			while (rs.next()) {
				resultLabel.append(i++ + "\t");
				resultLabel.append("\t" + rs.getObject(1));
				resultLabel.append("\t" + rs.getObject(2));
				resultLabel.append("\t\t" + rs.getObject(3));
				resultLabel.append("\t" + rs.getObject(4));
				resultLabel.append("\t" + rs.getObject(5));
				resultLabel.append("\t" + rs.getObject(6));
				resultLabel.append("\t\t" + rs.getObject(7));
				resultLabel.append("\t\t" + rs.getObject(8) + "\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		resultLabel.append("\n");

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from seats");
			int i = 1;

			resultLabel.append("seats \t\tseat_id \tscreen_id \t\tis_available\n");
			while (rs.next()) {
				resultLabel.append(i++ + "\t");
				resultLabel.append("\t" + rs.getObject(1));
				resultLabel.append("\t" + rs.getObject(2));
				resultLabel.append("\t\t" + rs.getObject(3) + "\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		resultLabel.append("\n");

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from members");
			int i = 1;

			resultLabel.append("members \t\tmember_id \tname \tphone \t\temail\n");
			while (rs.next()) {
				resultLabel.append(i++ + "\t");
				resultLabel.append("\t" + rs.getObject(1));
				resultLabel.append("\t" + rs.getObject(2));
				resultLabel.append("\t" + rs.getObject(3));
				resultLabel.append("\t\t" + rs.getObject(4) + "\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		resultLabel.append("\n");

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from booking");
			int i = 1;

			resultLabel.append("booking \t\tbooking_id \tpay_method \tpay_statement \t price \t member_id  \t date \n");
			while (rs.next()) {
				resultLabel.append(i++ + "\t");
				resultLabel.append("\t" + rs.getObject(1));
				resultLabel.append("\t" + rs.getObject(2));
				resultLabel.append("\t" + rs.getObject(3));
				resultLabel.append("\t" + rs.getObject(4));
				resultLabel.append("\t" + rs.getObject(5));
				resultLabel.append("\t" + rs.getObject(6) + "\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		resultLabel.append("\n");
	}

	public void ExcecuteUpdateQuery(String sql, JLabel error) {
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			error.setText("�߸� �Է��߽��ϴ�.");
			e.printStackTrace();
		}
	}

	public int GetSQLInt(String sql) {
		int result = 0;

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next())
				result = rs.getInt(1);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public void MovieSearch(JTable table, JLabel error, String sql) {
		Object[] attribute = { "��ȭ��", "�󿵽ð�", "�󿵵��", "������", "����", "�帣", "�󿵽�����", "�󿵽��۽ð�", "�󿵰�", "�¼���ȣ",};
		DefaultTableModel model = new DefaultTableModel(attribute, 0);

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Vector<Object> v = new Vector<Object>();
				v.add(rs.getString(1));
				v.add(rs.getString(2));
				v.add(rs.getString(3));
				v.add(rs.getString(4));
				v.add(rs.getString(5));
				v.add(rs.getString(6));
				v.add(rs.getString(7));
				v.add(rs.getString(8));
				v.add(rs.getString(9));
				v.add(rs.getString(10));
				model.addRow(v);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			error.setText("�߸� �Է��߽��ϴ�.");
		}

		table.setModel(model);
	}
	
	public void MovieSearch2(JTable table, JLabel error, String sql) {
		Object[] attribute = { "��ȭ��", "�󿵽ð�", "�󿵵��", "������", "����", "�帣", "�󿵽�����", "�󿵽��۽ð�", "�󿵰�", "�¼���ȣ", "��밡��" };
		DefaultTableModel model = new DefaultTableModel(attribute, 0);

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Vector<Object> v = new Vector<Object>();
				v.add(rs.getString(1));
				v.add(rs.getString(2));
				v.add(rs.getString(3));
				v.add(rs.getString(4));
				v.add(rs.getString(5));
				v.add(rs.getString(6));
				v.add(rs.getString(7));
				v.add(rs.getString(8));
				v.add(rs.getString(9));
				v.add(rs.getString(10));
				v.add(rs.getString(11));
				model.addRow(v);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			error.setText("�߸� �Է��߽��ϴ�.");
		}

		table.setModel(model);
	}

	public void BookingSearch(JTable table, String sql) {
		String[] attribute = { "��ȭ��", "����", "�󿵰���ȣ", "�¼���ȣ", "�ǸŰ���" };
		DefaultTableModel model = new DefaultTableModel(attribute, 0);

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Vector<Object> v = new Vector<Object>();
				v.add(rs.getString(1));
				v.add(rs.getString(2));
				v.add(rs.getString(3));
				v.add(rs.getString(4));
				v.add(rs.getString(5));
				model.addRow(v);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		table.setModel(model);
	}
	
	public void BookingSearch2(JTable table, String sql) {
		String[] attribute = { "���Ź�ȣ", "�������", "��������", "�����ݾ�", "ȸ�����̵�", "��������", "�߱ǿ���", "ǥ�ذ���", "�ǸŰ���", "������", "�󿵽ð�", "�󿵰���ȣ", "�¼���ȣ", "��ȭ����" };
		DefaultTableModel model = new DefaultTableModel(attribute, 0);

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Vector<Object> v = new Vector<Object>();
				v.add(rs.getString(1));
				v.add(rs.getString(2));
				v.add(rs.getString(3));
				v.add(rs.getString(4));
				v.add(rs.getString(5));
				v.add(rs.getString(6));
				v.add(rs.getString(7));
				v.add(rs.getString(8));
				v.add(rs.getString(9));
				v.add(rs.getString(10));
				v.add(rs.getString(11));
				v.add(rs.getString(12));
				v.add(rs.getString(13));
				v.add(rs.getString(14));
				model.addRow(v);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		table.setModel(model);
	}

	public void BookingClickShow(JTable table, String sql) {
		String[] attribute = { "����", "�󿵿���", "��ȸ��", "�󿵽��۽ð�", "�󿵰���ȣ", "�¼���ȣ", "�¼���", "�߱ǿ���", "ǥ�ذ���", "�ǸŰ���" };
		DefaultTableModel model = new DefaultTableModel(attribute, 0);
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Vector<Object> v = new Vector<Object>();
				v.add(rs.getString(1));
				v.add(rs.getString(2));
				v.add(rs.getString(3));
				v.add(rs.getString(4));
				v.add(rs.getString(5));
				v.add(rs.getString(6));
				v.add(rs.getString(7));
				v.add(rs.getString(8));
				v.add(rs.getString(9));
				v.add(rs.getString(10));
				model.addRow(v);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		table.setModel(model);
	}

	public void ExtraMoviescheduleShow(JTable table, String sql) {
		String[] attribute = { "����", "�󿵿���", "��ȸ��", "�󿵽��۽ð�", "�󿵰���ȣ", "�¼���ȣ" };
		DefaultTableModel model = new DefaultTableModel(attribute, 0);
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Vector<Object> v = new Vector<Object>();
				v.add(rs.getString(1));
				v.add(rs.getString(2));
				v.add(rs.getString(3));
				v.add(rs.getString(4));
				v.add(rs.getString(5));
				v.add(rs.getString(6));
				model.addRow(v);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		table.setModel(model);
	}
}

public class databaseProject {
	public static void main(String args[]) {
		new Title();
	}
}