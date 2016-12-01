package br.edu.ifsul.testes.Junit;


import br.edu.ifsul.modelo.Colaborador;
import br.edu.ifsul.modelo.Pessoa;
import br.edu.ifsul.modelo.Locacao;
import java.util.Calendar;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestePersistirLocacao {

    EntityManagerFactory emf;
    EntityManager em;

    public TestePersistirLocacao() {

    }

    @Before
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("LOCADORA-ModelPU");
        em = emf.createEntityManager();
    }

    @After
    public void tearDown() {
        em.close();
        emf.close();
    }

    @Test
    public void testePersistirPais() {
        boolean exception = false;
        try {
            Locacao obj = new Locacao();
            obj.setData_final(Calendar.getInstance());
            obj.setData_inicio(Calendar.getInstance());
            obj.setCarro("Gol bolinha");
            obj.setDescricao("completo");
            obj.setPessoa(em.find(Pessoa.class, 1));
            
           Colaborador c = new Colaborador();
            c.setPessoa(em.find(Pessoa.class, 1));
            c.setCargo("Atividade gerais");
            obj.adicionarColaborador(c);           
      
            
            em.getTransaction().begin();
            em.persist(obj);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            exception = true;

        }
        // verificando se o resultado é igual ao esperado
        Assert.assertEquals(false, exception);
    }

}
