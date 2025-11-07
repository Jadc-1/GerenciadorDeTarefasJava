package sistematarefas.view;

import sistematarefas.controller.DepartamentoController;
import sistematarefas.controller.FuncionarioController;
import sistematarefas.model.Funcionario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class FuncionariosView {
    private JButton btnAdicionarFuncionario;
    private JPanel jpFuncionarios;
    private JLabel txtTitulo;
    private JScrollPane spFuncionarios;
    private JTable tbFuncionarios;
    private FuncionarioController funcionarioController;
    private DepartamentoController departamentoController;

    public FuncionariosView() {
        funcionarioController = new FuncionarioController();
        departamentoController = new DepartamentoController();
        txtTitulo.setForeground(Color.WHITE);
        criarTabela();
        adicionarFuncionario();
    }

    public JTable criarTabela() {
        String[] colunas = {"ID", "Nome", "Email", "Telefone", "Endereco", "Departamento", "Salário"};
        DefaultTableModel modelo = new DefaultTableModel(null, colunas);
        tbFuncionarios.setModel(modelo);
        tbFuncionarios.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        tbFuncionarios.getTableHeader().setForeground(Color.WHITE);
        tbFuncionarios.getTableHeader().setBackground(new Color(25, 35, 45));
        return tbFuncionarios;
    }

    private void listarFuncionarios(DefaultTableModel modelo) {
        List<Funcionario> funcionarios = funcionarioController.listarFuncionarios();
        if (funcionarios != null) {
            Object[] row = new Object[8];
            for (Funcionario f : funcionarios) {
                row[0] = f.getIdFuncionario();
                row[1] = f.getNome();
                row[2] = f.getEmail();
                row[3] = f.getTelefone();
                row[4] = f.getEndereco();
                row[5] = f.getDepartamento().getNomeDepartamento();
                row[6] = f.getSalario();
                modelo.addRow(row);
            }
        }
    }

    public JPanel getFuncionariosPanel() {
        return jpFuncionarios;
    }

    public void adicionarFuncionario() {
        btnAdicionarFuncionario.addActionListener(e -> {
            new FuncionarioDialogView((Frame) SwingUtilities.getWindowAncestor(btnAdicionarFuncionario), null, funcionarioController, departamentoController).setVisible(true);
        });

    }

}
