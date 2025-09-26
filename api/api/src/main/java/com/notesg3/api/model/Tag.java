
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
@Table(name="Tag")
public class Tag {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id_tag;

    @Column(name="nome_tag", nullable = false,columnDefinition ="Text" )
    private String nome_tag;

    //um Usuario varias tags
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="id_usuario")
    private Usuario usuario;


}