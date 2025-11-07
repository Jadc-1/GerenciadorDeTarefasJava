package sistematarefas.view;

import sistematarefas.controller.GestorController;
import sistematarefas.controller.ProjetoController;
import sistematarefas.model.Gestor;
import sistematarefas.model.Projeto;
import java.time.format.DateTimeFormatter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.List;

public class ProjetoDialogView extends JDialog {

    private JTextField txtNome;
    private JTextArea txtDescricao;
    private JTextField txtDataTermino;
    private JComboBox<String> cmbStatus;
    private JComboBox<String> cmbPrioridade;
    private JComboBox<Gestor> cmbGestor;
    private JButton btnSalvar, btnCancelar;

    private Projeto projeto; // null = novo projeto, != null = editar
    private ProjetoController projetoController;
    private GestorController gestorController;

    public ProjetoDialogView(Frame parent, Projeto projeto, ProjetoController projetoController, GestorController gestorController) {
        super(parent, projeto == null ? "Novo Projeto" : "Editar Projeto", true);
        this.projeto = projeto;
        this.projetoController = projetoController;
        this.gestorController = gestorController;

        initComponents();
        pack();
        setLocationRelativeTo(parent);
    }

    private void initComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding do GridBag
        gbc.anchor = GridBagConstraints.WEST;

        // Campos para digitar dados
        txtNome = new JTextField(20);
        txtDescricao = new JTextArea(3, 20);
        txtDescricao.setLineWrap(true);
        txtDescricao.setWrapStyleWord(true);
        txtDataTermino = new JTextField(10);

        cmbStatus = new JComboBox<>(new String[]{"Em Andamento", "Concluído", "Pendente"});
        cmbPrioridade = new JComboBox<>(new String[]{"A - Alta", "M - Média", "B - Baixa"});
        cmbGestor = new JComboBox<>();

        // Popula gestores
        List<Gestor> gestores = gestorController.listarGestores();
        if (gestores != null) {
            for (Gestor g : gestores) {
                cmbGestor.addItem(g);
            }
        }

        // Esse Renderer permite que ao invés de mostrar o tipo de dado do Gestor, é possível informa apenas seu nome (ComboBox)
        cmbGestor.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Gestor g) setText(g.getNome());
                return this;
            }
        });

        // Preenche campos se for editar
        if (projeto != null) {
            txtNome.setText(projeto.getNome());
            txtDescricao.setText(projeto.getDescricao());
            txtDataTermino.setText(projeto.getDataTermino() != null ? projeto.getDataTermino().toString() : "");
            cmbStatus.setSelectedItem(projeto.getStatusProjeto());
            cmbPrioridade.setSelectedItem(switch (projeto.getPrioridade()) {
                case 'A' -> "A - Alta";
                case 'M' -> "M - Média";
                case 'B' -> "B - Baixa";
                default -> "M - Média";
            });
            cmbGestor.setSelectedItem(projeto.getGestor());
        }

        btnSalvar = new JButton("Salvar");
        btnCancelar = new JButton("Cancelar");

        // Layout - Campos de texto e combobox
        gbc.gridx = 0; gbc.gridy = 0; add(new JLabel("Nome:"), gbc);
        gbc.gridx = 1; add(txtNome, gbc);

        // Uso o ++ para adicionar +1 na altura da linha em que será adicionado o txt
        gbc.gridx = 0; gbc.gridy++; add(new JLabel("Descrição:"), gbc);
        gbc.gridx = 1; add(new JScrollPane(txtDescricao), gbc);

        gbc.gridx = 0; gbc.gridy++; add(new JLabel("Data Término:"), gbc);
        gbc.gridx = 1; add(txtDataTermino, gbc);

        gbc.gridx = 0; gbc.gridy++; add(new JLabel("Status:"), gbc);
        gbc.gridx = 1; add(cmbStatus, gbc);

        gbc.gridx = 0; gbc.gridy++; add(new JLabel("Prioridade:"), gbc);
        gbc.gridx = 1; add(cmbPrioridade, gbc);

        gbc.gridx = 0; gbc.gridy++; add(new JLabel("Gestor:"), gbc);
        gbc.gridx = 1; add(cmbGestor, gbc);

        gbc.gridx = 0; gbc.gridy++; add(btnSalvar, gbc);
        gbc.gridx = 1; add(btnCancelar, gbc);

        // Ações
        btnCancelar.addActionListener(e -> dispose()); //Fecha o dialog

        btnSalvar.addActionListener(this::salvarProjeto); // Salva o projeto
    }

    private void salvarProjeto(ActionEvent e) {
        try {
            int codigo = projeto == null ? 0 : projeto.getCodigoProjeto();
            String nome = txtNome.getText().trim();
            String descricao = txtDescricao.getText().trim();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");  // Formatado data BR
            LocalDate dataTermino = txtDataTermino.getText().isEmpty() ? null : LocalDate.parse(txtDataTermino.getText(), formatter); // Verificação Ternária (mesma ideia de um if)
            String status = (String) cmbStatus.getSelectedItem();
            char prioridade = ((String) cmbPrioridade.getSelectedItem()).charAt(0);
            Gestor gestor = (Gestor) cmbGestor.getSelectedItem();

            if (nome.isEmpty() || descricao.isEmpty() || gestor == null) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos obrigatórios.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (projeto == null) {
                Projeto novo = new Projeto(0, nome, descricao, dataTermino, status, prioridade, gestor);
                projetoController.salvarProjeto(novo);
            } else {
                projeto.setNome(nome);
                projeto.setDescricao(descricao);
                projeto.setDataTermino(dataTermino);
                projeto.setStatusProjeto(status);
                projeto.setPrioridade(prioridade);
                projeto.setGestor(gestor);
                projetoController.atualizarProjeto(projeto);
            }

            dispose(); // fecha o diálogo
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
