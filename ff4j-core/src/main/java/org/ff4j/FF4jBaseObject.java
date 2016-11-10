package org.ff4j;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * Superclass for FF4J objects.
 *
 * @author Cedrick LUNVEN (@clunven)
 */
public abstract class FF4jBaseObject<T extends FF4jBaseObject<?>> implements Serializable {

    /** serial number. */
    private static final long serialVersionUID = -6001829116967488353L;
    
    /** formatter for creation date and last modified. */
    protected static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    
    /** unique identifier. */
    protected String uid;
    
    /** Description of the meaning. */
    protected Optional < String > description = Optional.empty();
    
    /** Related people to contact for any relevant question. */
    protected Optional < String > owner = Optional.empty();
    
    /** Creation date if available in the store. */
    protected Optional < LocalDateTime > creationDate = Optional.empty();
    
    /** Last modified date if available in the underlying store. */
    protected Optional < LocalDateTime > lastModifiedDate = Optional.empty();
    
    /**
     * Parameterized constructor.
     *
     * @param uid
     */
    protected FF4jBaseObject(String uid) {
        this.uid = uid;
    }
    
    @SuppressWarnings("unchecked")
    public T setDescription(String description) {
        this.description = Optional.ofNullable(description);
        return (T) this;
    }
    
    @SuppressWarnings("unchecked")
    public T setOwner(String owner) {
        this.owner = Optional.ofNullable(owner);
        return (T) this;
    }
    
    @SuppressWarnings("unchecked")
    public T setCreationDate(LocalDateTime currentDate) {
        this.creationDate = Optional.of(currentDate);
        return (T) this;
    }
    
    @SuppressWarnings("unchecked")
    public T setLastModified(LocalDateTime currentDate) {
        this.lastModifiedDate = Optional.of(currentDate);
        return (T) this;
    }
    
    public String baseJson() {
        StringBuilder json = 
                new StringBuilder("\"uid\":\"" + uid + "\"");
        description.ifPresent(d -> 
                json.append(",\"description\":\"" + d + "\""));
        owner.ifPresent(o -> 
                json.append(",\"owner\":\"" + o + "\""));
        creationDate.ifPresent(c -> 
                json.append(",\"creationDate\":\"" + c.format(FORMATTER) + "\""));
        lastModifiedDate.ifPresent(c -> 
                json.append(",\"lastModifiedDate\":\"" + c.format(FORMATTER) + "\""));
        
        return json.toString();   
    }

    /**
     * Getter accessor for attribute 'description'.
     *
     * @return
     *       current value of 'description'
     */
    public Optional<String> getDescription() {
        return description;
    }

    /**
     * Getter accessor for attribute 'creationDate'.
     *
     * @return
     *       current value of 'creationDate'
     */
    public Optional<LocalDateTime> getCreationDate() {
        return creationDate;
    }

    /**
     * Getter accessor for attribute 'lastModifiedDate'.
     *
     * @return
     *       current value of 'lastModifiedDate'
     */
    public Optional<LocalDateTime> getLastModifiedDate() {
        return lastModifiedDate;
    }

    /**
     * Getter accessor for attribute 'owner'.
     *
     * @return
     *       current value of 'owner'
     */
    public Optional<String> getOwner() {
        return owner;
    }
    
    /**
     * Getter accessor for attribute 'uid'.
     *
     * @return current value of 'uid'
     */
    public String getUid() {
        return uid;
    }

}