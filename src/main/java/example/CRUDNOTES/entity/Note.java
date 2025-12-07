package example.CRUDNOTES.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "notes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Note {


    //Dont expose the id to setter
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    //pode ser nulo 
    @Column(columnDefinition = "TEXT", nullable = true, length = 300)
    private String content;

    @Column(nullable =false, length = 50)
    private String title;
    
    @JsonIgnore
    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    //automatic create/update the date
    @CreationTimestamp
    @Column( name = "created_at", updatable = false)
    private LocalDateTime createdDate;

    @UpdateTimestamp
    @Column( name = "updated_at")
    private LocalDateTime updatedAt;
    

}
