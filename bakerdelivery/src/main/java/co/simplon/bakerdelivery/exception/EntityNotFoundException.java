package co.simplon.bakerdelivery.exception;

public class EntityNotFoundException extends RuntimeException{


    private String entityName;

    public EntityNotFoundException(String message, String entityName) {
        super(message);
        this.entityName = entityName;
    }

    public String getEntityName() {
        return entityName;
    }
}
