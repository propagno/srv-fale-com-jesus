package br.com.propagno.falecomjesus.domain.entity;

/**
 * Entidade de exemplo do domínio
 */
public class ExampleEntity extends BaseEntity {
    
    private String name;
    private String description;
    
    public ExampleEntity() {
    }
    
    public ExampleEntity(String name, String description) {
        this.name = name;
        this.description = description;
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
}

