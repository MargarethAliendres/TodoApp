package controller;

import java.sql.Connection;
import java.sql.Date;      //date do sql nao do  java util//
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Task;
import util.ConnectionFactory;
 
 
public class TaskController {
    
    public void save (Task task){
          
            //cria a variavel sql//
           String sql = "INSERT INTO tasks("
              + "idProject,"
               + "name,"
               + "description, "
               + "iscompleted, "
               + "notes, "
               + "deadline, "
               + "createdAt ,"
               + "updatedAt) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";  //noa coloca o campo id auto increment//
               
           //cria essas variaveis//
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            //Cria esse objeto de conexao com o banco//
            connection = ConnectionFactory.getConnection();
            //Cria esse objeto que prepara o sql   PreparedStatment, classe usada para executar a query//
            statement = connection.prepareStatement(sql);
            
            //setando os valores do statatement
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setBoolean(4, task.getIscompleted());   // aqui é diferente no bolean//
            statement.setString(5, task.getNotes());
            statement.setDate(6, new Date(task.getDeadline().getTime())); //faz a conversao com new pois o import da classe Task é java.util.Date e aqui é java.sql.Date//
            statement.setDate(7, new Date(task.getCreatedAt().getTime()));
            statement.setDate(8, new Date(task.getUpdatedAt().getTime()));
                                                               //o get time retorna a deadline como variavel long//
               //Executa a query sql para inserção dos dados//
            statement.execute();
            
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao salvar a tarefa "+ ex.getMessage(), ex);
        } finally { 
            ConnectionFactory.closeConnection(connection, statement);   // fecha a conexao e o statement//
            
            
            
            } 
    
    }
     public void update (Task task){
         //cria a variavel sql//
     String sql = "UPDATE tasks SET idProject = ?,"
             + "name = ?, "
             + "description = ?,"
             + "iscompleted = ?,"
             + "notes = ?,"
             + "deadline = ?,"
             + "createdAt = ?,"
             + "updatedAt = ? " 
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
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setBoolean(4, task.getIscompleted());
            statement.setString(5, task.getNotes());
            statement.setDate(6, new Date(task.getDeadline().getTime()));
            statement.setDate(7, new Date(task.getCreatedAt().getTime()));
            statement.setDate(8, new Date(task.getUpdatedAt().getTime()));
            statement.setInt(9, task.getId());
                        
            //Executa a query sql para atualização dos dados//
            statement.execute();
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao atualizar a tarefa" + ex.getMessage(), ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
            
    }
                    
    }
      
    public void removeById(int taskId)  {
            
        //cria a variavel sql//
        String sql = "DELETE FROM tasks WHERE id = ?";  // comando responsavel por exluir tarefas//
        
        //cria essas variaveis//
        Connection connection = null;                          // criamos a variavel de conexao conn//
        PreparedStatement statement = null;              // criamos a variavel de statement//
        
        try {
             //Cria esse objeto de conexao com o banco//
            connection = ConnectionFactory.getConnection();
             //Cria esse objeto que prepara o sql   PreparedStatment, classe usada para executar a query//
            statement = connection.prepareStatement(sql);   //prepara o comando sql pra deixar prontinho para ser executado pela conexao//
            //setando os valores do statment
            statement.setInt(1, taskId);          // seta o 1º parametro, eu quero setar o valor  - mudar o valor da interrogação do sql acima, pelo ID que for enviado pelo parametro ex. quando a pessao chamar e for o 3 aqui ficará 3//
             
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
       public  List<Task> getAll (int idProject){  
           
        //cria essas variavel sql//
       String sql = "SELECT * FROM tasks  WHERE idProject = ?";   // scrpit sql//
             
        //cria essas variaveis//
        Connection connection = null;                          // criamos a variavel de conexao conn//
        PreparedStatement statement = null; 
        ResultSet resultSet = null;    // essa classe ResultSet guarda os valores da consulta/retorno do banco de dados//
                                       //  quando faz um select traz uma reposta, tem que ter uma classe que guarde os valores do retorno do banco de dados//
                                       //ResultSet é uma classe que vai guardar os valores do retorno/resposta//
       
          //essa classe List<Task>,representa uma lista, um vetor. Criar objeto tasks que é a lista de tarefas //
        List<Task> tasks = new ArrayList<Task>();   //representa um ventor, mas é bom quando trabalho com coleções/conjunto de valores //
        //lista de tarefas devolvida quando a chamada do metoto acontecer//
                
        try {
             //Cria esse objeto de conexao com o banco//
            connection = ConnectionFactory.getConnection();  
            //Cria esse objeto que prepara o sql   PreparedStatment, classe usada para executar a query//
            statement = connection.prepareStatement(sql);   
            
             //setando os valores do statement
            statement.setInt(1, idProject);          // setou eu quero setar o valor  - mudar o valor da interrogação do sql acima, pelo ID que for enviado pelo parametro ex. quando a pessao chamar e for o 3 aqui ficará 3.//
            
            //aqui criei o objeto para guardar os valores da consulta dentro dele//
            resultSet = statement.executeQuery();        // aqui tambem é diferente, aqui executa e retorna o resiltado da Query//
            //valor retornado pela execução da query
            
            while(resultSet.next()){ // enquanto houver um proximo valor a ser percorrido no resulte set eu vai pegando o valor//
                 
             Task task = new Task();  // criar um novo objeto de tarefa, seta nessa tarefa os valores que estão no resultSet /converaso do objeto banco de dados para a classe tarefa //  
             
             
             task.setId(resultSet.getInt("id"));
             task.setIdProject(resultSet.getInt("idProject"));
             task.setName(resultSet.getString("name"));
             task.setDescription(resultSet.getString("description"));
             task.setIscompleted(resultSet.getBoolean("Iscompleted"));
             task.setNotes(resultSet.getString("notes"));
             task.setDeadline(resultSet.getDate("deadline"));
             task.setCreatedAt(resultSet.getDate("createdAt"));
             task.setUpdatedAt(resultSet.getDate("updatedAt"));
             
             tasks.add(task);    //agora pega essa tarefa ecoloca dentro do lista de tarefa criada acima com array list//
            }
        }  catch (SQLException ex) {
           throw new RuntimeException("Erro ao inserir terefa" + ex.getMessage(),  ex); 
        }finally {                                        //sempte é executado //
          ConnectionFactory.closeConnection(connection, statement, resultSet);   // aqui tambem precisa fechao o resultset//
            
          //dá um ctrl clic no ConnectionFactory e crie um novo metodo, copie o metodo de cima  e acrescente, para inserir o resultSet, //
          
        }
          return tasks;   //lista de tarefas que foi criada e carregada do banco de dados//
    }
public List<Task> getByProjectId(int id) {
        String sql = "SELECT * FROM tasks where idProject = ?";

        List<Task> tasks = new ArrayList<Task>();

        Connection conn = null;
        PreparedStatement stmt = null;

        //Classe que vai recuperar os dados do banco de dados
        ResultSet rset = null;

        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, id);

            rset = stmt.executeQuery();

            //Enquanto existir dados no banco de dados, fa�a
            while (rset.next()) {

                Task task = new Task();

                task.setId(rset.getInt("id"));
                task.setIdProject(rset.getInt("idProject"));
                task.setName(rset.getString("name"));
                task.setDescription(rset.getString("description"));
                task.setStatus(rset.getByte("status"));
                task.setNotes(rset.getString("notes"));
                task.setDeadline(rset.getDate("deadline"));
                task.setIscompleted(rset.getBoolean("iscompleted"));
                task.setCreatedAt(rset.getDate("createdAt"));
                task.setCreatedAt(rset.getDate("updatedAt"));

                //Adiciono o contato recuperado, a lista de contatos
                tasks.add(task);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar as tarefas", ex);
        } finally {
            try {
                if (rset != null) {
                    rset.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                throw new RuntimeException("Erro ao fechar a conexão", ex);
            }
        }
        return tasks;
    }                        
    
}
