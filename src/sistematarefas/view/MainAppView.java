package sistematarefas.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MainAppView extends JFrame {

    private JPanel contentPanel; //Conteúdo principal
    private JPanel menuSelecionado;


    public MainAppView(){
        initComponents();
    }

    public static void main(String[] args)
    {
        new MainAppView().setVisible(true); // Deixa a tela visível
    }

    public void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Gerenciador de Tarefas");
        setSize(new Dimension(1280, 720));

        GridLayout layout = new GridLayout(1, 2);
        setLayout(layout);

        setLocationRelativeTo(null);

        // Adiciona componente no Jframe
        add(mainPanel());
    }

    public JPanel mainPanel() {
        JPanel mainPanel = new JPanel(null);
        mainPanel.setBackground(new Color(10, 20, 30));

        // Menu Bem-vindo
        contentPanel = new JPanel();
        contentPanel.setBounds(200, 50, 1080, 670);
        contentPanel.setBackground(new Color(15, 25, 35));
        contentPanel.setForeground(Color.WHITE);
        contentPanel.setLayout(new BorderLayout());
        var bemVindo = new JLabel("Bem-vindo!", SwingConstants.CENTER);
        bemVindo.setFont(new Font("Arial", Font.BOLD, 50));
        bemVindo.setForeground(Color.WHITE);
        // Tela inicial
        contentPanel.add(bemVindo);

        // Adiciona componentes no mainPanel
        mainPanel.add(contentPanel);
        mainPanel.add(header());
        mainPanel.add(sideBar(new String[] {"Projetos", "Funcionários", "Gestores", "Departamentos"}));

        return mainPanel;
    }

    public JPanel sideBar(String[] values)
    {
        JPanel panel = new JPanel(null);
        FlowLayout layout = new FlowLayout(FlowLayout.LEFT, 5, 5);

        panel.setLayout(layout);
        panel.setBounds(0, 50, 200, 700);
        panel.setBackground(new Color(25, 35, 45));


        for (int i = 0; i < values.length; i++)
        {
            JPanel menus = new JPanel(new GridLayout(1, 1));
            menus.setPreferredSize(new Dimension(200, 45));
            menus.setBackground(new Color(25, 35, 45));


            JLabel valores = new JLabel(" " + values[i]);
            valores.setForeground(new Color(90, 180, 255));

            menus.add(valores);


            menus.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    trocarTela(valores.getText().trim());

                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    menus.setBackground(new Color(40, 60, 80));
                    valores.setForeground(Color.WHITE);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    menus.setBackground(new Color(25, 35, 45));
                    valores.setForeground(new Color(90, 180, 255));
                }
            });

            panel.add(menus);
        }

        return panel;
    }

    public JPanel header()
    {
        JPanel header = new JPanel(null);
        header.setBackground(new Color(25, 35, 45));
        header.setBounds(0,0, 1280, 45);

        JLabel titulo = new JLabel("Gerenciador de Tarefas");
        JLabel appLogo = new JLabel(new ImageIcon("src/sistematarefas/view/img/app_logo.png"));

        appLogo.setBounds(10, 10, 45, 45);
        titulo.setBounds(0, 0, 480, 45);
        titulo.setForeground(new Color(90, 180, 255));
        titulo.setBorder(new EmptyBorder(20, 20, 20, 20));
        titulo.setFont(new Font("Arial", Font.BOLD, 18));

        header.add(appLogo);
        header.add(titulo);

        return header;
    }

    public void trocarTela(String nomeTela) {
        contentPanel.removeAll();

        switch (nomeTela) {
            case "Projetos":
                contentPanel.add(new ProjetosView());
                break;
            case "Funcionários":
                contentPanel.add(new FuncionariosView());
                break;
            case "Gestores":
                contentPanel.add(new GestoresView());
                break;
            case "Departamentos":
                contentPanel.add(new DepartamentoView());
                break;
            default:
                contentPanel.add(new JLabel("Tela não encontrada"));

        }

        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void selecionarMenu(JPanel menu) {
        if (menuSelecionado != null) {
            menuSelecionado.setBackground(new Color(25, 35, 45));
        }
        menu.setBackground(new Color(40, 60, 80));
        menuSelecionado = menu;
    }
}
