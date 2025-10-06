
package com.notesg3.api.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Lombok
@Getter
@Setter

//Obrigratorio para JPA Funcionar
@NoArgsConstructor
@AllArgsConstructor

//JPA
//INFORMA QUE ESSA CLASSE E UMATABLEA
@Entity

//Table-Permite que voce configure a tabela
@Table(name="tag")
public class Tag {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_tag", nullable = false)
    private Integer idTag;

    @Column(name="nome_tag", nullable = false,columnDefinition ="Text" )
    private String nomeTag;

    //um Usuario varias tags
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_usuario")
    private Usuario usuario;

}