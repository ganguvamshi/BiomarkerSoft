package biomarker;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.border.*;

public class LoginPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JLabel lbPassword;
    private JButton btnLogin;
    JPanel panel = new JPanel(new GridBagLayout());
    JFrame main ;
    private final static int DEFAULT_PSWD_CHARS = 10;
    private JPasswordField passwordField =null ;
    
	public LoginPanel(JFrame mainFrame){
		super();
		main = mainFrame;
	}
	
	public JPanel showPanel(){
        GridBagConstraints cs = new GridBagConstraints();
        cs.fill = GridBagConstraints.HORIZONTAL;
        
        lbPassword = new JLabel("Password: ");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.weighty = 10;
        panel.add(lbPassword, cs);
 
        cs.gridx = 1;
        cs.gridy = 0;
        cs.weighty = 10;
        panel.setBorder(new LineBorder(Color.GRAY));
   
        passwordField = new JPasswordField(DEFAULT_PSWD_CHARS);
        passwordField.setToolTipText("Only for Admin");
        panel.add(passwordField, cs);
        btnLogin = new JButton("Login");
        panel.add(btnLogin);
        ActionListener actionButton = new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent event) {
                event.getSource();
                if(authenticate(passwordField.getPassword())){
					//ConstantPanel constants = new ConstantPanel(main, "Constants");
                	Constants constants = new Constants(main, "Constants");
                    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
              	  	constants.setLocation(dim.width / 2 - constants.getSize().width / 2,
              				dim.height / 2 - constants.getSize().height / 6);
					constants.setVisible(true);
					if(!constants.isShowing()){
						passwordField.setText(null);
					}
                }
                else{
                	JOptionPane.showMessageDialog(main, "Invalid password", "Login",
                            JOptionPane.ERROR_MESSAGE);	
                }
            }
        };
        btnLogin.addActionListener(actionButton);
        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent event) {
                JPasswordField field = (JPasswordField) event.getSource();
                char[] password = field.getPassword();
                if (password == null || password.length == 0) {
                    //passwordField.setEnabled(false);
                } else {
                    passwordField.setEnabled(true);
                }
            }
        });
        return panel;
	}

	public  char[] getPfPassword() {
		return passwordField.getPassword();
	}

	public void setPfPassword(JPasswordField pfPassword) {
	}
	public static boolean authenticate(char[] jPasswordField) {
        if (Arrays.equals(jPasswordField, Thresholds.AdminPWD)) {
            return true;
        }
        return false;
    }
}
