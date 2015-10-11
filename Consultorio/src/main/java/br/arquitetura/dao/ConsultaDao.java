package br.arquitetura.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.arquitetura.dominio.Consulta;
import br.arquitetura.dominio.DenteArcadaDentaria;
import br.arquitetura.dominio.Pessoa;

@Repository
@Transactional
public class ConsultaDao extends GenericDaoImpl{

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public List<Consulta> findHistoricoByPacienteAndDente(Pessoa paciente, DenteArcadaDentaria denteArcada){
		
		String hql = "select c from Consulta c"+
		             " join c.pessoa p"+
				     " join c.denteArcadaDentaria d"+
		             " join c.consultaGeral"+
		             " where p.id = :idPaciente"; 
			
			   if(denteArcada !=null){
				   if(denteArcada.getId()>0)
					   hql+=" and d.id = :idDenteArcada";
			   }
			   
		Query q = getSession().createQuery(hql);
		q.setParameter("idPaciente", paciente.getId());
		if(denteArcada !=null){
			if(denteArcada.getId()>0)
				q.setParameter("idDenteArcada", denteArcada.getId());
		}
		
		return (List<Consulta>) q.list();
	}
	
	

}
