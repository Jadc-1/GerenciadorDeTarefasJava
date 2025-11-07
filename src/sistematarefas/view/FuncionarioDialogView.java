package sistematarefas.view;

import sistematarefas.controller.FuncionarioController;
import sistematarefas.controller.DepartamentoController;
import sistematarefas.model.Funcionario;
import sistematarefas.model.Departamento;
import sistematarefas.model.Endereco;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FuncionarioDialogView extends JDialog {

    private JTextField txtNome;
    private JTextField txtEmail;
    private JTextField txtTelefone;
    private JTextField txtSalario;
    private JComboBox<Departamento> cmbDepartamento;
    private JCheckBox chkAtivo;

    private JButton btnSalvar, btnCancelar;

    private Funcionario funcionario;
    private FuncionarioController funcionarioController;
    private DepartamentoController departamentoController;

    public FuncionarioDialogView(Frame parent, Funcionario funcionario, FuncionarioController funcionarioController, DepartamentoController departamentoController) {
        super(parent, funcionario == null ? "Novo Funcionário" : "Editar Funcionário", true);
        this.funcionario = funcionario;
        this.funcionarioController = funcionarioController;
        this.departamentoController = departamentoController;

        initComponents();
        pack();
        setLocationRelativeTo(parent);
    }

    private void initComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        txtNome = new JTextField(20);
        txtEmail = new JTextField(20);
        txtTelefone = new JTextField(15);
        txtSalario = new JTextField(10);
        chkAtivo = new JCheckBox("Ativo");
        cmbDepartamento = new JComboBox<>();

        // Popula departamentos
        List<Departamento> departamentos = departamentoController.listarDepartamentos();
        if (departamentos != null) {
            for (Departamento d : departamentos) {
                cmbDepartamento.addItem(d);
            }
        }
        else{
            cmbDepartamento.addItem(null);
        }

        cmbDepartamento.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Departamento d) setText(d.getNomeDepartamento());
                return this;
            }
        });

        // Preenche os campos se for edição
        if (funcionario != null) {
            txtNome.setText(funcionario.getNome());
            txtEmail.setText(funcionario.getEmail());
            txtTelefone.setText(funcionario.getTelefone());
            txtSalario.setText(String.valueOf(funcionario.getSalario()));
            chkAtivo.setSelected(funcionario.getAtivo());
            if (funcionario.getDepartamento() != null)
                cmbDepartamento.setSelectedItem(null);
            cmbDepartamento.setSelectedItem(funcionario.getDepartamento());
        }

        btnSalvar = new JButton("Salvar");
        btnCancelar = new JButton("Cancelar");

        // Layout - campos
        gbc.gridx = 0; gbc.gridy = 0; add(new JLabel("Nome:"), gbc);
        gbc.gridx = 1; add(txtNome, gbc);

        gbc.gridx = 0; gbc.gridy++; add(new JLabel("Email:"), gbc);
        gbc.gridx = 1; add(txtEmail, gbc);

        gbc.gridx = 0; gbc.gridy++; add(new JLabel("Telefone:"), gbc);
        gbc.gridx = 1; add(txtTelefone, gbc);

        gbc.gridx = 0; gbc.gridy++; add(new JLabel("Salário:"), gbc);
        gbc.gridx = 1; add(txtSalario, gbc);

        gbc.gridx = 0; gbc.gridy++; add(new JLabel("Departamento:"), gbc);
        gbc.gridx = 1; add(cmbDepartamento, gbc);

        gbc.gridx = 0; gbc.gridy++; add(new JLabel("Status:"), gbc);
        gbc.gridx = 1; add(chkAtivo, gbc);

        gbc.gridx = 0; gbc.gridy++; add(btnSalvar, gbc);
        gbc.gridx = 1; add(btnCancelar, gbc);

        // Ações
        btnCancelar.addActionListener(e -> dispose());
        btnSalvar.addActionListener(this::salvarFuncionario);
    }

    private void salvarFuncionario(ActionEvent e) {
        try {
            int idFuncionario = funcionario == null ? 0 : funcionario.getIdFuncionario();
            int idUsuario = funcionario == null ? 0 : funcionario.getIdUsuario();

            String nome = txtNome.getText().trim();
            String email = txtEmail.getText().trim();
            String telefone = txtTelefone.getText().trim();
            String salarioStr = txtSalario.getText().trim();

            Departamento departamento = (Departamento) cmbDepartamento.getSelectedItem();
            boolean ativo = chkAtivo.isSelected();

            if (nome.isEmpty() || email.isEmpty() || telefone.isEmpty() || salarioStr.isEmpty() || departamento == null) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos obrigatórios.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double salario;
            try {
                salario = Double.parseDouble(salarioStr);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Salário inválido. Use apenas números.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }


            // Endereço ainda pode ser adicionado futuramente
            Endereco endereco = null;

            if (funcionario == null) {
                Funcionario novo = new Funcionario(
                        idFuncionario, salario, idUsuario, nome, email, telefone,
                        endereco, ativo, departamento
                );
                funcionarioController.salvarFuncionario(novo);
            } else {
                funcionario.setNome(nome);
                funcionario.setEmail(email);
                funcionario.setTelefone(telefone);
                funcionario.setSalario(salario);
                funcionario.setAtivo(ativo);
                funcionario.setDepartamento(departamento);

                funcionarioController.atualizarFuncionario(funcionario);
            }

            dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
