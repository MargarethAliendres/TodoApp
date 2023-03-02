
package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Project;
import util.ConnectionFactory;


public class ProjectController {
        public void save(Project project){
          
            //cria a variavel sql//
           String sql = "INSERT INTO projects (name,"
               + "description, "
               + "createdAt ,"
               + "updateAt) VALUES (?, ?, ?, ?)";  //noa coloca o campo id auto increment//
               
           //cria essas variaveis//
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            //Cria esse objeto de conexao com o banco//
            connection = ConnectionFactory.getConnection();
            //Cria esse objeto que prepara o sql   PreparedStatment, classe usada para executar a query//
            statement = connection.prepareStatement(sql);
            
            //setando os valores do statatement
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescritption());
            statement.setDate(3, new Date(project.getCreatedAt().getTime()));
            statement.setDate(4, new Date(project.getUpdatedAt().getTime() )); //project.getUpdatedAt().getTime()

                                                               //o get time retorna a deadline como variavel long//
               //Executa a query sql para inserção dos dados//
            statement.execute();
            
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao salvar a tarefa "+ ex.getMessage(), ex);
        } finally { 
            ConnectionFactory.closeConnection(connection, statement);   // fecha a conexao e o statement//
 
            } 
    
    }
     public void update (Project project){
         //cria a variavel sql//
     String sql = "UPDATE projects SET name = ?, "
             + "description = ?,"
             + "createdAt = ?,"
             + "updateAt = ? " 
             + "WHERE id = ?";
           //atualiza os dados so do id informado//
             
           //cria essas variaveis//
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            //Cria esse objeto de conexao com o banco//
            connection = ConnectionFactory.getConnection();
            //Cria esse objeto que prepara o sql   PreparedStatment, classe usada para executar a query//
            statement = connection.prepareStatement(sql);
            
            //setando os valores do statatement
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescritption());
            statement.setDate(3, new Date(project.getCreatedAt().getTime()));
            statement.setDate(4, new Date(project.getUpdatedAt().getTime()));
            statement.setInt(5, project.getId());
                        
            //Executa a query sql para atualização dos dados//
            statement.execute();
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao atualizar a tarefa" + ex.getMessage(), ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
            
    }
                    
    }
      
    public void removeById(int idProject) {
            
        //cria a variavel sql//
        String sql = "DELETE FROM projects WHERE id = ?";  // comando responsavel por exluir tarefas//
        
        //cria essas variaveis//
        Connection connection = null;                          // criamos a variavel de conexao conn//
        PreparedStatement statement = null;              // criamos a variavel de statement//
        
        try {
             //Cria esse objeto de conexao com o banco//
            connection = ConnectionFactory.getConnection();
             //Cria esse objeto que prepara o sql   PreparedStatment, classe usada para executar a query//
            statement = connection.prepareStatement(sql);   //prepara o comando sql pra deixar prontinho para ser executado pela conexao//
            //setando os valores do statment
            statement.setInt(1, idProject);          // seta o 1º parametro, eu quero setar o valor  - mudar o valor da interrogação do sql acima, pelo ID que for enviado pelo parametro ex. quando a pessao chamar e for o 3 aqui ficará 3//
             
            //Executa a query sql para atualização dos dados//
            statement.execute();                  
        } catch (Exception ex) {
           throw new RuntimeException("Erro ao deletar a terefa" + ex.getMessage(), ex);
        }finally {                                         //sempte é executado //
            ConnectionFactory.closeConnection(connection, statement);
            //dá um ctrl clic no ConnectionFactory e crie um novo metodo, copie o metodo de cima  e acrescente, para inserir o resultSet, //
                                                   
        }
        
    }
    
    
    //todos os metodos acima é VOID, nao traz retorno, 
    
    // o UNICO que vai trazer retorno é essa classe List<Task>// 
    //listar todas as tarefas//
     //esse list é um pacote de estrutura de dados do java //pega todas as tarefas com base em um projeto//
       public  List<Project> getAll(){     // na de tarefa tem filro,aqui nao precisa vai trazer todos os projetos
           
        //cria essas variavel sql//        //aqui tambem é diferente da tarefa nao tem filtro, select todos os projetos//
       String sql = "SELECT * FROM projects";   
             
        //cria essas variaveis//
        Connection connection = null;                          // criamos a variavel de conexao conn//
        PreparedStatement statement = null; 
        ResultSet resultSet = null;    // essa classe ResultSet guarda os valores da consulta/retorno do banco de dados//
                                       //  quando faz um select traz uma reposta, tem que ter uma classe que guarde os valores do retorno do banco de dados//
                                       //ResultSet é uma classe que vai guardar os valores do retorno/resposta//
       
          //essa classe List<Task>,representa uma lista, um vetor. Criar objeto tasks que é a lista de tarefas //
        List<Project> projects = new ArrayList<Project>();   //representa um ventor, mas é bom quando trabalho com coleções/conjunto de valores //
        //lista de tarefas devolvida quando a chamada do metoto acontecer//
                
        try {
             //Cria esse objeto de conexao com o banco//
            connection = ConnectionFactory.getConnection();  
            //Cria esse objeto que prepara o sql   PreparedStatment, classe usada para executar a query//
            statement = connection.prepareStatement(sql);   
            
            //aqui já vai direto  para executar a query
            //aqui criei o objeto para guardar os valores da consulta dentro dele//
            resultSet = statement.executeQuery();        // aqui tambem é diferente, aqui executa e retorna o resiltado da Query//
            //valor retornado pela execução da query
            
            while(resultSet.next()){ // enquanto houver um proximo valor a ser percorrido no resulte set eu vai pegando o valor//
                 
             Project project = new Project();  // criar um novo objeto de tarefa, seta nessa tarefa os valores que estão no resultSet /converaso do objeto banco de dados para a classe tarefa //  
             
             
             project.setId(resultSet.getInt("id"));
             project.setName(resultSet.getString("name"));
             project.setDescritption(resultSet.getString("description"));
             project.setCreatedAt(resultSet.getDate("createdAt"));
             project.setUpdatedAt(resultSet.getDate("updatedAt"));
             
             projects.add(project);    //agora pega essa tarefa ecoloca dentro do lista de tarefa criada acima com array list//
            }
        }  catch (SQLException ex) {
           throw new RuntimeException("Erro ao inserir terefa" + ex.getMessage(),  ex); 
        }finally {                                        //sempte é executado //
          ConnectionFactory.closeConnection(connection, statement, resultSet);   // aqui tambem precisa fechao o resultset//
            
          //dá um ctrl clic no ConnectionFactory e crie um novo metodo, copie o metodo de cima  e acrescente, para inserir o resultSet, //
          
        }
          return projects;   //lista de tarefas que foi criada e carregada do banco de dados//
    }                        
    
}