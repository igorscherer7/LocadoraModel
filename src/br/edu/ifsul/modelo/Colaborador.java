package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "colaborador")
public class Colaborador implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_colaborador", sequenceName = "seq_colaborador_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_colaborador", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @NotNull(message = "O cargo não pode ser nulo")
    @Column(name = "cargo", nullable = false)
    private String cargo;

    @NotNull(message = "A pessoa não pode ser nula")
    @ManyToOne
    @JoinColumn(name = "pessoa", referencedColumnName = "id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_colaborador_pessoa"))
    private Pessoa pessoa;

    @NotNull(message = "A locação não pode ser nuao")
    @ManyToOne
    @JoinColumn(name = "locacao", referencedColumnName = "id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_colaborador_locacao"))
    private Locacao locacao;

    public Colaborador() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFuncao() {
        return cargo;
    }

    public void setFuncao(String cargo) {
        this.cargo = cargo;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

  
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Colaborador other = (Colaborador) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Locacao getLocacao() {
        return locacao;
    }

    public void setLocacao(Locacao locacao) {
        this.locacao = locacao;
    }

  
}
