package edu.bionic.sverkunov.com.DAODB3.interfaces;

import java.sql.Timestamp;
import java.util.List;

import edu.bionic.sverkunov.com.DAODB3.classes.Menusection;
import edu.bionic.sverkunov.com.DAODB3.classes.PeriodTotalReport;

public interface BusinessAnalystDAOI {

	public PeriodTotalReport getPeriodTotalReport(Timestamp from, Timestamp until);

	public PeriodTotalReport getDailyReport(Timestamp from, Timestamp until,
			int menuSection);

	public List<Menusection> getMenusection();
}
