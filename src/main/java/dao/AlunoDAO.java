package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.Aluno;
import util.JPAUtil;

import java.util.List;

public class AlunoDAO {

    public void salvar(Aluno aluno) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(aluno);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Aluno buscarPorNome(String nome) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Aluno> query = em.createQuery(
                    "SELECT a FROM Aluno a WHERE a.nome = :nome", Aluno.class);
            query.setParameter("nome", nome);

            List<Aluno> resultado = query.getResultList();

            if (resultado.isEmpty()) {
                return null;
            }

            return resultado.get(0);

        } finally {
            em.close();
        }
    }

    public void excluir(Aluno aluno) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Aluno alunoGerenciado = em.merge(aluno);
            em.remove(alunoGerenciado);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void atualizar(Aluno aluno) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(aluno);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<Aluno> listarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Aluno> query =
                    em.createQuery("SELECT a FROM Aluno a", Aluno.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}