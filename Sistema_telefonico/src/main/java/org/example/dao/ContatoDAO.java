package org.example.dao;

import org.example.Database.Conexao;
import org.example.Model.Contato;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContatoDAO {
    public void inserirContato(Contato contato) throws SQLException {
        String query = "INSERT INTO contato (nome, telefone, email, observacao) VALUES (?, ?, ?, ?)";


        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, contato.getNome());
            stmt.setString(2, contato.getTelefone());
            stmt.setString(3, contato.getEmail());
            stmt.setString(4, contato.getObservacao());

            stmt.executeUpdate();
        }
    }


    // LISTAR
    public List<Contato> listarContato() throws SQLException{
        List<Contato> contatos = new ArrayList<>();
        String query = """
                SELECT id
                , nome
                , telefone
                , email
                , observacao
             FROM contato
             """;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String telefone = rs.getString("telefone");
                String email = rs.getString("email");
                String observacao = rs.getString("observacao");

                var contato = new Contato(id, nome, telefone, email, observacao);
                contatos.add(contato);
            }
        }
        return contatos;
    }


    //BUSCAR CONTATO
    public List<Contato> buscarContatoPornNome(String nome) throws SQLException{
        List<Contato> contatos = new ArrayList<>();

        String query = """
                SELECT id
                        ,nome
                        ,telefone
                        ,email
                        ,observacao
                FROM contato
                WHERE nome LIKE ?
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1,"%" + nome + "%");

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                String nomeBanco = rs.getString("nome");
                String telefone = rs.getString("telefone");
                String email = rs.getString("email");
                String observacao = rs.getString("observacao");

                var contato = new Contato(id, nomeBanco, telefone, email, observacao);
                contatos.add(contato);
            }
        }
        return contatos;
    }

}
