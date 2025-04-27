package chatting_application;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.net.*;

public class server implements ActionListener{
    static DataOutputStream dout;
    JTextField text1;
    static JScrollPane sp;
    static JPanel a1;
    static Box vertical =  Box.createVerticalBox();
    static JFrame f = new JFrame();
    server(){
        f.setLayout(null);
        JPanel p1= new JPanel();
        p1.setBackground(new Color(7,94,84));
        p1.setBounds(0,0,450,60);
        p1.setLayout(null);
        f.add(p1);

        ImageIcon I1 = new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));
        Image I2 = I1.getImage().getScaledInstance(25,25,Image.SCALE_DEFAULT);
        ImageIcon I3 = new ImageIcon(I2);
        JLabel back = new JLabel(I3);
        back.setBounds(5,20,25,25);
        p1.add(back);

        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });

        ImageIcon I4 = new ImageIcon(ClassLoader.getSystemResource("icons/Sharma Ji.png"));
        Image I5 = I4.getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT);
        ImageIcon I6 = new ImageIcon(I5);
        JLabel profile = new JLabel(I6);
        profile.setBounds(25,5,60,50);
        p1.add(profile);

        ImageIcon I7 = new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));
        Image I8 = I7.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
        ImageIcon I9 = new ImageIcon(I8);
        JLabel video = new JLabel(I9);
        video.setBounds(300,18,30,30);
        p1.add(video);

        ImageIcon I10 = new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));
        Image I11 = I10.getImage().getScaledInstance(35,30,Image.SCALE_DEFAULT);
        ImageIcon I12 = new ImageIcon(I11);
        JLabel phone = new JLabel(I12);
        phone.setBounds(350,18,35,30);
        p1.add(phone);

        ImageIcon I13 = new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png"));
        Image I14 = I13.getImage().getScaledInstance(10,25,Image.SCALE_DEFAULT);
        ImageIcon I15 = new ImageIcon(I14);
        JLabel icon3 = new JLabel(I15);
        icon3.setBounds(405,18,10,25);
        p1.add(icon3);

        JLabel name = new JLabel("Sharma Ji");
        name.setBounds(80,8,150,30);
        name.setForeground(Color.WHITE);
        name.setFont(new Font("SAN_SERIF",Font.BOLD,18));
        p1.add(name);

        JLabel status = new JLabel("Online");
        status.setBounds(80,23,150,30);
        status.setForeground(Color.WHITE);
        status.setFont(new Font("SAN_SERIF",Font.PLAIN,12));
        p1.add(status);

        a1 = new JPanel();
        a1.setLayout(new BoxLayout(a1, BoxLayout.Y_AXIS)); // Layout important for vertical stacking

        sp = new JScrollPane(a1);
        sp.setBounds(5, 65, 440, 480);
        sp.setBorder(BorderFactory.createEmptyBorder());
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        f.add(sp);


        text1 = new JTextField();
        text1.setBounds(5,547,310,40);
        text1.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
        f.add(text1);

        JButton send = new JButton("Send");
        send.setBounds(320,547,123,40);
        send.setBackground(new Color(7,94,84));
        send.setForeground(Color.WHITE);
        send.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
        send.addActionListener(this);
        f.add(send);

        f.setSize(450,590);
        f.setUndecorated(true);
        f.setLocation(200,50);
        f.getContentPane().setBackground(Color.WHITE);

        f.setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        try{
            String out1=text1.getText();
            JPanel p2 = formatLabel(out1);
            a1.setLayout(new BorderLayout());
            JPanel right = new JPanel(new BorderLayout());
            right.add(p2,BorderLayout.LINE_END);
            vertical.add(right);
            vertical.add(Box.createVerticalStrut(15));
            a1.add(vertical,BorderLayout.PAGE_START);
            dout.writeUTF(out1);
            text1.setText("");
            f.repaint();
            f.invalidate();
            f.validate();
            sp.getVerticalScrollBar().setValue(sp.getVerticalScrollBar().getMaximum());
        } catch (Exception e){
            e.printStackTrace();
        }

    }
    public static JPanel formatLabel(String out1){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        JLabel out = new JLabel("<html><p style=\"width: 150px\">"+out1+"</html>");
        out.setFont(new Font("Tahoma",Font.PLAIN,16));
        out.setBackground(new Color(37,211,102));
        out.setOpaque(true);
        out.setBorder(new EmptyBorder(15,15,15,50));
        panel.add(out);

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        JLabel time = new JLabel();
        time.setText(sdf.format(cal.getTime()));

        panel.add(time);

        return panel;
    }
    public static void main(String[] args){
        new server();
        try{
            ServerSocket skt = new ServerSocket(6001);
            while(true){
                Socket s = skt.accept();
                DataInputStream din = new DataInputStream(s.getInputStream());
                dout = new DataOutputStream(s.getOutputStream());

                while(true){
                    String msg = din.readUTF();
                    JPanel panel = formatLabel(msg);
                    JPanel left = new JPanel(new BorderLayout());
                    left.add(panel,BorderLayout.LINE_START);
                    vertical.add(left);
                    vertical.add(Box.createVerticalStrut(15));

                    a1.add(vertical, BorderLayout.PAGE_START);

                    f.validate();
                    f.repaint();
                    sp.getVerticalScrollBar().setValue(sp.getVerticalScrollBar().getMaximum());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
