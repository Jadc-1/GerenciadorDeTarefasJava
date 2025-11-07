package sistematarefas.view;

import sistematarefas.controller.GestorController;
import sistematarefas.controller.ProjetoController;
import sistematarefas.model.Projeto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public class ProjetosView {
    private JPanel projetoPanel;
    private JLabel txtTitulo;
    private JButton btnAdicionarProjeto;
    private JTable tbProjetos;
    private JScrollPane spTabela;
    private ProjetoController projetoController;
    private GestorController gestorController;

    public ProjetosView() {
        projetoController = new ProjetoController();
        gestorController = new GestorController();
        txtTitulo.setForeground(Color.WHITE);
        getTbProjetos();
        spTabela.setBackground(new Color(25, 35, 45));
        AdicionarProjetoEstilo();
    }

    public JPanel getProjetoPanel() {
        return projetoPanel;
    }

    public JTable getTbProjetos() {

        String[] colunas = {"ID", "Nome", "Descrição", "Data início", "Data Término", "Status", "Prioridade", "Gestor"};
        DefaultTableModel modelo = new DefaultTableModel(null, colunas);
        tbProjetos.setModel(modelo);
        tbProjetos.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        tbProjetos.getTableHeader().setForeground(Color.WHITE);
        tbProjetos.getTableHeader().setBackground(new Color(25, 35, 45));

        listarProjetos(modelo);

        return tbProjetos;
    }

    private void listarProjetos(DefaultTableModel modelo) {
        List<Projeto> projetos = projetoController.listarProjetos();
        if(projetos != null) {
            for (Projeto p : projetos) {
                Object [] row = {
                        p.getCodigoProjeto(),
                        p.getNome(),
                        p.getDescricao(),
                        p.getDataInicio(),
                        p.getDataTermino(),
                        p.getStatusProjeto(),
                        p.getPrioridade(),
                        p.getGestor().getNome() != null ? p.getGestor().getNome() : ""
                };
                modelo.addRow(row);
            }
        }
    }

    private void AdicionarProjetoEstilo(){
        btnAdicionarProjeto.setOpaque(true); // torna o fundo visível
        btnAdicionarProjeto.setBorderPainted(false); // opcional: remove borda
        btnAdicionarProjeto.setContentAreaFilled(true); // garante que o conteúdo seja preenchido
        btnAdicionarProjeto.setForeground(new Color(255, 255, 255));

        btnAdicionarProjeto.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ProjetoDialogView projetoDialogView = new ProjetoDialogView((Frame) SwingUtilities.getWindowAncestor(btnAdicionarProjeto), //retorna o frame pai,
                        null, //
                        projetoController,
                        gestorController);
                projetoDialogView.setVisible(true);

                // Limpa as linhas antigas
                DefaultTableModel model = (DefaultTableModel) tbProjetos.getModel();
                model.setRowCount(0);
                //Atualizar após adicionar ao banco
                listarProjetos((DefaultTableModel) tbProjetos.getModel());
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnAdicionarProjeto.setBackground(new Color(40, 60, 80));
                btnAdicionarProjeto.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnAdicionarProjeto.setBackground(new Color(37, 157, 33));
                btnAdicionarProjeto.setForeground(Color.WHITE);
            }
        });
    }
}
