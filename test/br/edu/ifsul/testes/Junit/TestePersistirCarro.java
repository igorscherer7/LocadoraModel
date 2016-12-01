package br.edu.ifsul.testes.Junit;

import br.edu.ifsul.modelo.Carro;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestePersistirCarro {

    EntityManagerFactory emf;
    EntityManager em;

    public TestePersistirCarro() {

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
            Carro c1 = new Carro();
            c1.setModelo("Gol bolinha");
            c1.setMarca("Volkswagem");

            Carro c2 = new Carro();
            c2.setModelo("Fusca");
            c2.setMarca("Volkswagem");

            em.getTransaction().begin();
            em.persist(c1);
            em.persist(c2);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            exception = true;

        }
        // verificando se o resultado é igual ao esperado
        Assert.assertEquals(false, exception);
    }

}
