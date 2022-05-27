package databaseProject;

import java.io.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Title extends JFrame{
	SQL sql = new SQL();
	JButton button[] = new JButton[2];

	public Title() {
		Container c1 = getContentPane();
		c1.setLayout(null);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(2000,2000);
		setTitle("18011560/���μ�");
	
		button[0] = new JButton("������");
		button[0].setSize(300,300);
		button[0].setLocation(700, 300);
		c1.add(button[0]);
		
		button[1] = new JButton("ȸ��");
		button[1].setSize(300,300);
		button[1].setLocation(1000, 300);
		c1.add(button[1]);
		
		buttonFunction();
		
		setVisible(true);
	}
	
	public void buttonFunction() {
		button[0].addActionListener(event -> {
			new Manager(sql);
			setVisible(false);
		});
		
		button[1].addActionListener(event -> {
			new Member(sql);
			setVisible(false);
		});
	}
}

class Manager extends JFrame {
	JButton button[] = new JButton[3];
	SQL sql;
	JTextField  jt2 = new JTextField(1000);
	JLabel error = new JLabel();
	JTextArea result = new JTextArea();
	boolean insert = false;
	JScrollPane scrollPane = new JScrollPane(result);

	public Manager(SQL _sql) {
		sql = _sql;
		Container c1 = getContentPane();
		c1.setLayout(null);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(2000,2000);
		setTitle("18011560/���μ�");
	
		button[0] = new JButton("�ʱ�ȭ");
		button[0].setSize(200,100);
		button[0].setLocation(0, 0);
		c1.add(button[0]);
		
		button[1] = new JButton("�Է�/����/����");
		button[1].setSize(200,100);
		button[1].setLocation(500, 150);
		c1.add(button[1]);
		
		
		button[2] = new JButton("��ü ���̺� ����");
		button[2].setSize(200,100);
		button[2].setLocation(200, 0);
		c1.add(button[2]);
		
		JLabel label = new JLabel("SQL");
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setSize(100,10);
		label.setLocation(0, 200);
		c1.add(label);

		jt2.setLocation(100,200);
		jt2.setSize(400,50);
		c1.add(jt2);
		
		error.setSize(300,50);
		error.setLocation(0, 250);
		c1.add(error);
		
		scrollPane.setSize(1700,500);
		scrollPane.setLocation(0, 400);
		c1.add(scrollPane);
		
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
			
			//sql.Insert(jt1.getText(), jt2.getText(), error);
		});
		
		button[2].addActionListener(event -> {
			result.setText(null);
			error.setText(null);
			
			
			sql.AllSearch(result);
		});
	}
}


class Member extends JFrame {
	JButton button[] = new JButton[6];
	SQL sql;
	JTextArea result = new JTextArea();
	boolean insert = false;
	JScrollPane scrollPane = new JScrollPane(result);

	public Member(SQL _sql) {
		sql = _sql;
		Container c1 = getContentPane();
		c1.setLayout(null);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(2000,2000);
		setTitle("18011560/���μ�");
	
		button[0] = new JButton("��� ��ȭ ��ȸ");
		button[0].setSize(200,100);
		button[0].setLocation(0, 0);
		c1.add(button[0]);
		
		button[1] = new JButton("����");
		button[1].setSize(200,100);
		button[1].setLocation(200, 0);
		c1.add(button[1]);
		
		button[2] = new JButton("���� ���� ��ȸ");
		button[2].setSize(200,100);
		button[2].setLocation(400, 0);
		c1.add(button[2]);
		
		button[3] = new JButton("���� ����");
		button[3].setSize(200,100);
		button[3].setLocation(600, 0);
		c1.add(button[3]);
		
		button[4] = new JButton("��ȭ ����");
		button[4].setSize(200,100);
		button[4].setLocation(800, 0);
		c1.add(button[4]);
		
		button[5] = new JButton("���� ����");
		button[5].setSize(200,100);
		button[5].setLocation(1000, 0);
		c1.add(button[5]);
		
		JLabel label1 = new JLabel("��� ��ȭ");
		label1.setHorizontalAlignment(JLabel.CENTER);
		label1.setSize(100, 50);
		label1.setLocation(50, 100);
		c1.add(label1);
		
		JButton temp = new JButton("��ü ���̺� ����");
		temp.setSize(200,100);
		temp.setLocation(30, 100);
		
		
		scrollPane.setSize(1700,400);
		scrollPane.setLocation(0, 150);
		scrollPane.add(temp);
		c1.add(scrollPane);
		
		buttonFunction();
		
		setVisible(true);
	}
	
