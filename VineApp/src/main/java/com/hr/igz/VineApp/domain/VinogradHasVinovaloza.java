package com.hr.igz.VineApp.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;

@Entity(name="vinograd_has_vinovaloza")
@Table(name="vinograd_has_vinovaloza")
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@ToString(onlyExplicitlyIncluded = true)
public class VinogradHasVinovaloza {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;

    @ManyToOne(fetch=FetchType.LAZY)
    private Vinograd vinograd;

    @ManyToOne(fetch=FetchType.LAZY)
    private Vinovaloza vinovaloza;

    @ManyToOne(fetch=FetchType.LAZY)
    private User user;

    @CreatedDate
    private Instant date;

    @LastModifiedDate
    @Column(name = "updated_date")
    private Instant updatedDate;
}
