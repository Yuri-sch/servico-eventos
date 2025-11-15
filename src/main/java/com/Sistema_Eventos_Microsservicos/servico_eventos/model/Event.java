package com.Sistema_Eventos_Microsservicos.servico_eventos.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.time.OffsetDateTime;

@Entity
@Table(name = "events") 
//"intercepta" qualquer chamada de 'delete' e roda este SQL
@SQLDelete(sql = "UPDATE events SET deleted_at = NOW() WHERE id = ?") 
public class Event {

    @Id
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    private String id; // 

    @Column(name = "event_name", nullable = false, length = 45)
    private String eventName;

    @Column(name = "event_date", nullable = false) 
    private OffsetDateTime eventDate;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "duration", length = 45)
    private String duration;

    @Column(name = "category", length = 45)
    private String category;

    @Column(name = "event_local", length = 100) 
    private String eventLocal;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @Column(name = "deleted_at")
    private Instant deletedAt;

    // Construtor
    public Event() {}

    // Getters e Setters
    public String getId() { 
        return id; 
    }

    public void setId(String id) { 
        this.id = id; 
    }
    
    public String getEventName() { 
        return eventName; 
    }

    public void setEventName(String eventName) { 
        this.eventName = eventName; 
    }

    public OffsetDateTime getEventDate() { 
        return eventDate; 
    }

    public void setEventDate(OffsetDateTime eventDate) { 
        this.eventDate = eventDate; 
    }

    public String getDescription() { 
        return description; 
    }

    public void setDescription(String description) { 
        this.description = description;
    }

    public String getDuration() { 
        return duration; 
    }

    public void setDuration(String duration) { 
        this.duration = duration; 
    }

    public String getCategory() {
        return category; 
    }

    public void setCategory(String category) { 
        this.category = category; 
    }

    public String getEventLocal() { 
        return eventLocal; 
    }

    public void setEventLocal(String eventLocal) { 
        this.eventLocal = eventLocal; 
    }
    public Instant getCreatedAt() { 
        return createdAt; 
    }

    public void setCreatedAt(Instant createdAt) { 
        this.createdAt = createdAt; 
    }

    public Instant getUpdatedAt() { 
        return updatedAt; 
    }

    public void setUpdatedAt(Instant updatedAt) { 
        this.updatedAt = updatedAt; 
    }

    public Instant getDeletedAt() { 
        return deletedAt; 
    }

    public void setDeletedAt(Instant deletedAt) { 
        this.deletedAt = deletedAt; 
    }
}