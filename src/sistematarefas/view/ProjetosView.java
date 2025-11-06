package sistematarefas.view;

import sistematarefas.controller.ProjetoController;

import javax.swing.*;
import java.awt.*;

public class ProjetosView extends JPanel {

    private ProjetoController projetoController;
    public ProjetosView() {
        projetoController = new ProjetoController();
        initComponents();

    }

    public void initComponents() {
        setBackground(new Color(20, 30, 40));
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Tela de Projetos", SwingConstants.CENTER);
        titulo.setForeground(new Color(90, 180, 255));
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));

        add(titulo, BorderLayout.NORTH);
    }


}
