package org.example;

import jdk.jshell.execution.Util;
import org.example.Database.Conexao;
import org.example.Model.Contato;
import org.example.Utils.Utils;
import org.example.dao.ContatoDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Main {
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        inicio();
    }

    // MENU
    public static void inicio() {
        boolean sair = false;

        System.out.println("\n\n-------------------Lista Telefonica-------------------");
        System.out.println("\n1. Cadastrar contato: Nome, Telefone, Email, Observação.");
        System.out.println("2. Listar todos os contatos cadastrados.");
        System.out.println("3. Buscar contato por nome.");
        System.out.println("4. Atualizar dados de um contato (telefone, email, observação).");
        System.out.println("5. Remover contato.");
        System.out.println("6. Sair do sistema.");
        System.out.println("\nOpção: ");

        int opcao = input.nextInt();
        input.nextLine();

        // SWITCH / CASE
        switch (opcao) {

            case 1:{
                cadastrarContado();
                break;
            }

            case 2:{
                listarContatos();
                break;
            }

            case 3:{
                buscarContatoPorNome();
                break;
            }

            case 4:{
                atualizarDados();
                break;
            }

            case 5:{
                removerCotato();
                break;
            }

            case 6:{
                sair = true;
                break;
        }

        }

        if (!sair) {
            inicio();
    }
    }


    // CADASTRAR CONTATO
    public static void cadastrarContado(){
        System.out.println ("\n\n--------Cadastrar Contato--------\n\n");
        System.out.println ("\nDigite o nome do contato: ");
        String nome = input.nextLine();
        System.out.println ("Digite o telefone do contato: ");
        String telefone = input.nextLine();
        System.out.println ("Digite o email do contato: ");
        String email = input.nextLine();
        System.out.println ("Digite a oberservação sobre o contato: ");
        String observacao = input.nextLine();

        var contato = new Contato(nome, telefone, email, observacao);
        var contatoDao = new ContatoDAO();

        // TRATAMENTO DE ERRO
        try {
            contatoDao.inserirContato(contato);

        }catch (SQLException e) {
            System.out.println ("\nOcorreu um erro no banco de dados!");
            e.printStackTrace();
        }
    }


    // LISTAR CONTATOS
    public static void listarContatos() {
        System.out.println ("\n\n--------Listar Contatos--------\n\n");
        List<Contato> contatos = new ArrayList<>();
        var contatoDao = new ContatoDAO();

        // TRATAMENTO DE ERRO
        try {
            contatos = contatoDao.listarContato();

        }catch (SQLException e) {
            System.out.println ("\nOcorreu um erro no banco de dados!");
            e.printStackTrace();
        }
        Utils.exibirContatos(contatos);
        }


        // BUSCAR CONTATO POR NOME
        public static void buscarContatoPorNome(){
            System.out.println ("\n\n--------Buscar Contato por Nome--------\n\n");
            System.out.println("Digite o nome do contato que deseja buscar");
            String nome = input.nextLine();
            List<Contato> contatos = new ArrayList<>();

            // TRATAMENTO DE ERROf
            try {
                var contatoDAO = new ContatoDAO();
                contatos = contatoDAO.buscarContatoPornNome(nome);

            }catch (SQLException e) {
                System.out.println ("\nOcorreu um erro no banco de dados!");
                e.printStackTrace();
            }
            Utils.exibirContatos(contatos);
        }


    // ATUALIZAR DADOS (telefone, email, observação)
    public static void atualizarDados() {
        System.out.println("\n\n--------Atualizar Contato--------\n\n");
        System.out.println("Digite o ID do contato que deseja atualizar: ");
        int id = input.nextInt();
        input.nextLine();
        System.out.println("Digite o novo telefone: ");
        String novoTelefone = input.nextLine();
        System.out.println("Digite o novo email: ");
        String novoEmail = input.nextLine();
        System.out.println("Digite a nova observação: ");
        String novaObservacao = input.nextLine();

        // CRIA O CONTATO E DEFINE O ID PARA ATUALIZAÇÃO
        Contato contato = new Contato("", novoTelefone, novoEmail, novaObservacao);
        contato.setId(id);

        var contatoDao = new ContatoDAO();

        // TRATAMENTO DE ERRO
        try {
            contatoDao.atualizarDados(contato);
            System.out.println("Contato atualizado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar o contato: " + e.getMessage());
            e.printStackTrace();
        }
    }


    // REMOVER CONTATO
    public static void removerCotato() {
        System.out.println("\n\n--------Deletar Contato--------\n\n");
        listarContatos();
        System.out.println("\n\nDigite o ID do contato que deseja deletar");
        int idContato = input.nextInt();

        var contatoDao = new ContatoDAO();

        try {
            contatoDao.removerContato(idContato);
            System.out.println("Contato deletado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao deletar o contato: " + e.getMessage());
        }
    }





}
