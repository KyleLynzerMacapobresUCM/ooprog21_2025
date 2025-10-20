package Chapter_0;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.time.LocalDate;

public class AccountingApp extends JFrame {

    // === DATABASE ===
    static final String DB_URL = "jdbc:sqlite:accounting.db";
    static {
        try (Connection c = DriverManager.getConnection(DB_URL);
             Statement s = c.createStatement()) {
            s.execute("""
                CREATE TABLE IF NOT EXISTS accounts(
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    code TEXT UNIQUE NOT NULL,
                    name TEXT NOT NULL,
                    type TEXT NOT NULL
                );
            """);
            s.execute("""
                CREATE TABLE IF NOT EXISTS transactions(
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    date TEXT NOT NULL,
                    description TEXT
                );
            """);
            s.execute("""
                CREATE TABLE IF NOT EXISTS transaction_lines(
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    transaction_id INTEGER,
                    account_id INTEGER,
                    debit REAL DEFAULT 0,
                    credit REAL DEFAULT 0,
                    FOREIGN KEY(transaction_id) REFERENCES transactions(id),
                    FOREIGN KEY(account_id) REFERENCES accounts(id)
                );
            """);
           
            s.execute("INSERT OR IGNORE INTO accounts(code,name,type) VALUES('1000','Cash','ASSET');");
            s.execute("INSERT OR IGNORE INTO accounts(code,name,type) VALUES('2000','Accounts Payable','LIABILITY');");
            s.execute("INSERT OR IGNORE INTO accounts(code,name,type) VALUES('3000','Equity','EQUITY');");
            s.execute("INSERT OR IGNORE INTO accounts(code,name,type) VALUES('4000','Revenue','INCOME');");
            s.execute("INSERT OR IGNORE INTO accounts(code,name,type) VALUES('5000','Expense','EXPENSE');");
        } catch (SQLException e) { e.printStackTrace(); }
    }

   
    private JTabbedPane tabs = new JTabbedPane();

   
    public AccountingApp() {
        setTitle("Simple Accounting App");
        setSize(900,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tabs.add("Accounts", createAccountsPanel());
        tabs.add("Transactions", createTransactionsPanel());
        tabs.add("Reports", createReportsPanel());
        add(tabs);
    }

    
    private JPanel createAccountsPanel() {
        JPanel p = new JPanel(new BorderLayout());
        DefaultListModel<String> model = new DefaultListModel<>();
        JList<String> list = new JList<>(model);
        JTextField code = new JTextField(6);
        JTextField name = new JTextField(15);
        JComboBox<String> type = new JComboBox<>(new String[]{"ASSET","LIABILITY","EQUITY","INCOME","EXPENSE"});
        JButton add = new JButton("Add Account");

        JPanel form = new JPanel();
        form.add(new JLabel("Code:")); form.add(code);
        form.add(new JLabel("Name:")); form.add(name);
        form.add(new JLabel("Type:")); form.add(type);
        form.add(add);

        p.add(form, BorderLayout.NORTH);
        p.add(new JScrollPane(list), BorderLayout.CENTER);

        add.addActionListener(e -> {
            try (Connection c = DriverManager.getConnection(DB_URL);
                 PreparedStatement ps = c.prepareStatement("INSERT INTO accounts(code,name,type) VALUES(?,?,?)")) {
                ps.setString(1, code.getText().trim());
                ps.setString(2, name.getText().trim());
                ps.setString(3, (String) type.getSelectedItem());
                ps.executeUpdate();
                JOptionPane.showMessageDialog(p, "Account added.");
                code.setText(""); name.setText("");
                loadAccounts(model);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(p, "Error: " + ex.getMessage());
            }
        });

        loadAccounts(model);
        return p;
    }

    private void loadAccounts(DefaultListModel<String> model) {
        model.clear();
        try (Connection c = DriverManager.getConnection(DB_URL);
             Statement s = c.createStatement();
             ResultSet rs = s.executeQuery("SELECT code,name,type FROM accounts ORDER BY code")) {
            while (rs.next()) {
                model.addElement(rs.getString("code")+" - "+rs.getString("name")+" ("+rs.getString("type")+")");
            }
        } catch (SQLException e) { e.printStackTrace(); }
    }

    
    private JPanel createTransactionsPanel() {
        JPanel p = new JPanel(new BorderLayout());
        JPanel form = new JPanel(new GridLayout(6,2,5,5));

        JTextField dateField = new JTextField(LocalDate.now().toString());
        JTextField descField = new JTextField();
        JComboBox<String> debitAcc = new JComboBox<>();
        JComboBox<String> creditAcc = new JComboBox<>();
        JTextField amtField = new JTextField();
        JButton record = new JButton("Record Transaction");

        form.add(new JLabel("Date (YYYY-MM-DD):")); form.add(dateField);
        form.add(new JLabel("Description:")); form.add(descField);
        form.add(new JLabel("Debit Account:")); form.add(debitAcc);
        form.add(new JLabel("Credit Account:")); form.add(creditAcc);
        form.add(new JLabel("Amount:")); form.add(amtField);
        form.add(new JLabel("")); form.add(record);
        p.add(form, BorderLayout.NORTH);

        loadAccountChoices(debitAcc);
        loadAccountChoices(creditAcc);

        record.addActionListener(e -> {
            try (Connection c = DriverManager.getConnection(DB_URL)) {
                c.setAutoCommit(false);
                try {
                    String date = dateField.getText().trim();
                    String desc = descField.getText().trim();
                    double amt = Double.parseDouble(amtField.getText().trim());
                    int debitId = Integer.parseInt(debitAcc.getSelectedItem().toString().split(" ")[0]);
                    int creditId = Integer.parseInt(creditAcc.getSelectedItem().toString().split(" ")[0]);

                    PreparedStatement tx = c.prepareStatement("INSERT INTO transactions(date,description) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS);
                    tx.setString(1, date);
                    tx.setString(2, desc);
                    tx.executeUpdate();
                    ResultSet keys = tx.getGeneratedKeys();
                    int txId = keys.next() ? keys.getInt(1) : 0;

                    PreparedStatement line = c.prepareStatement("INSERT INTO transaction_lines(transaction_id,account_id,debit,credit) VALUES(?,?,?,?)");
                    line.setInt(1, txId); line.setInt(2, debitId); line.setDouble(3, amt); line.setDouble(4, 0); line.executeUpdate();
                    line.setInt(1, txId); line.setInt(2, creditId); line.setDouble(3, 0); line.setDouble(4, amt); line.executeUpdate();

                    c.commit();
                    JOptionPane.showMessageDialog(p, "Transaction recorded.");
                    amtField.setText("");
                } catch (Exception ex) {
                    c.rollback();
                    JOptionPane.showMessageDialog(p, "Error: "+ex.getMessage());
                }
            } catch (SQLException ex) { ex.printStackTrace(); }
        });

        return p;
    }

    private void loadAccountChoices(JComboBox<String> combo) {
        combo.removeAllItems();
        try (Connection c = DriverManager.getConnection(DB_URL);
             Statement s = c.createStatement();
             ResultSet rs = s.executeQuery("SELECT id,code,name FROM accounts ORDER BY code")) {
            while (rs.next()) {
                combo.addItem(rs.getInt("id")+" "+rs.getString("code")+" - "+rs.getString("name"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
    }

    
    private JPanel createReportsPanel() {
        JPanel p = new JPanel(new BorderLayout());
        DefaultTableModel tm = new DefaultTableModel(new Object[]{"Code","Account","Type","Debit","Credit","Net"},0);
        JTable table = new JTable(tm);
        JButton refresh = new JButton("Refresh Trial Balance");

        p.add(refresh, BorderLayout.NORTH);
        p.add(new JScrollPane(table), BorderLayout.CENTER);

        refresh.addActionListener(e -> {
            tm.setRowCount(0);
            try (Connection c = DriverManager.getConnection(DB_URL);
                 Statement s = c.createStatement();
                 ResultSet rs = s.executeQuery("""
                    SELECT a.code,a.name,a.type,
                           IFNULL(SUM(tl.debit),0) AS d,
                           IFNULL(SUM(tl.credit),0) AS c
                    FROM accounts a
                    LEFT JOIN transaction_lines tl ON a.id = tl.account_id
                    GROUP BY a.id,a.code,a.name,a.type
                    ORDER BY a.code;
                 """)) {
                double td=0, tc=0;
                while (rs.next()) {
                    double d = rs.getDouble("d"), cr = rs.getDouble("c");
                    tm.addRow(new Object[]{rs.getString("code"),rs.getString("name"),rs.getString("type"),
                            String.format("%.2f",d),String.format("%.2f",cr),String.format("%.2f",(d-cr))});
                    td+=d; tc+=cr;
                }
                tm.addRow(new Object[]{"","TOTAL","",String.format("%.2f",td),String.format("%.2f",tc),""});
            } catch (SQLException ex) { ex.printStackTrace(); }
        });

        refresh.doClick(); 
        return p;
    }

    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AccountingApp().setVisible(true));
    }
}