	public void buttonFunction() {
		button[0].addActionListener(event -> {
			result.setText(null);
		});
		
		button[1].addActionListener(event -> {
			result.setText(null);
		});
		
		button[2].addActionListener(event -> {
			result.setText(null);
		});
		
		button[3].addActionListener(event -> {
			result.setText(null);
		});
		
		button[4].addActionListener(event -> {
			result.setText(null);
		});
		
		button[5].addActionListener(event -> {
			result.setText(null);
		});
	}
}

class SQL {
   Connection con;
   	
   public SQL() {
     String Driver="";
     String url="jdbc:mysql://localhost:3306/hospital?&serverTimezone=Asia/Seoul"; 
     String userid="hospital";
     String pwd="hospital";
     
     try {
       Class.forName("com.mysql.cj.jdbc.Driver");   
       System.out.println("����̹� �ε� ����");
     } catch(ClassNotFoundException e) {
         e.printStackTrace();
      }
     
     try {
       System.out.println("�����ͺ��̽� ���� �غ�...");	
       con=DriverManager.getConnection(url, userid, pwd);
       System.out.println("�����ͺ��̽� ���� ����");
     } catch(SQLException e) {
         e.printStackTrace();
       }
   }
   
   public void Initialize() {
	   try {
		  Statement stmt=con.createStatement();
		  stmt.executeUpdate("ALTER TABLE Patients DROP CONSTRAINT R_2;");
	  	  stmt.executeUpdate("ALTER TABLE Patients DROP CONSTRAINT R_3;");
	  	  stmt.executeUpdate("ALTER TABLE treatments DROP CONSTRAINT R_5;");
	  	  stmt.executeUpdate("ALTER TABLE treatments DROP CONSTRAINT R_6;");
	  	  stmt.executeUpdate("ALTER TABLE Charts DROP CONSTRAINT R_4;");
	  	  stmt.executeUpdate("ALTER TABLE Charts DROP CONSTRAINT R_7;");
	  	  
	  	  stmt.executeUpdate("DROP TABLE doctors;");
	  	  stmt.executeUpdate("DROP TABLE treatments;");
	  	  stmt.executeUpdate("DROP TABLE patients;");
	  	  stmt.executeUpdate("DROP TABLE nurses;");
	  	  stmt.executeUpdate("DROP TABLE charts;");
	  	  
	  	  stmt.executeUpdate("CREATE TABLE Doctors (\r\n"
	  	  		+ "  doc_id INTEGER NOT NULL,\r\n"
	  	  		+ "  major_treat VARCHAR(25) NOT NULL,\r\n"
	  	  		+ "  doc_name VARCHAR(20) NOT NULL,\r\n"
	  	  		+ "  doc_gen VARCHAR(1) NOT NULL,\r\n"
	  	  		+ "  doc_phone VARCHAR(15) NULL,\r\n"
	  	  		+ "  doc_email VARCHAR(50) UNIQUE,\r\n"
	  	  		+ "  doc_position VARCHAR(20) NOT NULL\r\n"
	  	  		+ ");");
	  	  stmt.executeUpdate("ALTER TABLE Doctors\r\n"
	  	  		+ "  ADD CONSTRAINT doc_id_pk PRIMARY KEY (doc_id);");
	  	  
	  	  stmt.executeUpdate("CREATE TABLE Nurses (\r\n"
	  	  		+ "  nur_id INTEGER NOT NULL,\r\n"
	  	  		+ "  major_job VARCHAR(25) NOT NULL,\r\n"
	  	  		+ "  nur_name VARCHAR(20) NOT NULL,\r\n"
	  	  		+ "  nur_gen char(1) NOT NULL,\r\n"
	  	  		+ "  nur_phone VARCHAR(15) NULL,\r\n"
	  	  		+ "  nur_email VARCHAR(50) UNIQUE,\r\n"
	  	  		+ "  nur_position VARCHAR(20) NOT NULL\r\n"
	  	  		+ ");");
	  	stmt.executeUpdate("ALTER TABLE Nurses\r\n"
	  			+ "  ADD CONSTRAINT nur_id_pk PRIMARY KEY (nur_id);");
	  	
	  	stmt.executeUpdate("CREATE TABLE Patients (\r\n"
	  			+ "  pat_id INTEGER NOT NULL,\r\n"
	  			+ "  nur_id INTEGER NOT NULL,\r\n"
	  			+ "  doc_id INTEGER NOT NULL,\r\n"
	  			+ "  pat_name VARCHAR(20) NOT NULL,\r\n"
	  			+ "  pat_gen VARCHAR(1) NOT NULL,\r\n"
	  			+ "  pat_jumin VARCHAR(14) NOT NULL,\r\n"
	  			+ "  pat_addr VARCHAR(100) NOT NULL,\r\n"
	  			+ "  pat_phone VARCHAR(15) NULL,\r\n"
	  			+ "  pat_email VARCHAR(50) UNIQUE,\r\n"
	  			+ "  pat_job VARCHAR(20) NOT NULL\r\n"
	  			+ ");");
	  	stmt.executeUpdate("ALTER TABLE Patients\r\n"
	  			+ "  ADD CONSTRAINT pat_id_pk PRIMARY KEY (pat_id);");
	  	stmt.executeUpdate("ALTER TABLE Patients\r\n"
	  			+ "  ADD (CONSTRAINT R_2 FOREIGN KEY (doc_id) REFERENCES Doctors (doc_id));");
	  	stmt.executeUpdate("ALTER TABLE Patients\r\n"
	  			+ "  ADD (CONSTRAINT R_3 FOREIGN KEY (nur_id) REFERENCES Nurses (nur_id));");
	  	
	  	stmt.executeUpdate("CREATE TABLE Treatments (\r\n"
	  			+ "  treat_id INTEGER NOT NULL,\r\n"
	  			+ "  pat_id INTEGER NOT NULL,\r\n"
	  			+ "  doc_id INTEGER NOT NULL,\r\n"
	  			+ "  treat_contents VARCHAR(1000) NOT NULL,\r\n"
	  			+ "  treat_date DATE NOT NULL\r\n"
	  			+ ");");
	  	stmt.executeUpdate("ALTER TABLE Treatments\r\n"
	  			+ "  ADD CONSTRAINT treat_pat_doc_id_pk PRIMARY KEY (treat_id, pat_id, doc_id);");
	  	stmt.executeUpdate("ALTER TABLE Treatments\r\n"
	  			+ "  ADD (CONSTRAINT R_5 FOREIGN KEY (pat_id) REFERENCES Patients (pat_id));");
	  	stmt.executeUpdate("ALTER TABLE Treatments\r\n"
	  			+ "  ADD (CONSTRAINT R_6 FOREIGN KEY (doc_id) REFERENCES Doctors (doc_id));");
	  	
	  	stmt.executeUpdate("CREATE TABLE Charts (\r\n"
	  			+ "  chart_id VARCHAR(20) NOT NULL,\r\n"
	  			+ "  treat_id INTEGER NOT NULL,\r\n"
	  			+ "  doc_id INTEGER NOT NULL,\r\n"
	  			+ "  pat_id INTEGER NOT NULL,\r\n"
	  			+ "  nur_id INTEGER NOT NULL,\r\n"
	  			+ "  chart_contents VARCHAR(1000) NOT NULL\r\n"
	  			+ ");");
	  	stmt.executeUpdate("ALTER TABLE Charts\r\n"
	  			+ "  ADD CONSTRAINT chart_treat_doc_pat_id_pk PRIMARY KEY (chart_id, treat_id, doc_id, pat_id);");
	  	stmt.executeUpdate("ALTER TABLE Charts\r\n"
	  			+ "  ADD (CONSTRAINT R_4 FOREIGN KEY (nur_id) REFERENCES Nurses (nur_id));");
	  	stmt.executeUpdate("ALTER TABLE Charts\r\n"
	  			+ "  ADD (CONSTRAINT R_7 FOREIGN KEY (treat_id, pat_id, doc_id) REFERENCES Treatments (treat_id, pat_id, doc_id));");
	  	
	  	stmt.executeUpdate("INSERT INTO Doctors VALUES(980312, '�Ҿư�', '������', 'M', '010-333-1340', 'ltj@hanbit.com', '����');");
	  	stmt.executeUpdate("INSERT INTO Doctors VALUES(000601, '����', '�ȼ���', 'M', '011-222-0987', 'ask@hanbit.com', '����');");
	  	stmt.executeUpdate("INSERT INTO Doctors VALUES(001208, '�ܰ�', '�����', 'M', '010-333-8743', 'kmj@hanbit.com', '����');");
	  	stmt.executeUpdate("INSERT INTO Doctors VALUES(020403, '�Ǻΰ�', '���¼�', 'M', '019-777-3764', 'lts@hanbit.com', '����');");
	  	stmt.executeUpdate("INSERT INTO Doctors VALUES(050900, '�Ҿư�', '�迬��', 'F', '010-555-3746', 'kya@hanbit.com', '������');");
	  	stmt.executeUpdate("INSERT INTO Doctors VALUES(050101, '����', '������', 'M', '011-222-7643', 'cth@hanbit.com', '������');");
	  	stmt.executeUpdate("INSERT INTO Doctors VALUES(062019, '�Ҿư�', '������', 'F', '010-999-1265', 'jjh@hanbit.com', '������');");
	  	stmt.executeUpdate("INSERT INTO Doctors VALUES(070576, '�Ǻΰ�', 'ȫ�浿', 'M', '016-333-7263', 'hgd@hanbit.com', '������');");
	  	stmt.executeUpdate("INSERT INTO Doctors VALUES(080543, '��缱��', '���缮', 'M', '010-222-1263', 'yjs@hanbit.com', '����');");
	  	stmt.executeUpdate("INSERT INTO Doctors VALUES(091001, '�ܰ�', '�躴��', 'M', '010-555-3542', 'kbm@hanbit.com', '������');");
	  	
	  	stmt.executeUpdate("INSERT INTO Nurses VALUES(050302, '�Ҿư�', '������', 'F', '010-555-8751', 'key@hanbit.com', '����ȣ��');");
	  	stmt.executeUpdate("INSERT INTO Nurses VALUES(050021, '����', '������', 'F', '016-333-8745', 'ysa@hanbit.com', '����ȣ��');");
	  	stmt.executeUpdate("INSERT INTO Nurses VALUES(040089, '�Ǻΰ�', '������', 'M', '010-666-7646', 'sjw@hanbit.com', '����');");
	  	stmt.executeUpdate("INSERT INTO Nurses VALUES(070605, '��缱��', '����ȭ', 'F', '010-333-4588', 'yjh@hanbit.com', '����');");
	  	stmt.executeUpdate("INSERT INTO Nurses VALUES(070804, '����', '���ϳ�', 'F', '010-222-1340', 'nhn@hanbit.com', '����');");
	  	stmt.executeUpdate("INSERT INTO Nurses VALUES(071018, '�Ҿư�', '��ȭ��', 'F', '019-888-4116', 'khk@hanbit.com', '����');");
	  	stmt.executeUpdate("INSERT INTO Nurses VALUES(100356, '�Ҿư�', '�̼���', 'M', '010-777-1234', 'lsy@hanbit.com', '��ȣ��');");
	  	stmt.executeUpdate("INSERT INTO Nurses VALUES(104145, '�ܰ�', '����', 'M', '010-999-8520', 'kh@hanbit.com', '��ȣ��');");
	  	stmt.executeUpdate("INSERT INTO Nurses VALUES(120309, '�Ǻΰ�', '�ڼ���', 'M', '010-777-4996', 'psw@hanbit.com', '��ȣ��');");
	  	stmt.executeUpdate("INSERT INTO Nurses VALUES(130211, '�ܰ�', '�̼���', 'F', '010-222-3214', 'lsy2@hanbit.com', '��ȣ��');");
	  	
	  	stmt.executeUpdate("INSERT INTO Patients VALUES(2345, 050302, 980312, '�Ȼ��', 'M', 232345, '����', '010-555-7845', 'ask@ab.com', 'ȸ���');");
	  	stmt.executeUpdate("INSERT INTO Patients VALUES(3545, 040089, 020403, '�輺��', 'M', 543545, '����', '010-333-7812', 'ksn@bb.com', '�ڿ���');");
	  	stmt.executeUpdate("INSERT INTO Patients VALUES(3424, 070605, 080543, '������', 'M', 433424, '�λ�', '010-888-4859', 'ljj@ab.com', 'ȸ���');");
	  	stmt.executeUpdate("INSERT INTO Patients VALUES(7675, 100356, 050900, '�ֱ���', 'M', 677675, '����', '010-222-4847', 'cks@cc.com', 'ȸ���');");
	  	stmt.executeUpdate("INSERT INTO Patients VALUES(4533, 070804, 000601, '���Ѱ�', 'M', 744533, '����', '010-777-9630', 'jhk@ab.com', '����');");
	  	stmt.executeUpdate("INSERT INTO Patients VALUES(5546, 120309, 070576, '������', 'M', 765546, '�뱸', '016-777-0214', 'ywh@cc.com', '�ڿ���');");
	  	stmt.executeUpdate("INSERT INTO Patients VALUES(4543, 070804, 050101, '������', 'M', 454543, '�λ�', '010-555-4187', 'cjj@bb.com', 'ȸ���');");
	  	stmt.executeUpdate("INSERT INTO Patients VALUES(9768, 130211, 091001, '������', 'F', 119768, '����', '010-888-3675', 'ljh@ab.com', '����');");
	  	stmt.executeUpdate("INSERT INTO Patients VALUES(4234, 130211, 091001, '������', 'F', 234234, '����', '010-999-6541', 'onm@cc.com', '�л�');");
	  	stmt.executeUpdate("INSERT INTO Patients VALUES(7643, 071018, 062019, '�ۼ���', 'M', 987643, '����', '010-222-5874', 'ssm@bb.com', '�л�');");
	  	
	  	stmt.executeUpdate("INSERT INTO Treatments VALUES(130516023, 2345, 980312, '����, ����', STR_TO_DATE('2013-05-16','%Y-%m-%d'));");
	  	stmt.executeUpdate("INSERT INTO Treatments VALUES(130628100, 3545, 020403, '�Ǻ� Ʈ���� ġ��', STR_TO_DATE('2013-06-28','%Y-%m-%d'));");
	  	stmt.executeUpdate("INSERT INTO Treatments VALUES(131205056, 3424, 080543, '�� ��ũ�� MRI �Կ�', STR_TO_DATE('2013-12-05','%Y-%m-%d'));");
	  	stmt.executeUpdate("INSERT INTO Treatments VALUES(131218024, 7675, 050900, '���̿�', STR_TO_DATE('2013-12-18','%Y-%m-%d'));");
	  	stmt.executeUpdate("INSERT INTO Treatments VALUES(131224012, 4533, 000601, '�忰', STR_TO_DATE('2013-12-24','%Y-%m-%d'));");
	  	stmt.executeUpdate("INSERT INTO Treatments VALUES(140103001, 5546, 070576, '���帧 ġ��', STR_TO_DATE('2014-01-03','%Y-%m-%d'));");
	  	stmt.executeUpdate("INSERT INTO Treatments VALUES(140109026, 4543, 050101, '����', STR_TO_DATE('2014-01-09','%Y-%m-%d'));");
	  	stmt.executeUpdate("INSERT INTO Treatments VALUES(140226102, 9768, 091001, 'ȭ��ġ��', STR_TO_DATE('2014-02-26','%Y-%m-%d'));");
	  	stmt.executeUpdate("INSERT INTO Treatments VALUES(140303003, 4234, 091001, '������ �ܻ�ġ��', STR_TO_DATE('2014-03-03','%Y-%m-%d'));");
	  	stmt.executeUpdate("INSERT INTO Treatments VALUES(140308087, 7643, 062019, '�忰', STR_TO_DATE('2014-03-08','%Y-%m-%d'));");
	  	
	  	stmt.executeUpdate("INSERT INTO Charts VALUES('PD13572410', 130516023, 980312, 2345, 050302, '����, ����� ó��');");
	  	stmt.executeUpdate("INSERT INTO Charts VALUES('DM11389132', 130628100, 020403, 3545, 040089, '�Ǻξ� ó��');");
	  	stmt.executeUpdate("INSERT INTO Charts VALUES('RD10023842', 131205056, 080543, 3424, 070605, '�� ��ũ �ǽ�, �߰� �˻� �ʿ�');");
	  	stmt.executeUpdate("INSERT INTO Charts VALUES('PD13581241', 131218024, 050900, 7675, 100356, '���ݰ��� �߰� �˻� �ʿ�');");
	  	stmt.executeUpdate("INSERT INTO Charts VALUES('IM12557901', 131224012, 000601, 4533, 070804, '����� ó��');");
	  	stmt.executeUpdate("INSERT INTO Charts VALUES('DM11400021', 140103001, 070576, 5546, 120309, '���帧 ġ���� ó��');");
	  	stmt.executeUpdate("INSERT INTO Charts VALUES('IM12708224', 140109026, 050101, 4543, 070804, '���� �ɰ�, �߰� �˻� �� ���� ����');");
	  	
	  	stmt.executeUpdate("INSERT INTO Charts VALUES('GS17223681', 140226102, 091001, 9768, 130211, 'ȭ��� ó�� �� ����ġ��');");
	  	stmt.executeUpdate("INSERT INTO Charts VALUES('GS17264430', 140303003, 091001, 4234, 130211, '�߰� �������� �ʿ���');");
	  	stmt.executeUpdate("INSERT INTO Charts VALUES('PD13664611', 140308087, 062019, 7643, 071018, '�忰�� ó��');");
	  	
	     } catch(SQLException e) {
	         e.printStackTrace();
	       }
	   }
   
