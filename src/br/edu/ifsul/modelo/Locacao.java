package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "locacao")
public class Locacao implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_locacao", sequenceName = "seq_locacao_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_locacao", strategy = GenerationType.SEQUENCE)
    private Integer id;
    
     @NotBlank(message = "O carro deve ser informado")
    @Length(max = 50, message = "O nome do carro não deve ultrapassar {max} caracteres")
    @Column(name = "carro", length = 50, nullable = false)
    private String carro;

     @NotBlank(message = "A placa deve ser informada")
    @Length(max = 8, message = "O placa não deve ultrapassar {max} caracteres")
    @Column(name = "placa", length = 8, nullable = false)
    private String placa;
     
    @Column(name = "descricao", columnDefinition = "text")
    private String descricao;

    @NotNull(message = "A data de inicio deve ser informada")
    @Temporal(TemporalType.DATE)
    @Column(name = "data_inicio", nullable = false)
    private Calendar data_inicio;

    @NotNull(message = "A data final deve ser informada")
    @Temporal(TemporalType.DATE)
    @Column(name = "data_final", nullable = false)
    private Calendar data_final;
     
         
    @NotNull(message = "A pessoa não pode ser nula")
    @ManyToOne
    @JoinColumn(name = "pessoa", referencedColumnName = "id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_locacao_pessoa"))
    private Pessoa pessoa;


   @OneToMany(mappedBy = "locacao", cascade = CascadeType.ALL, 
            orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Colaborador> colaboradores = new ArrayList<>();

        

  
    public Locacao() {
    }
    
    
    public void adicionarColaborador(Colaborador obj) {
        obj.setLocacao(this);
        this.colaboradores.add(obj);
    }

  
    public void removerColaborador(int idx) {
        this.colaboradores.remove(idx);
    }
    
    
     public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

     public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

  
    
  
 
    
    public List<Colaborador> getColaboradores() {
        return colaboradores;
    }

    public void setColaboradores(List<Colaborador> colaboradores) {
        this.colaboradores = colaboradores;
    }

   

    public String getCarro() {
        return carro;
    }

    public void setCarro(String carro) {
        this.carro = carro;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Calendar getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(Calendar data_inicio) {
        this.data_inicio = data_inicio;
    }

    public Calendar getData_final() {
        return data_final;
    }

    public void setData_final(Calendar data_final) {
        this.data_final = data_final;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.id);
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
        final Locacao other = (Locacao) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
    
     
}
