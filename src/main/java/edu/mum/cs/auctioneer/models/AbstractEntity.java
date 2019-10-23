package edu.mum.cs.auctioneer.models;


import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class AbstractEntity {

    private LocalDateTime created;

    private LocalDateTime Modified;

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getModified() {
        return Modified;
    }

    public void setModified(LocalDateTime modified) {
        Modified = modified;
    }

    @PrePersist
    public void onPrePersist() {
        setCreated(LocalDateTime.now());
    }

    @PreUpdate
    public void onPreUpdate() {
        setModified(LocalDateTime.now());
    }
}
