/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lpsmuseum.service;

import com.lpsmuseum.dao.AnswerDAO;
import com.lpsmuseum.dto.scenario.Answer;
import com.lpsmuseum.entity.AnswerDO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author filip_000
 */
public class AnswerService {
    
	/**
	 * This fields provides communication with the database.
	 */
	private final AnswerDAO dao = new AnswerDAO();

	/**
	 * Class constructor.
	 */
	public AnswerService() {
	}

	/**
	 * Creates a new register in the database for an <code>Answer</code>.
	 *
	 * <p>
	 * Example:
	 * <pre>
	 * <code>
	 * Answer answer = new Answer();
	 * // Getters call on answer
	 * AnswerService service = new AnswerService();
	 * service.createAnswer(answer);
	 * </code>
	 * </pre>
	 * </p>
	 *
	 * @param answer the <code>Answer</code> instance to be registered
	 * in the database.
	 */
	public void createAnswer(Answer answer) throws Exception {
		AnswerDO ado = getEntity(answer);

		dao.createAnswer(ado);

		answer.setId(ado.getId());
	}

	/**
	 * Searchs for the answer with given <code>id</code>.
	 * 
	 * <p>
	 * Example:
	 * <pre>
	 * <code>
	 * Long id = 1L;
	 * AnswerService service = new AnswerService();
	 * Answer answer = service.findById(id);
	 * </code>
	 * </pre>
	 * </p>
	 * 
	 * @param id the id of the answer.
	 * @return the <code>Answer</code> instance representing a register in 
	 * the database with given <code>id</code>. If the register can't be found, 
	 * <code>null</code> is returned.
	 */
	public Answer findById(Long id) throws Exception {
		Answer an = new Answer();
		
		an.setId(id);
		
		return findAnswer(an);
	}

	
	/**
	 * Searchs for the register corresponding with given <code>answer</code>.
	 * 
	 * <p>
	 * Example:
	 * <pre>
	 * <code>
	 * Answer answer = new Answer().
	 * answer.setId(1L);
	 * AnswerService service = new AnswerService();
	 * Answer answer = service.findAnswer(answer);
	 * </code>
	 * </pre>
	 * </p>
	 * 
	 * @param answer the answer to be searched in the database.
	 * @return the <code>Answer</code> instance representing a register in 
	 * the database. If the register can't be found, <code>null</code> is 
	 * returned.
	 */
	private Answer findAnswer(Answer answer) throws Exception {
		AnswerDO ado = (AnswerDO) dao.findEntity(answer.getEntity());
		
		return ado == null ? null : ado.getDto();
	}

	

	

	/**
	 * Lists all the answers.
	 * 
	 * <p>
	 * Example:
	 * <pre>
	 * <code>
	 * AnswerService service = new AnswerService();
	 * List&lt;Answer&gt; answers = service.listAnswers();
	 * </code>
	 * </pre>
	 * </p>
	 * 
	 * @return the <code>List</code> of all <code>Answer</code> stored in the 
	 * database.
	 */
	public List<Answer> listAnswers() {
		List<AnswerDO> ados = dao.listAnswers();
		
		return listAnswer(ados);
	}

	/**
	 * Converts a <code>List</code> of <code>AnswerDO</code> in a <code>List
	 * </code> of <code>Answer</code>.
	 * 
	 * <p>
	 * Example:
	 * <pre>
	 * <code>
	 * AnswerService service = new AnswerService();
	 * List&lt;AnswerDO&gt; ados = new AnswerDAO().listAnswers();
	 * List&lt;Answer&gt; answers = service.listAnswer(ados);
	 * </code>
	 * </pre>
	 * </p>
	 * 
	 * @param ados the <code>List</code> of <code>AnswerDO</code> to be 
	 * converted.
	 * @return the <code>List</code> of <code>Answer</code>.
	 */
	private List<Answer> listAnswer(List<AnswerDO> ados) {
		ArrayList<Answer> ans = new ArrayList<Answer>();
		
		for (AnswerDO ado : ados) {
			ans.add(ado.getDto());
		}
		
		return ans;
	}

	/**
	 * Updates an <code>Answer</code> stored in the database.
	 * 
	 * <p>
	 * Example:
	 * <pre>
	 * <code>
	 * AnswerService service = new AnswerService();
	 * Answer answer = service.findByTitle("Older answer");
	 * answer.setTitle("New answer");
	 * service.editAnswer(answer);
	 * </code>
	 * </pre>
	 * </p>
	 * 
	 * @param answer the <code>Answer</code> to be updated.
	 */
	public void editAnswer(Answer answer) throws Exception {
		dao.editAnswer(answer.getEntity());
	}

	/**
	 * Deletes an <code>Answer</code> stored in the database.
	 * 
	 * <p>
	 * Example:
	 * <pre>
	 * <code>
	 * AnswerService service = new AnswerService();
	 * Answer answer = service.findByTitle("Older answer");
	 * service.deleteAnswer(answer.getId());
	 * </code>
	 * </pre>
	 * </p>
	 * 
	 * @param id the <code>id</code> of <code>Answer</code> to be deleted.
	 */
	public void deleteAnswer(Long id) {
		AnswerDO ado = new AnswerDO();
		
		ado.setId(id);
		
		dao.deleteAnswer(ado);
	}
        
        public AnswerDO getEntity(Answer answer){
		AnswerDO tdo = new AnswerDO();
		tdo.setId(answer.getId());
		tdo.setCorrect(answer.isCorrect());
		tdo.setDescription(answer.getDescription());
		return tdo;
	}
}
