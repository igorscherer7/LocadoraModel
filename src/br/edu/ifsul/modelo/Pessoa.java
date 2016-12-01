package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@Table(name = "pessoa")
public class Pessoa implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_pessoa", sequenceName = "seq_pessoa_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_pessoa", strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @CPF(message = "O CPF deve ser válido")
    @NotBlank(message = "O CPF deve ser informado")
    @Length(max = 14, message = "O CPF não deve ultrapassar {max} caracteres")
    @Column(name = "cpf", length = 14, nullable = false, unique = true)
    private String cpf;

    @NotBlank(message = "O nome deve ser informado")
    @Length(max = 50, message = "O nome deve ter até {max} caracteres")
    @Column(name = "nome", nullable = false, length = 50)
    private String nome;

    @NotBlank(message = "A cidade estado deve ser informado")
    @Length(max = 50, message = "A cidade deve ter até {max} caracteres")
    @Column(name = "cidade", nullable = false, length = 50)
    private String cidade;

    
    @NotBlank(message = "O estado deve ser informado")
    @Length(max = 50, message = "O estado deve ter até {max} caracteres")
    @Column(name = "estado", nullable = false, length = 50)
    private String estado;
    
    @NotBlank(message = "O endereço deve ser informado")
    @Length(max = 50, message = "O endereço deve ter até {max} caracteres")
    @Column(name = "endereco", nullable = false, length = 50)
    private String endereco;

    
   
  @ManyToMany
    @JoinTable(name = "carros",
            joinColumns
            = @JoinColumn(name = "pessoa", referencedColumnName = "nome", nullable = false),
            inverseJoinColumns
            = @JoinColumn(name = "carro", referencedColumnName = "modelo", nullable = false),
            uniqueConstraints = {
                @UniqueConstraint(
                        name = "UK_carros",
                        columnNames = {"pessoa", "carro"})})
    private List<Carro> carros = new ArrayList<>();
  
  
  @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, 
            orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Telefone> telefones = new ArrayList<>();
      

    public Pessoa() {

    }

    public void adicionarTelefone(Telefone obj) {
        obj.setPessoa(this);
        this.telefones.add(obj);
    }

    public void removerTelefone(int index) {
        this.telefones.remove(index);
    }
 
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return cidade;
    }

    public void setEmail(String cidade) {
        this.cidade = cidade;
    }

   
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }
    
     public List<Carro> getCarros() {
        return carros;
    }

    public void setCarros(List<Carro> carros) {
        this.carros = carros;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.id);
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
        final Pessoa other = (Pessoa) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
        
   
}