   public void Insert(String tableName, String sql, JLabel result) {
	   if(tableName.equals("Doctors") || tableName.equals("Nurses") || tableName.equals("Patients") || tableName.equals("Treatments") || tableName.equals("Charts")) {
		   try {
		  	  	 Statement stmt=con.createStatement();
		  	  	 stmt.executeUpdate(sql);
		  	  } catch(SQLException e) { 
		  		  result.setText("SQL���� �ٽ� �Է����ּ���");
		  	    }	
	   }
	   else {
		   result.setText("���̺� �̸��� �ٽ� �Է����ּ���");
	   }
   }
   
   public void AllSearch(JTextArea resultLabel) {
	   resultLabel.append("\t\tdoc_id \tmajor_treat \t\tdoc_name \t\tdoc_gen \t\tdoc_phone \t\tdoc_email\t\tdoc_position \n");
	   
	   try {
	  	  	 Statement stmt=con.createStatement();
	  	  	 ResultSet rs=stmt.executeQuery("select * from Doctors");
	  	  	 int i=1;
	  	  	 
	  	  	 while(rs.next()) {
	  	  		resultLabel.append(i++ + "\t");
	  	  		resultLabel.append("\t"+String.format("%06d",rs.getInt(1)));
	  	  		resultLabel.append("\t"+rs.getString(2));
	  	  		resultLabel.append("\t\t"+rs.getString(3));
	  	  		resultLabel.append("\t\t"+rs.getString(4));
	  	  		resultLabel.append("\t\t"+rs.getString(5));
	  	  		resultLabel.append("\t\t"+rs.getString(6));
	  	  		resultLabel.append("\t"+rs.getString(7)+"\n");
	  	  	 }
	  	  } catch(SQLException e) {
	  	  	   e.printStackTrace();
	  	    }
	   resultLabel.append("\n");
	   
	   try {
	  	 Statement stmt=con.createStatement();
	  	 ResultSet rs=stmt.executeQuery("select * from Nurses");
	  	 int i=1;
	  	 
	  	 resultLabel.append("\t\tnur_id \tmajor_job \t\tnur_name \t\tnur_gen \t\tnur_phone \t\tnur_email \t\tnur_position\n");
	  	 while(rs.next()) {
	  		resultLabel.append(i++ + "\t");
	  		resultLabel.append("\t"+String.format("%06d",rs.getInt(1)));
	  		resultLabel.append("\t"+rs.getString(2));
	  		resultLabel.append("\t\t"+rs.getString(3));
	  		resultLabel.append("\t\t"+rs.getString(4));
	  		resultLabel.append("\t\t"+rs.getString(5));
	  		resultLabel.append("\t\t"+rs.getString(6));
	  		resultLabel.append("\t"+rs.getString(7)+"\n");
	  	 }
	   } catch(SQLException e) {
	  	   e.printStackTrace();
	    }
	   resultLabel.append("\n");
   
	   try {
	  	 Statement stmt=con.createStatement();
	  	 ResultSet rs=stmt.executeQuery("select * from Patients");
	  	 int i=1;
	  	 
	  	 resultLabel.append("\t\tpat_id \tnur_id \tdoc_id \tpat_name \t\tpat_gen \t\tpat_jumin \t\tpat_addr \t\tpat_phone \t\tpat_email \t\tpat_job\n");
	  	 while(rs.next()) {
	  		resultLabel.append(i++ + "\t");
	  		resultLabel.append("\t"+rs.getInt(1));
	  		resultLabel.append("\t"+String.format("%06d",rs.getInt(2)));
	  		resultLabel.append("\t"+rs.getInt(3));
	  		resultLabel.append("\t"+rs.getString(4));
	  		resultLabel.append("\t\t"+rs.getString(5));
	  		resultLabel.append("\t\t"+rs.getString(6));
	  		resultLabel.append("\t\t"+rs.getString(7));
	  		resultLabel.append("\t\t"+rs.getString(8));
	  		resultLabel.append("\t\t"+rs.getString(9));
	  		resultLabel.append("\t\t"+rs.getString(10)+"\n");
	  	 }
	  } catch(SQLException e) {
	  	   e.printStackTrace();
	    }
	   resultLabel.append("\n");
   
	   try {
	  	 Statement stmt=con.createStatement();
	  	 ResultSet rs=stmt.executeQuery("select * from Treatments");
	  	 int i=1;
	  	 
	  	 resultLabel.append("\t\ttreat_id \tpat_id \tdoc_id \ttreat_contents \t\ttreat_date\n");
	  	 while(rs.next()) {
	  		resultLabel.append(i++ + "\t");
	  		resultLabel.append("\t"+rs.getInt(1));
	  		resultLabel.append("\t"+rs.getInt(2));
	  		resultLabel.append("\t"+String.format("%06d",rs.getInt(3)));
	  		resultLabel.append("\t"+rs.getString(4));
	  		resultLabel.append("\t\t"+rs.getString(5)+"\n");
	  	 }
	  } catch(SQLException e) {
	  	   e.printStackTrace();
	    }
	   resultLabel.append("\n");
   
   try {
	  	 Statement stmt=con.createStatement();
	  	 ResultSet rs=stmt.executeQuery("select * from Charts");
	  	 int i=1;
	  	 resultLabel.append("\t\tchart_id \ttreat_id \tdoc_id \tpat_id \tnur_id \t\tchar_contents\n");
	  	 while(rs.next()) {
	  		resultLabel.append(i++ + "\t");
	  		resultLabel.append("\t"+rs.getString(1));
	  		resultLabel.append("\t"+rs.getInt(2));
	  		resultLabel.append("\t"+String.format("%06d",rs.getInt(3)));
	  		resultLabel.append("\t"+rs.getInt(4));
	  		resultLabel.append("\t"+String.format("%06d",rs.getInt(5)));
	  		resultLabel.append("\t\t"+rs.getString(6)+"\n");
	  	 }
	  } catch(SQLException e) {
	  	   e.printStackTrace();
	    }
   resultLabel.append("\n");
   }
   
