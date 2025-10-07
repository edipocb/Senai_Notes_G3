package com.notesg3.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class NotaTagId implements Serializable {

    private static final long serialVersionUID = 4601822686591325524L;
    @Column(name = "id_anotacao", nullable = false)
    private Integer idAnotacao;

    @Column(name = "id_tag", nullable = false)
    private Integer idTag;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        NotaTagId entity = (NotaTagId) o;
        return Objects.equals(this.idAnotacao, entity.idAnotacao) &&
                Objects.equals(this.idTag, entity.idTag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAnotacao, idTag);
    }
}
