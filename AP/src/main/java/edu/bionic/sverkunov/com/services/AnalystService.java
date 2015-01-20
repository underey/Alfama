package edu.bionic.sverkunov.com.services;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.inject.Inject;
import javax.inject.Named;

import edu.bionic.sverkunov.com.DAODB3.classes.PeriodTotalReport;
import edu.bionic.sverkunov.com.DAODB3.interfaces.BusinessAnalystDAOI;

@Named
public class AnalystService implements AnalystServiceI, Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private BusinessAnalystDAOI analystDAOI;

	public AnalystService() {
	}

	public BusinessAnalystDAOI getAnalystDAOI() {
		return analystDAOI;
	}

	public void setAnalystDAOI(BusinessAnalystDAOI analystDAOI) {
		this.analystDAOI = analystDAOI;
	}

	@Override
	public PeriodTotalReport getTotalReport(Timestamp from, Timestamp until) {
		return analystDAOI.getPeriodTotalReport(from, until);
	}

	@Override
	public PeriodTotalReport getDailyReport(Timestamp from, Timestamp until,
			int category) {
		return analystDAOI.getDailyReport(from, until, category);
	}

}
