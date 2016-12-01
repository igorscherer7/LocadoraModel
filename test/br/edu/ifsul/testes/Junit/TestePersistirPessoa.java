package br.edu.ifsul.testes.Junit;

import br.edu.ifsul.modelo.Pessoa;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestePersistirPessoa {

    EntityManagerFactory emf;
    EntityManager em;

    public TestePersistirPessoa() {

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
            Pessoa obj = new Pessoa();
            obj.setNome("Igor Scherer");
            obj.setEndereco("Nove de Julho, 319 São Cristóvão");
            obj.setCpf("43052374311");
            obj.setCidade("PF");
            obj.setEstado("RS");
            
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
