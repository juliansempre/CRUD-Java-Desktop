package com.mycompany.crudjavamysql.model;

import com.mycompany.crudjavamysql.controller.FuncionarioDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class FuncionarioDAO {

    //Inserir
    Connection conn;
    PreparedStatement pstm;
    ResultSet rs;
    ArrayList<FuncionarioDTO> lista = new ArrayList<>();

    //recebe como parametro a mesma informacao que eu envio pra ele do frmFuncionarioVIEW
    //Vai receber todos os objetos do controller no parametro
    public void cadastrarfuncionario(FuncionarioDTO objFuncionariodto) throws SQLException {
        String sql = "insert into funcionario(nome_funcionario, endereco_funcionario) VALUES (?,?)";

        conn = new ConexaoDAO().ConectaBD();

        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, objFuncionariodto.getNome_funcionario());
            pstm.setString(2, objFuncionariodto.getEndereco_funcionario());

            pstm.execute();
            JOptionPane.showMessageDialog(null, "Funcionário cadastrado!");
            pstm.close();
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Funcionario DAO" + erro);
        }
    }

    //Listar
    public ArrayList<FuncionarioDTO> PesquisarFuncionario() throws SQLException {
        String sql = "select * from funcionario";
        conn = new ConexaoDAO().ConectaBD();
        
        try {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro na pesquisa da classe Funcionario DAO" + erro);
        }
        //enquanto tiver linhas a ser exibida repita.
        while (rs.next()) {
            FuncionarioDTO objFuncionariodto = new FuncionarioDTO();
            objFuncionariodto.setId_funcionario(rs.getInt("id_funcionario"));
            objFuncionariodto.setNome_funcionario(rs.getString("nome_funcionario"));
            objFuncionariodto.setEndereco_funcionario(rs.getString("endereco_funcionario"));

            //montando as linhas do array list
            lista.add(objFuncionariodto);

        }

        //retorno da lista pronta
        return lista;
    }
}
