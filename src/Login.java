import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login extends JFrame{

    private JTextField fName;
    private JButton clickMeButton;
    private JPanel MainPanel;
    private JTextField lName;
    private JLabel user;
    private JLabel pass;
    private JButton crearNuevoUsuarioButton;
    private JLabel icono;
    private JLabel entrar;

    public Login(){
        setContentPane(MainPanel);
        setTitle("Login");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        insertarIcono("src/agregar-usuario.png",icono);
        insertarIcono("src/entrar.png",entrar);

        setVisible(true);

        MainPanel.setBackground(Color.LIGHT_GRAY);

        user.setFont(new Font("Arial", Font.BOLD, 14));
        pass.setFont(new Font("Arial", Font.BOLD, 14));


        Font font = new Font("Arial", Font.BOLD, 14);
        fName.setFont(font);
        lName.setFont(font);

        clickMeButton.setBackground(Color.BLUE);
        clickMeButton.setForeground(Color.WHITE);
        clickMeButton.setFont(font);
        crearNuevoUsuarioButton.setBackground(Color.BLUE);
        crearNuevoUsuarioButton.setForeground(Color.WHITE);
        crearNuevoUsuarioButton.setFont(font);
        clickMeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fN;
                String lN;
                String query;
                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    String url = "jdbc:mysql://localhost:3306/usersseguros";
                    String user = "root";
                    String pass = "";

                    System.out.println("Conexión exitosa");

                    Connection con = DriverManager.getConnection(url, user, pass);

                    if ("".equals(fName.getText())) {
                        JOptionPane.showMessageDialog(new JFrame(), "Usuario es requerido", "Dialog", JOptionPane.ERROR_MESSAGE);
                        fName.setText("");
                        lName.setText("");
                    } else if ("".equals(lName.getText())) {
                        JOptionPane.showMessageDialog(new JFrame(), "Contraseña es requerida", "Dialog", JOptionPane.ERROR_MESSAGE);
                        fName.setText("");
                        lName.setText("");
                    } else {
                        fN = fName.getText();
                        lN = lName.getText();

                        query = "SELECT COUNT(*) FROM usuarios WHERE Correo = ? AND password = ?";
                        PreparedStatement pst = con.prepareStatement(query);
                        pst.setString(1, fN);
                        pst.setString(2, lN);

                        ResultSet rs = pst.executeQuery();

                        if (rs.next() && rs.getInt(1) > 0) {
                            JOptionPane.showMessageDialog(new JFrame(), "Inicio de sesión exitoso", "Dialog", JOptionPane.INFORMATION_MESSAGE);
                            fName.setText("");
                            lName.setText("");
                        } else {
                            JOptionPane.showMessageDialog(new JFrame(), "Usuario o contraseña incorrectos", "Dialog", JOptionPane.ERROR_MESSAGE);
                        }
                    }

                }catch(Exception er){
                    System.out.println("Error de conexion"+er.getMessage());
                }
            }
        });
        crearNuevoUsuarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Register();
            }
        });
    }
    public void insertarIcono(String ruta,JLabel icono){
        ImageIcon imageIcon = new ImageIcon(ruta);
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        icono.setIcon(imageIcon); // Agrega el icono al JLabel
    }



    // Path: src/Register.java
    public class Register extends JFrame {
        private final JTextField  nombresField;
        private final JTextField apellidosField;
        private final JTextField email;
        private final JPasswordField password;
        private final JButton registerButton;
        private final JButton returnButton;
        public Register() {
            final String[] nombres = new String[1];
            final String[] apellidos = new String[1];
            final String[] correo = new String[1];
            final String[] pass = new String[1];
            final String[] query = new String[1];
            setTitle("Registro de usuario");
            setSize(500, 400);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);
            JPanel panel = new JPanel();

            panel.setLayout(new GridLayout(6, 2, 10, 10));

            panel.add(new JLabel("Nombres:"));
            nombresField = new JTextField();
            panel.add(nombresField);

            panel.add(new JLabel("Apellidos:"));
            apellidosField = new JTextField();
            panel.add(apellidosField);

            panel.add(new JLabel("Email:"));
            email = new JTextField();
            panel.add(email);

            panel.add(new JLabel("Contraseña:"));
            password = new JPasswordField();

            panel.add(password);

            returnButton = new JButton("Regresar");
            panel.add(returnButton);

            registerButton = new JButton("Registrar");
            panel.add(registerButton);

            Decoration(panel);

            add(panel);

            setVisible(true);

            registerButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    nombres[0] = nombresField.getText();
                    apellidos[0] = apellidosField.getText();
                    correo[0] = email.getText();
                    pass[0] = String.valueOf(password.getPassword());

                    System.out.println(pass[0]);

                    if(validarDatos(nombres[0], apellidos[0], correo[0], pass[0])){
                        try {
                            Class.forName("com.mysql.cj.jdbc.Driver");
                            String url = "jdbc:mysql://localhost:3306/usersseguros";
                            String user = "root";
                            String passw = "";

                            Connection con = DriverManager.getConnection(url, user, passw);

                            query[0] = "INSERT INTO usuarios (Nombres, Apellidos, Correo, password) VALUES (?, ?, ?, ?)";
                            PreparedStatement pst = con.prepareStatement(query[0]);
                            pst.setString(1, nombres[0]);
                            pst.setString(2, apellidos[0]);
                            pst.setString(3, correo[0]);
                            pst.setString(4, pass[0]);

                            int res = pst.executeUpdate();

                            if (res > 0) {
                                JOptionPane.showMessageDialog(new JFrame(), "Usuario registrado", "Dialog", JOptionPane.INFORMATION_MESSAGE);
                                nombresField.setText("");
                                apellidosField.setText("");
                                email.setText("");
                                password.setText("");
                                setVisible(false);
                                new Login();

                            } else {
                                JOptionPane.showMessageDialog(new JFrame(), "Error al registrar usuario", "Dialog", JOptionPane.ERROR_MESSAGE);
                            }

                        } catch (Exception er) {
                            System.out.println("Error de conexion" + er.getMessage());
                        }
                    }else{
                        JOptionPane.showMessageDialog(new JFrame(), "Error al registrar usuario", "Dialog", JOptionPane.ERROR_MESSAGE);
                    }
                }



            });

            returnButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setVisible(false);
                    new Login();
                }
            });
        }


        public void Decoration(JPanel jPanel){
            jPanel.setBackground(Color.LIGHT_GRAY);

            nombresField.setFont(new Font("Arial", Font.BOLD, 14));
            apellidosField.setFont(new Font("Arial", Font.BOLD, 14));
            email.setFont(new Font("Arial", Font.BOLD, 14));
            password.setFont(new Font("Arial", Font.BOLD, 14));

            Font font = new Font("Arial", Font.BOLD, 14);
            nombresField.setFont(font);
            apellidosField.setFont(font);
            email.setFont(font);
            password.setFont(font);

            registerButton.setBackground(Color.BLUE);
            registerButton.setForeground(Color.WHITE);
            registerButton.setFont(font);

            returnButton.setBackground(Color.BLUE);
            returnButton.setForeground(Color.WHITE);
            returnButton.setFont(font);
        }
    }

    public boolean validarDatos(String nombres, String apellidos, String correo, String pass){
        if ("".equals(nombres)) {
            JOptionPane.showMessageDialog(new JFrame(), "Nombres es requerido", "Dialog", JOptionPane.ERROR_MESSAGE);
        } else if ("".equals(apellidos)) {
            JOptionPane.showMessageDialog(new JFrame(), "Apellidos es requerido", "Dialog", JOptionPane.ERROR_MESSAGE);
        } else if ("".equals(correo)) {
            JOptionPane.showMessageDialog(new JFrame(), "Correo es requerido", "Dialog", JOptionPane.ERROR_MESSAGE);
        } else if ("".equals(pass)) {
            JOptionPane.showMessageDialog(new JFrame(), "Contraseña es requerida", "Dialog", JOptionPane.ERROR_MESSAGE);
        }
        else{
            return validarCorreo(correo);
        }
        return false;
    }
    private boolean validarCorreo(String correo){
        if (!correo.contains("@") || !correo.contains(".")) {
            JOptionPane.showMessageDialog(new JFrame(), "Correo no válido", "Dialog", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        new Login();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
