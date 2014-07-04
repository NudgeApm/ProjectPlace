package com.nla.dao;


import java.util.ArrayList;
import java.util.List;

import com.nla.domain.Account;
import com.nla.domain.KbUsr;
import com.nla.domain.Metric;
import com.nla.domain.ObjectCAST;
import com.nla.domain.Schema;
import com.nla.domain.SchemaCb;
import com.nla.domain.SchemaKb;
import com.nla.domain.SchemaMNGT;
import com.nla.domain.Violation;

public interface BenchmarkDAO {

	public void loadApplication(KbUsr kbUsr);
	public void insertdata(KbUsr kbUsr);
	public void selectStar();
	public void selectStar100(int i);
	public void selectOneColumn();
	public void selectCartesianProduct();

	public ArrayList<Account> getListAccountDAO(int i);

}
