/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lpsmuseum.dao;

import com.lpsmuseum.entity.AnswerDO;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author filip_000
 */
public class AnswerDAO extends BasicDAO{

    @Override
	@SuppressWarnings("unchecked")
	public Object findEntity(Object obj) {
		AnswerDO ado = (AnswerDO) obj;
		EntityManager em = PersistenceUtil.getEntityManager();
		if (ado.getId() != null) {
			Object xdo = em.find(ado.getClass(), ado.getId());
			return xdo;
		} else {
			List<AnswerDO> ados = (List<AnswerDO>) em.createQuery(
					"SELECT a "
					+ "FROM AnswerDO a "
					+ "WHERE a.description = '" + ado.getDescription()+ "'"
			).getResultList();
			ado = ados.isEmpty() ? null : ados.get(0);
		};
		em.close();
		return ado;
	}
        /**
	 * <b>Persists</b> a <b>new</b> answer to the datadase, <u>if only
	 * if</u> the object is not persisted yet.
	 *
	 * @param answer the answer to be persisted.
	 */
	public void createAnswer(AnswerDO answer) {
		create(answer);
	}

	/**
	 * <b>Merge</b> the answer's state to the database.
	 *
	 * @param answer the answer to have your state updated on the
	 * database.
	 */
	public void editAnswer(AnswerDO answer) {
		merge(answer);
	}

	/**
	 * <b>Delete</b> the <u>persisted</u> answer from the database.
	 *
	 * @param obj the answer to be deleted.
	 */
	public void deleteAnswer(AnswerDO answer) {
		delete(answer);
	}

	/**
	 * Lists <b>all</b> the persisted answers.
	 *
	 * @return the <code>List</code> with <b>all</b> persisted answers.
	 */
	@SuppressWarnings("unchecked")
	public List<AnswerDO> listAnswers() {
		return list("AnswerDO");
	}

    
    
}
