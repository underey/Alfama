package edu.bionic.sverkunov.com.services;

import java.sql.Timestamp;

import edu.bionic.sverkunov.com.DAODB3.classes.PeriodTotalReport;

public interface AnalystServiceI {

	public PeriodTotalReport getTotalReport(Timestamp from, Timestamp until);

	public PeriodTotalReport getDailyReport(Timestamp from, Timestamp until, int category);
}
