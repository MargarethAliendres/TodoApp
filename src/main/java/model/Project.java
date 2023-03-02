package model;

import java.util.Date;

public class Project {
        private int id;
        private String name;
        private String descritption;
        private Date createdAt;  
        private Date updatedAt;

    public Project(int id, String name, String descritption, Date createdAt, Date updatedAt) {
        this.id = id;
        this.name = name;
        this.descritption = descritption;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Project() {   //esse construtor faz com que ao salvar um projeto já gere automaticamente a data da criacao e atualização
       this.createdAt = new Date();
       this.updatedAt = new Date();
       
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescritption() {
        return descritption;
    }

    public void setDescritption(String descritption) {
        this.descritption = descritption;
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
        return "Projects{" + "id=" + id + ", name=" + name + ", descritption=" + descritption + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
    }

        
        
   
}
