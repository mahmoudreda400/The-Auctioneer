package edu.mum.cs.auctioneer.models;


import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDate;

@MappedSuperclass
public abstract class AbstarctEntity {

    private LocalDate created;

    private LocalDate Modified;

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public LocalDate getModified() {
        return Modified;
    }

    public void setModified(LocalDate modified) {
        Modified = modified;
    }

    @PrePersist
    public void onPrePersist() {
        setCreated(LocalDate.now());
    }

    @PreUpdate
    public void onPreUpdate() {
        setModified(LocalDate.now());
    }
}
