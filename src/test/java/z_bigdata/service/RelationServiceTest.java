package z_bigdata.service;

import java.sql.SQLException;

import com.diaodu.dao.impl.RelationDaoImpl;
import com.diaodu.domain.Relation;

public class RelationServiceTest {

	public static void main(String[] args) throws SQLException {
		RelationDaoImpl rd = new RelationDaoImpl();
		Relation r =new Relation();
		r.setEtl_id(155);
		r.setSource_id(7);
		r.setType(1);
		int deleteRelation = rd.deleteRelation(r);
		System.out.println(deleteRelation);
		
	}

}