   public void Search1(JTextArea resultLabel) {
	   try {
		  	 Statement stmt=con.createStatement();
		  	 ResultSet rs=stmt.executeQuery("select pat_name, pat_addr, pat_phone, treat_contents, treat_date\r\n"
		  	 		+ "from Patients, treatments\r\n"
		  	 		+ "where patients.doc_id = (select doc_id from Doctors where doc_name = \"�躴��\") and \r\n"
		  	 		+ "	patients.pat_id = treatments.pat_id;");
		  	 int i=1;
		  	resultLabel.append("\t\tpat_name \tpat_addr \tpat_phone \ttreat_contents \t\ttreat_date\n");
		  	 while(rs.next()) {
		  		resultLabel.append(i++ + "\t");
		  		resultLabel.append("\t"+rs.getString(1));
		  		resultLabel.append("\t "+rs.getString(2));
		  		resultLabel.append("\t"+rs.getString(3));
		  		resultLabel.append("\t"+rs.getString(4));
		  		resultLabel.append("\t\t"+rs.getString(5)+"\n");
		  	 }
		  } catch(SQLException e) {
		  	   e.printStackTrace();
		    }
	   resultLabel.append("\n");
   }
   
   public void Search2(JTextArea resultLabel) {
	   try {
		  	 Statement stmt=con.createStatement();
		  	 ResultSet rs=stmt.executeQuery("select *\r\n"
		  	 		+ "from Doctors\r\n"
		  	 		+ "where Doctors.doc_id Not IN (select doc_id from treatments);");
		  	 int i=1;
		  	 
		  	resultLabel.append("\t\tdoc_id \tmajor_treat \tdoc_name \tdoc_gen \tdoc_phone \t\tdoc_email\t\tdoc_position\n");
	  	  	 while(rs.next()) {
	  	  		resultLabel.append(i++ + "\t");
	  	  		resultLabel.append("\t"+String.format("%06d",rs.getInt(1)));
	  	  		resultLabel.append("\t"+rs.getString(2));
	  	  		resultLabel.append("\t"+rs.getString(3));
	  	  		resultLabel.append("\t"+rs.getString(4));
	  	  		resultLabel.append("\t"+rs.getString(5));
	  	  		resultLabel.append("\t\t"+rs.getString(6));
	  	  		resultLabel.append("\t"+rs.getString(7)+"\n");
	  	  	 }
		  } catch(SQLException e) {
		  	   e.printStackTrace();
		    }
	   resultLabel.append("\n");
   }
   
