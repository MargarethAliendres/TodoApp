package model;

import java.util.Date;

public class Task {
   
private int id;
private int idProject;
private String name;
private String description;
private String notes;
private boolean iscompleted;
private Date deadline;
private Date createdAt;
private Date updatedAt;

         //metodo construtor com parametro//
    public Task(int id, int idProject, String name, String description, String notes, boolean iscompleted, Date deadline, Date createdAt, Date updatedAt) {
        this.id = id;
        this.idProject = idProject;
        this.name = name;
        this.description = description;
        this.notes = notes;
        this.iscompleted = iscompleted;
        this.deadline = deadline;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    //metodo construtor sem parametro, tudo inicia//
    public Task() {   //esse construtor faz com que ao salvar um projeto já gere automaticamente a data da criacao e atualização
       this.iscompleted = false; 
       this.createdAt = new Date();
       this.updatedAt = new Date(); 
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProject() {
        return idProject;
    }

    public void setIdProject(int idProject) {
        this.idProject = idProject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean getIscompleted() {
        return iscompleted;
    }

    public void setIscompleted(boolean iscompleted) {
        this.iscompleted = iscompleted;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "tasks{" + "id=" + id + ", idProject=" + idProject + ", name=" + name + ", description=" + description + ", notes=" + notes + ", iscompleted=" + iscompleted + ", deadline=" + deadline + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
    }

    public void setStatus(byte aByte) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
        
    }
