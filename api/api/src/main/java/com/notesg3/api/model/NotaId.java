package com.notesg3.api.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
public class NotaId implements Serializable {

    private Integer idNota;
    private Integer idTag;

    public NotaId(){}

    public NotaId(Integer idNota, Integer idTag) {
        this.idNota = idNota;
        this.idTag = idTag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NotaId)) return false;
        NotaId notaId = (NotaId) o;
        return Objects.equals(idNota, notaId.idNota) &&
               Objects.equals(idTag, notaId.idTag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idNota, idTag);
    }

}