   public void Search3(JTextArea resultLabel) {
	   try {
		  	 Statement stmt=con.createStatement();
		  	 ResultSet rs=stmt.executeQuery("select *\r\n"
		  	 		+ "from Doctors\r\n"
		  	 		+ "where doctors.doc_id = (select doc_id \r\n"
		  	 		+ "						from (select count(*) AS cnt, doc_id from treatments group by treatments.doc_id) A\r\n"
		  	 		+ "						where cnt = (select max(cnt) from (select count(*) AS cnt, doc_id from treatments group by treatments.doc_id) B));");
		  	 int i=1;
		  	 
		  	resultLabel.append("\t\tdoc_id \tmajor_treat \tdoc_name \tdoc_gen \tdoc_phone \t\tdoc_email\t\tdoc_position\n");
	  	  	 while(rs.next()) {
	  	  		resultLabel.append(i++ + "\t");
	  	  		resultLabel.append("\t"+String.format("%06d",rs.getInt(1)));
	  	  		resultLabel.append("\t"+rs.getString(2));
	  	  		resultLabel.append("\t"+rs.getString(3));
	  	  		resultLabel.append("\t"+rs.getString(4));
	  	  		resultLabel.append("\t"+rs.getString(5));
	  	  		resultLabel.append("\t\t"+rs.getString(6));
	  	  		resultLabel.append("\t"+rs.getString(7)+"\n");
	  	  	 }
		  } catch(SQLException e) {
		  	   e.printStackTrace();
		    }
	   resultLabel.append("\n");
   }
}
   
public class databaseProject {
 public static void main(String args[]) {
  	new Title();
 }
}
